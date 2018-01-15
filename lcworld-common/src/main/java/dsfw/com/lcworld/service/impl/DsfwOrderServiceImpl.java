package com.lcworld.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import com.lcworld.dao.*;
import com.lcworld.entity.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dto.DsfwOrderDTO;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.DsfwCommentService;
import com.lcworld.service.DsfwOrderService;
import com.lcworld.utils.CommonUtils;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.RRException;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("dsfwOrderService")
public class DsfwOrderServiceImpl implements DsfwOrderService {
	private Logger log = LoggerFactory.getLogger(DsfwOrderServiceImpl.class);
	@Autowired
	private DsfwOrderDao dsfwOrderDao;
	@Autowired
	private DsfwGoodsDao dsfwGoodsDao;
	@Autowired
	private DsfwOrderdetailDao dsfwOrderdetailDao;
	@Autowired
	private DsfwCommentService dsfwCommentService;
	
	@Autowired
	private ServiceWallelogDao serviceWallelogDao;
	@Autowired
	private DsfwAppointmentDao dsfwAppointmentDao;
	
	@Override
	public DsfwOrderEntity queryObject(Integer id){
		return dsfwOrderDao.queryObject(id);
	}
	
	@Override
	public List<DsfwOrderEntity> queryList(Map<String, Object> map){
		return dsfwOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dsfwOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(DsfwOrderEntity dsfwOrder){
		dsfwOrderDao.save(dsfwOrder);
	}
	
	@Override
	public void update(DsfwOrderEntity dsfwOrder){
		dsfwOrderDao.update(dsfwOrder);
	}
	
	@Override
	public void delete(Integer id){
		dsfwOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dsfwOrderDao.deleteBatch(ids);
	}

	@Override
	public R generateOrder(JSONObject params, List<DsfwAppointmentEntity> appointmentList) {

		//先判断预约是否冲突
		Map<String,Object> appoint = new HashMap<>();
		if(params.get("check_flag") == null){
			for (DsfwAppointmentEntity appointment : appointmentList) {
				String date = DateUtils.format(appointment.getDate(), "yyyy-MM-dd");
				String intervalids = appointment.getIntervalids();
				String[] idArr = {};
				if (ValidateUtil.isValid(intervalids)) {
					idArr = intervalids.split(",");
				}
				appoint.put("date", date);
				appoint.put("ids", idArr);
				appoint.put("status", 1);
				List<DsfwAppointmentEntity> list = dsfwAppointmentDao.queryList(appoint);
//				if (list.size()!=0) {
//					return R.error(1,"预约时间冲突");
//				}
			}
		}
		
		//需要用到的参数
		Integer uid = params.getInteger("uid");
		String realname = params.getString("realname");
		String mobile = params.getString("mobile");
		Integer addressId = params.getInteger("addressId");
		Integer sendfoodtime = params.getInteger("sendTimeID");
		JSONArray goodsList = params.getJSONArray("goodsList");
		String remark = params.getString("remark");
		String sendtime = params.getString("sendtime");
		//goodsList=[{goodsid:1,count:1}]
		//设置总单号
		Integer paytype = params.getInteger("paytype");
		String parentOrdercode = OrderCodeGenerator.createOrderCode(APPConstant.TYPE_DSFW);
		BigDecimal totalMoney = new BigDecimal(0);
		for (int i = 0; i < goodsList.size(); i++) {
			DsfwOrderEntity orderEntity = new DsfwOrderEntity();
			JSONObject food = goodsList.getJSONObject(i);
			Integer goodsid = food.getInteger("goodsid");
			Integer count = food.getInteger("count");
			DsfwGoodsEntity goodsEntity = dsfwGoodsDao.queryObject(goodsid);
			if (ValidateUtil.isValid(goodsEntity)) {
				BigDecimal price = goodsEntity.getPrice();
				BigDecimal subtotal = price.multiply(new BigDecimal(count));
				totalMoney = totalMoney.add(subtotal);
				DsfwOrderdetailEntity orderdetailEntity = new DsfwOrderdetailEntity();
				orderdetailEntity.setGoodsid(goodsid);
				orderdetailEntity.setCount(count);
				orderdetailEntity.setCreatetime(new Date());
				orderdetailEntity.setPrice(price);
				orderdetailEntity.setTotalprice(subtotal);
				//添加到订单
				orderEntity.setCreatetime(new Date());
				orderEntity.setSendtimeid(sendfoodtime);
				orderEntity.setMobile(mobile);
				orderEntity.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_DSFW));
				orderEntity.setRealname(realname);
				orderEntity.setStatus(APPConstant.TYPE_ORDER_STATUS_ORDERED);
				orderEntity.setTotalprice(subtotal);
				orderEntity.setUid(uid);
				orderEntity.setAddressid(addressId);
				orderEntity.setRemark(remark);
				orderEntity.setSendTime(sendtime);
				orderEntity.setPayStatus(APPConstant.TYPE_ORDER_PAY_STATUS_UNPAY);
				orderEntity.setParentOrdercode(parentOrdercode);
				orderEntity.setRealdel(0);
				save(orderEntity);
				for (int j = 0; j < appointmentList.size(); j++) {
					appointmentList.get(j).setOrderid(orderEntity.getId());;
					appointmentList.get(j).setStatus(1);
				}
				//保存预约信息
				if (appointmentList.size()>0){
					dsfwAppointmentDao.saveBatch(appointmentList);
				}
				orderdetailEntity.setOrderid(orderEntity.getId());
				dsfwOrderdetailDao.save(orderdetailEntity);
			}
		}
		return R.ok().put("ordertype", APPConstant.TYPE_DSFW).put("ordercode", parentOrdercode).put("createtime", new Date()).put("paytype", paytype).put("totalMoney", totalMoney);
	}

	/*public R generateOrder(JSONObject params) {
		//需要用到的参数
		Integer uid = params.getInteger("uid");
		String realname = params.getString("realname");
        String mobile = params.getString("mobile");
        Integer addressId = params.getInteger("addressId");
        Integer sendfoodtime = params.getInteger("sendTimeID");
        JSONArray goodsList = params.getJSONArray("goodsList");
        String remark = params.getString("remark");
        String sendtime = params.getString("sendtime");
        //goodsList=[{goodsid:1,count:1}]
		//设置总单号
        Integer paytype = params.getInteger("paytype");
		String parentOrdercode = OrderCodeGenerator.createOrderCode(APPConstant.TYPE_DSFW);
		BigDecimal totalMoney = new BigDecimal(0);
		for (int i = 0; i < goodsList.size(); i++) {
			DsfwOrderEntity orderEntity = new DsfwOrderEntity();
			JSONObject food = goodsList.getJSONObject(i);
			Integer goodsid = food.getInteger("goodsid");
			Integer count = food.getInteger("count");
			DsfwGoodsEntity goodsEntity = dsfwGoodsDao.queryObject(goodsid);
			if (ValidateUtil.isValid(goodsEntity)) {
				BigDecimal price = goodsEntity.getPrice();
				BigDecimal subtotal = price.multiply(new BigDecimal(count));
				totalMoney = totalMoney.add(subtotal);
				DsfwOrderdetailEntity orderdetailEntity = new DsfwOrderdetailEntity();
				orderdetailEntity.setGoodsid(goodsid);
				orderdetailEntity.setCount(count);
				orderdetailEntity.setCreatetime(new Date());
				orderdetailEntity.setPrice(price);
				orderdetailEntity.setTotalprice(subtotal);
				//添加到订单
				orderEntity.setCreatetime(new Date());
				orderEntity.setSendtimeid(sendfoodtime);
				orderEntity.setMobile(mobile);
				orderEntity.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_DSFW));
				orderEntity.setRealname(realname);
				orderEntity.setStatus(APPConstant.TYPE_ORDER_STATUS_ORDERED);
				orderEntity.setTotalprice(subtotal);
				orderEntity.setUid(uid);
				orderEntity.setAddressid(addressId);
				orderEntity.setRemark(remark);
				orderEntity.setSendTime(sendtime);
				orderEntity.setPayStatus(APPConstant.TYPE_ORDER_PAY_STATUS_UNPAY);
				orderEntity.setParentOrdercode(parentOrdercode);
				save(orderEntity);
				orderdetailEntity.setOrderid(orderEntity.getId());
				dsfwOrderdetailDao.save(orderdetailEntity);
			}
		}
		return R.ok().put("ordertype", APPConstant.TYPE_DSFW).put("ordercode", parentOrdercode).put("createtime", new Date()).put("paytype", paytype).put("totalMoney", totalMoney);
	}*/
	public static void main(String[] args) {
		System.out.println();
	}
	@Override
	public R orderCancel(JSONObject params) {
		JSONArray reasonIds = params.getJSONArray("reasonIds");
		String reasonContent = params.getString("reasonContent");
		List<DsfwOrderEntity> orderList = queryList(params);
		DsfwGoodsEntity goods = new DsfwGoodsEntity();
		if (ValidateUtil.isValid(orderList)) {
			DsfwOrderEntity order = orderList.get(0);
			log.debug("order:"+order);
			Integer status = order.getStatus();
			if (status!=APPConstant.TYPE_ORDER_STATUS_ORDERED) {
				return R.error(2,"非法操作");
			}
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_CANCEL);
			//添加退款原因
			if (ValidateUtil.isValid(reasonIds)) {
				order.setCancelReasonids(CommonUtils.concatWithSeparater(reasonIds.toArray(), ","));
			}
			order.setReasonContent(reasonContent);
			update(order);
			//执行退款操作
			//清除预约
			dsfwAppointmentDao.clearAppointment(order.getId());
		}
		return R.ok();
	}
	
	@Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
	    DsfwOrderEntity order;
	    BigDecimal sum = new BigDecimal(0);
        if(pay.getOrderid() != null&&pay.getOrderid().intValue()!=0){
            order = queryObject(pay.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", pay.getOrdercode());
            List<DsfwOrderEntity> orderlist = queryList(params);
            if(ValidateUtil.isValid(orderlist)){
                order = orderlist.get(0);
                for (DsfwOrderEntity orderE : orderlist) {
					sum = sum.add(orderE.getTotalprice());
				}
            }else{
            	//如果是父订单需要进行支付
                throw new ZHHQException(1500, "支付前订单校验失败，可能原因:订单不存在");
            }
        }
	    if(!checkBeforePay(order)){
            throw new ZHHQException(1500, "支付前订单校验失败，可能原因:订单已支付或不存在");
        }
        PayOrderVo payorder = new PayOrderVo();
        payorder.setUid(order.getUid());
        payorder.setOrdercode(pay.getOrdercode());
//        payorder.setOrderid(order.getId());
        payorder.setOrdertype(APPConstant.TYPE_DSFW);
        payorder.setPaymoney(sum);
        payorder.setStatus(order.getStatus());
        return payorder;
    }

    private boolean checkBeforePay(DsfwOrderEntity order) {
        if(order == null || 1 == order.getPayStatus()){
            return false;
        }
        return true;
    }
    
    @Override
    public void savePayed(PayOrderVo orderVo) {
    	Map<String,Object> params = new HashMap<>();
    	params.put("payordercode", orderVo.getOrdercode());
        List<DsfwOrderEntity> list = queryList(params);
        if (ValidateUtil.isValid(list)) {
        	for (DsfwOrderEntity order : list) {
        		order.setPayStatus(1);
        		order.setPayType(orderVo.getPaytype());
        		//order.setStatus(APPConstant.TYPE_ORDER_STATUS_SERVING);
        		update(order);
        	}
		}
    }
    @Override
	public int invalidOrderBatch(Integer[] array) {
		HashMap<String,Object> params = new HashMap<>();
		params.put("ids", array);
		params.put("orderStatus", APPConstant.TYPE_ORDER_STATUS_INVALID);
		params.put("orderStatusFormer", APPConstant.TYPE_ORDER_STATUS_CANCEL);
		int num = dsfwOrderDao.invalidOrderBatch(params);
		return num;
	}

    @Override
	public R cancelOrder(Integer id, String cancelreason) {
    	DsfwOrderEntity order = queryObject(id);
		if (ValidateUtil.isValid(order)) {
			Integer status = order.getStatus();
			if (status!=APPConstant.TYPE_ORDER_STATUS_ORDERED) {
				return R.error(2,"非法操作");
			}
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_CANCEL);
			order.setReasonContent(cancelreason);
			update(order);
		}
		return R.ok();
	}

	@Override
	public void deleteOrder(Integer id) {
		Integer[] ids = {id};
		invalidOrderBatch(ids);
	}

	@Override
	public R addComment(CommentVo comment, MultipartFile[] files) {
		DsfwCommentEntity commentEntity = new DsfwCommentEntity();
		commentEntity.setAnonymous(comment.getAnonymous());
		commentEntity.setContent(comment.getContent());
		commentEntity.setCreatetime(new Date());
		commentEntity.setOrderid(comment.getId());
		commentEntity.setProductscore(comment.getProductscore());
		commentEntity.setScore(comment.getScore());
		commentEntity.setServicescore(comment.getServicescore());
		commentEntity.setUid(comment.getUid());
		try {
			return dsfwCommentService.addComment(commentEntity, files);
		} catch (IOException e) {
			e.printStackTrace();
			return R.error(2,"添加评论失败");
		}
	}

	@Override
    public R finishOrder(Integer id) {
		DsfwOrderEntity order = queryObject(id);
		if (order.getStatus()==APPConstant.TYPE_ORDER_STATUS_SERVING) {
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_FINISHED);
		}else{
			return R.error(1,"原始状态有误");
		}
		update(order);
		return R.ok();
    }

	@Override
	public Date getDeliverytime(Integer id) {
		DsfwOrderEntity order = queryObject(id);
		DsfwSendtimeEntity time = dsfwOrderDao.getSendtime(order.getSendtimeid());
		Date date;
		if (time!=null) {
			Date createtime = order.getCreatetime();
			int hour = 0;
			int minute = 0;
			if (ValidateUtil.isValid(time.getEndtime())) {
				String[] split = time.getEndtime().split(":");
				hour = Integer.parseInt(split[0]);
				minute = Integer.parseInt(split[1]);
			}
			Calendar c = Calendar.getInstance();
			c.setTime(createtime);
			c.set(Calendar.HOUR, hour);
			c.set(Calendar.MINUTE, minute);
			date = c.getTime();
		}else{
			date = new Date();
		}
		return date;
	}

	@Override
	public R updateOrderStatus(Integer id, Integer lastStatus, Integer newStatus) {
		DsfwOrderEntity order = queryObject(id);
		if (order.getStatus()!=lastStatus) {
			return R.error(1,"非法操作");
		}
		order.setStatus(newStatus);
		update(order);
		return R.ok();
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
	public List<DsfwOrderEntity> OrderList(Query q) {
		return dsfwOrderDao.queryOrderlist(q);
	}

	@Override
	public DsfwOrderDTO OrderDetail(JSONObject params) {
		return dsfwOrderDao.queryOrderDetail(params);
	}

	@Override
	public void deleteOrders(JSONObject in) {

		dsfwOrderDao.deleteCancelOrder(in);
	}

	@Override
	public void finishOrders(JSONObject p) {
		DsfwOrderEntity order = new DsfwOrderEntity();
		order.setId(p.getInteger("id"));
		order.setStatus(4);
        update(order);
        
        dsfwOrderDao.finishDeliveryOrders(p);
        
        // 保存钱包记录
        DsfwOrderEntity o = queryObject(p.getInteger("id"));
        if(o.getTotalprice() != null && o.getTotalprice().compareTo(new BigDecimal("0")) > 0){
        	ServiceWallelogEntity t = new ServiceWallelogEntity();
        	t.setUid(p.getInteger("uid"));
        	t.setMoney(o.getTotalprice().toString());
        	t.setContent("订水订单完成");
        	t.setUseType(1);
        	t.setServiceType(18);
        	t.setCreatTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			serviceWallelogDao.save(t);
        }
		
	}

	@Override
	public void instantService(String id,Integer uid) {

        Map<String,Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("uid", uid);
        
        Integer did = dsfwOrderDao.selectCountByOrderIdAndOrderType(param);
        if(did == null){
        	dsfwOrderDao.insertDeliveryOrder(param);
        }else{
        	if(did.intValue() != uid.intValue()){
    			throw new RRException("该订单已被接单");// 此处不用考虑其他状态情况，如果服务端已取消的话就会把t_delivery_order表中的数据删除
        	}else{
                dsfwOrderDao.instantUpdateOrder(param);
        	}
        }
        
        param.put("status", 6);
        param.remove("uid");
		// 修改订单为已接单
        dsfwOrderDao.update(param);
        
	}

	@Override
	public R cancelOrders(JSONObject in) {
		int id = in.getInteger("id");
		
		DsfwOrderEntity order = queryObject(id);
    	if(order.getStatus() ==2 || order.getStatus() == 6  ){
            order.setStatus(1);
            order.setId(id);
            update(order);
            
            // 保存维修人员取消记录
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("order_id", in.get("id"));
            param.put("menderid", in.get("menderid"));
            param.put("cancel_time", new Date());
            param.put("cancel_reason", in.get("cancelreason"));
            dsfwOrderDao.saveOrderCancel(param);

            dsfwOrderDao.deleteDeliveryOrder(param);
            return R.ok();
        }else{
            return R.error(1101, "订单只有在已接单或者已配送中才可以取消");
        }
	}

	@Override
	public void distribution(JSONObject in) {
		dsfwOrderDao.distributionOrder(in);

		DsfwOrderEntity o = new DsfwOrderEntity();
		o.setId(in.getInteger("id"));
		o.setStatus(2);
		update(o);
	}

	@Override
	public void refuseOrder(JSONObject p) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("order_id", p.get("orderId"));
        param.put("menderid", p.get("uid"));
        dsfwOrderDao.deleteDeliveryOrder(param);
        
		DsfwOrderEntity t = new DsfwOrderEntity();
        t.setStatus(1);
        t.setId(p.getInteger("orderId"));
		update(t);
		
		dsfwOrderDao.insertRefuseOrder(p);
	}

	@Override
	public void deleteOverOrder(JSONObject in) {
		dsfwOrderDao.deleteOverOrder(in);
	}
}
