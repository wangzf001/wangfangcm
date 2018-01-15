package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.utils.R;
import com.lcworld.vo.RefundVo;

import java.util.List;
import java.util.Map;

/**
 * 公用服务-支付信息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 15:46:07
 */
public interface RefundinfoService {
	
	RefundinfoEntity queryObject(Integer id);
	
	List<RefundinfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RefundinfoEntity refundinfo);
	
	void update(RefundinfoEntity refundinfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	R savedorefund(RefundinfoEntity refundVo);
}
