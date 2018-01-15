package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.InfoCommentDao;
import com.lcworld.entity.InfoCommentEntity;
import com.lcworld.service.InfoCommentService;
import com.lcworld.utils.Query;



@Service("infoCommentService")
public class InfoCommentServiceImpl implements InfoCommentService {
	@Autowired
	private InfoCommentDao infoCommentDao;
	
	@Override
	public InfoCommentEntity queryObject(Integer id){
		return infoCommentDao.queryObject(id);
	}
	
	@Override
	public List<InfoCommentEntity> queryList(Map<String, Object> map){
		return infoCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return infoCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(InfoCommentEntity infoComment){
		infoCommentDao.save(infoComment);
	}
	
	@Override
	public void update(InfoCommentEntity infoComment){
		infoCommentDao.update(infoComment);
	}
	
	@Override
	public void delete(Integer id){
		infoCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		infoCommentDao.deleteBatch(ids);
	}

    @Override
    public List<Map<String, Object>> querycommentList(Query query) {
       return infoCommentDao.querycommentList(query);
    }

    @Override
    public int querycommentTotal(Query query) {
        return infoCommentDao.querycommentTotal(query);
    }

   
	
}
