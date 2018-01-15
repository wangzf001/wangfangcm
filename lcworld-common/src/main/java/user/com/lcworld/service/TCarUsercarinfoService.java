package com.lcworld.service;

import com.lcworld.dto.UserCarDTO;
import com.lcworld.entity.TCarUsercarinfoEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 用户车辆信息表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-28 17:21:21
 */
public interface TCarUsercarinfoService {
	
	TCarUsercarinfoEntity queryObject(Integer id);
	
	List<TCarUsercarinfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TCarUsercarinfoEntity tCarUsercarinfo);
	
	void update(TCarUsercarinfoEntity tCarUsercarinfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<UserCarDTO> queryUserCarinfoList(Query query);
}
