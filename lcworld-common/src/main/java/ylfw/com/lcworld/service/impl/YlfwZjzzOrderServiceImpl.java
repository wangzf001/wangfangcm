package com.lcworld.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.common.utils.DateUtil;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.YlfwZjzzDoctorScheduleDao;
import com.lcworld.dao.YlfwZjzzOrderDao;
import com.lcworld.dao.YlfwZjzzPeriodDao;
import com.lcworld.dto.YlfwyyghOrderDTO;
import com.lcworld.dto.YlfwzjzzOrderDTO;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.YlfwYyghCommentEntity;
import com.lcworld.entity.YlfwYyghDoctorScheduleEntity;
import com.lcworld.entity.YlfwYyghOrderEntity;
import com.lcworld.entity.YlfwZjzzCommentEntity;
import com.lcworld.entity.YlfwZjzzDoctorScheduleEntity;
import com.lcworld.entity.YlfwZjzzOrderEntity;
import com.lcworld.entity.YlfwZjzzPeriodEntity;
import com.lcworld.service.YlfwZjzzCommentService;
import com.lcworld.service.YlfwZjzzOrderService;
import com.lcworld.test.queue.OrderQueueSingleton;
import com.lcworld.test.queue.RemindQueue;
import com.lcworld.test.queue.vo.RemindOrderVo;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.OSSConstantKey;
import com.lcworld.utils.OSSUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("ylfwZjzzOrderService")
public class YlfwZjzzOrderServiceImpl implements YlfwZjzzOrderService {
	@Autowired
	private YlfwZjzzOrderDao ylfwZjzzOrderDao;
	@Autowired
	private YlfwZjzzDoctorScheduleDao ylfwZjzzDoctorScheduleDao;
	@Autowired
	private YlfwZjzzPeriodDao ylfwZjzzPeriodDao;
	   @Autowired
	    private YlfwZjzzCommentService ylfwZjzzCommentService;
	
	@Override
	public YlfwZjzzOrderEntity queryObject(Integer id){
		return ylfwZjzzOrderDao.queryObject(id);
	}
	
	@Override
	public List<YlfwZjzzOrderEntity> queryList(Map<String, Object> map){
		return ylfwZjzzOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwZjzzOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwZjzzOrderEntity ylfwZjzzOrder){
		ylfwZjzzOrderDao.save(ylfwZjzzOrder);
	}
	
	@Override
	public void update(YlfwZjzzOrderEntity ylfwZjzzOrder){
		ylfwZjzzOrderDao.update(ylfwZjzzOrder);
	}
	
	@Override
	public void delete(Integer id){
		ylfwZjzzOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwZjzzOrderDao.deleteBatch(ids);
	}

