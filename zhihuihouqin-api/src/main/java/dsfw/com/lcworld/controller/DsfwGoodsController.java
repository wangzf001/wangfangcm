package com.lcworld.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.DsfwGoodsEntity;
import com.lcworld.service.DsfwCategoryService;
import com.lcworld.service.DsfwGoodsService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;

import com.lcworld.entity.DsfwCategoryEntity;


/**
 * 订水服务-商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:21
 */
@RestController
@RequestMapping("appuser/dsfwgoods")
public class DsfwGoodsController {
	private Logger log = LoggerFactory.getLogger(DsfwGoodsController.class);
	@Autowired
	private DsfwGoodsService dsfwGoodsService;
	@Autowired
	private DsfwCategoryService dsfwCategoryService;
	
	/**
	 * 查询商品分类
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findCategoryList")
	public R findCategoryList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//查询列表数据
		List<DsfwCategoryEntity> categoryList = dsfwCategoryService.queryList(params);
		return R.ok().put("categoryList", categoryList);
	}
	/**
	 * 查询商品
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findGoodsList")
	public R findGoodsList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		Integer categoryId = params.getInteger("categoryId");
		//查询列表数据
        Query query = new Query(params);
		List<DsfwGoodsEntity> productList = dsfwGoodsService.queryList(query);
		int total = dsfwGoodsService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(productList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
}
