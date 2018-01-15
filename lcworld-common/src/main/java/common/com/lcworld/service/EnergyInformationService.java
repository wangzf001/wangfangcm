package com.lcworld.service;

import com.lcworld.entity.EnergyInformationEntity;

import java.util.List;
import java.util.Map;

/**
 * 节能减排资讯信息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-16 15:59:18
 */
public interface EnergyInformationService {
	
	EnergyInformationEntity queryObject(Integer id);
	
	List<EnergyInformationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EnergyInformationEntity energyInformation);
	
	void update(EnergyInformationEntity energyInformation);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
