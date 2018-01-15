package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.TsjyfwBookEntity;
import com.lcworld.utils.Query;

/**
 * 图书借阅服务-图书
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public interface TsjyfwBookService extends ICollectionService<TsjyfwBookEntity>{
	
	TsjyfwBookEntity queryObject(Integer id);
	
	List<TsjyfwBookEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TsjyfwBookEntity tsjyfwBook);
	
	void update(TsjyfwBookEntity tsjyfwBook);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<com.lcworld.dto.TsjyfwBookDTO> queryBookList(Query query);
}
