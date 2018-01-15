package com.lcworld.dao;

import java.util.List;

import com.lcworld.dto.UserFrontRolesDTO;
import com.lcworld.entity.BaseFrontrolesEntity;
import com.lcworld.utils.Query;

/**
 * 角色表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-11 15:56:43
 */
public interface BaseFrontrolesDao extends BaseDao<BaseFrontrolesEntity> {

    List<UserFrontRolesDTO> queryRoleList(Query q);
	
}
