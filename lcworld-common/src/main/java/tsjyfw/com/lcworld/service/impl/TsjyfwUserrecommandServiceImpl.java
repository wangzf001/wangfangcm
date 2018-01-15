package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TsjyfwUserrecommandDao;
import com.lcworld.entity.TsjyfwUserrecommandEntity;
import com.lcworld.service.TsjyfwUserrecommandService;
import com.lcworld.utils.Query;



@Service("tsjyfwUserrecommandService")
public class TsjyfwUserrecommandServiceImpl implements TsjyfwUserrecommandService {
	@Autowired
	private TsjyfwUserrecommandDao tsjyfwUserrecommandDao;
	
	@Override
	public TsjyfwUserrecommandEntity queryObject(Integer id){
		return tsjyfwUserrecommandDao.queryObject(id);
	}
	
	@Override
	public List<TsjyfwUserrecommandEntity> queryList(Map<String, Object> map){
		return tsjyfwUserrecommandDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tsjyfwUserrecommandDao.queryTotal(map);
	}
	
	@Override
	public void save(TsjyfwUserrecommandEntity tsjyfwUserrecommand){
		tsjyfwUserrecommandDao.save(tsjyfwUserrecommand);
	}
	
	@Override
	public void update(TsjyfwUserrecommandEntity tsjyfwUserrecommand){
		tsjyfwUserrecommandDao.update(tsjyfwUserrecommand);
	}
	
	@Override
	public void delete(Integer id){
		tsjyfwUserrecommandDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tsjyfwUserrecommandDao.deleteBatch(ids);
	}

    @Override
    public List<Map<String,Object>> queryrecommandList(Query query) {
        return tsjyfwUserrecommandDao.queryrecommandList(query);
    }

    @Override
    public int queryrecommandTotal(Query query) {
        return tsjyfwUserrecommandDao.queryrecommandTotal(query);
    }
	
}
