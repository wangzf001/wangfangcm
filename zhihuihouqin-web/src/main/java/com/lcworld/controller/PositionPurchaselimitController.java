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

import com.lcworld.entity.PositionPurchaselimitEntity;
import com.lcworld.service.PositionPurchaselimitService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 职位额度表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 14:35:19
 */
@RestController
@RequestMapping("positionpurchaselimit")
public class PositionPurchaselimitController {
	@Autowired
	private PositionPurchaselimitService positionPurchaselimitService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("positionpurchaselimit:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PositionPurchaselimitEntity> positionPurchaselimitList = positionPurchaselimitService.queryList(query);
		int total = positionPurchaselimitService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(positionPurchaselimitList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("positionpurchaselimit:info")
	public R info(@PathVariable("id") Integer id){
		PositionPurchaselimitEntity positionPurchaselimit = positionPurchaselimitService.queryObject(id);
		
		return R.ok().put("positionPurchaselimit", positionPurchaselimit);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("positionpurchaselimit:save")
	public R save(@RequestBody PositionPurchaselimitEntity positionPurchaselimit){
		positionPurchaselimitService.save(positionPurchaselimit);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("positionpurchaselimit:update")
	public R update(@RequestBody PositionPurchaselimitEntity positionPurchaselimit){
		positionPurchaselimitService.update(positionPurchaselimit);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("positionpurchaselimit:delete")
	public R delete(@RequestBody Integer[] ids){
		positionPurchaselimitService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
