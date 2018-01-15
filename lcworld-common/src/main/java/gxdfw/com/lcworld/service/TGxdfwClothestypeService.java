package com.lcworld.service;

import com.lcworld.entity.TGxdfwClothestypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 干洗店服务-衣服类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:55
 */
public interface TGxdfwClothestypeService {
	
	TGxdfwClothestypeEntity queryObject(Integer id);
	
	List<TGxdfwClothestypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGxdfwClothestypeEntity tGxdfwClothestype);
	
	void update(TGxdfwClothestypeEntity tGxdfwClothestype);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
