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

import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.entity.TGxdfwOrdercancelReasonEntity;
import com.lcworld.service.TGxdfwOrdercancelReasonService;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 09:58:35
 */
@RestController
@RequestMapping("gxdfwordercancelreason")
public class GxdfwOrdercancelReasonController {
	@Autowired
	private TGxdfwOrdercancelReasonService gxdfwOrdercancelReasonService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TGxdfwOrdercancelReasonEntity> gxdfwOrdercancelReasonList = gxdfwOrdercancelReasonService.queryList(query);
		int total = gxdfwOrdercancelReasonService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(gxdfwOrdercancelReasonList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TGxdfwOrdercancelReasonEntity gxdfwOrdercancelReason = gxdfwOrdercancelReasonService.queryObject(id);
		
		return R.ok().put("gxdfwOrdercancelReason", gxdfwOrdercancelReason);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TGxdfwOrdercancelReasonEntity gxdfwOrdercancelReason){
		gxdfwOrdercancelReasonService.save(gxdfwOrdercancelReason);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TGxdfwOrdercancelReasonEntity gxdfwOrdercancelReason){
		gxdfwOrdercancelReasonService.update(gxdfwOrdercancelReason);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		gxdfwOrdercancelReasonService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
