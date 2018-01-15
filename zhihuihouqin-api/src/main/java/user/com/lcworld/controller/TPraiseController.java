package com.lcworld.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TPraiseEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.TPraiseService;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 10:53:19
 */
@RestController
@RequestMapping("appuser/tpraise")
public class TPraiseController {
	private Logger log = LoggerFactory.getLogger(TPraiseController.class);
	@Autowired
	private TPraiseService tPraiseService;
	/**
	 * 点击点赞按钮(添加或删除)
	 * @param biz
	 * @return
	 */
	@RequestMapping("/addPraise")
	public R addPraise(HttpServletRequest req){
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
		TPraiseEntity praise = new TPraiseEntity();
		praise.setUpTargetType(type);
		praise.setEntityid(entityid);
		praise.setUid(params.getInteger("uid"));
		tPraiseService.saveOrUpdate(praise);
		return R.ok();
	}
}
