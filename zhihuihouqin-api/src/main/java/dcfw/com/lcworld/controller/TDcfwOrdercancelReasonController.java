package com.lcworld.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TDcfwOrdercancelReasonEntity;
import com.lcworld.service.TDcfwOrdercancelReasonService;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 14:32:20
 */
@RestController
@RequestMapping("appuser/tdcfwordercancelreason")
public class TDcfwOrdercancelReasonController {
	private Logger log = LoggerFactory.getLogger(TYytcMealController.class);
	@Autowired
	private TDcfwOrdercancelReasonService tDcfwOrdercancelReasonService;
	
	/**
	 * 退货原因列表
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findReasonList")
	public R findReasonList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//查询列表数据
		List<TDcfwOrdercancelReasonEntity> reasonList = tDcfwOrdercancelReasonService.queryList(params);
		log.debug("reasonList:"+reasonList);
		return R.ok().put("reasonList", reasonList);
	}
}
