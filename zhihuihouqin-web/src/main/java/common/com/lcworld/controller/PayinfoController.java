package com.lcworld.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.PayinfoEntity;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 公用服务-支付信息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:37
 */
@RestController
@RequestMapping("payinfo")
public class PayinfoController {
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("payinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PayinfoEntity> payinfoList = null;
		int total = 1;
		
		PageUtils pageUtil = new PageUtils(payinfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("payinfo:info")
	public R info(@PathVariable("id") Integer id){
		PayinfoEntity payinfo = null;
		
		return R.ok().put("payinfo", payinfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("payinfo:save")
	public R save(@RequestBody PayinfoEntity payinfo){
		
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("payinfo:update")
	public R update(@RequestBody PayinfoEntity payinfo){
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("payinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		
		return R.ok();
	}
	
}
