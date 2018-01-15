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

import com.lcworld.entity.TBxwxMenderEntity;
import com.lcworld.service.TBxwxMenderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 维修人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 11:50:03
 */
@RestController
@RequestMapping("tbxwxmender")
public class TBxwxMenderController {
	@Autowired
	private TBxwxMenderService tBxwxMenderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tbxwxmender:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TBxwxMenderEntity> tBxwxMenderList = tBxwxMenderService.queryList(query);
		int total = tBxwxMenderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tBxwxMenderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tbxwxmender:info")
	public R info(@PathVariable("id") Integer id){
		TBxwxMenderEntity tBxwxMender = tBxwxMenderService.queryObject(id);
		
		return R.ok().put("tBxwxMender", tBxwxMender);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tbxwxmender:save")
	public R save(@RequestBody TBxwxMenderEntity tBxwxMender){
		tBxwxMender.setPassword("e10adc3949ba59abbe56e057f20f883e");
		tBxwxMenderService.save(tBxwxMender);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tbxwxmender:update")
	public R update(@RequestBody TBxwxMenderEntity tBxwxMender){
		tBxwxMenderService.update(tBxwxMender);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tbxwxmender:delete")
	public R delete(@RequestBody Integer[] ids){
		tBxwxMenderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
