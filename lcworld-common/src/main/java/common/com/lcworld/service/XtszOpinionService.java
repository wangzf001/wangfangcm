package com.lcworld.service;

import com.lcworld.entity.XtszOpinionEntity;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-18 15:41:48
 */
public interface XtszOpinionService {
	
	XtszOpinionEntity queryObject(Integer oid);
	
	List<XtszOpinionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(XtszOpinionEntity xtszOpinion);
	
	void update(XtszOpinionEntity xtszOpinion);
	
	void delete(Integer oid);
	
	void deleteBatch(Integer[] oids);
}
