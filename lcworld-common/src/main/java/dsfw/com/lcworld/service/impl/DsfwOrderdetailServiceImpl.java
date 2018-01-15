package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.DsfwOrderdetailDao;
import com.lcworld.entity.DsfwOrderdetailEntity;
import com.lcworld.service.DsfwOrderdetailService;



@Service("dsfwOrderdetailService")
public class DsfwOrderdetailServiceImpl implements DsfwOrderdetailService {
	@Autowired
	private DsfwOrderdetailDao dsfwOrderdetailDao;
	
	@Override
	public DsfwOrderdetailEntity queryObject(Integer id){
		return dsfwOrderdetailDao.queryObject(id);
	}
	
	@Override
	public List<DsfwOrderdetailEntity> queryList(Map<String, Object> map){
		return dsfwOrderdetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dsfwOrderdetailDao.queryTotal(map);
	}
	
	@Override
	public void save(DsfwOrderdetailEntity dsfwOrderdetail){
		dsfwOrderdetailDao.save(dsfwOrderdetail);
	}
	
	@Override
	public void update(DsfwOrderdetailEntity dsfwOrderdetail){
		dsfwOrderdetailDao.update(dsfwOrderdetail);
	}
	
	@Override
	public void delete(Integer id){
		dsfwOrderdetailDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dsfwOrderdetailDao.deleteBatch(ids);
	}
	
}
