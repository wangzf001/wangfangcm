package com.lcworld.service;

import com.lcworld.entity.DcfwGzcSendfoodtimeEntity;

import java.util.List;
import java.util.Map;

/**
 * 订餐服务-工作餐预定-送餐时间
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:13
 */
public interface DcfwGzcSendfoodtimeService {
	
	DcfwGzcSendfoodtimeEntity queryObject(Integer id);
	
	List<DcfwGzcSendfoodtimeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DcfwGzcSendfoodtimeEntity dcfwGzcSendfoodtime);
	
	void update(DcfwGzcSendfoodtimeEntity dcfwGzcSendfoodtime);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
