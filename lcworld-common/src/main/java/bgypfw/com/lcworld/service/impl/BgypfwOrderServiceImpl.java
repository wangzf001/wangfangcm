package com.lcworld.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.AddressDao;
import com.lcworld.dao.BgypfwCartDao;
import com.lcworld.dao.BgypfwOrderDao;
import com.lcworld.dao.BgypfwOrderdetailDao;
import com.lcworld.dao.BgypfwSkuidDao;
import com.lcworld.dao.DeliveryOrderDao;
import com.lcworld.dao.ServiceWallelogDao;
import com.lcworld.dto.BgypfwOrderDTO;
import com.lcworld.dto.BxwxOrderDTO;
import com.lcworld.dto.HysfwOrderDTO;
import com.lcworld.entity.AddressEntity;
import com.lcworld.entity.BgypfwCommentEntity;
import com.lcworld.entity.BgypfwOrderEntity;
import com.lcworld.entity.BgypfwOrderdetailEntity;
import com.lcworld.entity.BgypfwSkuidEntity;
import com.lcworld.entity.DcfwGzcOrderEntity;
import com.lcworld.entity.HysfwOrderEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.ServiceWallelogEntity;
import com.lcworld.entity.TBxwxOrderEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.BgypfwCommentService;
import com.lcworld.service.BgypfwOrderService;
import com.lcworld.utils.CommonUtils;
import com.lcworld.utils.DateUtil;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.RRException;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("bgypfwOrderService")
public class BgypfwOrderServiceImpl implements BgypfwOrderService {
	private Logger log = LoggerFactory.getLogger(DcfwGzcOrderServiceImpl.class);
	@Autowired
	private BgypfwOrderDao bgypfwOrderDao;
	@Autowired
	private AddressDao bgypfwAddressDao;
	@Autowired
	private BgypfwSkuidDao bgypfwSkuidDao;
	@Autowired
	private BgypfwOrderdetailDao bgypfwOrderdetailDao;
	@Autowired
	private BgypfwCartDao bgypfwCartDao;
	@Autowired
	private BgypfwCommentService bgypfwCommentService;
	
	@Autowired
	ServiceWallelogDao serviceWallelogDao;
	
