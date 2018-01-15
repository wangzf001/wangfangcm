package com.lcworld.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcworld.dao.LffwBarberDao;
import com.lcworld.dao.LffwCommentDao;
import com.lcworld.dao.LffwOrderDao;
import com.lcworld.dto.LffwCommentDTO;
import com.lcworld.entity.LffwBarberEntity;
import com.lcworld.entity.LffwCommentEntity;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.service.LffwCommentService;
import com.lcworld.utils.Query;



@Service("lffwCommentService")
public class LffwCommentServiceImpl implements LffwCommentService {
	@Autowired
	private LffwCommentDao lffwCommentDao;
	@Autowired
	private LffwBarberDao lffwBarberDao;
	@Autowired
	private LffwOrderDao lffwOrderDao;
	
	@Override
	public LffwCommentEntity queryObject(Integer id){
		return lffwCommentDao.queryObject(id);
	}
	
	@Override
	public List<LffwCommentEntity> queryList(Map<String, Object> map){
		return lffwCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwCommentEntity lffwComment){
		lffwCommentDao.save(lffwComment);
	}
	
	@Override
	public void update(LffwCommentEntity lffwComment){
		lffwCommentDao.update(lffwComment);
	}
	
	@Override
	public void delete(Integer id){
		lffwCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwCommentDao.deleteBatch(ids);
	}

    @Override
    public void savecomment(LffwCommentEntity comment, LffwOrderEntity order) {
        lffwCommentDao.save(comment);
        
        LffwBarberEntity barber = new LffwBarberEntity();
        Double score = lffwCommentDao.queryScore(comment.getBarberid());
        barber.setScore(score == null?0:score);
        barber.setId(comment.getBarberid());
        lffwBarberDao.update(barber); 
     
        //设置已评论
        order.setChanges(1);
        order.setStatus(3);
        lffwOrderDao.update(order);   
        
    }

    @Override
    public List<LffwCommentDTO> queryCommentList(Query q) {
       return lffwCommentDao.queryCommentList(q);
    }

    @Override
    public LffwCommentDTO queryLffwComment(Integer id) {
       return lffwCommentDao.queryLffwComment(id);
    }
}
