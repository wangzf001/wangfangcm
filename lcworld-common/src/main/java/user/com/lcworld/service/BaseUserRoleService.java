package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.UserFrontRolesDTO;
import com.lcworld.entity.BaseFrontrolesEntity;
import com.lcworld.entity.BaseUserRoleEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 用户角色表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-31 11:23:51
 */
public interface BaseUserRoleService {
	
	BaseUserRoleEntity queryObject(Integer id);
	
	List<BaseUserRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BaseUserRoleEntity baseUserRole);
	
	void update(BaseUserRoleEntity baseUserRole);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    int queryUserRoleCount(JSONObject params);

    List<UserFrontRolesDTO> queryUserRoleList(Query q);

    void deleteByParams(JSONObject params);

    void savebench(List<BaseUserRoleEntity> rlist);

    List<BaseUserRoleEntity> queryRoleList(JSONObject obj);
}
