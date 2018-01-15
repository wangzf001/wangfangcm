package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TBxwxMenderItemDao;
import com.lcworld.dto.BxwxMenderItemDTO;
import com.lcworld.entity.TBxwxMenderItemEntity;
import com.lcworld.service.TBxwxMenderItemService;
import com.lcworld.utils.Query;



@Service("tBxwxMenderItemService")
public class TBxwxMenderItemServiceImpl implements TBxwxMenderItemService {
	@Autowired
	private TBxwxMenderItemDao tBxwxMenderItemDao;
	
	@Override
	public TBxwxMenderItemEntity queryObject(Integer id){
		return tBxwxMenderItemDao.queryObject(id);
	}
	
	@Override
	public List<TBxwxMenderItemEntity> queryList(Map<String, Object> map){
		return tBxwxMenderItemDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tBxwxMenderItemDao.queryTotal(map);
	}
	
	@Override
	public void save(TBxwxMenderItemEntity tBxwxMenderItem){
		tBxwxMenderItemDao.save(tBxwxMenderItem);
	}
	
	@Override
	public void update(TBxwxMenderItemEntity tBxwxMenderItem){
		tBxwxMenderItemDao.update(tBxwxMenderItem);
	}
	
	@Override
	public void delete(Integer id){
		tBxwxMenderItemDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tBxwxMenderItemDao.deleteBatch(ids);
	}

    @Override
    public void deleteByParams(JSONObject params) {
        tBxwxMenderItemDao.deleteByParams(params);
    }

    @Override
    public void savebench(List<TBxwxMenderItemEntity> rlist) {
        tBxwxMenderItemDao.savebench(rlist);
        
    }

    @Override
    public List<BxwxMenderItemDTO> queryUserItemList(Query q) {
        return tBxwxMenderItemDao.queryUserItemList(q);
    }

    @Override
    public List<Map<String, Object>> querymenderitemlist(Query query) {
        return tBxwxMenderItemDao.querymenderitemlist(query);
    }

    @Override
    public int querymenderitemTotal(Query query) {
        return tBxwxMenderItemDao.querymenderitemTotal(query);
    }
	
}
