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
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.AddressEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.AddressService;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 19:32:34
 */
@RestController
@RequestMapping("appuser/address")
public class AddressController {
	private Logger log = LoggerFactory.getLogger(AddressController.class);
	@Autowired
	private AddressService addressService;
	
	/**
	 * 查询用户地址
	 * @param biz
	 * @param sign
	 * @return
	 */
	@RequestMapping("/findUserAddressList")
	public R findUserAddressList(HttpServletRequest req){
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
		Integer isRecommend = params.getInteger("isDefault");
		List<AddressEntity> addressList = addressService.queryList(params);
		return R.ok().put("addressList", addressList);
	}
	/**
	 * 添加地址
	 * @param req
	 * @return
	 */
	@RequestMapping("/addAddress")
	public R addAddress(HttpServletRequest req){
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
		String realname = params.getString("realname");
		String mobile = params.getString("mobile");
		String address = params.getString("address");
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setMobile(mobile);
		addressEntity.setRealname(realname);
		addressEntity.setUid(params.getInteger("uid"));
		addressEntity.setAddress(address);
		if (addressService.queryTotal(params)==0) {
			addressEntity.setIsDefault(APPConstant.ADDRESS_DEFAULT);
		}else{
			addressEntity.setIsDefault(APPConstant.ADDRESS_NOTDEFAULT);
		}
		addressService.save(addressEntity);
		return R.ok();
	}
	/**
	 * 修改地址
	 * @param req
	 * @return
	 */
	@RequestMapping("/updateAddress")
	public R updateAddress(HttpServletRequest req){
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
		Integer id = params.getInteger("id");
		String realname = params.getString("realname");
		String mobile = params.getString("mobile");
		String address = params.getString("address");
		Integer isDefault = params.getInteger("isDefault");
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setId(id);
		addressEntity.setMobile(mobile);
		addressEntity.setRealname(realname);
		addressEntity.setUid(params.getInteger("uid"));
		addressEntity.setAddress(address);
		addressEntity.setIsDefault(isDefault);
		addressService.update(addressEntity);
		return R.ok();
	}
	/**
	 * 删除地址
	 * @param req
	 * @return
	 */
	@RequestMapping("/deleteAddress")
	public R deleteAddress(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数APP
		JSONArray ids = params.getJSONArray("ids");
		addressService.deleteBatch(ids.toArray(new Integer[1]));
		return R.ok();
	}
	/**
	 * 查询地址详情
	 * @param req
	 * @return
	 */
	@RequestMapping("/getAddress")
	public R getAddress(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数APP
		Integer id = params.getInteger("id");
		AddressEntity address = addressService.queryObject(id);
		return R.ok().put("address", address);
	}
	
}
