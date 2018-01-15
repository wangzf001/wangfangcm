package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TUserDao;
import com.lcworld.dao.UserWallelogDao;
import com.lcworld.dao.UserWalletDao;
import com.lcworld.dto.UserDTO;
import com.lcworld.entity.TUserEntity;
import com.lcworld.entity.UserWallelogEntity;
import com.lcworld.entity.UserWalletEntity;
import com.lcworld.service.TUserService;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;



@Service("tUserService")
public class TUserServiceImpl implements TUserService {
	@Autowired
	private TUserDao tUserDao;
	@Autowired
	private UserWalletDao userWalletDao;
	
	@Override
	public TUserEntity queryObject(Integer id){
		return tUserDao.queryObject(id);
	}
	
	@Override
	public List<TUserEntity> queryList(Map<String, Object> map){
		return tUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tUserDao.queryTotal(map);
	}
	
	@Override
	public void save(TUserEntity tUser){
		tUserDao.save(tUser);
	}
	
	@Override
	public void update(TUserEntity tUser){
		tUserDao.update(tUser);
	}
	
	@Override
	public void delete(Integer id){
		tUserDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tUserDao.deleteBatch(ids);
	}

    @Override
    public TUserEntity queryByUserName(String userName) {
        Map<String,Object> params = new HashMap<String,Object>(); 
        params.put("userName", userName);
        List<TUserEntity> ulist = tUserDao.queryList(params);
        return ValidateUtil.isValid(ulist)?ulist.get(0):null;
    }

    @Override
    public TUserEntity getUserByThird(Integer type, String thirdNum) {
        JSONObject params = new JSONObject();
        params.put("logintype", type);
        params.put("thirdNum", thirdNum);
        List<TUserEntity> ulist = tUserDao.queryList(params);
        if(ValidateUtil.isValid(ulist)){
            TUserEntity user = ulist.get(0);
            if(user.getDeadline().getTime() > new Date().getTime() &&1 ==  user.getValid() ){
                return user;
            }
            
        }
        return null;
    }

    @Override
    public void setThirdNumByType(TUserEntity u, Integer type,String thirdNum) {
        switch(type.intValue()){
        case 1:  u.setQqnum(thirdNum);break;
        case 2 : u.setWbnum(thirdNum);break;
        case 3 : u.setWxnum(thirdNum);break;
        default : ;
        }
    }

    @Override
    public String getThirdnumByType(TUserEntity u,Integer type) {
        String thirdNum=null;
        switch(type.intValue()){
        case 1:  thirdNum = u.getQqnum();break;
        case 2 : thirdNum = u.getWbnum();break;
        case 3 : thirdNum = u.getWxnum();break;
        default : ;
        }
        return thirdNum;
    }

    @Override
    public void saveUser(TUserEntity tuser) {
        save(tuser);
        //save walllet 
        UserWalletEntity w = new UserWalletEntity();
        w.setUid(tuser.getId());
        w.setRemain(new BigDecimal(0));
        w.setPublicRemain(new BigDecimal(0));
        userWalletDao.save(w);
        
    }

    @Override
    public List<UserDTO> queryUserList(Query query) {
        return tUserDao.queryUserList(query);
    }

    @Override
    public int queryUserTotal(Query query) {
        return tUserDao.queryUserTotal(query);
    }

	@Override
	public List<Map<String, Object>> queryPositionList() {
		return tUserDao.queryPositionList();
	}

    @Override
    public List<UserDTO> queryUserList1(Query query) {
        return tUserDao.queryUserList1(query);
    }

    @Override
    public int queryUserTotal1(Query query) {
        return tUserDao.queryUserTotal1(query);
    }

    @Override
    public TUserEntity queryByAvaliableUid(int uid) {
        return tUserDao.queryByAvaliableUid(uid);
    }

	
}
