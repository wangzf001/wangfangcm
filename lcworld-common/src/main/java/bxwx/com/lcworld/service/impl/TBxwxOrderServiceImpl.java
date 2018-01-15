package com.lcworld.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.BxwxAppointmentDao;
import com.lcworld.dao.ServiceWallelogDao;
import com.lcworld.dao.TBxwxMenderDao;
import com.lcworld.dao.TBxwxOrderDao;
import com.lcworld.dao.TBxwxOrderProcessesDao;
import com.lcworld.dto.BxwxOrderDTO;
import com.lcworld.entity.BxwxAppointmentEntity;
import com.lcworld.entity.HysfwAppointmentEntity;
import com.lcworld.entity.HysfwOrderEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.ServiceWallelogEntity;
import com.lcworld.entity.TBxwxCommentEntity;
import com.lcworld.entity.TBxwxOrderEntity;
import com.lcworld.entity.TBxwxOrderProcessesEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.BxwxAppointmentService;
import com.lcworld.service.TBxwxCommentService;
import com.lcworld.service.TBxwxOrderProcessesService;
import com.lcworld.service.TBxwxOrderService;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.RRException;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("tBxwxOrderService")
public class TBxwxOrderServiceImpl implements TBxwxOrderService {
	@Autowired
	private TBxwxOrderDao tBxwxOrderDao;
	@Autowired
	private TBxwxOrderProcessesDao tBxwxOrderProcessesDao;
	@Autowired
	private TBxwxOrderProcessesService tBxwxOrderProcessesService;
	@Autowired
	private TBxwxMenderDao tBxwxMenderDao;
	@Autowired
	private TBxwxCommentService tBxwxCommentService;
	@Autowired
	private BxwxAppointmentDao appointmentDao;
	@Autowired
	private ServiceWallelogDao serviceWallelogDao;
	@Override
	public TBxwxOrderEntity queryObject(Integer id){
		return tBxwxOrderDao.queryObject(id);
	}
	
