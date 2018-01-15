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

import com.lcworld.entity.YlfwZjzzCommentEntity;
import com.lcworld.service.YlfwZjzzCommentService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 医疗服务专家坐诊评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
@RestController
@RequestMapping("ylfwzjzzcomment")
public class YlfwZjzzCommentController {
	@Autowired
	private YlfwZjzzCommentService ylfwZjzzCommentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwZjzzCommentEntity> ylfwZjzzCommentList = ylfwZjzzCommentService.queryList(query);
		int total = ylfwZjzzCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwZjzzCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		YlfwZjzzCommentEntity ylfwZjzzComment = ylfwZjzzCommentService.queryObject(id);
		
		return R.ok().put("ylfwZjzzComment", ylfwZjzzComment);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/commnetinfo/{id}")
	public R commnetinfo(@PathVariable("id") Integer id){
	    YlfwZjzzCommentEntity ylfwZjzzComment = ylfwZjzzCommentService.queryObject(id);
	    String imgs = ylfwZjzzComment.getImgs();
	    return R.ok().put("ylfwZjzzComment", ylfwZjzzComment).put("imglist", ValidateUtil.isValid(imgs)? Arrays.asList(imgs.split(",")):new ArrayList<String>());
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody YlfwZjzzCommentEntity ylfwZjzzComment){
		ylfwZjzzCommentService.save(ylfwZjzzComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody YlfwZjzzCommentEntity ylfwZjzzComment){
		ylfwZjzzCommentService.update(ylfwZjzzComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwZjzzCommentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
