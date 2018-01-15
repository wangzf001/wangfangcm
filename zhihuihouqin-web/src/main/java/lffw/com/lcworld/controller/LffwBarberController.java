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

import com.lcworld.entity.LffwBarberEntity;
import com.lcworld.service.LffwBarberService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务-理发师
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:15
 */
@RestController
@RequestMapping("lffwbarber")
public class LffwBarberController {
	@Autowired
	private LffwBarberService lffwBarberService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("lffwbarber:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwBarberEntity> lffwBarberList = lffwBarberService.queryList(query);
		int total = lffwBarberService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwBarberList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("lffwbarber:info")
	public R info(@PathVariable("id") Integer id){
		LffwBarberEntity lffwBarber = lffwBarberService.queryObject(id);
		
		return R.ok().put("lffwBarber", lffwBarber);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("lffwbarber:save")
	public R save(@RequestBody LffwBarberEntity lffwBarber){
		lffwBarberService.save(lffwBarber);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("lffwbarber:update")
	public R update(@RequestBody LffwBarberEntity lffwBarber){
		lffwBarberService.update(lffwBarber);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("lffwbarber:delete")
	public R delete(@RequestBody Integer[] ids){
		lffwBarberService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
