package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.BgypfwSkuCataInfoDao;
import com.lcworld.dto.BgypSkuDto;
import com.lcworld.entity.BgypfwSkuCataInfoEntity;
import com.lcworld.service.BgypfwSkuCataInfoService;



@Service("bgypfwSkuCataInfoService")
public class BgypfwSkuCataInfoServiceImpl implements BgypfwSkuCataInfoService {
	@Autowired
	private BgypfwSkuCataInfoDao bgypfwSkuCataInfoDao;
	
	@Override
	public BgypfwSkuCataInfoEntity queryObject(Integer id){
		return bgypfwSkuCataInfoDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwSkuCataInfoEntity> queryList(Map<String, Object> map){
		return bgypfwSkuCataInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwSkuCataInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwSkuCataInfoEntity bgypfwSkuCataInfo){
		bgypfwSkuCataInfoDao.save(bgypfwSkuCataInfo);
	}
	
	@Override
	public void update(BgypfwSkuCataInfoEntity bgypfwSkuCataInfo){
		bgypfwSkuCataInfoDao.update(bgypfwSkuCataInfo);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwSkuCataInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwSkuCataInfoDao.deleteBatch(ids);
	}

	@Override
	public List<BgypSkuDto> querySkuList(JSONObject params) {
		
		return bgypfwSkuCataInfoDao.querySkuList(params);
	}

	@Override
	public void saveBench(List<BgypfwSkuCataInfoEntity> catainfolist) {
		bgypfwSkuCataInfoDao.saveBatch(catainfolist);
		
	}

	@Override
	public void deletebyskuid(Integer skuid) {
		bgypfwSkuCataInfoDao.deletebyskuid(skuid);
	}
	
}
