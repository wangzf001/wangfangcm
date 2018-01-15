package com.lcworld.service;

import com.lcworld.dto.UserFrontRolesDTO;
import com.lcworld.entity.BaseFrontrolesEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-11 15:56:43
 */
public interface BaseFrontrolesService {
	
	BaseFrontrolesEntity queryObject(Integer id);
	
	List<BaseFrontrolesEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BaseFrontrolesEntity baseFrontroles);
	
	void update(BaseFrontrolesEntity baseFrontroles);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<UserFrontRolesDTO> queryRoleList(Query q);
}
