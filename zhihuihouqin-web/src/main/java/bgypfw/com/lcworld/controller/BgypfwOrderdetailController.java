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

import com.lcworld.entity.BgypfwOrderdetailEntity;
import com.lcworld.service.BgypfwOrderdetailService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 办公用品服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
@RestController
@RequestMapping("bgypfworderdetail")
public class BgypfwOrderdetailController {
	@Autowired
	private BgypfwOrderdetailService bgypfwOrderdetailService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
		public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwOrderdetailEntity> bgypfwOrderdetailList = bgypfwOrderdetailService.queryList(query);
		int total = bgypfwOrderdetailService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwOrderdetailList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
		public R info(@PathVariable("id") Integer id){
		BgypfwOrderdetailEntity bgypfwOrderdetail = bgypfwOrderdetailService.queryObject(id);
		
		return R.ok().put("bgypfwOrderdetail", bgypfwOrderdetail);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
		public R save(@RequestBody BgypfwOrderdetailEntity bgypfwOrderdetail){
		bgypfwOrderdetailService.save(bgypfwOrderdetail);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
		public R update(@RequestBody BgypfwOrderdetailEntity bgypfwOrderdetail){
		bgypfwOrderdetailService.update(bgypfwOrderdetail);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
		public R delete(@RequestBody Integer[] ids){
		bgypfwOrderdetailService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
