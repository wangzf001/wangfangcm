package com.lcworld.service;

import com.lcworld.entity.PurchaseAccounlogEntity;
import com.lcworld.vo.PayOrderVo;

import java.util.List;
import java.util.Map;

/**
 * 采购账号记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-31 14:40:06
 */
public interface PurchaseAccounlogService {
	
	PurchaseAccounlogEntity queryObject(Integer id);
	
	List<PurchaseAccounlogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PurchaseAccounlogEntity purchaseAccounlog);
	
	void update(PurchaseAccounlogEntity purchaseAccounlog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    /**
     * @param ordervo
     * @param id
     * @param i 2:收入，1：出
     * @param j 层级 1：司局，2：处室
     */
    void savelog(PayOrderVo ordervo, Integer id, int i, int j);
}
