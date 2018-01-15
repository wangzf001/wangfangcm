package com.lcworld.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TNoticeTypeEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.TNoticeTypeService;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 公告分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
@RestController
@RequestMapping("appuser/tnoticetype")
public class TNoticeTypeController {
	private Logger log = LoggerFactory.getLogger(TYytcMealController.class);
	@Autowired
	private TNoticeTypeService tNoticeTypeService;
	/**
	 * 查询分类列表
	 * @param biz
	 * @param sign
	 * @return
	 */
	@RequestMapping("/findNoticeTypeList")
	public R findNoticeTypeList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数APP
		Integer users = params.getInteger("users");
		if (users!=null&&users==1) {
			//添加uid到params
			Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
			if (ValidateUtil.isValid(uid)) {
				params.put("uid", uid);
			}else{
				//return R.error(1,"未登录");
			}
		}
		List<TNoticeTypeEntity> noticeTypeList = tNoticeTypeService.queryList(params);
		return R.ok().put("noticeTypeList", noticeTypeList);
	}
	/**
	 * 修改用户分类
	 * @param biz
	 * @param sign
	 * @return
	 */
	@RequestMapping("/updateUserNoticeType")
	public R updateUserNoticeType(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//添加uid到params
		Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
		if (ValidateUtil.isValid(uid)) {
			params.put("uid", uid);
		}else{
			//return R.error(1,"未登录");
		}
		//接口参数APP
		JSONArray typeIdList = params.getJSONArray("typeIdList");
		//typeIdList={1,2}
		R r = tNoticeTypeService.updateUserType(params);
		return r;
	}
	
}
