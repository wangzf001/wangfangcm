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

import com.lcworld.entity.NationEntity;
import com.lcworld.service.NationService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-11 10:35:19
 */
@RestController
@RequestMapping("nation")
public class NationController {
	@Autowired
	private NationService nationService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("nation:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<NationEntity> nationList = nationService.queryList(query);
		int total = nationService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(nationList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("nation:info")
	public R info(@PathVariable("id") Integer id){
		NationEntity nation = nationService.queryObject(id);
		
		return R.ok().put("nation", nation);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("nation:save")
	public R save(@RequestBody NationEntity nation){
		nationService.save(nation);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("nation:update")
	public R update(@RequestBody NationEntity nation){
		nationService.update(nation);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("nation:delete")
	public R delete(@RequestBody Integer[] ids){
		nationService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
