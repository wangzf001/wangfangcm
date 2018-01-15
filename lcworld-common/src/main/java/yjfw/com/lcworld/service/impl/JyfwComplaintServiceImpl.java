package com.lcworld.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.JyfwComplaintDao;
import com.lcworld.entity.JyfwComplaintEntity;
import com.lcworld.oss.OSSFactory;
import com.lcworld.service.JyfwComplaintService;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.OSSConstantKey;
import com.lcworld.utils.OSSUtils;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.utils.WebUtils;



@Service("jyfwComplaintService")
public class JyfwComplaintServiceImpl implements JyfwComplaintService {
	@Autowired
	private JyfwComplaintDao jyfwComplaintDao;
	
	@Override
	public JyfwComplaintEntity queryObject(Integer id){
		JyfwComplaintEntity complaint = jyfwComplaintDao.queryObject(id);
		String imgsStr = complaint.getImgsStr();
		if (ValidateUtil.isValid(imgsStr)) {
			String[] imgsArr = imgsStr.split(",");
			complaint.setImgs(Arrays.asList(imgsArr));
		}
		return complaint;
	}
	
	@Override
	public List<JyfwComplaintEntity> queryList(Map<String, Object> map){
		List<JyfwComplaintEntity> list = jyfwComplaintDao.queryList(map);
		if (ValidateUtil.isValid(list)) {
			for (int i = 0; i < list.size(); i++) {
				JyfwComplaintEntity c = list.get(i);
				String imgsStr = c.getImgsStr();
				if (ValidateUtil.isValid(imgsStr)) {
					String[] imgsArr = imgsStr.split(",");
					c.setImgs(Arrays.asList(imgsArr));
				}
			}
		}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return jyfwComplaintDao.queryTotal(map);
	}
	
	@Override
	public void save(JyfwComplaintEntity jyfwComplaint){
		jyfwComplaintDao.save(jyfwComplaint);
	}
	
	@Override
	public void update(JyfwComplaintEntity jyfwComplaint){
		jyfwComplaintDao.update(jyfwComplaint);
	}
	
	@Override
	public void delete(Integer id){
		jyfwComplaintDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		jyfwComplaintDao.deleteBatch(ids);
	}

	@Override
	public void addComplaint(JyfwComplaintEntity complaintEntity, MultipartFile[] imgFile) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		/*for(MultipartFile file:imgFile){
	        String fileName = file.getOriginalFilename();// 文件原名称
	        String filePath = DateUtils.format(new Date())+"/"+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."), fileName.length());
	        OSSUtils.uploadFile(filePath, file.getInputStream());
	        String imgUrl = OSSConstantKey.OSS_BASE_URL+filePath;
	        sb.append(",").append(imgUrl);
		}*/
		
		JSONObject obj = WebUtils.uploadFiles(imgFile, null, RedisConst.USER_JYFW_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
		complaintEntity.setImgsStr(obj.getString("data"));
		if(!StringUtils.isBlank(sb.toString())){
			complaintEntity.setImgsStr(sb.toString().substring(1));
		}
		save(complaintEntity);
	}
	
}
