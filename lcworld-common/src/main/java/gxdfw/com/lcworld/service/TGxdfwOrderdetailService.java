package com.lcworld.service;

import com.lcworld.entity.TGxdfwOrderdetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 干洗店服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:51
 */
public interface TGxdfwOrderdetailService {
	
	TGxdfwOrderdetailEntity queryObject(Integer id);
	
	List<TGxdfwOrderdetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGxdfwOrderdetailEntity tGxdfwOrderdetail);
	
	void update(TGxdfwOrderdetailEntity tGxdfwOrderdetail);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void savedetail(TGxdfwOrderdetailEntity tGxdfwOrderdetail);

    void updatedetail(TGxdfwOrderdetailEntity tGxdfwOrderdetail);
}
