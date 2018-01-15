package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.SysRoleTypeDao;
import com.lcworld.entity.SysRoleTypeEntity;
import com.lcworld.service.SysRoleTypeService;



@Service("sysRoleTypeService")
public class SysRoleTypeServiceImpl implements SysRoleTypeService {
	@Autowired
	private SysRoleTypeDao sysRoleTypeDao;
	
	@Override
	public SysRoleTypeEntity queryObject(Integer id){
		return sysRoleTypeDao.queryObject(id);
	}
	
	@Override
	public List<SysRoleTypeEntity> queryList(Map<String, Object> map){
		return sysRoleTypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysRoleTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(SysRoleTypeEntity sysRoleType){
		sysRoleTypeDao.save(sysRoleType);
	}
	
	@Override
	public void update(SysRoleTypeEntity sysRoleType){
		sysRoleTypeDao.update(sysRoleType);
	}
	
	@Override
	public void delete(Integer id){
		sysRoleTypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sysRoleTypeDao.deleteBatch(ids);
	}
	
}
