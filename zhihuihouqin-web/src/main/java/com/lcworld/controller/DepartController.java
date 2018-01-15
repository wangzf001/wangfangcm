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

import com.lcworld.entity.DepartEntity;
import com.lcworld.service.DepartService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;


/**
 * 部门
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-19 13:54:13
 */
@RestController
@RequestMapping("depart")
public class DepartController {
	@Autowired
	private DepartService departService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("depart:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DepartEntity> departList = departService.queryList(query);
		int total = departService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(departList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/getdepartlist")
	public R getdepartlist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    query.setLimit(1000);
	    
	    List<DepartEntity> departlist = departService.queryList(query);
	    return R.ok().put("departlist", departlist);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/getdepartlist1")
	public R getdepartlist1(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<DepartEntity> departlist =null;
		if( Constant.SUPER_ADMIN  == ShiroUtils.getUserId().intValue()){
			departlist = departService.queryList(params);
		}else{
			params.put("departid", ShiroUtils.getUserEntity().getDepartid());
			departlist = departService.queryList(params);
		}
		return R.ok().put("departlist", departlist);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("depart:info")
	public R info(@PathVariable("id") Integer id){
		DepartEntity depart = departService.queryObject(id);
		
		return R.ok().put("depart", depart);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("depart:save")
	public R save(@RequestBody DepartEntity depart){
		departService.save(depart);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("depart:update")
	public R update(@RequestBody DepartEntity depart){
		departService.update(depart);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("depart:delete")
	public R delete(@RequestBody Integer[] ids){
		departService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
