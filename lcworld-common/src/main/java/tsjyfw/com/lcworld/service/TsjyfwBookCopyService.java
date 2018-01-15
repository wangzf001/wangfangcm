package com.lcworld.service;

import com.lcworld.entity.TsjyfwBookCopyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 17:48:09
 */
public interface TsjyfwBookCopyService {
	
	TsjyfwBookCopyEntity queryObject(Integer id);
	
	List<TsjyfwBookCopyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TsjyfwBookCopyEntity tsjyfwBookCopy);
	
	void update(TsjyfwBookCopyEntity tsjyfwBookCopy);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void saveAddOrderCountBench(List<Integer> ids);
}
