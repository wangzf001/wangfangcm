package com.lcworld.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TGxdfwClothestypeEntity;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TGxdfwClothestypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 干洗店服务-衣服类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:55
 */
@RestController
@RequestMapping("appuser/tgxdfwclothestype")
public class TGxdfwClothestypeController {
	private Logger log = LoggerFactory.getLogger(TGxdfwClothestypeController.class);
	@Autowired
	private TGxdfwClothestypeService tGxdfwClothestypeService;
	/**
	 * 查找衣服类型列表
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findClothesTypeList")
	public R findClothesTypeList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		//查询列表数据
        Query query = new Query(params);

		List<TGxdfwClothestypeEntity> tGxdfwClothestypeList = tGxdfwClothestypeService.queryList(query);
		int total = tGxdfwClothestypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tGxdfwClothestypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
}
