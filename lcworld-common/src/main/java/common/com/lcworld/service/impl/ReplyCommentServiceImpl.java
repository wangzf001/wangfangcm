package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.ReplyCommentDao;
import com.lcworld.entity.ReplyCommentEntity;
import com.lcworld.service.ReplyCommentService;



@Service("replyCommentService")
public class ReplyCommentServiceImpl implements ReplyCommentService {
	@Autowired
	private ReplyCommentDao replyCommentDao;
	
	@Override
	public ReplyCommentEntity queryObject(Integer id){
		return replyCommentDao.queryObject(id);
	}
	
	@Override
	public List<ReplyCommentEntity> queryList(Map<String, Object> map){
		return replyCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return replyCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(ReplyCommentEntity replyComment){
		replyCommentDao.save(replyComment);
	}
	
	@Override
	public void update(ReplyCommentEntity replyComment){
		replyCommentDao.update(replyComment);
	}
	
	@Override
	public void delete(Integer id){
		replyCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		replyCommentDao.deleteBatch(ids);
	}
	
}
