package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.XtszOpinionDao;
import com.lcworld.entity.XtszOpinionEntity;
import com.lcworld.service.XtszOpinionService;



@Service("xtszOpinionService")
public class XtszOpinionServiceImpl implements XtszOpinionService {
	@Autowired
	private XtszOpinionDao xtszOpinionDao;
	
	@Override
	public XtszOpinionEntity queryObject(Integer oid){
		return xtszOpinionDao.queryObject(oid);
	}
	
	@Override
	public List<XtszOpinionEntity> queryList(Map<String, Object> map){
		return xtszOpinionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return xtszOpinionDao.queryTotal(map);
	}
	
	@Override
	public void save(XtszOpinionEntity xtszOpinion){
		xtszOpinionDao.save(xtszOpinion);
	}
	
	@Override
	public void update(XtszOpinionEntity xtszOpinion){
		xtszOpinionDao.update(xtszOpinion);
	}
	
	@Override
	public void delete(Integer oid){
		xtszOpinionDao.delete(oid);
	}
	
	@Override
	public void deleteBatch(Integer[] oids){
		xtszOpinionDao.deleteBatch(oids);
	}
	
}
