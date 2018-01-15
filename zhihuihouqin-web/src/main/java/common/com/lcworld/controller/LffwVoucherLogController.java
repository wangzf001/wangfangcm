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

import com.lcworld.entity.LffwVoucherLogEntity;
import com.lcworld.service.LffwVoucherLogService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 代金券记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-31 14:40:06
 */
@RestController
@RequestMapping("lffwvoucherlog")
public class LffwVoucherLogController {
	@Autowired
	private LffwVoucherLogService lffwVoucherLogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("lffwvoucherlog:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwVoucherLogEntity> lffwVoucherLogList = lffwVoucherLogService.queryList(query);
		int total = lffwVoucherLogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwVoucherLogList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("lffwvoucherlog:info")
	public R info(@PathVariable("id") Integer id){
		LffwVoucherLogEntity lffwVoucherLog = lffwVoucherLogService.queryObject(id);
		
		return R.ok().put("lffwVoucherLog", lffwVoucherLog);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("lffwvoucherlog:save")
	public R save(@RequestBody LffwVoucherLogEntity lffwVoucherLog){
		lffwVoucherLogService.save(lffwVoucherLog);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("lffwvoucherlog:update")
	public R update(@RequestBody LffwVoucherLogEntity lffwVoucherLog){
		lffwVoucherLogService.update(lffwVoucherLog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("lffwvoucherlog:delete")
	public R delete(@RequestBody Integer[] ids){
		lffwVoucherLogService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
