package com.lcworld.service.impl;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.lcworld.dao.TDcfwFoodDao;
import com.lcworld.entity.TDcfwFoodEntity;
import com.lcworld.service.TDcfwFoodService;
import com.lcworld.utils.POIUtil;



@Service("tDcfwFoodService")
public class TDcfwFoodServiceImpl implements TDcfwFoodService {
	@Autowired
	private TDcfwFoodDao tDcfwFoodDao;
	
	@Override
	public TDcfwFoodEntity queryObject(Integer id){
		return tDcfwFoodDao.queryObject(id);
	}
	
	@Override
	public List<TDcfwFoodEntity> queryList(Map<String, Object> map){
		return tDcfwFoodDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tDcfwFoodDao.queryTotal(map);
	}
	
	@Override
	public void save(TDcfwFoodEntity tDcfwFood){
		tDcfwFoodDao.save(tDcfwFood);
	}
	
	@Override
	public void update(TDcfwFoodEntity tDcfwFood){
		tDcfwFoodDao.update(tDcfwFood);
	}
	
	@Override
	public void delete(Integer id){
		tDcfwFoodDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tDcfwFoodDao.deleteBatch(ids);
	}

	@Override
	public void saveBatch(List<TDcfwFoodEntity> list) {
		tDcfwFoodDao.saveBatch(list);
	}

	@Override
	public void exportExcel(DualHashBidiMap titleMapping,
			List<TDcfwFoodEntity> objectList, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			POIUtil.generateExcel(titleMapping, objectList, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<TDcfwFoodEntity> importExcel(Class<TDcfwFoodEntity> t,
			DualHashBidiMap titleMapping, MultipartFile file) {
		// TODO Auto-generated method stub
		List<TDcfwFoodEntity> list = null;
		try {
			list = POIUtil.readExcel(TDcfwFoodEntity.class,titleMapping,file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
