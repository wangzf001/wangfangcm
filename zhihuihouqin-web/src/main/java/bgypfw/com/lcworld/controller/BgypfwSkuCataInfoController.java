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

import com.lcworld.entity.BgypfwSkuCataInfoEntity;
import com.lcworld.service.BgypfwSkuCataInfoService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-27 16:25:55
 */
@RestController
@RequestMapping("bgypfwskucatainfo")
public class BgypfwSkuCataInfoController {
	@Autowired
	private BgypfwSkuCataInfoService bgypfwSkuCataInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("bgypfwskucatainfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwSkuCataInfoEntity> bgypfwSkuCataInfoList = bgypfwSkuCataInfoService.queryList(query);
		int total = bgypfwSkuCataInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwSkuCataInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("bgypfwskucatainfo:info")
	public R info(@PathVariable("id") Integer id){
		BgypfwSkuCataInfoEntity bgypfwSkuCataInfo = bgypfwSkuCataInfoService.queryObject(id);
		
		return R.ok().put("bgypfwSkuCataInfo", bgypfwSkuCataInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("bgypfwskucatainfo:save")
	public R save(@RequestBody BgypfwSkuCataInfoEntity bgypfwSkuCataInfo){
		bgypfwSkuCataInfoService.save(bgypfwSkuCataInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("bgypfwskucatainfo:update")
	public R update(@RequestBody BgypfwSkuCataInfoEntity bgypfwSkuCataInfo){
		bgypfwSkuCataInfoService.update(bgypfwSkuCataInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("bgypfwskucatainfo:delete")
	public R delete(@RequestBody Integer[] ids){
		bgypfwSkuCataInfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
