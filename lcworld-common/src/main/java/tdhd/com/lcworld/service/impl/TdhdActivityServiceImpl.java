package com.lcworld.service.impl;
 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TdhdActivityDao;
import com.lcworld.dto.TdhdActivityDTO;
import com.lcworld.entity.TdhdActivityEntity;
import com.lcworld.service.TdhdActivityService;
import com.lcworld.utils.Query;



@Service("tdhdActivityService")
public class TdhdActivityServiceImpl implements TdhdActivityService {
	@Autowired
	private TdhdActivityDao tdhdActivityDao;
	
	@Override
	public TdhdActivityEntity queryObject(Integer aId){
		return tdhdActivityDao.queryObject(aId);
	}
	
	@Override
	public List<TdhdActivityEntity> queryList(Map<String, Object> map){
		return tdhdActivityDao.queryList(map);
	}

	@Override
	public List<TdhdActivityDTO> queryDTOList(Map<String, Object> map) {
		return tdhdActivityDao.queryDTOList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return tdhdActivityDao.queryTotal(map);
	}
	
	@Override
	public void save(TdhdActivityEntity tdhdActivity){
		tdhdActivityDao.save(tdhdActivity);
	}
	
	@Override
	public void update(TdhdActivityEntity tdhdActivity){
		tdhdActivityDao.update(tdhdActivity);
	}
	
	@Override
	public void delete(Integer aId){
		tdhdActivityDao.delete(aId);
	}
	
	@Override
	public void deleteBatch(Integer[] aIds){
		tdhdActivityDao.deleteBatch(aIds);
	}

    @Override
    public List<TdhdActivityDTO> queryActivityList(JSONObject q) {
       return tdhdActivityDao.queryActivityList(q);
    }

	@Override
	public List<TdhdActivityEntity> queryFavorList(Query params) {
		return queryList(params);
	}
	
}
