package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.DcfwGzcSendfoodtimeDao;
import com.lcworld.entity.DcfwGzcSendfoodtimeEntity;
import com.lcworld.service.DcfwGzcSendfoodtimeService;



@Service("dcfwGzcSendfoodtimeService")
public class DcfwGzcSendfoodtimeServiceImpl implements DcfwGzcSendfoodtimeService {
	@Autowired
	private DcfwGzcSendfoodtimeDao dcfwGzcSendfoodtimeDao;
	
	@Override
	public DcfwGzcSendfoodtimeEntity queryObject(Integer id){
		return dcfwGzcSendfoodtimeDao.queryObject(id);
	}
	
	@Override
	public List<DcfwGzcSendfoodtimeEntity> queryList(Map<String, Object> map){
		return dcfwGzcSendfoodtimeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dcfwGzcSendfoodtimeDao.queryTotal(map);
	}
	
	@Override
	public void save(DcfwGzcSendfoodtimeEntity dcfwGzcSendfoodtime){
		dcfwGzcSendfoodtimeDao.save(dcfwGzcSendfoodtime);
	}
	
	@Override
	public void update(DcfwGzcSendfoodtimeEntity dcfwGzcSendfoodtime){
		dcfwGzcSendfoodtimeDao.update(dcfwGzcSendfoodtime);
	}
	
	@Override
	public void delete(Integer id){
		dcfwGzcSendfoodtimeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dcfwGzcSendfoodtimeDao.deleteBatch(ids);
	}
	
}
