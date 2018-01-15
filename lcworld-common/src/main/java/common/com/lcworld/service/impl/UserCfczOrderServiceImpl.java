package com.lcworld.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.UserCfczOrderDao;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.UserCfczOrderEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.UserCfczOrderService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("userCfczOrderService")
public class UserCfczOrderServiceImpl implements UserCfczOrderService {
	@Autowired
	private UserCfczOrderDao userCfczOrderDao;
	
	@Override
	public UserCfczOrderEntity queryObject(Integer id){
		return userCfczOrderDao.queryObject(id);
	}
	
	@Override
	public List<UserCfczOrderEntity> queryList(Map<String, Object> map){
		return userCfczOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userCfczOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(UserCfczOrderEntity userCfczOrder){
		userCfczOrderDao.save(userCfczOrder);
	}
	
	@Override
	public void update(UserCfczOrderEntity userCfczOrder){
		userCfczOrderDao.update(userCfczOrder);
	}
	
	@Override
	public void delete(Integer id){
		userCfczOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userCfczOrderDao.deleteBatch(ids);
	}
	@Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
	    UserCfczOrderEntity order;
        if(pay.getOrderid() != null&&pay.getOrderid().intValue()!=0){
            order = queryObject(pay.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", pay.getOrdercode());
            List<UserCfczOrderEntity> orderlist = queryList(params);
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
        payorder.setOrdertype(APPConstant.TYPE_BGYPFW);
        payorder.setPaymoney(order.getMoney());
        payorder.setStatus(order.getStatus());
        return payorder;
    }

    private boolean checkBeforePay(UserCfczOrderEntity order) {
        if(order == null || 2 == order.getStatus()){
            return false;
        }
        return true;
    }
    
    @Override
    public void savePayed(PayOrderVo orderVo) {
    	UserCfczOrderEntity order = queryObject(orderVo.getOrderid());
        order.setStatus(APPConstant.TYPE_CFCZ_ORDER_STATUS_PAYED);
        order.setPaytype(orderVo.getPaytype());
        update(order);
    }

	

	@Override
	public void deleteOrder(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public R addComment(CommentVo comment, MultipartFile[] files) {
		return null;
	}

	@Override
	public R cancelOrder(Integer id, String cancelreason) {
		// TODO Auto-generated method stub
		return null;
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
