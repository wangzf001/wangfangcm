package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.WlryCommentDao;
import com.lcworld.entity.LffwBarberEntity;
import com.lcworld.entity.TVisitUserEntity;
import com.lcworld.entity.WlryCommentEntity;
import com.lcworld.service.TVisitUserService;
import com.lcworld.service.WlryCommentService;



@Service("wlryCommentService")
public class WlryCommentServiceImpl implements WlryCommentService {
	@Autowired
	private WlryCommentDao wlryCommentDao;
	@Autowired
	private TVisitUserService tVisitUserService;
	
	@Override
	public WlryCommentEntity queryObject(Integer id){
		return wlryCommentDao.queryObject(id);
	}
	
	@Override
	public List<WlryCommentEntity> queryList(Map<String, Object> map){
		return wlryCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return wlryCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(WlryCommentEntity wlryComment){
		wlryCommentDao.save(wlryComment);
	}
	
	@Override
	public void update(WlryCommentEntity wlryComment){
		wlryCommentDao.update(wlryComment);
	}
	
	@Override
	public void delete(Integer id){
		wlryCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		wlryCommentDao.deleteBatch(ids);
	}

    @Override
    public void savecomment(WlryCommentEntity comment, TVisitUserEntity order) {
        wlryCommentDao.save(comment);
        //设置已评论
        order.setStatus(3);
        tVisitUserService.update(order);   
        
    }
	
}
