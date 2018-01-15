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

import com.lcworld.entity.BgypfwProducbrandEntity;
import com.lcworld.service.BgypfwProducbrandService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 办公用品服务-商品品牌分类表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 20:10:14
 */
@RestController
@RequestMapping("bgypfwproducbrand")
public class BgypfwProducbrandController {
	@Autowired
	private BgypfwProducbrandService bgypfwProducbrandService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("bgypfwproducbrand:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwProducbrandEntity> bgypfwProducbrandList = bgypfwProducbrandService.queryList(query);
		int total = bgypfwProducbrandService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwProducbrandList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("bgypfwproducbrand:info")
	public R info(@PathVariable("id") Integer id){
		BgypfwProducbrandEntity bgypfwProducbrand = bgypfwProducbrandService.queryObject(id);
		
		return R.ok().put("bgypfwProducbrand", bgypfwProducbrand);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("bgypfwproducbrand:save")
	public R save(@RequestBody BgypfwProducbrandEntity bgypfwProducbrand){
		bgypfwProducbrandService.save(bgypfwProducbrand);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("bgypfwproducbrand:update")
	public R update(@RequestBody BgypfwProducbrandEntity bgypfwProducbrand){
		bgypfwProducbrandService.update(bgypfwProducbrand);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("bgypfwproducbrand:delete")
	public R delete(@RequestBody Integer[] ids){
		bgypfwProducbrandService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
