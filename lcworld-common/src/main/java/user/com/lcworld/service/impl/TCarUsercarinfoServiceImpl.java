package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TCarUsercarinfoDao;
import com.lcworld.dto.UserCarDTO;
import com.lcworld.entity.TCarUsercarinfoEntity;
import com.lcworld.service.TCarUsercarinfoService;
import com.lcworld.utils.Query;



@Service("tCarUsercarinfoService")
public class TCarUsercarinfoServiceImpl implements TCarUsercarinfoService {
	@Autowired
	private TCarUsercarinfoDao tCarUsercarinfoDao;
	
	@Override
	public TCarUsercarinfoEntity queryObject(Integer id){
		return tCarUsercarinfoDao.queryObject(id);
	}
	
	@Override
	public List<TCarUsercarinfoEntity> queryList(Map<String, Object> map){
		return tCarUsercarinfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tCarUsercarinfoDao.queryTotal(map);
	}
	
	@Override
	public void save(TCarUsercarinfoEntity tCarUsercarinfo){
		tCarUsercarinfoDao.save(tCarUsercarinfo);
	}
	
	@Override
	public void update(TCarUsercarinfoEntity tCarUsercarinfo){
		tCarUsercarinfoDao.update(tCarUsercarinfo);
	}
	
	@Override
	public void delete(Integer id){
		tCarUsercarinfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tCarUsercarinfoDao.deleteBatch(ids);
	}


    @Override
    public List<UserCarDTO> queryUserCarinfoList(Query query) {
        return tCarUsercarinfoDao.queryUserCarinfoList(query);
    }
	
}
