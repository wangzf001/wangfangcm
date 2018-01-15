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

import com.lcworld.entity.InneruserEntity;
import com.lcworld.service.InneruserService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 内部人员表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 11:54:31
 */
@RestController
@RequestMapping("inneruser")
public class InneruserController {
	@Autowired
	private InneruserService inneruserService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("inneruser:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<InneruserEntity> inneruserList = inneruserService.queryList(query);
		int total = inneruserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(inneruserList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("inneruser:info")
	public R info(@PathVariable("id") Integer id){
		InneruserEntity inneruser = inneruserService.queryObject(id);
		
		return R.ok().put("inneruser", inneruser);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("inneruser:save")
	public R save(@RequestBody InneruserEntity inneruser){
		inneruserService.save(inneruser);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("inneruser:update")
	public R update(@RequestBody InneruserEntity inneruser){
		inneruserService.update(inneruser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("inneruser:delete")
	public R delete(@RequestBody Integer[] ids){
		inneruserService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
