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

import com.lcworld.entity.HysfwOrderEntity;
import com.lcworld.service.HysfwOrderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-30 18:43:48
 */
@RestController
@RequestMapping("hysfworder")
public class HysfwOrderController {
	@Autowired
	private HysfwOrderService hysfwOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("hysfworder:list")
	public R list(@RequestParam Map<String, Object> params){
		if (!ValidateUtil.isValid(params.get("orderStatus"))) {
			params.remove("orderStatus");
		}
		//查询列表数据
        Query query = new Query(params);

		List<HysfwOrderEntity> hysfwOrderList = hysfwOrderService.queryList(query);
		int total = hysfwOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(hysfwOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hysfworder:info")
	public R info(@PathVariable("id") Integer id){
		HysfwOrderEntity hysfwOrder = hysfwOrderService.queryObject(id);
		
		return R.ok().put("hysfwOrder", hysfwOrder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("hysfworder:save")
	public R save(@RequestBody HysfwOrderEntity hysfwOrder){
		hysfwOrderService.save(hysfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("hysfworder:update")
	public R update(@RequestBody HysfwOrderEntity hysfwOrder){
		hysfwOrderService.update(hysfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("hysfworder:delete")
	public R delete(@RequestBody Integer[] ids){
		hysfwOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
