package com.lcworld.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.DcfwGzcCommentDao;
import com.lcworld.dao.DcfwGzcOrderDao;
import com.lcworld.entity.DcfwGzcCommentEntity;
import com.lcworld.entity.DcfwGzcOrderEntity;
import com.lcworld.oss.OSSFactory;
import com.lcworld.service.DcfwGzcCommentService;
import com.lcworld.utils.POIUtil;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;



@Service("dcfwGzcCommentService")
public class DcfwGzcCommentServiceImpl implements DcfwGzcCommentService {
	@Autowired
	private DcfwGzcCommentDao dcfwGzcCommentDao;
	@Autowired
	private DcfwGzcOrderDao dcfwGzcOrderDao;
	
	@Override
	public DcfwGzcCommentEntity queryObject(Integer id){
		return dcfwGzcCommentDao.queryObject(id);
	}
	
	@Override
	public List<DcfwGzcCommentEntity> queryList(Map<String, Object> map){
		List<DcfwGzcCommentEntity> list = dcfwGzcCommentDao.queryList(map);
		for (int i = 0; i < list.size(); i++) {
			DcfwGzcCommentEntity comment = list.get(i);
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
		return dcfwGzcCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(DcfwGzcCommentEntity dcfwGzcComment){
		dcfwGzcCommentDao.save(dcfwGzcComment);
	}
	
	@Override
	public void update(DcfwGzcCommentEntity dcfwGzcComment){
		dcfwGzcCommentDao.update(dcfwGzcComment);
	}
	
	@Override
	public void delete(Integer id){
		dcfwGzcCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dcfwGzcCommentDao.deleteBatch(ids);
	}

	@Override
	public R addComment(DcfwGzcCommentEntity commentEntity, MultipartFile[] imgFile) throws IOException {
		JSONObject obj = WebUtils.uploadFiles(imgFile, null, RedisConst.USER_BXWX_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
		commentEntity.setImgs(obj.getString("data"));
		save(commentEntity);
		DcfwGzcOrderEntity orderEntity = new DcfwGzcOrderEntity();
		orderEntity.setId(commentEntity.getOrderid());
		orderEntity.setStatus(APPConstant.TYPE_ORDER_STATUS_FINISHED);
		dcfwGzcOrderDao.update(orderEntity);
		return R.ok();
	}

	@Override
	public void exportExcel(DualHashBidiMap titleMapping, List<DcfwGzcCommentEntity> objectList,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			POIUtil.generateExcel(titleMapping, objectList, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<DcfwGzcCommentEntity> importExcel(Class<DcfwGzcCommentEntity> t, DualHashBidiMap titleMapping,
			MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
