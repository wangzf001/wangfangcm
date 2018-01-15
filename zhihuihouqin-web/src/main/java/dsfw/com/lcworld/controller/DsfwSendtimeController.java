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

import com.lcworld.entity.DsfwSendtimeEntity;
import com.lcworld.service.DsfwSendtimeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订水服务-送水时间
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:19
 */
@RestController
@RequestMapping("dsfwsendtime")
public class DsfwSendtimeController {
	@Autowired
	private DsfwSendtimeService dsfwSendtimeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DsfwSendtimeEntity> dsfwSendtimeList = dsfwSendtimeService.queryList(query);
		int total = dsfwSendtimeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dsfwSendtimeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DsfwSendtimeEntity dsfwSendtime = dsfwSendtimeService.queryObject(id);
		
		return R.ok().put("dsfwSendtime", dsfwSendtime);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DsfwSendtimeEntity dsfwSendtime){
		dsfwSendtimeService.save(dsfwSendtime);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DsfwSendtimeEntity dsfwSendtime){
		dsfwSendtimeService.update(dsfwSendtime);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dsfwSendtimeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
