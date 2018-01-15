package com.lcworld.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.lcworld.dao.TGxdfwClothestypeDao;
import com.lcworld.dao.TGxdfwOrderDao;
import com.lcworld.dao.TGxdfwOrderLogDao;
import com.lcworld.dao.TGxdfwOrderdetailDao;
import com.lcworld.entity.DcfwGzcCommentEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.TDcfwOrderEntity;
import com.lcworld.entity.TGxdfwClothestypeEntity;
import com.lcworld.entity.TGxdfwCommentEntity;
import com.lcworld.entity.TGxdfwOrderEntity;
import com.lcworld.entity.TGxdfwOrderLogEntity;
import com.lcworld.entity.TGxdfwOrderdetailEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.TGxdfwCommentService;
import com.lcworld.service.TGxdfwOrderService;
import com.lcworld.utils.CommonUtils;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("tGxdfwOrderService")
@Transactional
public class TGxdfwOrderServiceImpl implements TGxdfwOrderService {
	private Logger log = LoggerFactory.getLogger(TDcfwOrderServiceImpl.class);
	@Autowired
	private TGxdfwOrderDao tGxdfwOrderDao;
	@Autowired
	private TGxdfwClothestypeDao tGxdfwClothestypeDao;
	@Autowired
	private TGxdfwOrderdetailDao tGxdfwOrderdetailDao;
	@Autowired
	private TGxdfwOrderLogDao tGxdfwOrderLogDao;
	@Autowired
	private TGxdfwCommentService tGxdfwCommentService;
	
	@Override
	public TGxdfwOrderEntity queryObject(Integer id){
		return tGxdfwOrderDao.queryObject(id);
	}
	
