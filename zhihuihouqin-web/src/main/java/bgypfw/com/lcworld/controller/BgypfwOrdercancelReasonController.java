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

import com.lcworld.entity.BgypfwOrdercancelReasonEntity;
import com.lcworld.service.BgypfwOrdercancelReasonService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 14:36:28
 */
@RestController
@RequestMapping("bgypfwordercancelreason")
public class BgypfwOrdercancelReasonController {
	@Autowired
	private BgypfwOrdercancelReasonService bgypfwOrdercancelReasonService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
		public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwOrdercancelReasonEntity> bgypfwOrdercancelReasonList = bgypfwOrdercancelReasonService.queryList(query);
		int total = bgypfwOrdercancelReasonService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwOrdercancelReasonList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
		public R info(@PathVariable("id") Integer id){
		BgypfwOrdercancelReasonEntity bgypfwOrdercancelReason = bgypfwOrdercancelReasonService.queryObject(id);
		
		return R.ok().put("bgypfwOrdercancelReason", bgypfwOrdercancelReason);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
		public R save(@RequestBody BgypfwOrdercancelReasonEntity bgypfwOrdercancelReason){
		bgypfwOrdercancelReasonService.save(bgypfwOrdercancelReason);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
		public R update(@RequestBody BgypfwOrdercancelReasonEntity bgypfwOrdercancelReason){
		bgypfwOrdercancelReasonService.update(bgypfwOrdercancelReason);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
		public R delete(@RequestBody Integer[] ids){
		bgypfwOrdercancelReasonService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
