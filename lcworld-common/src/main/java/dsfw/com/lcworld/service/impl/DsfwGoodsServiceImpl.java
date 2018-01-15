package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lcworld.dao.DsfwGoodsDao;
import com.lcworld.entity.DsfwGoodsEntity;
import com.lcworld.service.DsfwGoodsService;
import com.lcworld.utils.POIUtil;



@Service("dsfwGoodsService")
public class DsfwGoodsServiceImpl implements DsfwGoodsService {
	@Autowired
	private DsfwGoodsDao dsfwGoodsDao;
	
	@Override
	public DsfwGoodsEntity queryObject(Integer id){
		return dsfwGoodsDao.queryObject(id);
	}
	
	@Override
	public List<DsfwGoodsEntity> queryList(Map<String, Object> map){
		return dsfwGoodsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dsfwGoodsDao.queryTotal(map);
	}
	
	@Override
	public void save(DsfwGoodsEntity dsfwGoods){
		dsfwGoodsDao.save(dsfwGoods);
	}
	
	@Override
	public void update(DsfwGoodsEntity dsfwGoods){
		dsfwGoodsDao.update(dsfwGoods);
	}
	
	@Override
	public void delete(Integer id){
		dsfwGoodsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dsfwGoodsDao.deleteBatch(ids);
	}

	@Override
	public void saveBatch(List<DsfwGoodsEntity> list) {
		dsfwGoodsDao.saveBatch(list);
	}

	@Override
	public void exportExcel(DualHashBidiMap titleMapping,
			List<DsfwGoodsEntity> objectList, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			POIUtil.generateExcel(titleMapping, objectList, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<DsfwGoodsEntity> importExcel(Class<DsfwGoodsEntity> t,
			DualHashBidiMap titleMapping, MultipartFile file) {
		// TODO Auto-generated method stub
		List<DsfwGoodsEntity> list = null;
		try {
			list = POIUtil.readExcel(DsfwGoodsEntity.class,titleMapping,file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
