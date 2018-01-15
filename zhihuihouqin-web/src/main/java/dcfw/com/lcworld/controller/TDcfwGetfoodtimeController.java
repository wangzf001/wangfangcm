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

import com.lcworld.entity.TDcfwGetfoodtimeEntity;
import com.lcworld.service.TDcfwGetfoodtimeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订餐服务-取餐时间
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
@RestController
@RequestMapping("dcfwgetfoodtime")
public class TDcfwGetfoodtimeController {
	@Autowired
	private TDcfwGetfoodtimeService tDcfwGetfoodtimeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TDcfwGetfoodtimeEntity> tDcfwGetfoodtimeList = tDcfwGetfoodtimeService.queryList(query);
		int total = tDcfwGetfoodtimeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDcfwGetfoodtimeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TDcfwGetfoodtimeEntity tDcfwGetfoodtime = tDcfwGetfoodtimeService.queryObject(id);
		
		return R.ok().put("dcfwGetfoodtime", tDcfwGetfoodtime);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TDcfwGetfoodtimeEntity tDcfwGetfoodtime){
		tDcfwGetfoodtimeService.save(tDcfwGetfoodtime);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TDcfwGetfoodtimeEntity tDcfwGetfoodtime){
		tDcfwGetfoodtimeService.update(tDcfwGetfoodtime);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tDcfwGetfoodtimeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
