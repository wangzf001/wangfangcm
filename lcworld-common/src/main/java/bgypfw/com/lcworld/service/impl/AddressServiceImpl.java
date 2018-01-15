package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.AddressDao;
import com.lcworld.entity.AddressEntity;
import com.lcworld.service.AddressService;
import com.lcworld.utils.util.ValidateUtil;



@Service("bgypfwAddressService")
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDao bgypfwAddressDao;
	
	@Override
	public AddressEntity queryObject(Integer id){
		return bgypfwAddressDao.queryObject(id);
	}
	
	@Override
	public List<AddressEntity> queryList(Map<String, Object> map){
		return bgypfwAddressDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwAddressDao.queryTotal(map);
	}
	
	@Override
	public void save(AddressEntity bgypfwAddress){
		bgypfwAddressDao.save(bgypfwAddress);
	}
	@Transactional
	@Override
	public void update(AddressEntity bgypfwAddress){
		if (ValidateUtil.isValid(bgypfwAddress.getIsDefault())&&"1".equals(bgypfwAddress.getIsDefault().toString())) {
			//需要修改成默认的地址，首先需要清除所有默认地址
			Integer uid = bgypfwAddress.getUid();
			bgypfwAddressDao.clearAllDefault(uid);
		}else{
			bgypfwAddress.setIsDefault(0);
		}
		bgypfwAddressDao.update(bgypfwAddress);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwAddressDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwAddressDao.deleteBatch(ids);
	}
	
}