	@Override
	public List<TGxdfwOrderEntity> queryList(Map<String, Object> map){
		List<TGxdfwOrderEntity> orderList = tGxdfwOrderDao.queryList(map);
		log.debug("orderListSize:"+orderList.size());
//		for (int i = 0; i < orderList.size(); i++) {
//			TGxdfwOrderEntity order = orderList.get(i);
//			String orderDetailIds = order.getOrderDetailIds();
//			log.debug("orderDetailIds:"+orderDetailIds);
//			if (ValidateUtil.isValid(orderDetailIds)) {
//				String[] orderDetailIdArr = orderDetailIds.split(",");
//				HashMap<String,Object> params = new HashMap<>();
//				params.put("idArr", orderDetailIdArr);
//				List<TGxdfwOrderdetailEntity> detailList = tGxdfwOrderdetailDao.queryList(params);
//				log.debug("detailList:"+detailList);
//				order.setDetailList(detailList);
//			}
//		}
		return orderList;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGxdfwOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(TGxdfwOrderEntity tGxdfwOrder){
		tGxdfwOrderDao.save(tGxdfwOrder);
	}
	
	@Override
	public void update(TGxdfwOrderEntity tGxdfwOrder){
		tGxdfwOrderDao.update(tGxdfwOrder);
	}
	
	@Override
	public void delete(Integer id){
		tGxdfwOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tGxdfwOrderDao.deleteBatch(ids);
	}

	@Override
	public R generateOrder(JSONObject params) {
		//需要用到的参数
		Integer uid = params.getInteger("uid");
		String mobile = params.getString("mobile");
		BigDecimal totalprice = params.getBigDecimal("totalprice");
		String time = params.getString("expectarrivaltme");
		String paytype = params.getString("paytype");
		Date parseTime = DateUtils.parse(time, "yyyy-MM-dd hh:mm");
		JSONArray clothList = params.getJSONArray("clothList");
		//初始化参数
		ArrayList<TGxdfwOrderdetailEntity> orderdetailList = new ArrayList<>();
		TGxdfwOrderEntity order = new TGxdfwOrderEntity();
		for (int i = 0; i < clothList.size(); i++) {
			JSONObject cloth = clothList.getJSONObject(i);
			Integer clothestypeid = cloth.getInteger("clothestypeid");
			Integer count = cloth.getInteger("count");
			BigDecimal price  = cloth.getBigDecimal("price");
			BigDecimal subtotal = cloth.getBigDecimal("totalprice");
			String remark = cloth.getString("remark");
			TGxdfwOrderdetailEntity orderdetailEntity = new TGxdfwOrderdetailEntity();
			orderdetailEntity.setClothestypeid(clothestypeid);
			orderdetailEntity.setCount(count);
			orderdetailEntity.setCreatetime(new Date());
			orderdetailEntity.setPrice(price);
			orderdetailEntity.setTotalprice(subtotal);
			orderdetailEntity.setRemark(remark);
			orderdetailList.add(orderdetailEntity);
		}
		order.setCode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_GXDFW));
		order.setCreatetime(new Date());
		order.setExpectarrivaltime(parseTime);
		order.setMobile(mobile);
		order.setStatus(APPConstant.TYPE_ORDER_STATUS_ORDERED);
		order.setTotalprice(totalprice);
		 order.setCheckstatus(0);
		order.setUid(uid);
		order.setPayStatus(APPConstant.TYPE_ORDER_PAY_STATUS_UNPAY);
		tGxdfwOrderDao.save(order);
		for (int i = 0; i < orderdetailList.size(); i++) {
			TGxdfwOrderdetailEntity orderDetail = orderdetailList.get(i);
			orderDetail.setOrderid(order.getId());
//			tGxdfwOrderdetailDao.save(orderDetail);
		}
		tGxdfwOrderdetailDao.saveBatch(orderdetailList);
		TGxdfwOrderLogEntity orderLog = new TGxdfwOrderLogEntity();
		orderLog.setCreatetime(new Date());
		orderLog.setOrderid(order.getId());
		orderLog.setStatus(APPConstant.TYPE_GXDFW_LOG_STATUS_WAITSEND);
		tGxdfwOrderLogDao.save(orderLog);
		return R.ok().put("orderid", order.getId()).put("ordertype", APPConstant.TYPE_GXDFW).put("ordercode", order.getCode()).put("createtime", order.getCreatetime()).put("paytype",paytype).put("totalMoney", totalprice);
	}

	@Override
	public R orderCancel(JSONObject params) {
		JSONArray reasonIds = params.getJSONArray("reasonIds");
		String reasonContent = params.getString("reasonContent");
		List<TGxdfwOrderEntity> orderList = queryList(params);
		if (ValidateUtil.isValid(orderList)) {
			TGxdfwOrderEntity order = orderList.get(0);
			log.debug("order:"+order);
			Integer status = order.getStatus();
			if (status!=APPConstant.TYPE_ORDER_STATUS_ORDERED) {
				return R.error(2,"非法操作");
			}
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_CANCEL);
			//添加取消原因
			if (ValidateUtil.isValid(reasonIds)) {
				order.setCancelReasonids(CommonUtils.concatWithSeparater(reasonIds.toArray(), ","));
			}
			order.setReasonContent(reasonContent);
			update(order);
		}
		return R.ok();
	}
	
	@Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
	    TGxdfwOrderEntity order;
        if(pay.getOrderid() != null&&pay.getOrderid().intValue()!=0){
            order = queryObject(pay.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", pay.getOrdercode());
            List<TGxdfwOrderEntity> orderlist = queryList(params);
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
        payorder.setOrdercode(order.getCode());
        payorder.setOrderid(order.getId());
        payorder.setOrdertype(APPConstant.TYPE_GXDFW);
        payorder.setPaymoney(order.getTotalprice());
        payorder.setStatus(order.getStatus());
        return payorder;
    }

    private boolean checkBeforePay(TGxdfwOrderEntity order) {
        if(order == null || 1 == order.getPayStatus()){
            return false;
        }
        return true;
    }
    
    @Override
    public void savePayed(PayOrderVo orderVo) {
        Map<String,Object> params = new HashMap<>();
    	params.put("payordercode", orderVo.getOrdercode());
        List<TGxdfwOrderEntity> list = queryList(params);
        if (ValidateUtil.isValid(list)) {
        	for (TGxdfwOrderEntity order : list) {
        		order.setPayStatus(1);
        		order.setPayType(orderVo.getPaytype());
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
   		int num = tGxdfwOrderDao.invalidOrderBatch(params);
   		return num;
   	}

    @Override
	public R cancelOrder(Integer id, String cancelreason) {
    	TGxdfwOrderEntity order = queryObject(id);
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
		TGxdfwCommentEntity commentEntity = new TGxdfwCommentEntity();
		commentEntity.setAnonymous(comment.getAnonymous());
		commentEntity.setContent(comment.getContent());
		commentEntity.setCreatetime(new Date());
		commentEntity.setOrderid(comment.getId());
		commentEntity.setProductscore(comment.getProductscore());
		commentEntity.setScore(comment.getScore());
		commentEntity.setServicescore(comment.getServicescore());
		commentEntity.setUid(comment.getUid());
		try {
			return tGxdfwCommentService.addComment(commentEntity, files);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(2,"添加评论失败");
		}
	}

	@Override
    public R finishOrder(Integer id) {
		TGxdfwOrderEntity order = queryObject(id);
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
	public void instantService(String ordercode, Integer uid) {
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
