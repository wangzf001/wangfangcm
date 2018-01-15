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

import com.lcworld.entity.DsfwOrderdetailEntity;
import com.lcworld.service.DsfwOrderdetailService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订水服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:19
 */
@RestController
@RequestMapping("dsfworderdetail")
public class DsfwOrderdetailController {
	@Autowired
	private DsfwOrderdetailService dsfwOrderdetailService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DsfwOrderdetailEntity> dsfwOrderdetailList = dsfwOrderdetailService.queryList(query);
		int total = dsfwOrderdetailService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dsfwOrderdetailList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DsfwOrderdetailEntity dsfwOrderdetail = dsfwOrderdetailService.queryObject(id);
		
		return R.ok().put("dsfwOrderdetail", dsfwOrderdetail);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DsfwOrderdetailEntity dsfwOrderdetail){
		dsfwOrderdetailService.save(dsfwOrderdetail);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DsfwOrderdetailEntity dsfwOrderdetail){
		dsfwOrderdetailService.update(dsfwOrderdetail);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dsfwOrderdetailService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
