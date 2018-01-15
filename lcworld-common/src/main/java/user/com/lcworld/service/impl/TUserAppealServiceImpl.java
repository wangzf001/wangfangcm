package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TUserAppealDao;
import com.lcworld.entity.TUserAppealEntity;
import com.lcworld.service.TUserAppealService;



@Service("tUserAppealService")
public class TUserAppealServiceImpl implements TUserAppealService {
	@Autowired
	private TUserAppealDao tUserAppealDao;
	
	@Override
	public TUserAppealEntity queryObject(Integer id){
		return tUserAppealDao.queryObject(id);
	}
	
	@Override
	public List<TUserAppealEntity> queryList(Map<String, Object> map){
		return tUserAppealDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tUserAppealDao.queryTotal(map);
	}
	
	@Override
	public void save(TUserAppealEntity tUserAppeal){
		tUserAppealDao.save(tUserAppeal);
	}
	
	@Override
	public void update(TUserAppealEntity tUserAppeal){
		tUserAppealDao.update(tUserAppeal);
	}
	
	@Override
	public void delete(Integer id){
		tUserAppealDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tUserAppealDao.deleteBatch(ids);
	}
	
}
