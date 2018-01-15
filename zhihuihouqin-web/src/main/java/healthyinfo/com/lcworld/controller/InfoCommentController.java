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

import com.lcworld.entity.InfoCommentEntity;
import com.lcworld.service.InfoCommentService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 资讯评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:14:38
 */
@RestController
@RequestMapping("infocomment")
public class InfoCommentController {
	@Autowired
	private InfoCommentService infoCommentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("infocomment:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<InfoCommentEntity> infoCommentList = infoCommentService.queryList(query);
		int total = infoCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(infoCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 列表
	 */
	@RequestMapping("/commentlist")
	public R commentlist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    
	    List<Map<String,Object>> infoCommentList = infoCommentService.querycommentList(query);
	    int total = infoCommentService.querycommentTotal(query);
	    
	    PageUtils pageUtil = new PageUtils(infoCommentList, total, query.getLimit(), query.getPage());
	    
	    return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("infocomment:info")
	public R info(@PathVariable("id") Integer id){
		InfoCommentEntity infoComment = infoCommentService.queryObject(id);
		
		return R.ok().put("infoComment", infoComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("infocomment:save")
	public R save(@RequestBody InfoCommentEntity infoComment){
		infoCommentService.save(infoComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("infocomment:update")
	public R update(@RequestBody InfoCommentEntity infoComment){
		infoCommentService.update(infoComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("infocomment:delete")
	public R delete(@RequestBody Integer[] ids){
		infoCommentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
