package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TUserAdviceDao;
import com.lcworld.entity.TUserAdviceEntity;
import com.lcworld.service.TUserAdviceService;



@Service("tUserAdviceService")
public class TUserAdviceServiceImpl implements TUserAdviceService {
	@Autowired
	private TUserAdviceDao tUserAdviceDao;
	
	@Override
	public TUserAdviceEntity queryObject(Integer id){
		return tUserAdviceDao.queryObject(id);
	}
	
	@Override
	public List<TUserAdviceEntity> queryList(Map<String, Object> map){
		return tUserAdviceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tUserAdviceDao.queryTotal(map);
	}
	
	@Override
	public void save(TUserAdviceEntity tUserAdvice){
		tUserAdviceDao.save(tUserAdvice);
	}
	
	@Override
	public void update(TUserAdviceEntity tUserAdvice){
		tUserAdviceDao.update(tUserAdvice);
	}
	
	@Override
	public void delete(Integer id){
		tUserAdviceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tUserAdviceDao.deleteBatch(ids);
	}
	
}
