package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TsjyNoticeDao;
import com.lcworld.entity.TsjyNoticeEntity;
import com.lcworld.service.TsjyNoticeService;
import com.lcworld.utils.Query;



@Service("tsjyNoticeService")
public class TsjyNoticeServiceImpl implements TsjyNoticeService {
	@Autowired
	private TsjyNoticeDao tsjyNoticeDao;
	
	@Override
	public TsjyNoticeEntity queryObject(Integer id){
		return tsjyNoticeDao.queryObject(id);
	}
	
	@Override
	public List<TsjyNoticeEntity> queryList(Map<String, Object> map){
		return tsjyNoticeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tsjyNoticeDao.queryTotal(map);
	}
	
	@Override
	public void save(TsjyNoticeEntity tsjyNotice){
		tsjyNoticeDao.save(tsjyNotice);
	}
	
	@Override
	public void update(TsjyNoticeEntity tsjyNotice){
		tsjyNoticeDao.update(tsjyNotice);
	}
	
	@Override
	public void delete(Integer id){
		tsjyNoticeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tsjyNoticeDao.deleteBatch(ids);
	}

    @Override
    public List<TsjyNoticeEntity> queryNoticeList(Query q) {
        return tsjyNoticeDao.queryNoticeList(q);
    }

    @Override
    public int queryNoticeListTotal(Query q) {
        return tsjyNoticeDao.queryNoticeListTotal(q);
    }
	
}
