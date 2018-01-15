package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lcworld.dao.UserPurchaseLimilogDao;
import com.lcworld.entity.UserPurchaseLimilogEntity;
import com.lcworld.service.UserPurchaseLimilogService;



@Service("userPurchaseLimilogService")
public class UserPurchaseLimilogServiceImpl implements UserPurchaseLimilogService {
	@Autowired
	private UserPurchaseLimilogDao userPurchaseLimilogDao;
	
	@Override
	public UserPurchaseLimilogEntity queryObject(Integer id){
		return userPurchaseLimilogDao.queryObject(id);
	}
	
	@Override
	public List<UserPurchaseLimilogEntity> queryList(Map<String, Object> map){
		return userPurchaseLimilogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userPurchaseLimilogDao.queryTotal(map);
	}
	
	@Override
	public void save(UserPurchaseLimilogEntity userPurchaseLimilog){
		userPurchaseLimilogDao.save(userPurchaseLimilog);
	}
	
	@Override
	public void update(UserPurchaseLimilogEntity userPurchaseLimilog){
		userPurchaseLimilogDao.update(userPurchaseLimilog);
	}
	
	@Override
	public void delete(Integer id){
		userPurchaseLimilogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userPurchaseLimilogDao.deleteBatch(ids);
	}

    @Override
    public void savelimitlog(Integer limitid,Integer uid, int servicecode, BigDecimal paymoney,int usetype) {
        UserPurchaseLimilogEntity log = new UserPurchaseLimilogEntity();
        log.setUid(uid);
        log.setCreatetime(new Date());
        log.setMoney(paymoney.toString());
        log.setServicecode(String.valueOf(servicecode));
        log.setLimitid(limitid);
        log.setUsetype(usetype);
        userPurchaseLimilogDao.save(log);
        
        
    }
	
}
