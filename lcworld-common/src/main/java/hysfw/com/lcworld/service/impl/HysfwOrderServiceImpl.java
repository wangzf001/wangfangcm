package com.lcworld.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.DeliveryOrderDao;
import com.lcworld.dao.HysfwAppointmentDao;
import com.lcworld.dao.HysfwConferenceEquipmentDao;
import com.lcworld.dao.HysfwOrderDao;
import com.lcworld.dao.ServiceWallelogDao;
import com.lcworld.dto.HysfwOrderDTO;
import com.lcworld.entity.BgypfwOrderEntity;
import com.lcworld.entity.DeliveryOrderEntity;
import com.lcworld.entity.HysfwAppointmentEntity;
import com.lcworld.entity.HysfwCommentEntity;
import com.lcworld.entity.HysfwConferenceEquipmentEntity;
import com.lcworld.entity.HysfwOrderEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.ServiceWallelogEntity;
import com.lcworld.service.HysfwCommentService;
import com.lcworld.service.HysfwOrderService;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.RRException;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("hysfwOrderService")
public class HysfwOrderServiceImpl implements HysfwOrderService {
	@Autowired
	private HysfwOrderDao hysfwOrderDao;
	@Autowired
	private HysfwAppointmentDao hysfwAppointmentDao;
	@Autowired
	private HysfwCommentService hysfwCommentService;
	@Autowired
	private HysfwConferenceEquipmentDao conferenceEquipmentDao;
	@Autowired
	private DeliveryOrderDao deliveryOrderDao;
	@Autowired
	ServiceWallelogDao serviceWallelogDao;
	
	@Override
	public HysfwOrderEntity queryObject(Integer id){
		return hysfwOrderDao.queryObject(id);
	}
	
	@Override
	public List<HysfwOrderEntity> queryList(Map<String, Object> map){
		return hysfwOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hysfwOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(HysfwOrderEntity hysfwOrder){
		hysfwOrderDao.save(hysfwOrder);
	}
	
	@Override
	public void update(HysfwOrderEntity hysfwOrder){
		hysfwOrderDao.update(hysfwOrder);
	}
	
	@Override
	public void delete(Integer id){
		hysfwOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hysfwOrderDao.deleteBatch(ids);
	}

	@Override
	public R generateOrder(HysfwOrderEntity order,List<HysfwAppointmentEntity> appointmentList) {
		//先判断预约是否冲突
		Map<String,Object> params = new HashMap<>();
		for (HysfwAppointmentEntity appointment : appointmentList) {
			String date = DateUtils.format(appointment.getDate(), "yyyy-MM-dd");
			String intervalids = appointment.getIntervalids();
			String[] idArr = {};
			if (ValidateUtil.isValid(intervalids)) {
				idArr = intervalids.split(",");
			}
			params.put("date", date);
			params.put("ids", idArr);
			params.put("status", 1);
			params.put("roomid", order.getRoomid());
			List<HysfwAppointmentEntity> list = hysfwAppointmentDao.queryList(params);
			if (list.size()!=0) {
				return R.error(1,"预约时间冲突");
			}
		}
		order.setCreatetime(new Date());
		order.setCheckstatus(APPConstant.TYPE_HYSFW_CHECKING);
		order.setStatus(APPConstant.TYPE_ORDER_STATUS_ORDERED);
		order.setRealdel(0);
		save(order);
		
		for (int i = 0; i < appointmentList.size(); i++) {
			appointmentList.get(i).setOrderid(order.getId());;
			appointmentList.get(i).setStatus(1);
			appointmentList.get(i).setRoomid(order.getRoomid());
		}
		//保存预约信息
		hysfwAppointmentDao.saveBatch(appointmentList);
		return R.ok();
	}
	@Override
	public R orderCancel(JSONObject params) {
		String reasonContent = params.getString("reasonContent");
		List<HysfwOrderEntity> orderList = queryList(params);
		if (ValidateUtil.isValid(orderList)) {
			HysfwOrderEntity order = orderList.get(0);
			Integer status = order.getStatus();
			if (status!=APPConstant.TYPE_ORDER_STATUS_ORDERED) {
				return R.error(2,"非法操作");
			}
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_CANCEL);
			//添加退款原因
			order.setReasoncontent(reasonContent);
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
   		int num = hysfwOrderDao.invalidOrderBatch(params);
   		return num;
   	}

	@Override
	public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
		return null;
	}

	@Override
	public void savePayed(PayOrderVo orderVo) {
		
	}

