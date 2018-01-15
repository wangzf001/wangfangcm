package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BaseFrontrolesDao;
import com.lcworld.dto.UserFrontRolesDTO;
import com.lcworld.entity.BaseFrontrolesEntity;
import com.lcworld.service.BaseFrontrolesService;
import com.lcworld.utils.Query;



@Service("baseFrontrolesService")
public class BaseFrontrolesServiceImpl implements BaseFrontrolesService {
	@Autowired
	private BaseFrontrolesDao baseFrontrolesDao;
	
	@Override
	public BaseFrontrolesEntity queryObject(Integer id){
		return baseFrontrolesDao.queryObject(id);
	}
	
	@Override
	public List<BaseFrontrolesEntity> queryList(Map<String, Object> map){
		return baseFrontrolesDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return baseFrontrolesDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseFrontrolesEntity baseFrontroles){
		baseFrontrolesDao.save(baseFrontroles);
	}
	
	@Override
	public void update(BaseFrontrolesEntity baseFrontroles){
		baseFrontrolesDao.update(baseFrontroles);
	}
	
	@Override
	public void delete(Integer id){
		baseFrontrolesDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		baseFrontrolesDao.deleteBatch(ids);
	}

    @Override
    public List<UserFrontRolesDTO> queryRoleList(Query q) {
       return baseFrontrolesDao.queryRoleList(q);
    }
	
}
