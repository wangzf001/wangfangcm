package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TsjyfwBookCopyDao;
import com.lcworld.entity.TsjyfwBookCopyEntity;
import com.lcworld.service.TsjyfwBookCopyService;



@Service("tsjyfwBookCopyService")
public class TsjyfwBookCopyServiceImpl implements TsjyfwBookCopyService {
	@Autowired
	private TsjyfwBookCopyDao tsjyfwBookCopyDao;
	
	@Override
	public TsjyfwBookCopyEntity queryObject(Integer id){
		return tsjyfwBookCopyDao.queryObject(id);
	}
	
	@Override
	public List<TsjyfwBookCopyEntity> queryList(Map<String, Object> map){
		return tsjyfwBookCopyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tsjyfwBookCopyDao.queryTotal(map);
	}
	
	@Override
	public void save(TsjyfwBookCopyEntity tsjyfwBookCopy){
		tsjyfwBookCopyDao.save(tsjyfwBookCopy);
	}
	
	@Override
	public void update(TsjyfwBookCopyEntity tsjyfwBookCopy){
		tsjyfwBookCopyDao.update(tsjyfwBookCopy);
	}
	
	@Override
	public void delete(Integer id){
		tsjyfwBookCopyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tsjyfwBookCopyDao.deleteBatch(ids);
	}

    @Override
    public void saveAddOrderCountBench(List<Integer> ids) {
        tsjyfwBookCopyDao.saveAddOrderCountBench(ids);
    }
	
}
