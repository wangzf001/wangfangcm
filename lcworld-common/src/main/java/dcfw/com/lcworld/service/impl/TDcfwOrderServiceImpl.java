package com.lcworld.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.TDcfwFoodDao;
import com.lcworld.dao.TDcfwOrderDao;
import com.lcworld.dao.TDcfwOrderdetailDao;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TDcfwCommentEntity;
import com.lcworld.entity.TDcfwFoodEntity;
import com.lcworld.entity.TDcfwGetfoodtimeEntity;
import com.lcworld.entity.TDcfwOrderEntity;
import com.lcworld.entity.TDcfwOrderdetailEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.RefundinfoService;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TDcfwCommentService;
import com.lcworld.service.TDcfwGetfoodtimeService;
import com.lcworld.service.TDcfwOrderService;
import com.lcworld.test.queue.CancelQueue;
import com.lcworld.test.queue.OrderQueueSingleton;
import com.lcworld.test.queue.RemindQueue;
import com.lcworld.test.queue.vo.CancelOrderVo;
import com.lcworld.test.queue.vo.RemindOrderVo;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("tDcfwOrderService")
@Transactional
public class TDcfwOrderServiceImpl implements TDcfwOrderService {
	private Logger log = LoggerFactory.getLogger(TDcfwOrderServiceImpl.class);
	@Autowired
	private TDcfwOrderDao tDcfwOrderDao;
	@Autowired
	private TDcfwFoodDao tDcfwFoodDao;
	@Autowired
	private TDcfwOrderdetailDao tDcfwOrderdetailDao;
	@Autowired
	private TDcfwCommentService tDcfwCommentService;
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private TDcfwGetfoodtimeService tDcfwGetfoodtimeService;
	@Autowired
	private RefundinfoService refundinfoService;
	
	@Override
	public TDcfwOrderEntity queryObject(Integer id){
		return tDcfwOrderDao.queryObject(id);
	}
	
	@Override
	public List<TDcfwOrderEntity> queryList(Map<String, Object> map){
		List<TDcfwOrderEntity> orderList = tDcfwOrderDao.queryList(map);
		log.debug("orderListSize:"+orderList.size());
		return orderList;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tDcfwOrderDao.queryTotal(map);
	}
	@Override
	public void save(TDcfwOrderEntity tDcfwOrder){
		tDcfwOrderDao.save(tDcfwOrder);
	}
	
	@Override
	public void update(TDcfwOrderEntity tDcfwOrder){
		tDcfwOrderDao.update(tDcfwOrder);
	}
	
	@Override
	public void delete(Integer id){
		tDcfwOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tDcfwOrderDao.deleteBatch(ids);
	}

