package com.lcworld.service;

import com.lcworld.dto.BxwxMenderItemDTO;
import com.lcworld.entity.TBxwxItemEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 报修维修项
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public interface TBxwxItemService {
	
	TBxwxItemEntity queryObject(Integer id);
	
	List<TBxwxItemEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TBxwxItemEntity tBxwxItem);
	
	void update(TBxwxItemEntity tBxwxItem);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<BxwxMenderItemDTO> queryItemList(Query q);

}
