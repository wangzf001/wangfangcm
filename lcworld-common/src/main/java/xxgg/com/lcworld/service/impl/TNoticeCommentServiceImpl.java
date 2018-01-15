package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TNoticeCommentDao;
import com.lcworld.entity.TNoticeCommentEntity;
import com.lcworld.service.TNoticeCommentService;



@Service("tNoticeCommentService")
public class TNoticeCommentServiceImpl implements TNoticeCommentService {
	@Autowired
	private TNoticeCommentDao tNoticeCommentDao;
	
	@Override
	public TNoticeCommentEntity queryObject(Integer id){
		return tNoticeCommentDao.queryObject(id);
	}
	
	@Override
	public List<TNoticeCommentEntity> queryList(Map<String, Object> map){
		return tNoticeCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tNoticeCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(TNoticeCommentEntity tNoticeComment){
		tNoticeCommentDao.save(tNoticeComment);
	}
	
	@Override
	public void update(TNoticeCommentEntity tNoticeComment){
		tNoticeCommentDao.update(tNoticeComment);
	}
	
	@Override
	public void delete(Integer id){
		tNoticeCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tNoticeCommentDao.deleteBatch(ids);
	}
	
}
