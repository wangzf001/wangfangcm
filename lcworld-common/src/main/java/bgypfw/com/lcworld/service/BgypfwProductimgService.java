package com.lcworld.service;

import com.lcworld.entity.BgypfwProductimgEntity;

import java.util.List;
import java.util.Map;

/**
 * 办公用品服务-商品规格图片
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public interface BgypfwProductimgService {
	
	BgypfwProductimgEntity queryObject(Integer id);
	
	List<BgypfwProductimgEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwProductimgEntity bgypfwProductimg);
	
	void update(BgypfwProductimgEntity bgypfwProductimg);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void deleteByPids(Integer[] ids);
}