	@Override
	public List<TBxwxOrderEntity> queryList(Map<String, Object> map){
		return tBxwxOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tBxwxOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(TBxwxOrderEntity tBxwxOrder){
		tBxwxOrderDao.save(tBxwxOrder);
	}
	
	@Override
	public void update(TBxwxOrderEntity tBxwxOrder){
		tBxwxOrderDao.update(tBxwxOrder);
	}
	
	@Override
	public void delete(Integer id){
		tBxwxOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tBxwxOrderDao.deleteBatch(ids);
	}

    @Override
    public List<BxwxOrderDTO> queryOrderlist(Query q) {
        return tBxwxOrderDao.queryOrderlist(q);
    }

    @Override
    public BxwxOrderDTO queryOrderDetail(JSONObject params) {
        return tBxwxOrderDao.queryOrderDetail(params);
    }

    @Override
    public void saveorder(TBxwxOrderEntity order) {
        //获取维修人员
//      
        order.setCreatetime(new Date());
        order.setOrderchange(1);
        order.setOrderstatus(1);
        order.setIsdel(0);
        order.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_BXFW));
        //保存订单
        save(order);
        //保存流程
        TBxwxOrderProcessesEntity pro = new TBxwxOrderProcessesEntity();
        pro.setStatus(1);//下单
        pro.setCreatetime(new Date());
        pro.setDetail(tBxwxOrderProcessesService.getDatail(pro));
        pro.setOrderid(order.getId());
        tBxwxOrderProcessesDao.save(pro);
        
//        JSONObject params = new JSONObject();
//        params.put("itemid", order.getMenditem());
//        List<Integer> mlist = tBxwxMenderDao.queryMenderlist(params);
//        order.setMenderid(mlist.get(RandomUtil.randomForSize(mlist.size())));
        TBxwxOrderProcessesEntity pro1 = new TBxwxOrderProcessesEntity();
        pro1.setStatus(2);//体检审核
        pro1.setCreatetime(new Date());
        pro1.setDetail(tBxwxOrderProcessesService.getDatail(pro1));
        pro1.setOrderid(order.getId());
        tBxwxOrderProcessesDao.save(pro1);
        
        
    }
    
    @Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
        TBxwxOrderEntity order;
        if(pay.getOrderid() != null&&pay.getOrderid().intValue()!=0){
            order = queryObject(pay.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", pay.getOrdercode());
            List<TBxwxOrderEntity> orderlist = queryList(params);
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
        payorder.setOrdertype(APPConstant.TYPE_BXFW);
        payorder.setPaymoney(order.getPrice());
        payorder.setStatus(order.getOrderstatus());
        return payorder;
    }

    private boolean checkBeforePay(TBxwxOrderEntity order) {
        if(order == null || 1 == order.getPaystatus()){
            return false;
        }
        return true;
    }
    

    @Override
    public void savePayed(PayOrderVo orderVo) {
        TBxwxOrderEntity order = queryObject(orderVo.getOrderid());
        order.setPaystatus(1);
        order.setPaytype(orderVo.getPaytype());
        update(order);
    }

    @Override
    public List<Map<String,Object>> queryordermapList(Query query) {
       return tBxwxOrderDao.queryordermapList(query);
    }

    @Override
    public int queryordermapTotal(Query query) {
        return tBxwxOrderDao.queryordermapTotal(query);
    }
    @Override
	public R cancelOrder(Integer id, String cancelreason) {
    	TBxwxOrderEntity order = queryObject(id);
    	if(order.getOrderstatus() ==1 || order.getOrderstatus() == 2  ){
            order.setOrderchange(1);
            order.setCancelreason(cancelreason);
            order.setOrderstatus(5);
            update(order);
            return R.ok();
        }else{
            return R.error(1101, "该订单已结束，取消失败");
        }
	}

	@Override
	public void deleteOrder(Integer id) {
		TBxwxOrderEntity order = new TBxwxOrderEntity();
		order.setId(id);
		order.setIsdel(1);
        update(order);
	}

	@Override
	public R addComment(CommentVo comment, MultipartFile[] files) {
		TBxwxCommentEntity commentEntity = new TBxwxCommentEntity();
		commentEntity.setAnonymous(comment.getAnonymous());
		commentEntity.setContent(comment.getContent());
		commentEntity.setOrderid(comment.getId());
		commentEntity.setProductscore(comment.getProductscore());
		commentEntity.setScore(comment.getScore());
		commentEntity.setServicescore(comment.getServicescore());
		TBxwxOrderEntity order = queryObject(comment.getId());
		commentEntity.setMenderid(order.getMenderid());
        if(order.getOrderstatus().intValue() ==4 ){
            JSONObject obj = WebUtils.uploadFiles(files, null, RedisConst.USER_YLFW_YYGH_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
            if (0 == obj.getIntValue("errorCode")) {
            	commentEntity.setImgs(obj.getString("data"));
            }
            commentEntity.setCreatetime(new Date());
            commentEntity.setUid(order.getUid());
            tBxwxCommentService.savecomment(commentEntity,order);
            
            
            order.setOrderstatus(3);
            update(order);
            return R.ok();
        }else if(order.getOrderstatus() == 3){
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
		return tBxwxOrderDao.queryOrderlist(q);
	}

	@Override
	public BxwxOrderDTO OrderDetail(JSONObject params) {
		 return tBxwxOrderDao.queryOrderDetail(params);
	}

	@Override
	public void deleteOrders(JSONObject in) {
		tBxwxOrderDao.deleteCancelOrder(in);
	}

	@Override
	public void finishOrders(JSONObject p) {
		p.put("orderid", p.get("id"));
		BxwxOrderDTO orderDetail = queryOrderDetail(p);
		Integer uid = orderDetail.getUid();
		String mendcontent = orderDetail.getMendcontent();
		String createtime = orderDetail.getCreatetime();
		ServiceWallelogEntity serviceWallelogEntity = new ServiceWallelogEntity();
		serviceWallelogEntity.setUid(uid);
		serviceWallelogEntity.setCreatTime(createtime);
		serviceWallelogEntity.setContent(mendcontent);
		serviceWallelogEntity.setMoney("0");
		serviceWallelogEntity.setServiceType(1);
		serviceWallelogEntity.setUseType(1);
		serviceWallelogDao.save(serviceWallelogEntity);
		
		TBxwxOrderEntity order = new TBxwxOrderEntity();
		order.setId(p.getInteger("id"));
		order.setOrderstatus(4);
        update(order);
	}

	@Override
	public void instantService(String id ,Integer uid) {
		synchronized (this) {
			int orderid = Integer.parseInt(id);
			// 查询该订单状态，防止重复接单
			TBxwxOrderEntity queryObject = queryObject(orderid);
			if(queryObject.getOrderstatus().intValue() != 1){
				switch (queryObject.getOrderstatus().intValue()) {
					case 2:
						throw new RRException("操作失败，订单状态：【已接单】");
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
			
			queryObject = new TBxwxOrderEntity();
			queryObject.setId(orderid);
			queryObject.setOrderstatus(2);
			queryObject.setMenderid(uid);
	        update(queryObject);
		}
	}

	@Override
	public R cancelOrders(JSONObject in) {
		TBxwxOrderEntity order = queryObject(in.getInteger("id"));
		
    	if(order.getOrderstatus() == 2){
            tBxwxOrderDao.cancelOrder(in);
            
            in.put("orderId", in.get("id"));
    		tBxwxOrderDao.deleteProcesses(in);
            
            // 保存维修人员取消记录
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("order_id", in.get("id"));
            param.put("menderid", in.get("menderid"));
            param.put("cancel_time", new Date());
            param.put("cancel_reason", in.get("cancelreason"));
            tBxwxOrderDao.saveOrderCancel(param);
            
            return R.ok();
        }else{
            return R.error(1101, "已接单的状态下才可取消");
        }
	}

	@Override
	public void distribution(JSONObject in) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public R generateOrder(TBxwxOrderEntity order, List<BxwxAppointmentEntity> appointmentList) {
		//先判断预约是否冲突
		Map<String,Object> params = new HashMap<>();
		for (BxwxAppointmentEntity appointment : appointmentList) {
			String date = DateUtils.format(appointment.getDate(), "yyyy-MM-dd");
			String intervalids = appointment.getIntervalids();
			String[] idArr = {};
			if (ValidateUtil.isValid(intervalids)) {
				idArr = intervalids.split(",");
			}
			params.put("date", date);
			params.put("ids", idArr);
			params.put("status", 1);
			List<BxwxAppointmentEntity> list = appointmentDao.queryList(params);
			if (list.size()!=0) {
				return R.error(1,"预约时间冲突");
			}
		}
		order.setCreatetime(new Date());
		order.setOrderstatus(APPConstant.TYPE_ORDER_STATUS_ORDERED);
		order.setIsdel(0);
		save(order);
		for (int i = 0; i < appointmentList.size(); i++) {
			appointmentList.get(i).setOrderid(order.getId());;
			appointmentList.get(i).setStatus(1);
		}
		//保存预约信息
		appointmentDao.saveBatch(appointmentList);
		return R.ok();
	}

	@Override
	public R orderCancel(JSONObject params) {
		String reasonContent = params.getString("reasonContent");
		List<TBxwxOrderEntity> orderList = queryList(params);
		if (ValidateUtil.isValid(orderList)) {
			TBxwxOrderEntity order = orderList.get(0);
			Integer status = order.getOrderstatus();
			if (status!=APPConstant.TYPE_ORDER_STATUS_ORDERED) {
				return R.error(2,"非法操作");
			}
			order.setOrderstatus(APPConstant.TYPE_ORDER_STATUS_CANCEL);
			//添加退款原因
			order.setCancelreason(reasonContent);
			update(order);
			//清除预约
			appointmentDao.clearAppointment(order.getId());
		}
		return R.ok();
	}

	@Override
	public void refuseOrder(JSONObject p) {
		tBxwxOrderDao.updateOrderMenderIdNull(p);
		
		tBxwxOrderDao.deleteProcesses(p);
		
		tBxwxOrderDao.refuseOrder(p);
	}

	@Override
	public void deleteOverOrder(JSONObject in) {
		tBxwxOrderDao.deleteOverOrder(in);
	}
 
}
