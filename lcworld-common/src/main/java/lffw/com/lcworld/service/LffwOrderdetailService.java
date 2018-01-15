package com.lcworld.service;

import com.lcworld.entity.LffwOrderdetailEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 理发服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwOrderdetailService {
	
	LffwOrderdetailEntity queryObject(Integer id);
	
	List<LffwOrderdetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwOrderdetailEntity lffwOrderdetail);
	
	void update(LffwOrderdetailEntity lffwOrderdetail);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<Map<String, Object>> querydetailList(Map<String,Object> query);

    int querydetailTotal(Query query);

    void updateOrder(LffwOrderdetailEntity lffwOrderdetail);

    void saveOrder(LffwOrderdetailEntity lffwOrderdetail);
}
