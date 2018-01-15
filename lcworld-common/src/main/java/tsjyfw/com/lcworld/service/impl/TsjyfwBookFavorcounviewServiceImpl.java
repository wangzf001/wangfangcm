package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TsjyfwBookFavorcounviewDao;
import com.lcworld.entity.TsjyfwBookFavorcounviewEntity;
import com.lcworld.service.TsjyfwBookFavorcounviewService;



@Service("tsjyfwBookFavorcounviewService")
public class TsjyfwBookFavorcounviewServiceImpl implements TsjyfwBookFavorcounviewService {
	@Autowired
	private TsjyfwBookFavorcounviewDao tsjyfwBookFavorcounviewDao;
	
	@Override
	public TsjyfwBookFavorcounviewEntity queryObject(Integer bookid){
		return tsjyfwBookFavorcounviewDao.queryObject(bookid);
	}
	
	@Override
	public List<TsjyfwBookFavorcounviewEntity> queryList(Map<String, Object> map){
		return tsjyfwBookFavorcounviewDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tsjyfwBookFavorcounviewDao.queryTotal(map);
	}
	
	@Override
	public void save(TsjyfwBookFavorcounviewEntity tsjyfwBookFavorcounview){
		tsjyfwBookFavorcounviewDao.save(tsjyfwBookFavorcounview);
	}
	
	@Override
	public void update(TsjyfwBookFavorcounviewEntity tsjyfwBookFavorcounview){
		tsjyfwBookFavorcounviewDao.update(tsjyfwBookFavorcounview);
	}
	
	@Override
	public void delete(Integer bookid){
		tsjyfwBookFavorcounviewDao.delete(bookid);
	}
	
	@Override
	public void deleteBatch(Integer[] bookids){
		tsjyfwBookFavorcounviewDao.deleteBatch(bookids);
	}
	
}
