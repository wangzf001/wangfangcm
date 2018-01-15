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
import com.lcworld.entity.ServiceEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.ServiceCancelReasonService;
import com.lcworld.service.ServiceService;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;

/**
 * 服务
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-07-10 16:04:29
 */
@RestController
@RequestMapping("appuser/service")
public class ServiceController {
	private Logger log = LoggerFactory.getLogger(ServiceController.class);
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private ServiceCancelReasonService serviceCancelReasonService;
	/**
	 * 获取服务信息
	 * @param req
	 * @return
	 */
	@RequestMapping("/getServiceInfo")
	public R getServiceInfo(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		Integer serviceType = params.getInteger("serviceType");
		//接口参数
		ServiceEntity serviceInfo = serviceService.queryService(serviceType);
		return R.ok().put("serviceInfo", serviceInfo);
	}
	
	/**
	 * 获取取消原因信息
	 * @param req
	 * @return
	 */
	@RequestMapping("/getCancelReason")
	public R getCancelReason(HttpServletRequest req){
	    String biz = req.getParameter("biz");
	    log.debug("biz:"+biz);
	    //查询列表数据
	    JSONObject params = JSONObject.parseObject(biz);
	    Integer serviceType = params.getInteger("serviceType");
	    //接口参数
	    return R.ok().put("servicereasonlist", serviceCancelReasonService.queryServiceCancelReason(serviceType));
	}
	/**
	 * 修改用户服务
	 * @param req
	 * @return
	 */
	@RequestMapping("/updateUserService")
	public R updateUserService(HttpServletRequest req){
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
		//获取的参数
		JSONArray serviceidList = params.getJSONArray("serviceidList");
		serviceService.updateUserService(params);
		return R.ok();
	}
	/**
	 * 查询用户服务
	 * @param req
	 * @return
	 */
	@RequestMapping("/findUserService")
	public R findUserService(HttpServletRequest req){
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
		List<ServiceEntity> serviceList = serviceService.queryList(params);
		return R.ok().put("serviceList", serviceList);
	}

}
