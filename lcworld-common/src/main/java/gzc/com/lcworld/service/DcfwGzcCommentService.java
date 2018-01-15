package com.lcworld.service;

import com.lcworld.entity.DcfwGzcCommentEntity;
import com.lcworld.utils.R;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 订餐服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:13
 */
public interface DcfwGzcCommentService extends ExcelService<DcfwGzcCommentEntity>{
	
	DcfwGzcCommentEntity queryObject(Integer id);
	
	List<DcfwGzcCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DcfwGzcCommentEntity dcfwGzcComment);
	
	void update(DcfwGzcCommentEntity dcfwGzcComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	R addComment(DcfwGzcCommentEntity commentEntity, MultipartFile[] imgFile) throws IOException;
}
