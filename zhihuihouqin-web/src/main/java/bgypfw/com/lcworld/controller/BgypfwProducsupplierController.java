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

import com.lcworld.entity.BgypfwProducsupplierEntity;
import com.lcworld.service.BgypfwProducsupplierService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 办公用品服务-商品供应商信息表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 20:10:14
 */
@RestController
@RequestMapping("bgypfwproducsupplier")
public class BgypfwProducsupplierController {
	@Autowired
	private BgypfwProducsupplierService bgypfwProducsupplierService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("bgypfwproducsupplier:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwProducsupplierEntity> bgypfwProducsupplierList = bgypfwProducsupplierService.queryList(query);
		int total = bgypfwProducsupplierService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwProducsupplierList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("bgypfwproducsupplier:info")
	public R info(@PathVariable("id") Integer id){
		BgypfwProducsupplierEntity bgypfwProducsupplier = bgypfwProducsupplierService.queryObject(id);
		
		return R.ok().put("bgypfwProducsupplier", bgypfwProducsupplier);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("bgypfwproducsupplier:save")
	public R save(@RequestBody BgypfwProducsupplierEntity bgypfwProducsupplier){
		bgypfwProducsupplierService.save(bgypfwProducsupplier);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("bgypfwproducsupplier:update")
	public R update(@RequestBody BgypfwProducsupplierEntity bgypfwProducsupplier){
		bgypfwProducsupplierService.update(bgypfwProducsupplier);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("bgypfwproducsupplier:delete")
	public R delete(@RequestBody Integer[] ids){
		bgypfwProducsupplierService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
