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
import com.lcworld.dao.HysfwCommentDao;
import com.lcworld.dao.HysfwOrderDao;
import com.lcworld.entity.HysfwCommentEntity;
import com.lcworld.entity.HysfwOrderEntity;
import com.lcworld.service.HysfwCommentService;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;



@Service("hysfwCommentService")
public class HysfwCommentServiceImpl implements HysfwCommentService {
	@Autowired
	private HysfwCommentDao hysfwCommentDao;
	@Autowired
	private HysfwOrderDao hysfwOrderDao;
	
	@Override
	public HysfwCommentEntity queryObject(Integer id){
		return hysfwCommentDao.queryObject(id);
	}
	
	@Override
	public List<HysfwCommentEntity> queryList(Map<String, Object> map){
		List<HysfwCommentEntity> list = hysfwCommentDao.queryList(map);
		for (int i = 0; i < list.size(); i++) {
			HysfwCommentEntity comment = list.get(i);
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
		return hysfwCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(HysfwCommentEntity hysfwComment){
		hysfwCommentDao.save(hysfwComment);
	}
	
	@Override
	public void update(HysfwCommentEntity hysfwComment){
		hysfwCommentDao.update(hysfwComment);
	}
	
	@Override
	public void delete(Integer id){
		hysfwCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hysfwCommentDao.deleteBatch(ids);
	}
	@Override
	public R addComment(HysfwCommentEntity commentEntity, MultipartFile[] imgFile) throws IOException {
		JSONObject obj = WebUtils.uploadFiles(imgFile, null, RedisConst.USER_BXWX_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
		commentEntity.setImgs(obj.getString("data"));
		save(commentEntity);
		HysfwOrderEntity orderEntity = new HysfwOrderEntity();
		orderEntity.setId(commentEntity.getOrderid());
		orderEntity.setStatus(3);
		hysfwOrderDao.update(orderEntity);
		return R.ok();
	}
}
