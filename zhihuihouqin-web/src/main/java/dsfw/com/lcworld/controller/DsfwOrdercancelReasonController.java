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

import com.lcworld.annotation.SysLog;
import com.lcworld.entity.DsfwOrdercancelReasonEntity;
import com.lcworld.service.DsfwOrdercancelReasonService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订水服务-取消订单原因
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:20
 */
@RestController
@RequestMapping("dsfwordercancelreason")
public class DsfwOrdercancelReasonController {
	@Autowired
	private DsfwOrdercancelReasonService dsfwOrdercancelReasonService;
	
	/**
	 * 列表
	 */
	@SysLog("订水服务-取消订单原因查看")
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DsfwOrdercancelReasonEntity> dsfwOrdercancelReasonList = dsfwOrdercancelReasonService.queryList(query);
		int total = dsfwOrdercancelReasonService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dsfwOrdercancelReasonList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DsfwOrdercancelReasonEntity dsfwOrdercancelReason = dsfwOrdercancelReasonService.queryObject(id);
		
		return R.ok().put("dsfwOrdercancelReason", dsfwOrdercancelReason);
	}
	
	/**
	 * 保存
	 */
	@SysLog("订水服务-取消订单原因保存")
	@RequestMapping("/save")
	public R save(@RequestBody DsfwOrdercancelReasonEntity dsfwOrdercancelReason){
		dsfwOrdercancelReasonService.save(dsfwOrdercancelReason);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("订水服务-取消订单原因修改")
	@RequestMapping("/update")
	public R update(@RequestBody DsfwOrdercancelReasonEntity dsfwOrdercancelReason){
		dsfwOrdercancelReasonService.update(dsfwOrdercancelReason);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("订水服务-取消订单原因删除")
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dsfwOrdercancelReasonService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
