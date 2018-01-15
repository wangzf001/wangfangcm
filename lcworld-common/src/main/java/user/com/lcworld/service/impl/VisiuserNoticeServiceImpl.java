package com.lcworld.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcworld.dao.VisiuserNoticeDao;
import com.lcworld.entity.VisiuserNoticeEntity;
import com.lcworld.service.VisiuserNoticeService;



@Service("visiuserNoticeService")
public class VisiuserNoticeServiceImpl implements VisiuserNoticeService {
	@Autowired
	private VisiuserNoticeDao visiuserNoticeDao;
	
	@Override
	public VisiuserNoticeEntity queryObject(Integer id){
		return visiuserNoticeDao.queryObject(id);
	}
	
	@Override
	public List<VisiuserNoticeEntity> queryList(Map<String, Object> map){
		return visiuserNoticeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return visiuserNoticeDao.queryTotal(map);
	}
	
	@Override
	public void save(VisiuserNoticeEntity visiuserNotice){
		visiuserNoticeDao.save(visiuserNotice);
	}
	
	@Override
	public void update(VisiuserNoticeEntity visiuserNotice){
		visiuserNoticeDao.update(visiuserNotice);
	}
	
	@Override
	public void delete(Integer id){
		visiuserNoticeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		visiuserNoticeDao.deleteBatch(ids);
	}


}
