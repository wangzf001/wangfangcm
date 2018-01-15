package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TsjyUsersearchhistoryDao;
import com.lcworld.entity.TsjyUsersearchhistoryEntity;
import com.lcworld.service.TsjyUsersearchhistoryService;



@Service("tsjyUsersearchhistoryService")
public class TsjyUsersearchhistoryServiceImpl implements TsjyUsersearchhistoryService {
	@Autowired
	private TsjyUsersearchhistoryDao tsjyUsersearchhistoryDao;
	
	@Override
	public TsjyUsersearchhistoryEntity queryObject(Integer id){
		return tsjyUsersearchhistoryDao.queryObject(id);
	}
	
	@Override
	public List<TsjyUsersearchhistoryEntity> queryList(Map<String, Object> map){
		return tsjyUsersearchhistoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tsjyUsersearchhistoryDao.queryTotal(map);
	}
	
	@Override
	public void save(TsjyUsersearchhistoryEntity tsjyUsersearchhistory){
		tsjyUsersearchhistoryDao.save(tsjyUsersearchhistory);
	}
	
	@Override
	public void update(TsjyUsersearchhistoryEntity tsjyUsersearchhistory){
		tsjyUsersearchhistoryDao.update(tsjyUsersearchhistory);
	}
	
	@Override
	public void delete(Integer id){
		tsjyUsersearchhistoryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tsjyUsersearchhistoryDao.deleteBatch(ids);
	}

    @Override
    public void savehistory(JSONObject params) {
        TsjyUsersearchhistoryEntity t = new TsjyUsersearchhistoryEntity();
        t.setCreatetime(new Date());
        t.setKey(params.getString("keyword"));
        t.setUid(params.getInteger("uid"));
        tsjyUsersearchhistoryDao.save(t);
        
        
    }

    @Override
    public void deleteByWhere(JSONObject params) {
        tsjyUsersearchhistoryDao.deleteByWhere(params);
        
    }
	
}
