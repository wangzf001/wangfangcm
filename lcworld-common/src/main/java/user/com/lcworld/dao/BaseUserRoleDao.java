package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.UserFrontRolesDTO;
import com.lcworld.entity.BaseFrontrolesEntity;
import com.lcworld.entity.BaseUserRoleEntity;
import com.lcworld.utils.Query;

/**
 * 用户角色表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-31 11:23:51
 */
public interface BaseUserRoleDao extends BaseDao<BaseUserRoleEntity> {

    int queryUserRoleCount(JSONObject params);

    List<UserFrontRolesDTO> queryUserRoleList(Query q);

    void deleteByParams(JSONObject params);

    void savebench(List<BaseUserRoleEntity> rlist);

    List<BaseUserRoleEntity> queryRoleList(JSONObject obj);
	
}
