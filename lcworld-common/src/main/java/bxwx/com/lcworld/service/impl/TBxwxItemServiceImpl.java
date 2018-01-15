package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TBxwxItemDao;
import com.lcworld.dto.BxwxMenderItemDTO;
import com.lcworld.entity.TBxwxItemEntity;
import com.lcworld.service.TBxwxItemService;
import com.lcworld.utils.Query;



@Service("tBxwxItemService")
public class TBxwxItemServiceImpl implements TBxwxItemService {
	@Autowired
	private TBxwxItemDao tBxwxItemDao;
	
	@Override
	public TBxwxItemEntity queryObject(Integer id){
		return tBxwxItemDao.queryObject(id);
	}
	
	@Override
	public List<TBxwxItemEntity> queryList(Map<String, Object> map){
		return tBxwxItemDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tBxwxItemDao.queryTotal(map);
	}
	
	@Override
	public void save(TBxwxItemEntity tBxwxItem){
		tBxwxItemDao.save(tBxwxItem);
	}
	
	@Override
	public void update(TBxwxItemEntity tBxwxItem){
		tBxwxItemDao.update(tBxwxItem);
	}
	
	@Override
	public void delete(Integer id){
		tBxwxItemDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tBxwxItemDao.deleteBatch(ids);
	}

    @Override
    public List<BxwxMenderItemDTO> queryItemList(Query q) {
        return tBxwxItemDao.queryItemList(q);
    }
	
}
