package com.lcworld.service;

import com.lcworld.entity.VisiuserNoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 来访人员消息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-29 10:13:39
 */
public interface VisiuserNoticeService {
	
	VisiuserNoticeEntity queryObject(Integer id);
	
	List<VisiuserNoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(VisiuserNoticeEntity visiuserNotice);
	
	void update(VisiuserNoticeEntity visiuserNotice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
