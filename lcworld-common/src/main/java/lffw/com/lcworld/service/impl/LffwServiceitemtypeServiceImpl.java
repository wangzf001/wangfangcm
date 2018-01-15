package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.LffwServiceitemtypeDao;
import com.lcworld.dto.LffwServiceItemDTO;
import com.lcworld.entity.LffwServiceitemtypeEntity;
import com.lcworld.service.LffwServiceitemtypeService;



@Service("lffwServiceitemtypeService")
public class LffwServiceitemtypeServiceImpl implements LffwServiceitemtypeService {
	@Autowired
	private LffwServiceitemtypeDao lffwServiceitemtypeDao;
	
	@Override
	public LffwServiceitemtypeEntity queryObject(Integer id){
		return lffwServiceitemtypeDao.queryObject(id);
	}
	
	@Override
	public List<LffwServiceitemtypeEntity> queryList(Map<String, Object> map){
		return lffwServiceitemtypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwServiceitemtypeDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwServiceitemtypeEntity lffwServiceitemtype){
		lffwServiceitemtypeDao.save(lffwServiceitemtype);
	}
	
	@Override
	public void update(LffwServiceitemtypeEntity lffwServiceitemtype){
		lffwServiceitemtypeDao.update(lffwServiceitemtype);
	}
	
	@Override
	public void delete(Integer id){
		lffwServiceitemtypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwServiceitemtypeDao.deleteBatch(ids);
	}

    @Override
    public List<LffwServiceItemDTO> queryitemlist() {
        return lffwServiceitemtypeDao.queryitemlist();
    }
	
}
