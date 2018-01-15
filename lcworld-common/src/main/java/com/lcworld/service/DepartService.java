package com.lcworld.service;

import com.lcworld.entity.DepartEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-19 13:54:13
 */
public interface DepartService {
	
	DepartEntity queryObject(Integer id);
	
	List<DepartEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DepartEntity depart);
	
	void update(DepartEntity depart);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
