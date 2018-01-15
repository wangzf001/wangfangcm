package com.lcworld.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.LffwCommentEntity;
import com.lcworld.service.LffwCommentService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;


/**
 * 理发服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
@RestController
@RequestMapping("lffwcomment")
public class LffwCommentController {
	@Autowired
	private LffwCommentService lffwCommentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwCommentEntity> lffwCommentList = lffwCommentService.queryList(query);
		int total = lffwCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		LffwCommentEntity lffwComment = lffwCommentService.queryObject(id);
		return R.ok().put("lffwComment", lffwComment);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/commnetinfo/{id}")
	public R commnetinfo(@PathVariable("id") Integer id){
	    LffwCommentEntity lffwComment = lffwCommentService.queryObject(id);
	    if(lffwComment != null){
	        List<String> list = lffwComment.getImgs()==null?new ArrayList<String>():Arrays.asList(lffwComment.getImgs().split(","));
	        lffwComment.setImgs(list.toString());
	    }
	    return R.ok().put("lffwComment", lffwComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody LffwCommentEntity lffwComment){
		lffwCommentService.save(lffwComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody LffwCommentEntity lffwComment){
		lffwCommentService.update(lffwComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		lffwCommentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
