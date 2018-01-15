package com.lcworld.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.LffwBarberScheduleDao;
import com.lcworld.dao.LffwOrderDao;
import com.lcworld.dao.LffwOrderdetailDao;
import com.lcworld.dto.LffwOrderDTO;
import com.lcworld.entity.LffwBarberScheduleEntity;
import com.lcworld.entity.LffwCommentEntity;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.entity.LffwOrderdetailEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.TsjyfwOrderEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.LffwCommentService;
import com.lcworld.service.LffwOrderService;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.FastJSONUtils;
import com.lcworld.utils.OSSConstantKey;
import com.lcworld.utils.OSSUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.LffwOrderVO;
import com.lcworld.vo.PayOrderVo;



@Service("lffwOrderService")
public class LffwOrderServiceImpl implements LffwOrderService {
	@Autowired
	private LffwOrderDao lffwOrderDao;
	@Autowired
	private LffwOrderdetailDao lffwOrderdetailDao;
	@Autowired
	private LffwBarberScheduleDao lffwBarberScheduleDao;
	@Autowired
	private LffwCommentService lffwCommentService;
	
	@Override
	public LffwOrderEntity queryObject(Integer id){
		return lffwOrderDao.queryObject(id);
	}
	
	@Override
	public List<LffwOrderEntity> queryList(Map<String, Object> map){
		return lffwOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwOrderEntity lffwOrder){
		lffwOrderDao.save(lffwOrder);
	}
	
	@Override
	public void update(LffwOrderEntity lffwOrder){
		lffwOrderDao.update(lffwOrder);
	}
	
	@Override
	public void delete(Integer id){
		lffwOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwOrderDao.deleteBatch(ids);
	}

