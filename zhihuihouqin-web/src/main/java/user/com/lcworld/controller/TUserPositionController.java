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

import com.lcworld.entity.TUserPositionEntity;
import com.lcworld.service.TUserPositionService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 职位表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:42
 */
@RestController
@RequestMapping("tuserposition")
public class TUserPositionController {
	@Autowired
	private TUserPositionService tUserPositionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tuserposition:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TUserPositionEntity> tUserPositionList = tUserPositionService.queryList(query);
		int total = tUserPositionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tUserPositionList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tuserposition:info")
	public R info(@PathVariable("id") Integer id){
		TUserPositionEntity tUserPosition = tUserPositionService.queryObject(id);
		
		return R.ok().put("tUserPosition", tUserPosition);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tuserposition:save")
	public R save(@RequestBody TUserPositionEntity tUserPosition){
		tUserPositionService.save(tUserPosition);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tuserposition:update")
	public R update(@RequestBody TUserPositionEntity tUserPosition){
		tUserPositionService.update(tUserPosition);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tuserposition:delete")
	public R delete(@RequestBody Integer[] ids){
		tUserPositionService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
