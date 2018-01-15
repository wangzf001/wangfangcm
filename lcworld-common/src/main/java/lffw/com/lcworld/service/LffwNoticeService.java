package com.lcworld.service;

import com.lcworld.entity.LffwNoticeEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 理发服务-咨询
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwNoticeService extends TalkService{
	
	LffwNoticeEntity queryObject(Integer id);
	
	List<LffwNoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwNoticeEntity lffwNotice);
	
	void update(LffwNoticeEntity lffwNotice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<LffwNoticeEntity> querynoticeList(Query q);

    int querynoticeListTotal(Query q);
}
