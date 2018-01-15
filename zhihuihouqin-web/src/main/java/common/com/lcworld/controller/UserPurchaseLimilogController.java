package com.lcworld.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.LffwPeriodEntity;
import com.lcworld.entity.UserPurchaseLimilogEntity;
import com.lcworld.service.UserPurchaseLimilogService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 用户对公账户额度日志表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-26 17:31:54
 */
@RestController
@RequestMapping("userpurchaselimilog")
public class UserPurchaseLimilogController {
	@Autowired
	private UserPurchaseLimilogService userPurchaseLimilogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserPurchaseLimilogEntity> userPurchaseLimilogList = userPurchaseLimilogService.queryList(query);
		int total = userPurchaseLimilogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userPurchaseLimilogList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		UserPurchaseLimilogEntity userPurchaseLimilog = userPurchaseLimilogService.queryObject(id);
		
		return R.ok().put("userPurchaseLimilog", userPurchaseLimilog);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody UserPurchaseLimilogEntity userPurchaseLimilog){
		userPurchaseLimilogService.save(userPurchaseLimilog);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody UserPurchaseLimilogEntity userPurchaseLimilog){
		userPurchaseLimilogService.update(userPurchaseLimilog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		userPurchaseLimilogService.deleteBatch(ids);
		
		return R.ok();
	}

}
