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

import com.lcworld.entity.TDcfwOrderdetailEntity;
import com.lcworld.service.TDcfwOrderdetailService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订餐服务订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
@RestController
@RequestMapping("dcfworderdetail")
public class TDcfwOrderdetailController {
	@Autowired
	private TDcfwOrderdetailService tDcfwOrderdetailService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TDcfwOrderdetailEntity> tDcfwOrderdetailList = tDcfwOrderdetailService.queryList(query);
		int total = tDcfwOrderdetailService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDcfwOrderdetailList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TDcfwOrderdetailEntity tDcfwOrderdetail = tDcfwOrderdetailService.queryObject(id);
		return R.ok().put("dcfwOrderdetail", tDcfwOrderdetail);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TDcfwOrderdetailEntity tDcfwOrderdetail){
		tDcfwOrderdetailService.save(tDcfwOrderdetail);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TDcfwOrderdetailEntity tDcfwOrderdetail){
		tDcfwOrderdetailService.update(tDcfwOrderdetail);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tDcfwOrderdetailService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
