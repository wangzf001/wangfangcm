package com.lcworld.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.FavorConst;
import com.lcworld.dao.TFavorDao;
import com.lcworld.entity.TFavorEntity;
import com.lcworld.service.TFavorService;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;



@Service("tFavorService")
public class TFavorServiceImpl implements TFavorService {
	@Autowired
	private TFavorDao tFavorDao;
	
	@Override
	public TFavorEntity queryObject(Integer id){
		return tFavorDao.queryObject(id);
	}
	
	@Override
	public List<TFavorEntity> queryList(Map<String, Object> map){
		return tFavorDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tFavorDao.queryTotal(map);
	}
	
	@Override
	public void save(TFavorEntity tFavor){
		tFavorDao.save(tFavor);
	}
	
	@Override
	public void update(TFavorEntity tFavor){
		tFavorDao.update(tFavor);
	}
	
	@Override
	public void delete(Integer id){
		tFavorDao.delete(id);
	}
	
	@Override
	public R saveOrUpdate(TFavorEntity favor) {
		//条件查询数据库中是否存在
		HashMap<String,Object> paramsMap = new HashMap<>();
		if (ValidateUtil.isValid(favor.getId())) {
			paramsMap.put("id", favor.getId());
		}else{
			paramsMap.put("uid", favor.getUid());
			paramsMap.put("type", favor.getFavortype());
			paramsMap.put("entityid", favor.getEntityid());
		}
		List<TFavorEntity> favorList = tFavorDao.queryList(paramsMap);
		int addOrDel = -1;
		if (ValidateUtil.isValid(favorList)) {
			//对象存在更新
			TFavorEntity favorEntity = favorList.get(0);
			addOrDel = Math.abs(favorEntity.getStatus()-1);
			favorEntity.setStatus(addOrDel);
			tFavorDao.update(favorEntity);
		}else{
			//对象不存在
			addOrDel = APPConstant.TYPE_STATUS_HAS;
			favor.setStatus(addOrDel);
			favor.setCreatetime(new Date());
			tFavorDao.save(favor);
		}
		//改变对应对象中的点赞数量
		changeFavorNumOf(favor.getFavortype(),favor.getEntityid(),addOrDel);
		return R.ok().put("status", addOrDel);
	}

	private void changeFavorNumOf(Integer favortype, Integer entityid, int addOrDel) {
		int amount = addOrDel == 0?-1:1;
		switch (favortype.intValue()) {
		    
		default:
			break;
		}
	}

    @Override
    public int queryFavorStatus(Integer uid,Integer favortype, Integer entityid) {
        JSONObject params = new JSONObject();
        if(ValidateUtil.isValid(uid)){
            params.put("uid", uid.toString());
            params.put("type", favortype);
            params.put("entityid",entityid);
            List<TFavorEntity> favorList = tFavorDao.queryList(params);
            return ValidateUtil.isValid(favorList)?favorList.get(0).getStatus():0;
        }else{
            return 0;
        }
    }

	@Override
	public R deleteBatch(JSONObject params) {
		JSONArray list = params.getJSONArray("list");
		TFavorEntity favor = new TFavorEntity();
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = list.getJSONObject(i);
			favor.setUid(params.getInteger("uid"));
			favor.setEntityid(obj.getInteger("entityid"));
			favor.setFavortype(obj.getInteger("type"));
			saveOrUpdate(favor);
		}
		return R.ok();
	}
	
}
