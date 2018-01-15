package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lcworld.consts.APPConstant;
import com.lcworld.dao.UserWallelogDao;
import com.lcworld.dao.UserWalletDao;
import com.lcworld.entity.PayinfoEntity;
import com.lcworld.entity.UserWallelogEntity;
import com.lcworld.entity.UserWalletEntity;
import com.lcworld.service.UserWallelogService;
import com.lcworld.service.UserWalletService;



@Service("userWalletService")
public class UserWalletServiceImpl implements UserWalletService {
	@Autowired
	private UserWalletDao userWalletDao;
	@Autowired
	private UserWallelogDao userWallelogDao;
	
	@Override
	public UserWalletEntity queryObject(Integer id){
		return userWalletDao.queryObject(id);
	}
	
	@Override
	public List<UserWalletEntity> queryList(Map<String, Object> map){
		return userWalletDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userWalletDao.queryTotal(map);
	}
	
	@Override
	public void save(UserWalletEntity userWallet){
		userWalletDao.save(userWallet);
	}
	
	@Override
	public void update(UserWalletEntity userWallet){
		userWalletDao.update(userWallet);
	}
	
	@Override
	public void delete(Integer id){
		userWalletDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userWalletDao.deleteBatch(ids);
	}

    @Override
    public void saveWalletPayed(UserWalletEntity wallet, PayinfoEntity pay) {
        userWalletDao.update(wallet);
        UserWallelogEntity log = new UserWallelogEntity();
        log.setCreatetime(new Date());
        log.setMoney(pay.getPaymoney());
        log.setOrderid(pay.getOrderid());
        log.setOrdertype(pay.getOrdertype());
        log.setOrdertypename(APPConstant.gettypeMap().get(pay.getOrdertype()));
        log.setUid(pay.getUid());
        log.setUsetype(2);//使用类型 1: 收入，2： 支出
        userWallelogDao.save(log);
        
    }

	@Override
	public UserWalletEntity queryByUid(Integer uid) {
		// TODO Auto-generated method stub
		return userWalletDao.queryByUid(uid);
	}
	
}