    @Override
    public YlfwZjzzOrderEntity saveorder(YlfwZjzzOrderEntity order) throws ParseException {
        order.setChanges(1);
        order.setIsdel(0);
        order.setStatus(1);
        order.setCreatetime(new Date());
        order.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_YLFW_ZJZZ));
        
        YlfwZjzzDoctorScheduleEntity schedule = ylfwZjzzDoctorScheduleDao.queryObject(order.getScheduleid());
        schedule.setStatus(1);
        ylfwZjzzDoctorScheduleDao.update(schedule);
        
        YlfwZjzzPeriodEntity period = ylfwZjzzPeriodDao.queryObject(schedule.getConsultperiodid());
        order.setInvitetime(WebUtils.createDate(schedule.getScheduledate(),period.getStarttime()));
        
        save(order);
        //订单加入到队列中
        RemindQueue queue = OrderQueueSingleton.getRemindQueue();
        RemindOrderVo orderVo = changeToRemindOrderVo(order);
        queue.offer(orderVo);
        return order;
    }

    @Override
    public List<YlfwzjzzOrderDTO> queryOrderlist(Query q) {
        return ylfwZjzzOrderDao.queryOrderlist(q);
    }

    @Override
    public YlfwzjzzOrderDTO queryOrderdetail(JSONObject params) {
        return ylfwZjzzOrderDao.queryOrderdetail(params);
    }

    @Override
    public List<Map<String,Object>> queryorderList(Query query) {
        return ylfwZjzzOrderDao.queryorderList(query);
    }

    @Override
    public int queryorderTotal(Query query) {
        return ylfwZjzzOrderDao.queryorderTotal(query);
    }

	@Override
	public List<RemindOrderVo> queryRemindOrderVoList(HashMap<String, Object> params) {
		List<YlfwZjzzOrderEntity> queryList = queryList(params);
		List<RemindOrderVo> list = new ArrayList<>();
		for (YlfwZjzzOrderEntity order : queryList) {
			list.add(changeToRemindOrderVo(order));
		}
		return list;
	}

	@Override
	public RemindOrderVo changeToRemindOrderVo(YlfwZjzzOrderEntity order) {
			RemindOrderVo orderVo = new RemindOrderVo();
			orderVo.setMobile(order.getMobile());
			orderVo.setOrdercode(order.getOrdercode());
			orderVo.setOrdertype(APPConstant.TYPE_YLFW_ZJZZ);
			orderVo.setTimeAlarm(com.lcworld.utils.DateUtil.add_minute(order.getInvitetime(),-20));
			orderVo.setRemindmsg("您的专家坐诊预约还有20分钟开始");
			return orderVo;
	}

    @Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void savePayed(PayOrderVo orderVo) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public R cancelOrder(Integer id, String cancelreason) {
        YlfwZjzzOrderEntity order = queryObject(id);
        order.setChanges(1);
        order.setCancelreason(cancelreason);
        order.setStatus(5);
        order.setCreatetime(new Date());
        update(order);
        Integer scheduleid = order.getScheduleid();
        YlfwZjzzDoctorScheduleEntity schedule = ylfwZjzzDoctorScheduleDao.queryObject(scheduleid);
        if (ValidateUtil.isValid(schedule)) {
            schedule.setStatus(0);
            ylfwZjzzDoctorScheduleDao.update(schedule);
        }
        return R.ok();
    }

    @Override
    public void deleteOrder(Integer id) {
        YlfwZjzzOrderEntity order = new YlfwZjzzOrderEntity();
        order.setId(id);
        order.setChanges(0);
        order.setIsdel(1);
        update(order);
        
    }

    @Override
    public R addComment(CommentVo comment, MultipartFile[] files) {
        YlfwZjzzCommentEntity commentEntity = new YlfwZjzzCommentEntity();
        commentEntity.setAnonymous(comment.getAnonymous());
        commentEntity.setContent(comment.getContent());
        commentEntity.setOrderid(comment.getId());
        commentEntity.setProductscore(comment.getProductscore());
        commentEntity.setScore(comment.getScore());
        commentEntity.setServicescore(comment.getServicescore());
        YlfwZjzzOrderEntity order = queryObject(comment.getId());
        if(order.getStatus().intValue() == 4 ){
        	
        	/*StringBuilder sb = new StringBuilder();
        	for(MultipartFile file:files){
                String fileName = file.getOriginalFilename();// 文件原名称
                String filePath = DateUtils.format(new Date())+"/"+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
                try {
					OSSUtils.uploadFile(filePath, file.getInputStream());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                String imgUrl = OSSConstantKey.OSS_BASE_URL+filePath;
                sb.append(",").append(imgUrl);
        	}
        	
        	if(!StringUtils.isBlank(sb.toString())){
        		commentEntity.setImgs(sb.toString().substring(1));
        	}*/
        	
            JSONObject obj = WebUtils.uploadFiles(files, null, RedisConst.USER_YLFW_YYGH_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
            if (0 == obj.getIntValue("errorCode")) {
                commentEntity.setImgs(obj.getString("data"));
            }
        	
        	
            commentEntity.setCreatetime(new Date());
            commentEntity.setUid(order.getUid());
            ylfwZjzzCommentService.savecomment(commentEntity,order);
            return R.ok();
        }else if(order.getStatus() == 3){
            return R.error(1103,"该订单已进行过评论,不可重复评论");
        }else{
            return R.error(1102, "该订单还没有完成，不能进行评价");
        }
    }

    @Override
    public R finishOrder(Integer id) {
        YlfwZjzzOrderEntity order = queryObject(id);
        if(order.getStatus() != 3 && order.getStatus()  != 4){
            order.setStatus(3);
            order.setChanges(1);
            update(order);
        }
        return null;
    }

    @Override
    public Date getDeliverytime(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public R updateOrderStatus(Integer id, Integer lastStatus, Integer newStatus) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public RefundinfoEntity getRefundInfo(RefundinfoEntity refundVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRefund(RefundinfoEntity refundVo) {
		
	}


	@Override
	public void remindCancel(YlfwZjzzOrderEntity t) {
		
	}

	@Override
	public List<?> OrderList(Query q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T OrderDetail(JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrders(JSONObject in) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishOrders(JSONObject p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void instantService(String ordercode,Integer uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public R cancelOrders(JSONObject in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void distribution(JSONObject in) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refuseOrder(JSONObject p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOverOrder(JSONObject in) {
		// TODO Auto-generated method stub
		
	}
	
}