    @Override
    public LffwOrderEntity  saveorder(LffwOrderVO ordervo) throws Exception {
        LffwOrderEntity order = ordervo.getOrder();
        order.setChanges(1);
        order.setStatus(1);
        order.setCreatetime(new Date());
        order.setIsdel(0);
        order.setPaystatus(0);
        order.setCheckstatus(0);
        order.setScheduledate(WebUtils.createDate(new Date(),ordervo.getStarttime()));
        order.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_LFFW_YYLF));
        lffwOrderDao.save(order);
        
        List<LffwOrderdetailEntity> detaillist = ordervo.getDetaillist();
        if(ValidateUtil.isValid(detaillist)){
            for(LffwOrderdetailEntity detail : detaillist){
                detail.setOrderid(order.getId());
            }
        }
        lffwOrderdetailDao.saveBatch(detaillist);
        //更新预约时间状态
        LffwBarberScheduleEntity schedule = new LffwBarberScheduleEntity();
        schedule.setStatus(1);
        schedule.setId(order.getScheduleid());
        lffwBarberScheduleDao.update(schedule);
        return order;
    }

    @Override
    public List<LffwOrderDTO> queryOrderlist(Query q) {
        return lffwOrderDao.queryOrderlist(q);
    }

    @Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
        LffwOrderEntity order=null;
        if(pay.getOrderid() != null&&pay.getOrderid().intValue()!=0){
            order = queryObject(pay.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", pay.getOrdercode());
            
            List<LffwOrderEntity> orderlist;
                orderlist = queryList(params);
                if(ValidateUtil.isValid(orderlist)){
                    order = orderlist.get(0);
                }else{
                    throw new ZHHQException(1500, "支付前订单校验失败，可能原因:订单不存在");
                }
        }
        if(!checkBeforePay(order)){
            throw new ZHHQException(1500, "支付前订单校验失败，可能原因:订单已支付或不存在");
        }
        PayOrderVo payorder = new PayOrderVo();
        payorder.setUid(order.getUid());
        payorder.setOrdercode(order.getOrdercode());
        payorder.setOrderid(order.getId());
        payorder.setOrdertype(APPConstant.TYPE_LFFW_YYLF);
        payorder.setPaymoney(order.getPrice());
        payorder.setStatus(order.getStatus());
        int lockcount =  ValidateUtil.isValid(order.getLockvouchercount())?order.getLockvouchercount():0;
        payorder.setPrepayvouchercount(lockcount);
        order.setMixpay(order.getMixpay());
        return payorder;
    }

    private boolean checkBeforePay(LffwOrderEntity order) {
       if(order == null || 1 == order.getPaystatus()){
           return false;
       }
       return true;
    }

    @Override
    public void savePayed(PayOrderVo orderVo) {
        LffwOrderEntity order = queryObject(orderVo.getOrderid());
        order.setPaystatus(1);
        order.setPaytime(new Date());
        order.setPaytype(orderVo.getPaytype());
        update(order);
    }

    @Override
    public List<Map<String,Object>> queryorderList(Query query) {
        return lffwOrderDao.queryorderList1(query);
    }

    @Override
    public int queryorderTotal(Query query) {
        return lffwOrderDao.queryorderTotal(query);
    }

	@Override
	public R cancelOrder(Integer id,String cancelreason) {
		LffwOrderEntity order = queryObject(id);
		order.setChanges(1);
        order.setCancelreason(cancelreason);
        order.setStatus(5);
        update(order);
        Integer scheduleid = order.getScheduleid();
        LffwBarberScheduleEntity schedule = lffwBarberScheduleDao.queryObject(scheduleid);
        if (ValidateUtil.isValid(schedule)) {
        	schedule.setStatus(0);
        	lffwBarberScheduleDao.update(schedule);
		}
        return R.ok();
	}

	@Override
	public void deleteOrder(Integer id) {
		LffwOrderEntity order = new LffwOrderEntity();
		order.setId(id);
		order.setChanges(0);
		order.setIsdel(1);
        update(order);
	}

	@Override
	public R addComment(CommentVo comment, MultipartFile[] files) {
		LffwCommentEntity lffwCommentEntity = new LffwCommentEntity();
		lffwCommentEntity.setAnonymous(comment.getAnonymous());
		lffwCommentEntity.setContent(comment.getContent());
		lffwCommentEntity.setOrderid(comment.getId());
		lffwCommentEntity.setProductscore(comment.getProductscore());
		lffwCommentEntity.setScore(comment.getScore());
		lffwCommentEntity.setServicescore(comment.getServicescore());
        LffwOrderEntity order = queryObject(comment.getId());
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
        		lffwCommentEntity.setImgs(sb.toString().substring(1));
        	}*/
            JSONObject obj = WebUtils.uploadFiles(files, null, RedisConst.USER_YLFW_YYGH_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
            if (0 == obj.getIntValue("errorCode")) {
            	lffwCommentEntity.setImgs(obj.getString("data"));
            }
            
            lffwCommentEntity.setCreatetime(new Date());
            lffwCommentEntity.setBarberid(order.getBarberid());
            lffwCommentEntity.setUid(order.getUid());
            lffwCommentService.savecomment(lffwCommentEntity,order);
            return R.ok();
        }else if(order.getStatus() == 3){
            return R.error(1103,"该订单已进行过评论,不可重复评论");
        }else{
            return R.error(1102, "该订单还没有完成，不能进行评价");
        }
	}

    @Override
    public R finishOrder(Integer id) {
        LffwOrderEntity order = queryObject(id);
        if(order.getStatus() != 3 && order.getStatus()  != 4){
            order.setStatus(4);
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
		return null;
	}

	@Override
	public void saveRefund(RefundinfoEntity refundVo) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void savemixpay(PayOrderVo orderVo) {
        LffwOrderEntity order = queryObject(orderVo.getOrderid());
        order.setMixpay(1);
        order.setLockvouchercount(orderVo.getVouchercount());
        update(order);
        
    }

    @Override
    public void autofinishOrder() {
        lffwOrderDao.autofinishOrder();
       
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
