package com.lcworld.service;

import com.lcworld.entity.SysRoleTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 19:08:42
 */
public interface SysRoleTypeService {
	
	SysRoleTypeEntity queryObject(Integer id);
	
	List<SysRoleTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleTypeEntity sysRoleType);
	
	void update(SysRoleTypeEntity sysRoleType);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
