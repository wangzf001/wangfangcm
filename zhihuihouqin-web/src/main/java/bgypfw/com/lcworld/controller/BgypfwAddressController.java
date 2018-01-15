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

import com.lcworld.entity.AddressEntity;
import com.lcworld.service.AddressService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 19:32:34
 */
@RestController
@RequestMapping("bgypfwaddress")
public class BgypfwAddressController {
	@Autowired
	private AddressService bgypfwAddressService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
		public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<AddressEntity> bgypfwAddressList = bgypfwAddressService.queryList(query);
		int total = bgypfwAddressService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwAddressList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
		public R info(@PathVariable("id") Integer id){
		AddressEntity bgypfwAddress = bgypfwAddressService.queryObject(id);
		
		return R.ok().put("bgypfwAddress", bgypfwAddress);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
		public R save(@RequestBody AddressEntity bgypfwAddress){
		bgypfwAddressService.save(bgypfwAddress);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
		public R update(@RequestBody AddressEntity bgypfwAddress){
		bgypfwAddressService.update(bgypfwAddress);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
		public R delete(@RequestBody Integer[] ids){
		bgypfwAddressService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
