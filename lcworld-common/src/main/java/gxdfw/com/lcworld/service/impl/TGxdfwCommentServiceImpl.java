package com.lcworld.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.TGxdfwCommentDao;
import com.lcworld.dao.TGxdfwOrderDao;
import com.lcworld.dao.TGxdfwOrderLogDao;
import com.lcworld.entity.TGxdfwCommentEntity;
import com.lcworld.entity.TGxdfwOrderEntity;
import com.lcworld.entity.TGxdfwOrderLogEntity;
import com.lcworld.service.TGxdfwCommentService;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;



@Service("tGxdfwCommentService")
@Transactional
public class TGxdfwCommentServiceImpl implements TGxdfwCommentService {
	@Autowired
	private TGxdfwCommentDao tGxdfwCommentDao;
	@Autowired
	private TGxdfwOrderDao tGxdfwOrderDao;
	@Autowired
	private TGxdfwOrderLogDao tGxdfwOrderLogDao;
	
	@Override
	public TGxdfwCommentEntity queryObject(Integer id){
		return tGxdfwCommentDao.queryObject(id);
	}
	
	@Override
	public List<TGxdfwCommentEntity> queryList(Map<String, Object> map){
		List<TGxdfwCommentEntity> list = tGxdfwCommentDao.queryList(map);
		for (int i = 0; i < list.size(); i++) {
			TGxdfwCommentEntity comment = list.get(i);
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
		return tGxdfwCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(TGxdfwCommentEntity tGxdfwComment){
		tGxdfwCommentDao.save(tGxdfwComment);
	}
	
	@Override
	public void update(TGxdfwCommentEntity tGxdfwComment){
		tGxdfwCommentDao.update(tGxdfwComment);
	}
	
	@Override
	public void delete(Integer id){
		tGxdfwCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tGxdfwCommentDao.deleteBatch(ids);
	}

	@Override
	public R addComment(TGxdfwCommentEntity commentEntity, MultipartFile[] imgFile) throws Exception {
		JSONObject obj = WebUtils.uploadFiles(imgFile, null, RedisConst.USER_GXD_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
		commentEntity.setImgs(obj.getString("data"));
		save(commentEntity);
		TGxdfwOrderEntity orderEntity = new TGxdfwOrderEntity();
		orderEntity.setId(commentEntity.getOrderid());
		orderEntity.setStatus(APPConstant.TYPE_ORDER_STATUS_FINISHED);
		tGxdfwOrderDao.update(orderEntity);
		//改变logStatuts
		TGxdfwOrderLogEntity orderLog = new TGxdfwOrderLogEntity();
		orderLog.setOrderid(commentEntity.getOrderid());
		orderLog.setStatus(APPConstant.TYPE_GXDFW_LOG_STATUS_EVALUATED);
		tGxdfwOrderLogDao.update(orderLog);
		return R.ok();
	}

	@Override
	public int queryStarsCountWithin(Map<String, Object> map) {
		return tGxdfwCommentDao.queryStarsCountWithin(map);
	}
}
