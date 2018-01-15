package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.DsfwWaterDao;
import com.lcworld.dto.ServiceUserDTO;
import com.lcworld.entity.DsfwWaterEntity;
import com.lcworld.entity.TBxwxMenderEntity;
import com.lcworld.service.DsfwWaterService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;



@Service("dsfwWaterService")
public class DsfwWaterServiceImpl implements DsfwWaterService {
	@Autowired
	private DsfwWaterDao dsfwWaterDao;
	
	@Override
	public DsfwWaterEntity queryObject(Integer id){
		return dsfwWaterDao.queryObject(id);
	}
	
	@Override
	public List<DsfwWaterEntity> queryList(Map<String, Object> map){
		return dsfwWaterDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dsfwWaterDao.queryTotal(map);
	}
	
	@Override
	public void save(DsfwWaterEntity dsfwWater){
		dsfwWaterDao.save(dsfwWater);
	}
	
	@Override
	public void update(DsfwWaterEntity dsfwWater){
		dsfwWaterDao.update(dsfwWater);
	}
	
	@Override
	public void delete(Integer id){
		dsfwWaterDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dsfwWaterDao.deleteBatch(ids);
	}

	@Override
	public R getUserinfo(Integer uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceUserDTO queryByUsername(String uname, Integer servicetypeid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceUserDTO saveUser(ServiceUserDTO tuser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceUserDTO changeToUserDTO(DsfwWaterEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ServiceUserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<?> CommentList(Query q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T CommentDetail(JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DsfwWaterEntity> queryMenderlist(Map<String, Object> query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R forgot(ServiceUserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryCommentTotal(Query query) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer queryTotal(JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
