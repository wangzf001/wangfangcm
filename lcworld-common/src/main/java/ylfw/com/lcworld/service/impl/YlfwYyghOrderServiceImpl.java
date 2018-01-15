package com.lcworld.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.YlfwYyghDoctorScheduleDao;
import com.lcworld.dao.YlfwYyghOrderDao;
import com.lcworld.dto.YlfwyyghOrderDTO;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.YlfwYyghCommentEntity;
import com.lcworld.entity.YlfwYyghDoctorScheduleEntity;
import com.lcworld.entity.YlfwYyghOrderEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.YlfwYyghCommentService;
import com.lcworld.service.YlfwYyghOrderService;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("ylfwYyghOrderService")
public class YlfwYyghOrderServiceImpl implements YlfwYyghOrderService {
	@Autowired
	private YlfwYyghOrderDao ylfwYyghOrderDao;
	@Autowired
	private YlfwYyghDoctorScheduleDao ylfwYyghDoctorScheduleDao;
	@Autowired
	private YlfwYyghCommentService ylfwYyghCommentService;
	
	@Override
	public YlfwYyghOrderEntity queryObject(Integer id){
		return ylfwYyghOrderDao.queryObject(id);
	}
	
	@Override
	public List<YlfwYyghOrderEntity> queryList(Map<String, Object> map){
		return ylfwYyghOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwYyghOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwYyghOrderEntity ylfwYyghOrder){
		ylfwYyghOrderDao.save(ylfwYyghOrder);
	}
	
	@Override
	public void update(YlfwYyghOrderEntity ylfwYyghOrder){
		ylfwYyghOrderDao.update(ylfwYyghOrder);
	}
	
	@Override
	public void delete(Integer id){
		ylfwYyghOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwYyghOrderDao.deleteBatch(ids);
	}

    @Override
    public List<YlfwyyghOrderDTO> queryOrderlist(Query q) {
        return ylfwYyghOrderDao.queryOrderlist(q);
    }

    @Override
    public List<YlfwyyghOrderDTO> queryOrderdetail(JSONObject params) {
        return ylfwYyghOrderDao.queryOrderdetail(params);
    }

    @Override
    public YlfwYyghOrderEntity saveorder(YlfwYyghOrderEntity order) {
        order.setChanges(1);
        order.setIsdel(0);
        order.setStatus(1);
        order.setCreatetime(new Date());
        order.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_YLFW));
        order.setGhcode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_YLFW_GH));
        order.setGhpaystatus(0);
        save(order);
        
        YlfwYyghDoctorScheduleEntity schedule = new YlfwYyghDoctorScheduleEntity();
        schedule.setStatus(1);
        schedule.setId(order.getScheduleid());
        ylfwYyghDoctorScheduleDao.update(schedule);
        
        return order;
    }
    
    @Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
        YlfwYyghOrderEntity order;
        if(pay.getOrderid() != null&&pay.getOrderid().intValue()!=0){
            order = queryObject(pay.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            if(1 == pay.getSubstatus()){
                params.put("payghordercode", pay.getOrdercode());
            }else{
                params.put("payordercode", pay.getOrdercode());
            }
                
            List<YlfwYyghOrderEntity> orderlist = queryList(params);
            if(ValidateUtil.isValid(orderlist)){
                order = orderlist.get(0);
            }else{
                throw new ZHHQException(1500, "支付前订单校验失败，可能原因:订单不存在");
            }
        }
        if(order == null){
            throw new ZHHQException(1500, "支付前订单校验失败，可能原因:订单已支付或不存在");
        }
        PayOrderVo payorder = new PayOrderVo();
        payorder.setUid(order.getUid());
        if(1 == pay.getSubstatus() ){
            //挂号
            payorder.setOrderid(order.getId());
            payorder.setOrdercode(order.getGhcode());
            payorder.setOrdertype(APPConstant.TYPE_YLFW_GH);
            payorder.setPaymoney(order.getGhprice());
            payorder.setPaystatus(order.getGhpaystatus());
            if(!checkBeforePay(payorder)){
                throw new ZHHQException(1500, "支付前订单校验失败，可能原因:订单已支付或不存在");
            }
        }else{
            payorder.setOrdercode(order.getOrdercode());
            payorder.setOrderid(order.getId());
            payorder.setOrdertype(APPConstant.TYPE_YLFW);
            payorder.setPaymoney(order.getMoney());
            payorder.setStatus(order.getStatus());
            payorder.setPaystatus(order.getGhpaystatus());
            if(!checkBeforePay(payorder)){
                throw new ZHHQException(1500, "支付前订单校验失败，可能原因:订单已支付或不存在");
            }
        }
        return payorder;
    }

    private boolean checkBeforePay(PayOrderVo payorder) {
        if(1 == payorder.getPaystatus()){
            return false;
        }
        return true;
    }
    
    @Override
    public void savePayed(PayOrderVo orderVo) {
        YlfwYyghOrderEntity order = queryObject(orderVo.getOrderid());
        if(APPConstant.TYPE_YLFW_GH == orderVo.getOrdertype()){
            order.setGhpaystatus(1);
        }else{
            order.setPaystatus(1);
            order.setPaytime(new Date());
            order.setPaytype(orderVo.getPaytype());
        }
        update(order);
       
    }

	@Override
	public R cancelOrder(Integer id, String cancelreason) {
		YlfwYyghOrderEntity order = queryObject(id);
		order.setChanges(1);
        order.setCancelreason(cancelreason);
        order.setStatus(5);
        update(order);
        Integer scheduleid = order.getScheduleid();
        YlfwYyghDoctorScheduleEntity schedule = ylfwYyghDoctorScheduleDao.queryObject(scheduleid);
        if (ValidateUtil.isValid(schedule)) {
        	schedule.setStatus(0);
        	ylfwYyghDoctorScheduleDao.update(schedule);
		}
        return R.ok();
	}

	@Override
	public void deleteOrder(Integer id) {
		YlfwYyghOrderEntity order = new YlfwYyghOrderEntity();
		order.setId(id);
		order.setChanges(0);
		order.setIsdel(1);
        update(order);
	}

	@Override
	public R addComment(CommentVo comment, MultipartFile[] files) {
		YlfwYyghCommentEntity commentEntity = new YlfwYyghCommentEntity();
		commentEntity.setAnonymous(comment.getAnonymous());
		commentEntity.setContent(comment.getContent());
		commentEntity.setOrderid(comment.getId());
		commentEntity.setProductscore(comment.getProductscore());
		commentEntity.setScore(comment.getScore());
		commentEntity.setServicescore(comment.getServicescore());
		YlfwYyghOrderEntity order = queryObject(comment.getId());
        if(order.getStatus().intValue() ==4 ){
            JSONObject obj = WebUtils.uploadFiles(files, null, RedisConst.USER_YLFW_YYGH_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
            if (0 == obj.getIntValue("errorCode")) {
            	commentEntity.setImgs(obj.getString("data"));
            }
            commentEntity.setCreatetime(new Date());
            commentEntity.setUid(order.getUid());
            ylfwYyghCommentService.savecomment(commentEntity,order);
            return R.ok();
        }else if(order.getStatus() == 3){
            return R.error(1103,"该订单已进行过评论,不可重复评论");
        }else{
            return R.error(1102, "该订单还没有完成，不能进行评价");
        }
	}

    @Override
    public R finishOrder(Integer id) {
        // TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
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
