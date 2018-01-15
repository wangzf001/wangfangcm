package com.lcworld.service;

import com.lcworld.entity.InfoCommentEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 资讯评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:14:38
 */
public interface InfoCommentService {
	
	InfoCommentEntity queryObject(Integer id);
	
	List<InfoCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InfoCommentEntity infoComment);
	
	void update(InfoCommentEntity infoComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<Map<String, Object>> querycommentList(Query query);

    int querycommentTotal(Query query);

}
