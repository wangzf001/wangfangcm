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

import com.lcworld.entity.HysfwCommentEntity;
import com.lcworld.service.HysfwCommentService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 报修维修评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
@RestController
@RequestMapping("hysfwcomment")
public class HysfwCommentController {
	@Autowired
	private HysfwCommentService hysfwCommentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("hysfwcomment:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HysfwCommentEntity> hysfwCommentList = hysfwCommentService.queryList(query);
		int total = hysfwCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(hysfwCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hysfwcomment:info")
	public R info(@PathVariable("id") Integer id){
		HysfwCommentEntity hysfwComment = hysfwCommentService.queryObject(id);
		
		return R.ok().put("hysfwComment", hysfwComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("hysfwcomment:save")
	public R save(@RequestBody HysfwCommentEntity hysfwComment){
		hysfwCommentService.save(hysfwComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("hysfwcomment:update")
	public R update(@RequestBody HysfwCommentEntity hysfwComment){
		hysfwCommentService.update(hysfwComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("hysfwcomment:delete")
	public R delete(@RequestBody Integer[] ids){
		hysfwCommentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
