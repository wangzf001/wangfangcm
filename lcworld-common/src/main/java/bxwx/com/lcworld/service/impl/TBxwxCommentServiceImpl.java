package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TBxwxCommentDao;
import com.lcworld.dao.TBxwxMenderDao;
import com.lcworld.dao.TBxwxOrderDao;
import com.lcworld.entity.TBxwxCommentEntity;
import com.lcworld.entity.TBxwxMenderEntity;
import com.lcworld.entity.TBxwxOrderEntity;
import com.lcworld.service.TBxwxCommentService;



@Service("tBxwxCommentService")
public class TBxwxCommentServiceImpl implements TBxwxCommentService {
	@Autowired
	private TBxwxCommentDao tBxwxCommentDao;
	@Autowired
	private TBxwxOrderDao tBxwxOrderDao;
	@Autowired
	private TBxwxMenderDao tBxwxMenderDao;
	
	@Override
	public TBxwxCommentEntity queryObject(Integer id){
		return tBxwxCommentDao.queryObject(id);
	}
	
	@Override
	public List<TBxwxCommentEntity> queryList(Map<String, Object> map){
		return tBxwxCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tBxwxCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(TBxwxCommentEntity tBxwxComment){
		tBxwxCommentDao.save(tBxwxComment);
	}
	
	@Override
	public void update(TBxwxCommentEntity tBxwxComment){
		tBxwxCommentDao.update(tBxwxComment);
	}
	
	@Override
	public void delete(Integer id){
		tBxwxCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tBxwxCommentDao.deleteBatch(ids);
	}

    @Override
    public void savecomment(TBxwxCommentEntity comment, TBxwxOrderEntity order) {
        tBxwxCommentDao.save(comment);
        TBxwxMenderEntity mender = new TBxwxMenderEntity();
        mender.setScore(tBxwxCommentDao.queryScore(comment.getMenderid()));
        mender.setId(comment.getMenderid());
        tBxwxMenderDao.update(mender);
        //设置已评论
        order.setOrderchange(1);
        order.setOrderstatus(3);
        tBxwxOrderDao.update(order);
        
    }
	
}
