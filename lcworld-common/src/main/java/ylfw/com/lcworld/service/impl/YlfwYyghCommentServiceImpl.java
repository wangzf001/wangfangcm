package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.YlfwYyghCommentDao;
import com.lcworld.dao.YlfwYyghDoctorDao;
import com.lcworld.dao.YlfwYyghOrderDao;
import com.lcworld.dto.YlfwCommentDTO;
import com.lcworld.entity.YlfwYyghCommentEntity;
import com.lcworld.entity.YlfwYyghDoctorEntity;
import com.lcworld.entity.YlfwYyghOrderEntity;
import com.lcworld.service.YlfwYyghCommentService;
import com.lcworld.utils.Query;



@Service("ylfwYyghCommentService")
public class YlfwYyghCommentServiceImpl implements YlfwYyghCommentService {
	@Autowired
	private YlfwYyghCommentDao ylfwYyghCommentDao;
	@Autowired 
	private YlfwYyghOrderDao ylfwYyghOrderDao;
	@Autowired
	private YlfwYyghDoctorDao ylfwYyghDoctorDao;
	
	@Override
	public YlfwYyghCommentEntity queryObject(Integer id){
		return ylfwYyghCommentDao.queryObject(id);
	}
	
	@Override
	public List<YlfwYyghCommentEntity> queryList(Map<String, Object> map){
		return ylfwYyghCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwYyghCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwYyghCommentEntity ylfwYyghComment){
		ylfwYyghCommentDao.save(ylfwYyghComment);
	}
	
	@Override
	public void update(YlfwYyghCommentEntity ylfwYyghComment){
		ylfwYyghCommentDao.update(ylfwYyghComment);
	}
	
	@Override
	public void delete(Integer id){
		ylfwYyghCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwYyghCommentDao.deleteBatch(ids);
	}

    @Override
    public void savecomment(YlfwYyghCommentEntity comment, YlfwYyghOrderEntity order) {
        ylfwYyghCommentDao.save(comment);
        
        YlfwYyghDoctorEntity doctor = new YlfwYyghDoctorEntity();
        doctor.setId(comment.getDoctorid());
        Double score = ylfwYyghCommentDao.getScore(comment.getDoctorid());
        doctor.setScore(score==null?0:score);
        ylfwYyghDoctorDao.update(doctor);
        //设置已评论
        order.setChanges(1);
        order.setStatus(3);
        ylfwYyghOrderDao.update(order);        
    }

    @Override
    public List<YlfwCommentDTO> queryCommentList(Query q) {
        return ylfwYyghCommentDao.queryCommentList(q);
    }
}
