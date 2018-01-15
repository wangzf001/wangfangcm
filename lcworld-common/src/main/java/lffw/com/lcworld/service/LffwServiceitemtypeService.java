package com.lcworld.service;

import com.lcworld.dto.LffwServiceItemDTO;
import com.lcworld.entity.LffwServiceitemtypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 理发服务-服务项目分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:13
 */
public interface LffwServiceitemtypeService {
	
	LffwServiceitemtypeEntity queryObject(Integer id);
	
	List<LffwServiceitemtypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwServiceitemtypeEntity lffwServiceitemtype);
	
	void update(LffwServiceitemtypeEntity lffwServiceitemtype);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<LffwServiceItemDTO> queryitemlist();
}
