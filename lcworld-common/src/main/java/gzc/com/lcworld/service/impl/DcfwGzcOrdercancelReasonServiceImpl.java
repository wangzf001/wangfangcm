package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.DcfwGzcOrdercancelReasonDao;
import com.lcworld.entity.DcfwGzcOrdercancelReasonEntity;
import com.lcworld.service.DcfwGzcOrdercancelReasonService;



@Service("dcfwGzcOrdercancelReasonService")
public class DcfwGzcOrdercancelReasonServiceImpl implements DcfwGzcOrdercancelReasonService {
	@Autowired
	private DcfwGzcOrdercancelReasonDao dcfwGzcOrdercancelReasonDao;
	
	@Override
	public DcfwGzcOrdercancelReasonEntity queryObject(Integer id){
		return dcfwGzcOrdercancelReasonDao.queryObject(id);
	}
	
	@Override
	public List<DcfwGzcOrdercancelReasonEntity> queryList(Map<String, Object> map){
		return dcfwGzcOrdercancelReasonDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dcfwGzcOrdercancelReasonDao.queryTotal(map);
	}
	
	@Override
	public void save(DcfwGzcOrdercancelReasonEntity dcfwGzcOrdercancelReason){
		dcfwGzcOrdercancelReasonDao.save(dcfwGzcOrdercancelReason);
	}
	
	@Override
	public void update(DcfwGzcOrdercancelReasonEntity dcfwGzcOrdercancelReason){
		dcfwGzcOrdercancelReasonDao.update(dcfwGzcOrdercancelReason);
	}
	
	@Override
	public void delete(Integer id){
		dcfwGzcOrdercancelReasonDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dcfwGzcOrdercancelReasonDao.deleteBatch(ids);
	}
	
}