	@Override
	public BgypfwOrderEntity queryObject(Integer id){
		return bgypfwOrderDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwOrderEntity> queryList(Map<String, Object> map){
		return bgypfwOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwOrderEntity bgypfwOrder){
		bgypfwOrderDao.save(bgypfwOrder);
	}
	
	@Override
	public void update(BgypfwOrderEntity bgypfwOrder){
		bgypfwOrderDao.update(bgypfwOrder);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwOrderDao.deleteBatch(ids);
	}

	@Override
	public R generateOrder(JSONObject params) throws ParseException {
		//生成订单
		Integer addressid = params.getInteger("addressid");
		String expectedTimeStr = params.getString("expectedTime");
		Date expectedTime = DateUtil.parse(expectedTimeStr, "yyyy-MM-dd hh:mm");
		JSONArray productList = params.getJSONArray("productList");
		//productList=[{skuid=1,count=1}]
		String remark = params.getString("remark");
		String uid = params.getString("uid");
		Integer paytype = params.getInteger("paytype");
		AddressEntity address = bgypfwAddressDao.queryObject(addressid);
		ArrayList<Integer> skuidList = new ArrayList<>();
		
		//设置总单号
		String parentOrdercode = OrderCodeGenerator.createOrderCode(APPConstant.TYPE_BGYPFW);
		BigDecimal totalMoney = new BigDecimal(0);
		if (ValidateUtil.isValid(productList)) {
			JSONObject skuParam = productList.getJSONObject(0);
			BgypfwSkuidEntity skuEntity = bgypfwSkuidDao.queryObject(skuParam.getInteger("skuid"));
			BigDecimal price = skuEntity.getPrice();
			Integer productid = skuEntity.getProductid();
			for (int i = 0; i < productList.size(); i++) {
				BgypfwOrderEntity order = new BgypfwOrderEntity();
				JSONObject product = productList.getJSONObject(i);
				Integer skuid = product.getInteger("skuid");
				skuidList.add(skuid);
				Integer count = product.getInteger("count");
				Integer store = skuEntity.getStore();
				if (store-count<0) {
					return R.error(2,"库存不足");
				}
				BgypfwOrderdetailEntity orderDetail = new BgypfwOrderdetailEntity();
				orderDetail.setCount(count);
				orderDetail.setCreatetime(new Date());
				orderDetail.setPrice(price);
				orderDetail.setProductid(productid);
				BigDecimal subtotalprice = price.multiply(new BigDecimal(count));
				totalMoney = totalMoney.add(subtotalprice);
				orderDetail.setTotalprice(subtotalprice);
				DcfwGzcOrderEntity orderEntity = new DcfwGzcOrderEntity();
				orderDetail.setSkuid(skuid);
				//减少对应库存的量
				skuEntity.setStore(store-count);
				bgypfwSkuidDao.update(skuEntity);
				order.setAddressid(addressid);
				order.setCode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_BGYPFW));
				order.setCreatetime(new Date());
				order.setExpertarrivaltme(expectedTime);
				order.setPaytype(APPConstant.TYPE_PAYTYPE_ZFB);
				order.setRemark(remark);
				order.setStatus(APPConstant.TYPE_ORDER_STATUS_ORDERED);
				order.setUid(Integer.parseInt(uid));
				order.setUsername(address.getRealname());
				order.setMobile(address.getMobile());
				order.setTotalprice(subtotalprice);
				order.setPayStatus(0);
				order.setParentOrdercode(parentOrdercode);
				bgypfwOrderDao.save(order);
				orderDetail.setOrderid(order.getId());
				bgypfwOrderdetailDao.save(orderDetail);
			}
		}
		//删除购物车项
		Map<String,Object> paramCart = new HashMap<>();
		paramCart.put("uid", uid);
		paramCart.put("skuids", skuidList.toArray(new Integer[1]));
		bgypfwCartDao.deleteBatchBySkuids(paramCart);
		return R.ok().put("ordertype", APPConstant.TYPE_BGYPFW).put("ordercode", parentOrdercode).put("createtime", new Date()).put("paytype", paytype).put("totalMoney", totalMoney);
	}

	@Override
	public R orderCancel(JSONObject params) {
		JSONArray reasonIds = params.getJSONArray("reasonIds");
		String reasonContent = params.getString("reasonContent");
		List<BgypfwOrderEntity> orderList = queryList(params);
		if (ValidateUtil.isValid(orderList)) {
			BgypfwOrderEntity order = orderList.get(0);
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
		}
		return R.ok();
	}
	
	@Override
	public int invalidOrderBatch(Integer[] array) {
		HashMap<String,Object> params = new HashMap<>();
		params.put("ids", array);
		params.put("orderStatus", APPConstant.TYPE_ORDER_STATUS_INVALID);
		params.put("orderStatusFormer", APPConstant.TYPE_ORDER_STATUS_CANCEL);
		int num = bgypfwOrderDao.invalidOrderBatch(params);
		return num;
	}
	
    @Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
    	BgypfwOrderEntity order;
	    BigDecimal sum = new BigDecimal(0);
        if(pay.getOrderid() != null&&pay.getOrderid().intValue()!=0){
            order = queryObject(pay.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", pay.getOrdercode());
            List<BgypfwOrderEntity> orderlist = queryList(params);
            if(ValidateUtil.isValid(orderlist)){
                order = orderlist.get(0);
                for (BgypfwOrderEntity orderE : orderlist) {
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
        payorder.setOrdertype(APPConstant.TYPE_BGYPFW);
        payorder.setPaymoney(sum);
        payorder.setStatus(order.getStatus());
        return payorder;
    }

    private boolean checkBeforePay(BgypfwOrderEntity order) {
        if(order == null ||  1 == order.getPayStatus()){
            return false;
        }
        return true;
    }
    
    @Override
    public void savePayed(PayOrderVo orderVo) {
        Map<String,Object> params = new HashMap<>();
    	params.put("payordercode", orderVo.getOrdercode());
        List<BgypfwOrderEntity> list = queryList(params);
        if (ValidateUtil.isValid(list)) {
        	for (BgypfwOrderEntity order : list) {
        		order.setPayStatus(1);
        		order.setPaytype(orderVo.getPaytype());
        		order.setStatus(APPConstant.TYPE_ORDER_STATUS_ORDERED);
        		update(order);
        	}
		}
    }

    @Override
	public R cancelOrder(Integer id, String cancelreason) {
    	BgypfwOrderEntity order = queryObject(id);
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
		BgypfwCommentEntity commentEntity = new BgypfwCommentEntity();
		commentEntity.setAnonymous(comment.getAnonymous());
		commentEntity.setContent(comment.getContent());
		commentEntity.setCreatetime(new Date());
		commentEntity.setOrderid(comment.getId());
		commentEntity.setProductscore(comment.getProductscore());
		commentEntity.setScore(comment.getScore());
		commentEntity.setServicescore(comment.getServicescore());
		commentEntity.setUid(comment.getUid());
		Map<String,Object> params = new HashMap<>();
		params.put("oid", comment.getId());
		List<BgypfwOrderEntity> order = queryList(params);
		commentEntity.setProductid(order.get(0).getOrderDetailList().get(0).getProductid());
		try {
			return bgypfwCommentService.addComment(commentEntity, files);
		} catch (IOException e) {
			e.printStackTrace();
			return R.error(2,"添加评论失败");
		}
	}

    @Override
    public R finishOrder(Integer id) {
    	BgypfwOrderEntity order = queryObject(id);
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
		BgypfwOrderEntity order = queryObject(id);
		Date date;
		if (order.getExpertarrivaltme()!=null) {
			date = order.getExpertarrivaltme();
		}else{
			date = new Date();
		}
		return date;
	}

	@Override
	public R updateOrderStatus(Integer id, Integer lastStatus, Integer newStatus) {
		BgypfwOrderEntity order = queryObject(id);
		if (order.getStatus()!=lastStatus) {
			return R.error(1,"非法操作");
		}
		order.setStatus(newStatus);
		update(order);
		return R.ok();
	}

	@Override
	public RefundinfoEntity getRefundInfo(RefundinfoEntity refundVo) {
		BgypfwOrderEntity order;
		BigDecimal sum = new BigDecimal(0);
        if(refundVo.getOrderid() != null){
            order = queryObject(refundVo.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", refundVo.getOrdercode());
            List<BgypfwOrderEntity> orderlist = queryList(params);
            if(ValidateUtil.isValid(orderlist)){
                order = orderlist.get(0);
                if (order.getParentOrdercode()!=null) {
                	List<BgypfwOrderEntity> allOrderList = queryList(params);
                	for (BgypfwOrderEntity orderE : allOrderList) {
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
        refundVo.setRefundtype(order.getPaytype());
        return refundVo;
	}

	@Override
	public void saveRefund(RefundinfoEntity refundVo) {
		Map<String,Object> params = new HashMap<>();
    	params.put("payordercode", refundVo.getOrdercode());
        List<BgypfwOrderEntity> list = queryList(params);
        if (ValidateUtil.isValid(list)) {
        	BgypfwOrderEntity order = list.get(0);
        	order.setRefundstatus(1);
    		update(order);
		}
	}

	@Override
	public List<BgypfwOrderEntity> OrderList(Query q) {
		return bgypfwOrderDao.queryOrderlist(q);
	}

	@Override
	public BgypfwOrderDTO OrderDetail(JSONObject params) {
		 return bgypfwOrderDao.queryOrderDetail(params);
	}

	@Override
	public void deleteOrders(JSONObject in) {
		bgypfwOrderDao.deleteCancelOrder(in);
	}

	@Override
	public void finishOrders(JSONObject p) {
		BgypfwOrderEntity order = new BgypfwOrderEntity();
		order.setId(p.getInteger("id"));
		order.setStatus(4);
        update(order);
        
        bgypfwOrderDao.finishDeliveryOrders(p);
        
        // 保存钱包记录
        BgypfwOrderEntity o = queryObject(p.getInteger("id"));
        if(o.getTotalprice() != null && o.getTotalprice().compareTo(new BigDecimal("0")) > 0){
        	ServiceWallelogEntity t = new ServiceWallelogEntity();
        	t.setUid(p.getInteger("uid"));
        	t.setMoney(o.getTotalprice().toString());
        	t.setContent("办公用品订单完成");
        	t.setUseType(1);
        	t.setServiceType(8);
        	t.setCreatTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			serviceWallelogDao.save(t);
        }
	}

	@Override
	public void instantService(String id,Integer uid) {
		synchronized (this) {
	        Map<String,Object> param = new HashMap<String, Object>();
	        param.put("id", id);
	        param.put("uid", uid);
	        
	        Integer did = bgypfwOrderDao.selectCountByTypeAndOrderId(param);
	        
	        // 如果为空证明没有接单
	        if(did == null){
				bgypfwOrderDao.insertDeliveryOrder(param);
	        }else{
	        	// 如果不相等，证明不是自己接单
	        	if(did.intValue() != uid.intValue()){
	    			throw new RRException("该订单已被接单");// 此处不用考虑其他状态情况，如果服务端已取消的话就会把t_delivery_order表中的数据删除
	        	}else{
	        		// 相等为自己接单，修改即可
	    			bgypfwOrderDao.instantUpdateOrder(param);
	        	}
	        }
	        
	        param.put("status", 6);
	        param.remove("uid");
			// 修改订单为已接单
	        bgypfwOrderDao.update(param);
		}
	}

	@Override
	public R cancelOrders(JSONObject in) {
		int id = in.getInteger("id");
		
		BgypfwOrderEntity order = queryObject(id);
    	if(order.getStatus() == 2 || order.getStatus() == 6){
            order.setStatus(1);
            order.setId(id);
            update(order);
            
            // 保存维修人员取消记录
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("order_id", in.get("id"));
            param.put("menderid", in.get("menderid"));
            param.put("cancel_time", new Date());
            param.put("cancel_reason", in.get("cancelreason"));
            bgypfwOrderDao.saveOrderCancel(param);
            
            bgypfwOrderDao.deleteDeliveryOrder(param);
            return R.ok();
        }else{
            return R.error(1101, "订单只有在已接单或者已配送中才可以取消");
        }
	}

	@Override
	public void distribution(JSONObject in) {
        bgypfwOrderDao.distributionOrder(in);
		
        BgypfwOrderEntity bgypfwOrder = new BgypfwOrderEntity();
        bgypfwOrder.setId(in.getInteger("id"));
        bgypfwOrder.setStatus(2);
		update(bgypfwOrder);
	}

	@Override
	public void refuseOrder(JSONObject p) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("order_id", p.get("orderId"));
        param.put("menderid", p.get("uid"));
        bgypfwOrderDao.deleteDeliveryOrder(param);
        
        BgypfwOrderEntity bgypfwOrder = new BgypfwOrderEntity();
        bgypfwOrder.setStatus(1);
        bgypfwOrder.setId(p.getInteger("orderId"));
		update(bgypfwOrder);
		
		bgypfwOrderDao.insertRefuseOrder(p);
	}

	@Override
	public void deleteOverOrder(JSONObject in) {
		bgypfwOrderDao.deleteOverOrder(in);
	}
}
