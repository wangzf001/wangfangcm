package com.lcworld.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.BuildingDao;
import com.lcworld.dao.EnergyCostDao;
import com.lcworld.entity.BuildingEntity;
import com.lcworld.entity.EnergyCostEntity;
import com.lcworld.service.EnergyCostService;
import com.lcworld.utils.ConfigConstant;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.HttpUtils;


@Service("energyCostService")
public class EnergyCostServiceImpl implements EnergyCostService {
    @Autowired
    private EnergyCostDao energyCostDao;
    @Autowired
    private BuildingDao buildingDao;

    @Override
    public EnergyCostEntity queryObject(Integer eId) {
        return energyCostDao.queryObject(eId);
    }

    @Override
    public List<EnergyCostEntity> queryList(Map<String, Object> map) {
        return energyCostDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return energyCostDao.queryTotal(map);
    }


    @Override
    public void save(EnergyCostEntity energyCost) {
        energyCostDao.save(energyCost);
    }

    @Override
    public void update(EnergyCostEntity energyCost) {
        energyCostDao.update(energyCost);
    }

    @Override
    public void delete(Integer eId) {
        energyCostDao.delete(eId);
    }

    @Override
    public void deleteBatch(Integer[] eIds) {
        energyCostDao.deleteBatch(eIds);
    }

    @Override
    public void remoteGetEnergyCost() {
        String day = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
        String result = HttpUtils.sendPost(ConfigConstant.ENERGY_COST_URL, "day=" + day);
        if (StringUtils.isNotEmpty(result)) {
            try {
                Document document = DocumentHelper.parseText(result);
                Element rootElement = document.getRootElement();
                String text = rootElement.getText();
                JSONArray jsonArray = JSONObject.parseArray(text);
                for (Object object : jsonArray) {
                    JSONObject jsonObject = (JSONObject) object;
                    BuildingEntity buildingEntity = buildingDao.queryObjectByName(jsonObject.getString("BuildName"));
                    if(buildingEntity != null){
                        EnergyCostEntity costEntity = new EnergyCostEntity();
                        costEntity.setEnergyCost(new BigDecimal(jsonObject.getString("EnergyCost")));
                        costEntity.setCreatTime(day);
                        if ("电".equals(jsonObject.getString("EnergyType").trim())) {
                            costEntity.setEnergyType(1);
                        } else {
                            costEntity.setEnergyType(2);
                        }
                        costEntity.setBuildId(buildingEntity.getId());
                        save(costEntity);
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public List<EnergyCostEntity> energyCostQuery(Map<String, Object> map) {
		return energyCostDao.energyCostQuery(map);
	}
	
    @Override
    public EnergyCostEntity energyCostTotalQuery(Map<String, Object> map) {
        return energyCostDao.energyCostTotalQuery(map);
    }

	@Override
	public Integer bothCostQuery(Integer energyType) {
		
		return  energyCostDao.bothCostQuery(energyType);
	}

}
