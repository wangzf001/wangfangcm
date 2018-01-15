package com.lcworld.service;

import com.lcworld.entity.IndexCarouselEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-13 14:16:27
 */
public interface IndexCarouselService {
	
	IndexCarouselEntity queryObject(Integer id);
	
	List<IndexCarouselEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(IndexCarouselEntity indexCarousel);
	
	void update(IndexCarouselEntity indexCarousel);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
