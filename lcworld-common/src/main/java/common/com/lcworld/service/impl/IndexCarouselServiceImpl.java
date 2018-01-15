package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.IndexCarouselDao;
import com.lcworld.entity.IndexCarouselEntity;
import com.lcworld.service.IndexCarouselService;



@Service("indexCarouselService")
public class IndexCarouselServiceImpl implements IndexCarouselService {
	@Autowired
	private IndexCarouselDao indexCarouselDao;
	
	@Override
	public IndexCarouselEntity queryObject(Integer id){
		return indexCarouselDao.queryObject(id);
	}
	
	@Override
	public List<IndexCarouselEntity> queryList(Map<String, Object> map){
		return indexCarouselDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return indexCarouselDao.queryTotal(map);
	}
	
	@Override
	public void save(IndexCarouselEntity indexCarousel){
		indexCarouselDao.save(indexCarousel);
	}
	
	@Override
	public void update(IndexCarouselEntity indexCarousel){
		indexCarouselDao.update(indexCarousel);
	}
	
	@Override
	public void delete(Integer id){
		indexCarouselDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		indexCarouselDao.deleteBatch(ids);
	}
	
}
