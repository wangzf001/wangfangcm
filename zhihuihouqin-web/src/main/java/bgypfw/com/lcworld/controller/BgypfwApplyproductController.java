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

import com.lcworld.entity.BgypfwApplyproductEntity;
import com.lcworld.service.BgypfwApplyproductService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 办公用品服务-申请商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
@RestController
@RequestMapping("bgypfwapplyproduct")
public class BgypfwApplyproductController {
	@Autowired
	private BgypfwApplyproductService bgypfwApplyproductService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
		public R list(@RequestParam Map<String, Object> params){
		if ("".equals((String)params.get("status"))) {
			params.remove("status");
		}
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwApplyproductEntity> bgypfwApplyproductList = bgypfwApplyproductService.queryList(query);
		int total = bgypfwApplyproductService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwApplyproductList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
		public R info(@PathVariable("id") Integer id){
		BgypfwApplyproductEntity bgypfwApplyproduct = bgypfwApplyproductService.queryObject(id);
		
		return R.ok().put("bgypfwApplyproduct", bgypfwApplyproduct);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
		public R save(@RequestBody BgypfwApplyproductEntity bgypfwApplyproduct){
		bgypfwApplyproductService.save(bgypfwApplyproduct);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
		public R update(@RequestBody BgypfwApplyproductEntity bgypfwApplyproduct){
		bgypfwApplyproductService.update(bgypfwApplyproduct);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
		public R delete(@RequestBody Integer[] ids){
		bgypfwApplyproductService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
