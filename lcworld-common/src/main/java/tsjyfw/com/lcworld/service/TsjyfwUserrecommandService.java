package com.lcworld.service;

import com.lcworld.entity.TsjyfwUserrecommandEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 图书借阅服务-读者推荐
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public interface TsjyfwUserrecommandService {
	
	TsjyfwUserrecommandEntity queryObject(Integer id);
	
	List<TsjyfwUserrecommandEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TsjyfwUserrecommandEntity tsjyfwUserrecommand);
	
	void update(TsjyfwUserrecommandEntity tsjyfwUserrecommand);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<Map<String,Object>> queryrecommandList(Query query);

    int queryrecommandTotal(Query query);
}
