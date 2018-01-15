package com.lcworld.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.HysfwAppointmentEntity;
import com.lcworld.entity.HysfwCommentEntity;
import com.lcworld.entity.HysfwOrderEntity;
import com.lcworld.utils.R;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public interface HysfwOrderService extends IOrderService{
	
	HysfwOrderEntity queryObject(Integer id);
	
	List<HysfwOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HysfwOrderEntity hysfwOrder);
	
	void update(HysfwOrderEntity hysfwOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	R generateOrder(HysfwOrderEntity order,List<HysfwAppointmentEntity> appointmentList);

	R orderCancel(JSONObject params);

	int invalidOrderBatch(Integer[] array);

}
