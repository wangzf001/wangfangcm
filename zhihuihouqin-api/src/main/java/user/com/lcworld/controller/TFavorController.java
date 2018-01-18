package com.lcworld.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.FavorConst;
import com.lcworld.entity.TFavorEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.TFavorService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;



/**
 * 收藏
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 10:53:19
 */
@RestController
@RequestMapping("appuser/tfavor")
public class TFavorController {
	private Logger log = LoggerFactory.getLogger(TFavorController.class);
	@Autowired
	private TFavorService tFavorService;
	
	/**
	 * 点击收藏按钮(添加或删除)
	 * @param biz
	 * @return
	 */
	@RequestMapping("/addFavor")
	public R addFavor(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//添加uid到params
		Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
		if (ValidateUtil.isValid(uid)) {
			params.put("uid", uid);
		}else{
			//return R.error(1,"未登录");
		}
		//接口参数
		//收藏类型APPContent里定义
		Integer type = params.getInteger("type");
		//收藏目标id
		Integer entityid = params.getInteger("entityid");
		Integer id = params.getInteger("id");
		TFavorEntity favor = new TFavorEntity();
		if (id!=null) {
			favor.setId(id);
		}
		favor.setFavortype(type);
		favor.setEntityid(entityid);
		favor.setUid(params.getInteger("uid"));
		R r = tFavorService.saveOrUpdate(favor);
		return r;
	}
	/**
	 * 查询用户收藏
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findUserFavorList")
	public R findUserFavorList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//添加uid到params
		Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
		if (ValidateUtil.isValid(uid)) {
			params.put("uid", uid);
		}else{
			//return R.error(1,"未登录");
		}
		//接口参数
		Integer type = params.getInteger("serviceType");
		//只查询收藏的
		params.put("status", APPConstant.TYPE_STATUS_HAS);
		Map<String,Object> map = new HashMap<>();
		map.put("favorstatus", APPConstant.TYPE_STATUS_HAS);
		map.put("favoruid", params.getInteger("uid"));
		map.put("favortype", type);
		Query query = new Query(map);
		//ICollectionService<?> service = favorServiceFactory.getService(type);
		List<?> list = null;//service.queryFavorList(query);
		return R.ok().put("list", list);
	}
//	/**
//	 * 查询收藏对应列表
//	 * @param params
//	 * @param type
//	 * @return
//	 */
//	private List findTargetList(Map<String,Object> params, Integer type) {
//		switch (type.intValue()) {
//		case FavorConst.FAVORTYPE_NUTRITIOUS_MEALS:
//			return tYytcMealService.queryList(params);
//		case FavorConst.FAVORTYPE_BGYP_PRODUCT:
//			return bgypfwProductService.queryList(params);
//		case FavorConst.FAVORTYPE_XXGG:
//			return tNoticeService.queryList(params);
//		default:
//			return new ArrayList<>();
//		}
//	}
	/**
	 * 查询用户是否收藏
	 * @param biz
	 * @return
	 */
	@RequestMapping("/queryFavor")
	public R queryFavor(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//添加uid到params
		Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
		if (ValidateUtil.isValid(uid)) {
			params.put("uid", uid);
		}else{
			//return R.error(1,"未登录");
		}
		//接口参数
		//收藏类型APPContent里定义
		Integer type = params.getInteger("type");
		//收藏目标id
		Integer entityid = params.getInteger("entityid");
		int status = tFavorService.queryFavorStatus(params.getInteger("uid"), type, entityid);
		return R.ok().put("status", status);
	}
	/**
	 * 点击收藏按钮(添加或删除)
	 * @param biz
	 * @return
	 */
	@RequestMapping("/deleteFavor")
	public R deleteFavor(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//添加uid到params
		Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
		if (ValidateUtil.isValid(uid)) {
			params.put("uid", uid);
		}else{
			//return R.error(1,"未登录");
		}
		//接口参数
		//list:[{type:1,entityid:1}]
		R r = tFavorService.deleteBatch(params);
		return r;
	}
}