	@Override
	public R cancelOrder(Integer id, String cancelreason) {
		HysfwOrderEntity order = queryObject(id);
		if (ValidateUtil.isValid(order)) {
			Integer status = order.getStatus();
			if (status!=APPConstant.TYPE_ORDER_STATUS_ORDERED) {
				return R.error(2,"非法操作");
			}
			order.setStatus(APPConstant.TYPE_ORDER_STATUS_CANCEL);
			order.setReasoncontent(cancelreason);
			update(order);
			//清除预约
			hysfwAppointmentDao.clearAppointment(id);
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
		HysfwCommentEntity commentEntity = new HysfwCommentEntity();
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
		try {
			return hysfwCommentService.addComment(commentEntity, files);
		} catch (IOException e) {
			e.printStackTrace();
			return R.error(2,"添加评论失败");
		}
	}

	@Override
	public R finishOrder(Integer id) {
		HysfwOrderEntity order = queryObject(id);
		if (order.getStatus()==APPConstant.TYPE_ORDER_STATUS_SERVING&&order.getCheckstatus()==APPConstant.TYPE_HYSFW_CHECK_SUCCESSED) {
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
	public RefundinfoEntity getRefundInfo(RefundinfoEntity refundVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRefund(RefundinfoEntity refundVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HysfwOrderDTO> OrderList(Query q) {
		List<HysfwOrderDTO> queryOrderlist = hysfwOrderDao.queryOrderlist(q);
		for(int i=0;i<queryOrderlist.size();i++){
			
			HysfwOrderDTO hysfwOrderEntity = queryOrderlist.get(i);
			String equipmentids = hysfwOrderEntity.getEquipmentids();
			if(StringUtils.isBlank(equipmentids)){
				continue;
			}
			
			String[] equipmentid = equipmentids.split(",");
			
			String names = null;
			
			for(int j=0;j<equipmentid.length;j++){
				HysfwConferenceEquipmentEntity queryObject = conferenceEquipmentDao.queryObject(equipmentid[j]);
				if(queryObject == null){
					continue;
				}
				
				String equipmentname = queryObject.getName();
				if(names!=null){
					names = equipmentname;
				}else{
					names += ","+equipmentname;
				}
			}
			hysfwOrderEntity.setName(names);
		}
		
		return queryOrderlist;
	}

	@Override
	public HysfwOrderDTO OrderDetail(JSONObject params) {
		HysfwOrderDTO queryOrderDetail = hysfwOrderDao.queryOrderDetail(params);
		if(queryOrderDetail == null){
			throw new RRException("订单详情不存在");
		}
		
		String equipmentids = queryOrderDetail.getEquipmentids();
		if(StringUtils.isBlank(equipmentids)){
			return queryOrderDetail;
		}
		
		String[] equipmentid = equipmentids.split(",");
		String names = null;
		for(int i=0; i<equipmentid.length;i++){
			HysfwConferenceEquipmentEntity queryObject = conferenceEquipmentDao.queryObject(equipmentid[i]);
			if(queryObject == null){
				continue;
			}
			
			String equipmentname = queryObject.getName();
			if(names!=null){
				names = equipmentname;
			}else{
				names += ","+equipmentname;
			}
		}
		queryOrderDetail.setName(names);
		return queryOrderDetail;
	}

	@Override
	public void deleteOrders(JSONObject in) {
		hysfwOrderDao.deleteCancelOrder(in);
	}

	@Override
	public void finishOrders(JSONObject p) {
		HysfwOrderEntity order = new HysfwOrderEntity();
		order.setId(p.getInteger("id"));
		order.setStatus(4);
        update(order);
        
        // 修改delivery完成
        hysfwOrderDao.finishDeliveryOrders(p);
        
        // 保存钱包记录
    	ServiceWallelogEntity t = new ServiceWallelogEntity();
    	t.setUid(p.getInteger("uid"));
    	t.setMoney("0");
    	t.setContent("会议室订单完成");
    	t.setUseType(1);
    	t.setServiceType(7);
    	t.setCreatTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		serviceWallelogDao.save(t);
	}

	@Override
	public void instantService(String id,Integer uid) {
		synchronized (this) {
			int orderid = Integer.parseInt(id);
			HysfwOrderEntity order = queryObject(orderid);
			
			if(order.getStatus().intValue() != 1){
				switch (order.getStatus().intValue()) {
					case 2:
						throw new RRException("操作失败，订单状态：【服务中】");
					case 3:
						throw new RRException("操作失败，订单状态：【已完成】");
					case 4:
						throw new RRException("操作失败，订单状态：【已评价】");
					case 5:
						throw new RRException("操作失败，订单状态：【已取消】");
					case 6:
						throw new RRException("操作失败，订单状态：【已删除】");
					default:
						throw new RRException("操作失败，订单状态非法");
				}
			}
			
			
	        // 修改原订单状态
			order = new HysfwOrderEntity();
			order.setId(orderid);
			order.setStatus(2);
	        update(order);
	        
			// 插入到t_delivery_order 数据
	        DeliveryOrderEntity t = new DeliveryOrderEntity();
	        Date now = new Date();
	        t.setDid(uid);
	        t.setOrderid(Integer.parseInt(id));
	        t.setCreatetime(now);
	        t.setDeliverytime(now);
	        t.setStatus(2);
	        t.setOrdertype(7);
	        deliveryOrderDao.save(t);
		}
	}

	@Override
	public R cancelOrders(JSONObject in) {
		int id = in.getIntValue("id");
		
		HysfwOrderEntity order = queryObject(id);
    	if(order.getStatus() == 2){
            order.setStatus(1);
            order.setId(id);
            update(order);
            
            // 保存维修人员取消记录
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("order_id", in.get("id"));
            param.put("menderid", in.get("menderid"));
            param.put("cancel_time", new Date());
            param.put("cancel_reason", in.get("cancelreason"));
            hysfwOrderDao.saveOrderCancel(param);
            
            // 删除原订单
            hysfwOrderDao.deleteDeliveryOrder(param);
            return R.ok();
        }else{
            return R.error(1101, "订单为服务中才可取消");
        }
	}

	@Override
	public void distribution(JSONObject in) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refuseOrder(JSONObject p) {
		hysfwOrderDao.refuseOrder(p);
	}

	@Override
	public void deleteOverOrder(JSONObject in) {
		hysfwOrderDao.deleteOverOrder(in);
	}
}
