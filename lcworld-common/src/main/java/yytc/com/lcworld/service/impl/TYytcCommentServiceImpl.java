package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TYytcCommentDao;
import com.lcworld.entity.TYytcCommentEntity;
import com.lcworld.service.TYytcCommentService;



@Service("tYytcCommentService")
public class TYytcCommentServiceImpl implements TYytcCommentService {
	@Autowired
	private TYytcCommentDao tYytcCommentDao;
	
	@Override
	public TYytcCommentEntity queryObject(Integer id){
		return tYytcCommentDao.queryObject(id);
	}
	
	@Override
	public List<TYytcCommentEntity> queryList(Map<String, Object> map){
		return tYytcCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tYytcCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(TYytcCommentEntity tYytcComment){
		tYytcCommentDao.save(tYytcComment);
	}
	
	@Override
	public void update(TYytcCommentEntity tYytcComment){
		tYytcCommentDao.update(tYytcComment);
	}
	
	@Override
	public void delete(Integer id){
		tYytcCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tYytcCommentDao.deleteBatch(ids);
	}
	
}
