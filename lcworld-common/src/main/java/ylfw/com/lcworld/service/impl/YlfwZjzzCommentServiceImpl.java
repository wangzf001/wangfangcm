package com.lcworld.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcworld.dao.YlfwZjzzCommentDao;
import com.lcworld.dao.YlfwZjzzExpertDao;
import com.lcworld.dao.YlfwZjzzOrderDao;
import com.lcworld.dto.YlfwCommentDTO;
import com.lcworld.entity.YlfwZjzzCommentEntity;
import com.lcworld.entity.YlfwZjzzExpertEntity;
import com.lcworld.entity.YlfwZjzzOrderEntity;
import com.lcworld.service.YlfwZjzzCommentService;
import com.lcworld.utils.Query;



@Service("ylfwZjzzCommentService")
public class YlfwZjzzCommentServiceImpl implements YlfwZjzzCommentService {
	@Autowired
	private YlfwZjzzCommentDao ylfwZjzzCommentDao;
	@Autowired
	private YlfwZjzzExpertDao ylfwZjzzExpertDao;
	@Autowired
	private YlfwZjzzOrderDao ylfwZjzzOrderDao;
	
	@Override
	public YlfwZjzzCommentEntity queryObject(Integer id){
		return ylfwZjzzCommentDao.queryObject(id);
	}
	
	@Override
	public List<YlfwZjzzCommentEntity> queryList(Map<String, Object> map){
		return ylfwZjzzCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwZjzzCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwZjzzCommentEntity ylfwZjzzComment){
		ylfwZjzzCommentDao.save(ylfwZjzzComment);
	}
	
	@Override
	public void update(YlfwZjzzCommentEntity ylfwZjzzComment){
		ylfwZjzzCommentDao.update(ylfwZjzzComment);
	}
	
	@Override
	public void delete(Integer id){
		ylfwZjzzCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwZjzzCommentDao.deleteBatch(ids);
	}

    @Override
    public List<YlfwCommentDTO> queryCommentList(Query q) {
        return ylfwZjzzCommentDao.queryCommentList(q);
    }

    @Override
    public void savecomment(YlfwZjzzCommentEntity comment, YlfwZjzzOrderEntity order) {
        ylfwZjzzCommentDao.save(comment);
        
        YlfwZjzzExpertEntity  doctor = new YlfwZjzzExpertEntity();
        doctor.setId(comment.getDoctorid());
        Double score = ylfwZjzzCommentDao.getScore(comment.getDoctorid());
        doctor.setScore(score==null?0:score);
        ylfwZjzzExpertDao.update(doctor);
        
        //设置已评论
        order.setChanges(1);
        order.setStatus(3);
        ylfwZjzzOrderDao.update(order);   
    }
}
