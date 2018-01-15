package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwApplyproductDao;
import com.lcworld.entity.BgypfwApplyproductEntity;
import com.lcworld.service.BgypfwApplyproductService;



@Service("bgypfwApplyproductService")
public class BgypfwApplyproductServiceImpl implements BgypfwApplyproductService {
	@Autowired
	private BgypfwApplyproductDao bgypfwApplyproductDao;
	
	@Override
	public BgypfwApplyproductEntity queryObject(Integer id){
		return bgypfwApplyproductDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwApplyproductEntity> queryList(Map<String, Object> map){
		return bgypfwApplyproductDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwApplyproductDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwApplyproductEntity bgypfwApplyproduct){
		bgypfwApplyproductDao.save(bgypfwApplyproduct);
	}
	
	@Override
	public void update(BgypfwApplyproductEntity bgypfwApplyproduct){
		bgypfwApplyproductDao.update(bgypfwApplyproduct);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwApplyproductDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwApplyproductDao.deleteBatch(ids);
	}
	
}
