package com.lcworld.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.DsfwCommentDao;
import com.lcworld.dao.DsfwOrderDao;
import com.lcworld.entity.DsfwCommentEntity;
import com.lcworld.entity.DsfwOrderEntity;
import com.lcworld.service.DsfwCommentService;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;



@Service("dsfwCommentService")
public class DsfwCommentServiceImpl implements DsfwCommentService {
	@Autowired
	private DsfwCommentDao dsfwCommentDao;
	@Autowired
	private DsfwOrderDao dsfwOrderDao;
	
	@Override
	public DsfwCommentEntity queryObject(Integer id){
		return dsfwCommentDao.queryObject(id);
	}
	
	@Override
	public List<DsfwCommentEntity> queryList(Map<String, Object> map){
		List<DsfwCommentEntity> list = dsfwCommentDao.queryList(map);
		for (int i = 0; i < list.size(); i++) {
			DsfwCommentEntity comment = list.get(i);
			String imgs = comment.getImgs();
			if (ValidateUtil.isValid(imgs)) {
				String[] split = imgs.split(",");
				comment.setImgsArr(Arrays.asList(split));
			}
		}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dsfwCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(DsfwCommentEntity dsfwComment){
		dsfwCommentDao.save(dsfwComment);
	}
	
	@Override
	public void update(DsfwCommentEntity dsfwComment){
		dsfwCommentDao.update(dsfwComment);
	}
	
	@Override
	public void delete(Integer id){
		dsfwCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dsfwCommentDao.deleteBatch(ids);
	}
	@Override
	public R addComment(DsfwCommentEntity commentEntity, MultipartFile[] imgFile) throws IOException {
		JSONObject obj = WebUtils.uploadFiles(imgFile, null, RedisConst.USER_DSFW_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
		commentEntity.setImgs(obj.getString("data"));
		save(commentEntity);
		
		DsfwOrderEntity orderEntity = new DsfwOrderEntity();
		orderEntity.setId(commentEntity.getOrderid());
		orderEntity.setStatus(3);
		dsfwOrderDao.update(orderEntity);
		return R.ok();
	}
}
