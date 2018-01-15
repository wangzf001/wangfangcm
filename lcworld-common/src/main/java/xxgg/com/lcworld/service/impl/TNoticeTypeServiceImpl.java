package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TNoticeTypeDao;
import com.lcworld.entity.TNoticeTypeEntity;
import com.lcworld.service.TNoticeTypeService;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;



@Service("tNoticeTypeService")
public class TNoticeTypeServiceImpl implements TNoticeTypeService {
	@Autowired
	private TNoticeTypeDao tNoticeTypeDao;
	
	@Override
	public TNoticeTypeEntity queryObject(Integer id){
		return tNoticeTypeDao.queryObject(id);
	}
	
	@Override
	public List<TNoticeTypeEntity> queryList(Map<String, Object> map){
		return tNoticeTypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tNoticeTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(TNoticeTypeEntity tNoticeType){
		tNoticeTypeDao.save(tNoticeType);
	}
	
	@Override
	public void update(TNoticeTypeEntity tNoticeType){
		tNoticeTypeDao.update(tNoticeType);
	}
	
	@Override
	public void delete(Integer id){
		tNoticeTypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tNoticeTypeDao.deleteBatch(ids);
	}

	@Override
	public R updateUserType(JSONObject params) {
		//先删除用户所有选择类型
		tNoticeTypeDao.deleteUserType(params);
		//添加用户选择
		JSONArray typeIdList = params.getJSONArray("typeIdList");
		if (ValidateUtil.isValid(typeIdList)) {
			Integer uid = params.getInteger("uid");
			Map<String,Object> map = new HashMap<>();
			map.put("uid", uid);
			map.put("typeIdList", typeIdList.toArray());
			tNoticeTypeDao.addUserSelectedType(map);
		}
		return R.ok();
	}
	public static void main(String[] args) {
		ArrayList<Object> arrayList = new ArrayList<>();
		System.out.println(ValidateUtil.isValid(arrayList));
	}
}