	@Override
	public R generateOrder(JSONObject params) {
		//需要用到的参数
		Integer uid = params.getInteger("uid");
		String realname = params.getString("realname");
		String mobile = params.getString("mobile");
//		Integer getfoodtime = params.getInteger("getfoodtimeID");
		List<TDcfwGetfoodtimeEntity> list = tDcfwGetfoodtimeService.queryList(new HashMap<String,Object>());
		JSONArray foodList = params.getJSONArray("foodList");
		Integer paytype = params.getInteger("paytype");
		//初始化参数
		TDcfwGetfoodtimeEntity time = list.get(0);
		
		//设置总单号
		String parentOrdercode = OrderCodeGenerator.createOrderCode(APPConstant.TYPE_DCFW);
		BigDecimal totalMoney = new BigDecimal(0);
		for (int i = 0; i < foodList.size(); i++) {
			TDcfwOrderEntity orderEntity = new TDcfwOrderEntity();
			JSONObject food = foodList.getJSONObject(i);
			Integer foodid = food.getInteger("foodid");
			Integer count = food.getInteger("count");
			TDcfwFoodEntity foodEntity = tDcfwFoodDao.queryObject(foodid);
			if (ValidateUtil.isValid(foodEntity)) {
				BigDecimal price = foodEntity.getPrice();
				BigDecimal subtotal = price.multiply(new BigDecimal(count));
				totalMoney = totalMoney.add(subtotal);
				TDcfwOrderdetailEntity orderdetailEntity = new TDcfwOrderdetailEntity();
				orderdetailEntity.setFoodid(foodid);
				orderdetailEntity.setCount(count);
				orderdetailEntity.setCreatetime(new Date());
				orderdetailEntity.setPrice(price);
				orderdetailEntity.setTotalprice(subtotal);
				if ((foodEntity.getRemain()-count)<0) {
					return R.error(2,"数量不足").put("foodid", foodid);
				}
				//将这些食物的剩余数量减少
				foodEntity.setRemain(foodEntity.getRemain()-count);
				tDcfwFoodDao.update(foodEntity);
				orderEntity.setCreatetime(new Date());
				orderEntity.setGetfoodtime(time.getId());
				orderEntity.setMobile(mobile);
				orderEntity.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_DCFW));
				orderEntity.setRealname(realname);
				orderEntity.setStatus(APPConstant.TYPE_ORDER_STATUS_ORDERED);
				orderEntity.setTotalprice(subtotal);
				orderEntity.setUid(uid);
				orderEntity.setParentOrdercode(parentOrdercode);
				orderEntity.setPayStatus(APPConstant.TYPE_ORDER_PAY_STATUS_UNPAY);
				orderEntity.setRefundstatus(0);
				//设置过期时间
				orderEntity.setExpireTime(DateUtils.getDate(new Date(),Calendar.MINUTE, APPConstant.TYPE_DCFW_EXPIRETIME));
				tDcfwOrderDao.save(orderEntity);
				CancelQueue queue = OrderQueueSingleton.getCancelQueue();
				CancelOrderVo orderVo = changeToCancelOrderVo(orderEntity);
				queue.offer(orderVo);
				orderdetailEntity.setOrderid(orderEntity.getId());
				tDcfwOrderdetailDao.save(orderdetailEntity);
			}
		}
		//订单加入提醒队列中
		RemindQueue queue = OrderQueueSingleton.getRemindQueue();
		TDcfwOrderEntity order = new TDcfwOrderEntity();
		order.setOrdercode(parentOrdercode);
		order.setGetfoodtime(time.getId());
		order.setMobile(mobile);
		RemindOrderVo orderVo = changeToRemindOrderVo(order);
		queue.offer(orderVo);
		return R.ok().put("ordertype", APPConstant.TYPE_DCFW).put("ordercode", parentOrdercode).put("createtime", new Date()).put("paytype", paytype).put("totalMoney", totalMoney);
	}
	

	@Override
	public R orderCancel(JSONObject params) {
		//自动取消1是自动取消
		Integer autocancel = params.getInteger("autocancel");
		String reasonContent = params.getString("reasonContent");
		List<TDcfwOrderEntity> orderList = queryList(params);
		TDcfwFoodEntity food = new TDcfwFoodEntity();
		if (ValidateUtil.isValid(orderList)) {
			TDcfwOrderEntity order = orderList.get(0);
			log.debug("order:"+order);
			Integer status = order.getStatus();
			if (autocancel.intValue()==1&&order.getPayStatus()==1) {
				//超时取消订单不能取消已经付款的订单
				return R.error();
			}
			if (status!=APPConstant.TYPE_ORDER_STATUS_ORDERED) {
				return R.error(2,"非法操作");
			}
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_CANCEL);
			//恢复套餐可预订数
			List<TDcfwOrderdetailEntity> detailList = order.getDetailList();
			if (ValidateUtil.isValid(detailList)) {
				for (int i = 0; i < detailList.size(); i++) {
					TDcfwOrderdetailEntity detail = detailList.get(i);
					Integer foodRemain = detail.getFoodRemain();
					Integer count = detail.getCount();
					Integer foodid = detail.getFoodid();
					food.setId(foodid);
					food.setRemain(foodRemain+count);
					tDcfwFoodDao.update(food);
				}
			}
			//添加退款原因
			order.setReasonContent(reasonContent);
			update(order);
			//根据情况取消提醒
			remindCancel(order);
		}
		return R.ok();
	}
	/**
	 * 将商品转换为OrderVo
	 * @param obj
	 * @return
	 */
	@Override
	public CancelOrderVo changeToCancelOrderVo(Object obj) {
		TDcfwOrderEntity order = null;
		if (obj!=null && obj instanceof TDcfwOrderEntity) {
			order = (TDcfwOrderEntity)obj;
			CancelOrderVo orderVo = new CancelOrderVo();
			orderVo.setOrdercode(order.getOrdercode());
			orderVo.setOrdertype(APPConstant.TYPE_DCFW);
			orderVo.setTimeAlarm(order.getExpireTime());
			return orderVo;
		}else{
			return null;
		}
	}
    @Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
    	TDcfwOrderEntity order;
	    BigDecimal sum = new BigDecimal(0);
        if(pay.getOrderid() != null&&pay.getOrderid().intValue()!=0){
            order = queryObject(pay.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", pay.getOrdercode());
            List<TDcfwOrderEntity> orderlist = queryList(params);
            if(ValidateUtil.isValid(orderlist)){
                order = orderlist.get(0);
                for (TDcfwOrderEntity orderE : orderlist) {
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
        payorder.setOrdertype(APPConstant.TYPE_DCFW);
        payorder.setPaymoney(sum);
        payorder.setStatus(order.getStatus());
        return payorder;
    }

    private boolean checkBeforePay(TDcfwOrderEntity order) {
        if(order == null || 1 == order.getPayStatus()){
            return false;
        }
        return true;
    }
    
    @Override
    public void savePayed(PayOrderVo orderVo) {
    	Map<String,Object> params = new HashMap<>();
    	params.put("payordercode", orderVo.getOrdercode());
        List<TDcfwOrderEntity> list = queryList(params);
        if (ValidateUtil.isValid(list)) {
        	for (TDcfwOrderEntity order : list) {
        		order.setPayStatus(1);
        		order.setPayType(orderVo.getPaytype());
        		update(order);
        	}
		}
    }

	@Override
	public List<CancelOrderVo> queryOrderVoList(HashMap<String, Object> params1) {
		params1.put("webQuery", 1);
		List<TDcfwOrderEntity> list = queryList(params1);
		List<CancelOrderVo> outList = new ArrayList<>();
		for (TDcfwOrderEntity order : list) {
			outList.add(changeToCancelOrderVo(order));
		}
		return outList;
	}
	 @Override
	public int invalidOrderBatch(Integer[] array) {
		HashMap<String,Object> params = new HashMap<>();
		params.put("ids", array);
		params.put("orderStatus", APPConstant.TYPE_ORDER_STATUS_INVALID);
		params.put("orderStatusFormer", APPConstant.TYPE_ORDER_STATUS_CANCEL);
		int num = tDcfwOrderDao.updateOrderBatch(params);
		return num;
	}
	 @Override
	public R cancelOrder(Integer id, String cancelreason) {
		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_DCFW);
		String dcfwtokenordertime = service.getDcfwtokenordertime();
		Date cancelCanTime = null;
		if (ValidateUtil.isValid(dcfwtokenordertime)) {
			try {
				String[] split = dcfwtokenordertime.split("-");
				String timestr = split[1];
				String[] timeArr = timestr.split("~");
				String[] hAndm = timeArr[0].split("\\:");
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hAndm[0]));
				c.set(Calendar.MINUTE, Integer.parseInt(hAndm[1]));
				cancelCanTime = c.getTime();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if (!(ValidateUtil.isValid(cancelCanTime)&&cancelCanTime.getTime()<=new Date().getTime())) {
			return R.error(3,"超出可取消时间或未设置可预订时间");
		}
		HashMap<String, Object> params = new HashMap<>();
		params.put("oid", id);
		List<TDcfwOrderEntity> orderList = queryList(params);
		TDcfwFoodEntity food = new TDcfwFoodEntity();
		if (ValidateUtil.isValid(orderList)) {
			TDcfwOrderEntity order = orderList.get(0);
			log.debug("order:"+order);
			Integer status = order.getStatus();
			if (status!=APPConstant.TYPE_ORDER_STATUS_ORDERED) {
				return R.error(2,"非法操作");
			}
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_CANCEL);
			//恢复套餐可预订数
			List<TDcfwOrderdetailEntity> detailList = order.getDetailList();
			if (ValidateUtil.isValid(detailList)) {
				for (int i = 0; i < detailList.size(); i++) {
					TDcfwOrderdetailEntity detail = detailList.get(i);
					Integer foodRemain = detail.getFoodRemain();
					Integer count = detail.getCount();
					Integer foodid = detail.getFoodid();
					food.setId(foodid);
					food.setRemain(foodRemain+count);
					tDcfwFoodDao.update(food);
				}
			}
			//添加退款原因
			order.setReasonContent(cancelreason);
			update(order);
			//执行退款操作
			if (order.getPayStatus()==1) {
				//执行退款操作
				RefundinfoEntity refund = new RefundinfoEntity();
				String refundOrderCode = OrderCodeGenerator.createRefundOrderCode(APPConstant.TYPE_DCFW);
				refund.setRefundordercode(refundOrderCode);
				refund.setStatus(0);
				refund.setCreatetime(new Date());
				refund.setOrdercode(order.getOrdercode());
				refund.setOrdertype(APPConstant.TYPE_DCFW);
				RefundinfoEntity refundInfo = getRefundInfo(refund);
				try {
					R r = refundinfoService.savedorefund(refundInfo);
					return r;
 				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return R.error(1,"退款失败");
				}
			}
			//根据情况取消提醒
			remindCancel(order);
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
		TDcfwCommentEntity commentEntity = new TDcfwCommentEntity();
		commentEntity.setAnonymous(comment.getAnonymous());
		commentEntity.setContent(comment.getContent());
		commentEntity.setCreatetime(new Date());
		commentEntity.setOrderid(comment.getId());
		commentEntity.setProductscore(comment.getProductscore());
		commentEntity.setScore(comment.getScore());
		commentEntity.setServicescore(comment.getServicescore());
		commentEntity.setUid(comment.getUid());
		try {
			return tDcfwCommentService.addComment(commentEntity, files);
		} catch (IOException e) {
			e.printStackTrace();
			return R.error(2,"添加评论失败");
		}
	}

	@Override
    public R finishOrder(Integer id) {
		TDcfwOrderEntity order = queryObject(id);
		if (order.getStatus()==APPConstant.TYPE_ORDER_STATUS_ORDERED) {
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_EVALUATED);
		}else{
			return R.error(1,"原始状态有误");
		}
		update(order);
		return R.ok();
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
	public void updateBatch(Integer[] ids, int status) {
		HashMap<String,Object> params = new HashMap<>();
		params.put("orderStatus", status);
		params.put("ids", ids);
		params.put("orderStatusFormer", APPConstant.TYPE_ORDER_STATUS_ORDERED);
		tDcfwOrderDao.updateOrderBatch(params);
	}
	@Override
	public List<RemindOrderVo> queryRemindOrderVoList(HashMap<String, Object> params) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		params.put("createTimeStart", DateUtils.format(c.getTime(), "yyyy-MM-dd HH:mm"));
		params.put("createTimeEnd", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
		List<TDcfwOrderEntity> queryList = queryList(params);
		List<RemindOrderVo> list = new ArrayList<>();
		for (TDcfwOrderEntity order : queryList) {
			list.add(changeToRemindOrderVo(order));
		}
		return list;
	}

	@Override
	public RemindOrderVo changeToRemindOrderVo(TDcfwOrderEntity order) {
			RemindOrderVo orderVo = new RemindOrderVo();
			orderVo.setMobile(order.getMobile());
			orderVo.setOrdercode(order.getOrdercode());
			orderVo.setOrdertype(APPConstant.TYPE_DCFW);
			TDcfwGetfoodtimeEntity time = tDcfwGetfoodtimeService.queryObject(order.getGetfoodtime());
			ServiceEntity service = serviceService.queryService(APPConstant.TYPE_DCFW);
			Date parse = DateUtils.parse(time.getStarttime(), "HH:mm");
			Calendar c = Calendar.getInstance();
			Calendar c1 = Calendar.getInstance();
			c.setTime(new Date());
			c1.setTime(parse);
			c.set(Calendar.HOUR, c1.get(Calendar.HOUR));
			c.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
			orderVo.setTimeAlarm(com.lcworld.utils.DateUtil.add_minute(c.getTime(),-20));
//			orderVo.setTimeAlarm(com.lcworld.utils.DateUtil.add_minute(new Date(),2));
			orderVo.setRemindmsg("您的取货时间是今日："+time.getStarttime()+"，地点："+service.getDcfwtokenplace()+"。请及时取货");
			return orderVo;
	}

	@Override
	public RefundinfoEntity getRefundInfo(RefundinfoEntity refundVo) {
		TDcfwOrderEntity order;
		BigDecimal sum = new BigDecimal(0);
        if(refundVo.getOrderid() != null){
            order = queryObject(refundVo.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", refundVo.getOrdercode());
            List<TDcfwOrderEntity> orderlist = queryList(params);
            if(ValidateUtil.isValid(orderlist)){
                order = orderlist.get(0);
                if (order.getParentOrdercode()!=null) {
                	List<TDcfwOrderEntity> allOrderList = queryList(params);
                	for (TDcfwOrderEntity orderE : allOrderList) {
                		sum = sum.add(orderE.getTotalprice());
					}
                }
            }else{
            	//如果是父订单需要进行支付
                throw new ZHHQException(1, "订单不存在");
            }
        }
        if (order.getRefundstatus().intValue()==1) {
        	//如果是父订单需要进行支付
            throw new ZHHQException(2, "不能重复退单");
		}
        refundVo.setUid(order.getUid());
        if (order.getParentOrdercode()!=null) {
			refundVo.setOrdercode(order.getParentOrdercode());
		}
        refundVo.setRefundmoney(order.getTotalprice());
        refundVo.setTotalrefundmoney(sum);
        refundVo.setRefundtype(order.getPayType());
        return refundVo;
	}

	@Override
	public void saveRefund(RefundinfoEntity refundVo) {
		Map<String,Object> params = new HashMap<>();
    	params.put("payordercode", refundVo.getOrdercode());
        List<TDcfwOrderEntity> list = queryList(params);
        if (ValidateUtil.isValid(list)) {
        	TDcfwOrderEntity order = list.get(0);
        	order.setRefundstatus(1);
    		update(order);
		}
	}

	@Override
	public void finishOrders(JSONObject p) {
		tDcfwOrderDao.updateOrdersFinish(p);
	}

	@Override
	public void remindCancel(TDcfwOrderEntity order) {
		//如果取消的是父订单中的最后一单则取消提醒
		Map<String,Object> map = new HashMap<>();
		map.put("orderStatus", APPConstant.TYPE_ORDER_STATUS_ORDERED);
		map.put("uid", order.getUid());
		map.put("payordercode", order.getParentOrdercode());
		List<TDcfwOrderEntity> list = tDcfwOrderDao.queryList(map);
		if (!(ValidateUtil.isValid(list)&&list.size()>0)) {
			RemindQueue remindQueue = OrderQueueSingleton.getRemindQueue();
			RemindOrderVo remindOrder = new RemindOrderVo();
			remindOrder.setOrdercode(order.getParentOrdercode());
			remindOrder.setOrdertype(APPConstant.TYPE_DCFW);
			remindQueue.remove(remindOrder);
		}
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
