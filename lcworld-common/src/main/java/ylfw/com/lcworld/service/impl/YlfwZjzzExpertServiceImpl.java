package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.YlfwZjzzExpertDao;
import com.lcworld.dto.DoctorDTO;
import com.lcworld.dto.ZjzzExpertDTO;
import com.lcworld.entity.YlfwZjzzExpertEntity;
import com.lcworld.service.YlfwZjzzExpertService;
import com.lcworld.utils.Query;



@Service("ylfwZjzzExpertService")
public class YlfwZjzzExpertServiceImpl implements YlfwZjzzExpertService {
	@Autowired
	private YlfwZjzzExpertDao ylfwZjzzExpertDao;
	
	@Override
	public YlfwZjzzExpertEntity queryObject(Integer id){
		return ylfwZjzzExpertDao.queryObject(id);
	}
	
	@Override
	public List<YlfwZjzzExpertEntity> queryList(Map<String, Object> map){
		return ylfwZjzzExpertDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwZjzzExpertDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwZjzzExpertEntity ylfwZjzzExpert){
		ylfwZjzzExpertDao.save(ylfwZjzzExpert);
	}
	
	@Override
	public void update(YlfwZjzzExpertEntity ylfwZjzzExpert){
		ylfwZjzzExpertDao.update(ylfwZjzzExpert);
	}
	
	@Override
	public void delete(Integer id){
		ylfwZjzzExpertDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwZjzzExpertDao.deleteBatch(ids);
	}

    @Override
    public List<ZjzzExpertDTO> queryDoctorList(Query q) {
       return ylfwZjzzExpertDao.queryDoctorList(q);
    }

    @Override
    public List<Map<String,Object>> queryexpertList(Query query) {
        return ylfwZjzzExpertDao.queryexpertList(query);
    }

    @Override
    public int queryexpertTotal(Query query) {
        return ylfwZjzzExpertDao.queryexpertTotal(query);
    }

	@Override
	public List<YlfwZjzzExpertEntity> queryFavorList(Query params) {
		return queryList(params);
	}
	
}
