package com.lcworld.service;

import com.lcworld.entity.DsfwGoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 订水服务-商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:21
 */
public interface DsfwGoodsService extends ExcelService<DsfwGoodsEntity>{
	
	DsfwGoodsEntity queryObject(Integer id);
	
	List<DsfwGoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DsfwGoodsEntity dsfwGoods);
	
	void update(DsfwGoodsEntity dsfwGoods);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 批量保存
	 * @param list
	 */
	void saveBatch(List<DsfwGoodsEntity> list);
}
