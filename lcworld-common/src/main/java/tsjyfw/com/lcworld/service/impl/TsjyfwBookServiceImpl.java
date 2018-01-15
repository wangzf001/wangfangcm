package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TsjyfwBookDao;
import com.lcworld.dto.TsjyfwBookDTO;
import com.lcworld.entity.TsjyfwBookEntity;
import com.lcworld.service.TsjyfwBookService;
import com.lcworld.utils.Query;



@Service("tsjyfwBookService")
public class TsjyfwBookServiceImpl implements TsjyfwBookService {
	@Autowired
	private TsjyfwBookDao tsjyfwBookDao;
	
	@Override
	public TsjyfwBookEntity queryObject(Integer id){
		return tsjyfwBookDao.queryObject(id);
	}
	
	@Override
	public List<TsjyfwBookEntity> queryList(Map<String, Object> map){
		return tsjyfwBookDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tsjyfwBookDao.queryTotal(map);
	}
	
	@Override
	public void save(TsjyfwBookEntity tsjyfwBook){
		tsjyfwBookDao.save(tsjyfwBook);
	}
	
	@Override
	public void update(TsjyfwBookEntity tsjyfwBook){
		tsjyfwBookDao.update(tsjyfwBook);
	}
	
	@Override
	public void delete(Integer id){
		tsjyfwBookDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tsjyfwBookDao.deleteBatch(ids);
	}

    @Override
    public List<TsjyfwBookDTO> queryBookList(Query query) {
        return tsjyfwBookDao.queryBookList(query);
    }

	@Override
	public List<TsjyfwBookEntity> queryFavorList(Query params) {
		return queryList(params);
	}
	
}
