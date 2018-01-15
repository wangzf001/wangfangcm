package com.lcworld.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcworld.dao.TsjyfwBookDao;
import com.lcworld.dao.TsjyfwCommentDao;
import com.lcworld.dao.TsjyfwOrderDao;
import com.lcworld.entity.TsjyfwBookEntity;
import com.lcworld.entity.TsjyfwCommentEntity;
import com.lcworld.entity.TsjyfwOrderEntity;
import com.lcworld.service.TsjyfwCommentService;



@Service("tsjyfwCommentService")
public class TsjyfwCommentServiceImpl implements TsjyfwCommentService {
	@Autowired
	private TsjyfwCommentDao tsjyfwCommentDao;
	@Autowired
	private TsjyfwBookDao TsjyfwBookDao;
	@Autowired
	private TsjyfwOrderDao tsjyfwOrderDao;
	
	@Override
	public TsjyfwCommentEntity queryObject(Integer id){
		return tsjyfwCommentDao.queryObject(id);
	}
	
	@Override
	public List<TsjyfwCommentEntity> queryList(Map<String, Object> map){
		return tsjyfwCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tsjyfwCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(TsjyfwCommentEntity tsjyfwComment){
		tsjyfwCommentDao.save(tsjyfwComment);
	}
	
	@Override
	public void update(TsjyfwCommentEntity tsjyfwComment){
		tsjyfwCommentDao.update(tsjyfwComment);
	}
	
	@Override
	public void delete(Integer id){
		tsjyfwCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tsjyfwCommentDao.deleteBatch(ids);
	}

    @Override 
    public void savecomment(TsjyfwCommentEntity comment, TsjyfwOrderEntity order) {
        save(comment);
        TsjyfwBookEntity book = new TsjyfwBookEntity();
        Double score = tsjyfwCommentDao.queryScore(comment.getBookid());
        book.setScore(score == null?0:score);
        book.setId(comment.getBookid());
        TsjyfwBookDao.update(book); 
     
        //设置已评论
        order.setChanges(1);
        order.setStatus(4);
        tsjyfwOrderDao.update(order);   
    }
}
