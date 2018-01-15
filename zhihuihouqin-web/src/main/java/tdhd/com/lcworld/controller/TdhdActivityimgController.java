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

import com.lcworld.entity.TdhdActivityimgEntity;
import com.lcworld.service.TdhdActivityimgService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 团队活动系统-活动配图
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 13:45:52
 */
@RestController
@RequestMapping("tdhdactivityimg")
public class TdhdActivityimgController {
	@Autowired
	private TdhdActivityimgService tdhdActivityimgService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tdhdactivityimg:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TdhdActivityimgEntity> tdhdActivityimgList = tdhdActivityimgService.queryList(query);
		int total = tdhdActivityimgService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tdhdActivityimgList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{aiId}")
	@RequiresPermissions("tdhdactivityimg:info")
	public R info(@PathVariable("aiId") Integer aiId){
		TdhdActivityimgEntity tdhdActivityimg = tdhdActivityimgService.queryObject(aiId);
		
		return R.ok().put("tdhdActivityimg", tdhdActivityimg);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tdhdactivityimg:save")
	public R save(@RequestBody TdhdActivityimgEntity tdhdActivityimg){
		tdhdActivityimgService.save(tdhdActivityimg);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tdhdactivityimg:update")
	public R update(@RequestBody TdhdActivityimgEntity tdhdActivityimg){
		tdhdActivityimgService.update(tdhdActivityimg);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tdhdactivityimg:delete")
	public R delete(@RequestBody Integer[] aiIds){
		tdhdActivityimgService.deleteBatch(aiIds);
		
		return R.ok();
	}
	
}
