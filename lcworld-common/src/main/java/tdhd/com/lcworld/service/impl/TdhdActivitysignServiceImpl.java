package com.lcworld.service.impl;

import com.lcworld.dao.TdhdActivityDemandDao;
import com.lcworld.dto.TdhdActivityEnrollDTO;
import com.lcworld.entity.TdhdActivityDemandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TdhdActivityDao;
import com.lcworld.dao.TdhdActivitysignDao;
import com.lcworld.dto.TdhdActivityUserDTO;
import com.lcworld.entity.TdhdActivityEntity;
import com.lcworld.entity.TdhdActivitysignEntity;
import com.lcworld.service.TdhdActivitysignService;
import com.lcworld.utils.Query;
import com.lcworld.utils.util.ValidateUtil;



@Service("tdhdActivitysignService")
public class TdhdActivitysignServiceImpl implements TdhdActivitysignService {
	@Autowired
	private TdhdActivitysignDao tdhdActivitysignDao;
	@Autowired
	private TdhdActivityDao tdhdActivityDao;
	@Autowired
	private TdhdActivityDemandDao demandDao;
	
	@Override
	public TdhdActivitysignEntity queryObject(Integer asId){
		return tdhdActivitysignDao.queryObject(asId);
	}
	
	@Override
	public List<TdhdActivitysignEntity> queryList(Map<String, Object> map){
		return tdhdActivitysignDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tdhdActivitysignDao.queryTotal(map);
	}
	
	@Override
	public void save(TdhdActivitysignEntity tdhdActivitysign){
		tdhdActivitysignDao.save(tdhdActivitysign);
	}
	
	@Override
	public void update(TdhdActivitysignEntity tdhdActivitysign){
		tdhdActivitysignDao.update(tdhdActivitysign);
	}
	
	@Override
	public void delete(Integer asId){
		tdhdActivitysignDao.delete(asId);
	}
	
	@Override
	public void deleteBatch(Integer[] asIds){
		tdhdActivitysignDao.deleteBatch(asIds);
	}

    @Override
    public TdhdActivitysignEntity queryExistMember(TdhdActivitysignEntity sign) {
        JSONObject param = new JSONObject();
        param.put("uid", sign.getUserId());
        param.put("activityid", sign.getAId());
        List<TdhdActivitysignEntity> list = tdhdActivitysignDao.queryList(param);
        return ValidateUtil.isValid(list)?list.get(0):null;
    }

   
    @Override
    public void saveSign(TdhdActivitysignEntity sign, TdhdActivityEntity activity) {
        tdhdActivitysignDao.save(sign);
        activity.setaSignCount(activity.getaSignCount()+1);
        tdhdActivityDao.update(activity);
		TdhdActivityDemandEntity demand = activity.getDemand();
		demandDao.save(demand);
    }

    @Override
    public List<TdhdActivityUserDTO> queryuserList(Map<String,Object> query) {
   return tdhdActivitysignDao.queryuserList(query);
    }

    @Override
    public int queryuserTotal(Query query) {
        return tdhdActivitysignDao.queryuserTotal(query);
    }

    @Override
    public List<Map<String, Object>> queryuserList1(JSONObject params) {
        return tdhdActivitysignDao.queryuserList1(params);
    }

	@Override
	public List<TdhdActivityEnrollDTO> queryEnrollList(Map<String, Object> query) {
		return tdhdActivitysignDao.queryEnrollList(query);
	}
}
