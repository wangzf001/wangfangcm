package com.lcworld.service;

import com.lcworld.entity.TsjyNoticeEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 图书服务-咨询
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-24 10:06:29
 */
public interface TsjyNoticeService {
	
	TsjyNoticeEntity queryObject(Integer id);
	
	List<TsjyNoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TsjyNoticeEntity tsjyNotice);
	
	void update(TsjyNoticeEntity tsjyNotice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<TsjyNoticeEntity> queryNoticeList(Query q);

    int queryNoticeListTotal(Query q);
}
