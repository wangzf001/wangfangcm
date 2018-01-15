package com.lcworld.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TDcfwFoodEntity;
import com.lcworld.service.TDcfwFoodService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订餐系统-食物
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
@RestController
@RequestMapping("appuser/tdcfwfood")
public class TDcfwFoodController {
	private Logger log = LoggerFactory.getLogger(TYytcMealController.class);
	@Autowired
	private TDcfwFoodService tDcfwFoodService;
	
	/**
	 * 获取食物列表
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findFoodList")
	public R findFoodList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		String keyword = params.getString("keyword");
		//查询列表数据
        Query query = new Query(params);

		List<TDcfwFoodEntity> tDcfwFoodList = tDcfwFoodService.queryList(query);
		int total = tDcfwFoodService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDcfwFoodList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
}
