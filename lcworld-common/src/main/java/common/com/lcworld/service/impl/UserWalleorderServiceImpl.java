package com.lcworld.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.UserDepositDao;
import com.lcworld.dao.UserWalleorderDao;
import com.lcworld.dao.UserWalletDao;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.UserDepositEntity;
import com.lcworld.entity.UserWalleorderEntity;
import com.lcworld.entity.UserWalletEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.UserWalleorderService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("userWalleorderService")
public class UserWalleorderServiceImpl implements UserWalleorderService {
	@Autowired
	private UserWalleorderDao userWalleorderDao;
	@Autowired
	private UserWalletDao userWalletDao;
	@Autowired
	private UserDepositDao userDepositDao;
	
	@Override
	public UserWalleorderEntity queryObject(Integer id){
		return userWalleorderDao.queryObject(id);
	}
	
	@Override
	public List<UserWalleorderEntity> queryList(Map<String, Object> map){
		return userWalleorderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userWalleorderDao.queryTotal(map);
	}
	
	@Override
	public void save(UserWalleorderEntity userWalleorder){
		userWalleorderDao.save(userWalleorder);
	}
	
	@Override
	public void update(UserWalleorderEntity userWalleorder){
		userWalleorderDao.update(userWalleorder);
	}
	
	@Override
	public void delete(Integer id){
		userWalleorderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userWalleorderDao.deleteBatch(ids);
	}

	@Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
	    UserWalleorderEntity order;
        if(pay.getOrderid() != null&&pay.getOrderid().intValue()!=0){
            order = queryObject(pay.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", pay.getOrdercode());
            List<UserWalleorderEntity> orderlist = queryList(params);
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
        payorder.setOrdertype(order.getOrdertype());
        payorder.setPaymoney(order.getMoney());
        payorder.setStatus(order.getStatus());
        return payorder;
    }

    private boolean checkBeforePay(UserWalleorderEntity order) {
        if(order == null || 2 == order.getStatus() ){
            return false;
        }
        return true;
    }
    

    @Override
    public void savePayed(PayOrderVo orderVo) {
    	UserWalleorderEntity order = queryObject(orderVo.getOrderid());
        order.setStatus(APPConstant.TYPE_WALLET_ORDER_STATUS_PAYED);// 订单状态 1:下单，2：已支付，3：失败
        order.setPaytype(orderVo.getPaytype());// 支付类型 1: 支付宝，2：微信
        if (order.getOrdertype().intValue()==APPConstant.TYPE_DEPOSIT_DS) {// 订单类型19钱包20押金
			//保存用户押金
        	UserDepositEntity deposit = new UserDepositEntity();
        	deposit.setUid(order.getUid());
        	deposit.setDeposit(order.getMoney());
        	deposit.setType(order.getOrdertype());
        	userDepositDao.save(deposit);
		}
        
        update(order);
        
        // 更新用户余额
		userWalletDao.updateRemainByUid(order);
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
