package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.LffwVoucherPriceDao;
import com.lcworld.entity.LffwVoucherPriceEntity;
import com.lcworld.service.LffwVoucherPriceService;



@Service("lffwVoucherPriceService")
public class LffwVoucherPriceServiceImpl implements LffwVoucherPriceService {
	@Autowired
	private LffwVoucherPriceDao lffwVoucherPriceDao;
	
	@Override
	public LffwVoucherPriceEntity queryObject(Integer id){
		return lffwVoucherPriceDao.queryObject(id);
	}
	
	@Override
	public List<LffwVoucherPriceEntity> queryList(Map<String, Object> map){
		return lffwVoucherPriceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwVoucherPriceDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwVoucherPriceEntity lffwVoucherPrice){
		lffwVoucherPriceDao.save(lffwVoucherPrice);
	}
	
	@Override
	public void update(LffwVoucherPriceEntity lffwVoucherPrice){
		lffwVoucherPriceDao.update(lffwVoucherPrice);
	}
	
	@Override
	public void delete(Integer id){
		lffwVoucherPriceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwVoucherPriceDao.deleteBatch(ids);
	}
	
}
