package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.BaseUserRoleDao;
import com.lcworld.dto.UserFrontRolesDTO;
import com.lcworld.entity.BaseFrontrolesEntity;
import com.lcworld.entity.BaseUserRoleEntity;
import com.lcworld.service.BaseUserRoleService;
import com.lcworld.utils.Query;



@Service("baseUserRoleService")
public class BaseUserRoleServiceImpl implements BaseUserRoleService {
	@Autowired
	private BaseUserRoleDao baseUserRoleDao;
	
	@Override
	public BaseUserRoleEntity queryObject(Integer id){
		return baseUserRoleDao.queryObject(id);
	}
	
	@Override
	public List<BaseUserRoleEntity> queryList(Map<String, Object> map){
		return baseUserRoleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return baseUserRoleDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseUserRoleEntity baseUserRole){
		baseUserRoleDao.save(baseUserRole);
	}
	
	@Override
	public void update(BaseUserRoleEntity baseUserRole){
		baseUserRoleDao.update(baseUserRole);
	}
	
	@Override
	public void delete(Integer id){
		baseUserRoleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		baseUserRoleDao.deleteBatch(ids);
	}

    @Override
    public int queryUserRoleCount(JSONObject params) {
       return baseUserRoleDao.queryUserRoleCount(params);
    }

    @Override
    public List<UserFrontRolesDTO> queryUserRoleList(Query q) {
        return baseUserRoleDao.queryUserRoleList(q);
    }

    @Override
    public void deleteByParams(JSONObject params) {
        baseUserRoleDao.deleteByParams(params);
        
    }

    @Override
    public void savebench(List<BaseUserRoleEntity> rlist) {
        baseUserRoleDao.savebench(rlist);
        
    }

    @Override
    public List<BaseUserRoleEntity> queryRoleList(JSONObject obj) {
       return baseUserRoleDao.queryRoleList(obj);
    }
	
}
