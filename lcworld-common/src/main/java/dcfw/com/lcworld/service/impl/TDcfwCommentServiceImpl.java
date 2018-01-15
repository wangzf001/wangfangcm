package com.lcworld.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.TDcfwCommentDao;
import com.lcworld.dao.TDcfwOrderDao;
import com.lcworld.entity.TDcfwCommentEntity;
import com.lcworld.entity.TDcfwOrderEntity;
import com.lcworld.service.TDcfwCommentService;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.OSSConstantKey;
import com.lcworld.utils.OSSUtils;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;



@Service("tDcfwCommentService")
public class TDcfwCommentServiceImpl implements TDcfwCommentService {
	@Autowired
	private TDcfwCommentDao tDcfwCommentDao;
	@Autowired
	private TDcfwOrderDao tDcfwOrderDao;
	
	@Override
	public TDcfwCommentEntity queryObject(Integer id){
		return tDcfwCommentDao.queryObject(id);
	}
	
	@Override
	public List<TDcfwCommentEntity> queryList(Map<String, Object> map){
		List<TDcfwCommentEntity> list = tDcfwCommentDao.queryList(map);
		for (int i = 0; i < list.size(); i++) {
			TDcfwCommentEntity comment = list.get(i);
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
		return tDcfwCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(TDcfwCommentEntity tDcfwComment){
		tDcfwCommentDao.save(tDcfwComment);
	}
	
	@Override
	public void update(TDcfwCommentEntity tDcfwComment){
		tDcfwCommentDao.update(tDcfwComment);
	}
	
	@Override
	public void delete(Integer id){
		tDcfwCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tDcfwCommentDao.deleteBatch(ids);
	}

	@Override
	public R addComment(TDcfwCommentEntity commentEntity, MultipartFile[] imgFile) {
		
		/*try {
			StringBuilder sb = new StringBuilder();
			for(MultipartFile file:imgFile){
			    String fileName = file.getOriginalFilename();// 文件原名称
			    String filePath = DateUtils.format(new Date())+"/"+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."), fileName.length());
			    OSSUtils.uploadFile(filePath, file.getInputStream());
			    String imgUrl = OSSConstantKey.OSS_BASE_URL+filePath;
			    sb.append(",").append(imgUrl);
			}
			if(!StringUtils.isBlank(sb.toString())){
				commentEntity.setImgs(sb.toString().substring(1));
			}
		} catch (Exception e) {
			return R.error("图片上传失败");
		}*/

		JSONObject obj = WebUtils.uploadFiles(imgFile, null, RedisConst.USER_BXWX_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
		commentEntity.setImgs(obj.getString("data"));
		
		save(commentEntity);
		
		TDcfwOrderEntity orderEntity = new TDcfwOrderEntity();
		orderEntity.setId(commentEntity.getOrderid());
		orderEntity.setStatus(APPConstant.TYPE_ORDER_STATUS_FINISHED);
		tDcfwOrderDao.update(orderEntity);
		return R.ok();
	}
	
}
