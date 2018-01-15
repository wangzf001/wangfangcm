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

import com.lcworld.service.TDcfwOrdercancelReasonService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;

import com.lcworld.entity.TDcfwOrdercancelReasonEntity;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 15:23:09
 */
@RestController
@RequestMapping("dcfwordercancelreason")
public class DcfwOrdercancelReasonController {
	@Autowired
	private TDcfwOrdercancelReasonService dcfwOrdercancelReasonService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TDcfwOrdercancelReasonEntity> dcfwOrdercancelReasonList = dcfwOrdercancelReasonService.queryList(query);
		int total = dcfwOrdercancelReasonService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dcfwOrdercancelReasonList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TDcfwOrdercancelReasonEntity dcfwOrdercancelReason = dcfwOrdercancelReasonService.queryObject(id);
		
		return R.ok().put("dcfwOrdercancelReason", dcfwOrdercancelReason);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TDcfwOrdercancelReasonEntity dcfwOrdercancelReason){
		dcfwOrdercancelReasonService.save(dcfwOrdercancelReason);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TDcfwOrdercancelReasonEntity dcfwOrdercancelReason){
		dcfwOrdercancelReasonService.update(dcfwOrdercancelReason);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dcfwOrdercancelReasonService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
