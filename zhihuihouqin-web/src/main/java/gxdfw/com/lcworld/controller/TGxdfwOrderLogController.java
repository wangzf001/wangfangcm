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

import com.lcworld.entity.TGxdfwOrderLogEntity;
import com.lcworld.service.TGxdfwOrderLogService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 干洗店服务-订单记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:52
 */
@RestController
@RequestMapping("gxdfworderlog")
public class TGxdfwOrderLogController {
	@Autowired
	private TGxdfwOrderLogService tGxdfwOrderLogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TGxdfwOrderLogEntity> tGxdfwOrderLogList = tGxdfwOrderLogService.queryList(query);
		int total = tGxdfwOrderLogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tGxdfwOrderLogList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 查询订单状态日志
	 */
	@RequestMapping("/info/{oid}")
	public R info(@PathVariable("oid") Integer oid){
		TGxdfwOrderLogEntity tGxdfwOrderLog = tGxdfwOrderLogService.queryByOid(oid);
		return R.ok().put("gxdfwOrderLog", tGxdfwOrderLog);
	}
	/**
	 * 修改订单日志
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TGxdfwOrderLogEntity tGxdfwOrderLog){
		tGxdfwOrderLogService.update(tGxdfwOrderLog);
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TGxdfwOrderLogEntity tGxdfwOrderLog){
		tGxdfwOrderLogService.save(tGxdfwOrderLog);
		
		return R.ok();
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tGxdfwOrderLogService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
