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

import com.lcworld.entity.WlryCommentEntity;
import com.lcworld.service.WlryCommentService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 报修维修评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-09 11:05:54
 */
@RestController
@RequestMapping("wlrycomment")
public class WlryCommentController {
	@Autowired
	private WlryCommentService wlryCommentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wlrycomment:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WlryCommentEntity> wlryCommentList = wlryCommentService.queryList(query);
		int total = wlryCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(wlryCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("wlrycomment:info")
	public R info(@PathVariable("id") Integer id){
		WlryCommentEntity wlryComment = wlryCommentService.queryObject(id);
		
		return R.ok().put("wlryComment", wlryComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("wlrycomment:save")
	public R save(@RequestBody WlryCommentEntity wlryComment){
		wlryCommentService.save(wlryComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("wlrycomment:update")
	public R update(@RequestBody WlryCommentEntity wlryComment){
		wlryCommentService.update(wlryComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("wlrycomment:delete")
	public R delete(@RequestBody Integer[] ids){
		wlryCommentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
