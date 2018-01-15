package com.lcworld.service;

import com.lcworld.entity.JyfwComplaintEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 建议服务
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:01:19
 */
public interface JyfwComplaintService {
	
	JyfwComplaintEntity queryObject(Integer id);
	
	List<JyfwComplaintEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(JyfwComplaintEntity jyfwComplaint);
	
	void update(JyfwComplaintEntity jyfwComplaint);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 添加投诉
	 * @param complaintEntity
	 * @param imgFile
	 * @throws IOException 
	 */
	void addComplaint(JyfwComplaintEntity complaintEntity, MultipartFile[] imgFile) throws Exception;
}
