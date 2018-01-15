package com.lcworld.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.BgypfwCartEntity;
import com.lcworld.entity.BgypfwSkuidEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.BgypfwCartService;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;

import com.lcworld.service.BgypfwSkuidService;


/**
 * 办公用品服务-购物车
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
@RestController
@RequestMapping("appuser/bgypfwcart")
public class BgypfwCartController {
	private Logger log = LoggerFactory.getLogger(BgypfwCartController.class);
	@Autowired
	private BgypfwCartService bgypfwCartService;
	@Autowired
	private BgypfwSkuidService bgypfwSkuidService;
	
	/**
	 * 加入购物车
	 * @param biz
	 * @return
	 */
	@RequestMapping("/addToCart")
	public R addToCart(HttpServletRequest req){
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
		//接口参数
		Integer skuid = params.getInteger("skuid");
		Integer count = params.getInteger("count");
		
		List<BgypfwCartEntity> carts = bgypfwCartService.queryList(params);
		if (ValidateUtil.isValid(carts)) {
			BgypfwCartEntity cart = carts.get(0);
			cart.setCount(cart.getCount()+count);
			bgypfwCartService.update(cart);
		}else{
			BgypfwCartEntity cartEntity = new BgypfwCartEntity();
			cartEntity.setCount(count);
			cartEntity.setCreatetime(new Date());
			cartEntity.setSkuid(skuid);
			cartEntity.setUid(params.getInteger("uid"));
			BgypfwSkuidEntity sku = bgypfwSkuidService.queryObject(skuid);
			cartEntity.setProductid(sku.getProductid());
			bgypfwCartService.save(cartEntity);
		}
		return R.ok();
	}
	/**
	 * 删除购物车项
	 * @param biz
	 * @return
	 */
	@RequestMapping("/deleteCartItems")
	public R deleteCartItems(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		JSONArray ids = params.getJSONArray("ids");
		bgypfwCartService.deleteBatch(ids.toArray(new Integer[1]));
		return R.ok();
	}
	/**
	 * 修改商品购买数
	 * @param biz
	 * @return
	 */
	@RequestMapping("/updateCount")
	public R updateCount(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		Integer id = params.getInteger("id");
		Integer count = params.getInteger("count");
		BgypfwCartEntity cartEntity = bgypfwCartService.queryObject(id);
		BgypfwSkuidEntity sku = bgypfwSkuidService.queryObject(cartEntity.getSkuid());
		Integer store = sku.getStore();
		Integer newCount = cartEntity.getCount()+count;
		if (newCount<=0) {
			cartEntity.setCount(1);
			bgypfwCartService.update(cartEntity);
			return R.error(1002,"不能为零");
		}else if (newCount>store) {
			cartEntity.setCount(store);
			bgypfwCartService.update(cartEntity);
			return R.error(1003,"超过最大库存");
		}
		cartEntity.setCount(newCount);
		bgypfwCartService.update(cartEntity);
		return R.ok();
	}
	/**
	 * 查询用户购物车
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findUserCart")
	public R findUserCart(HttpServletRequest req){
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
		List<BgypfwCartEntity> cartItems = bgypfwCartService.queryList(params);
		return R.ok().put("cartItems", cartItems);
	}
}
