package com.lcworld.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcworld.dao.TYytcIngredientDao;
import com.lcworld.dao.TYytcMealDao;
import com.lcworld.entity.TYytcIngredientEntity;
import com.lcworld.entity.TYytcMealEntity;
import com.lcworld.entity.TYytcStepEntity;
import com.lcworld.service.TYytcMealService;
import com.lcworld.utils.freemarker.FreeMarkerUtils;
import com.lcworld.utils.freemarker.FreemarkerUtil;
import com.lcworld.utils.freemarker.FreemarkerWriterFileNames;
import com.lcworld.utils.util.ValidateUtil;

import com.lcworld.dao.TYytcStepDao;



@Service("tYytcMealService")
public class TYytcMealServiceImpl implements TYytcMealService {
	private static Logger log = LoggerFactory.getLogger(TYytcMealServiceImpl.class);
	@Autowired
	private TYytcMealDao tYytcMealDao;
	@Autowired
	private TYytcIngredientDao tYytcIngredientDao;
	@Autowired
	private TYytcStepDao tYytcStepDao;
	
	@Override
	public TYytcMealEntity queryObject(Integer mId){
		return tYytcMealDao.queryObject(mId);
	}
	
	@Override
	public List<TYytcMealEntity> queryList(Map<String, Object> map){
		return tYytcMealDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tYytcMealDao.queryTotal(map);
	}
	
	@Override
	public void save(TYytcMealEntity tYytcMeal){
		tYytcMeal.setMCreateTime(new Date());
		tYytcMeal.setMFavorNum(0);
		tYytcMeal.setMPraiseNum(0);
		tYytcMeal.setMScanNum(0);
		tYytcMeal.setmRecommend(0);
		tYytcMealDao.save(tYytcMeal);
		//保存原料
		List<TYytcIngredientEntity> ingredientList = tYytcMeal.getIngredientList();
		if (ValidateUtil.isValid(ingredientList)) {
			for (int i = 0; i < ingredientList.size(); i++) {
				ingredientList.get(i).setMId(tYytcMeal.getMId());
			}
			tYytcIngredientDao.saveBatch(ingredientList);
		}
		//保存步骤
		List<TYytcStepEntity> stepList = tYytcMeal.getStepList();
		if (ValidateUtil.isValid(stepList)) {
			for (int i = 0; i < stepList.size(); i++) {
				stepList.get(i).setMId(tYytcMeal.getMId());
			}
			tYytcStepDao.saveBatch(stepList);
		}
		
	}
	
	@Override
	public void update(TYytcMealEntity tYytcMeal){
		Integer mId = tYytcMeal.getMId();
		Integer[] mIds = {mId};
		//删除步骤
		tYytcStepDao.deleteByMids(mIds);
		//刪除原料
		tYytcIngredientDao.deleteByMids(mIds);
		//保存原料
		List<TYytcIngredientEntity> ingredientList = tYytcMeal.getIngredientList();
		if (ValidateUtil.isValid(ingredientList)) {
			for (int i = 0; i < ingredientList.size(); i++) {
				ingredientList.get(i).setMId(tYytcMeal.getMId());
			}
			tYytcIngredientDao.saveBatch(ingredientList);
		}
		//保存步骤
		List<TYytcStepEntity> stepList = tYytcMeal.getStepList();
		if (ValidateUtil.isValid(stepList)) {
			for (int i = 0; i < stepList.size(); i++) {
				stepList.get(i).setMId(tYytcMeal.getMId());
			}
			tYytcStepDao.saveBatch(stepList);
		}
		tYytcMealDao.update(tYytcMeal);
	}
	
	@Override
	public void delete(Integer mId){
		tYytcMealDao.delete(mId);
	}
	
	@Override
	public void deleteBatch(Integer[] mIds){
		//删除步骤
		tYytcStepDao.deleteByMids(mIds);
		//刪除原料
		tYytcIngredientDao.deleteByMids(mIds);
		//删除菜单
		tYytcMealDao.deleteBatch(mIds);
	}

	@Override
	public void saveBatch(List<TYytcMealEntity> list) {
		tYytcMealDao.saveBatch(list);
	}

	@Override
	public void recommend(Integer[] mIds) {
		tYytcMealDao.recommend(mIds);
	}
	
}
