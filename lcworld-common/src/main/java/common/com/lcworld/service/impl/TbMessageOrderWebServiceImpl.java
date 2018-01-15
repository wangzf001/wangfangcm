package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TbMessageOrderWebDao;
import com.lcworld.entity.TbMessageOrderWebEntity;
import com.lcworld.service.TbMessageOrderWebService;



@Service("tbMessageOrderWebService")
public class TbMessageOrderWebServiceImpl implements TbMessageOrderWebService {
	@Autowired
	private TbMessageOrderWebDao tbMessageOrderWebDao;
	
	@Override
	public TbMessageOrderWebEntity queryObject(Long autoId){
		return tbMessageOrderWebDao.queryObject(autoId);
	}
	
	@Override
	public List<TbMessageOrderWebEntity> queryList(Map<String, Object> map){
		return tbMessageOrderWebDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tbMessageOrderWebDao.queryTotal(map);
	}
	
	@Override
	public void save(TbMessageOrderWebEntity tbMessageOrderWeb){
		tbMessageOrderWebDao.save(tbMessageOrderWeb);
	}
	
	@Override
	public void update(TbMessageOrderWebEntity tbMessageOrderWeb){
		tbMessageOrderWebDao.update(tbMessageOrderWeb);
	}
	
	@Override
	public void delete(Long autoId){
		tbMessageOrderWebDao.delete(autoId);
	}
	
	@Override
	public void deleteBatch(Long[] autoIds){
		tbMessageOrderWebDao.deleteBatch(autoIds);
	}
	
}
