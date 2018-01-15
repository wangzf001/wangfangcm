package com.lcworld.service;

import com.lcworld.entity.YlfwZjzzNoticeEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务专家坐诊-咨询
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public interface YlfwZjzzNoticeService extends TalkService{
	
	YlfwZjzzNoticeEntity queryObject(Integer id);
	
	List<YlfwZjzzNoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwZjzzNoticeEntity ylfwZjzzNotice);
	
	void update(YlfwZjzzNoticeEntity ylfwZjzzNotice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<YlfwZjzzNoticeEntity> queryNoticeList(Query q);

    int queryNoticeListTotal(Query q);
}
