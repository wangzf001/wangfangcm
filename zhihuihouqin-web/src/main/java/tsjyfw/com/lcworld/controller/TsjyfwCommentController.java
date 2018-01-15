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

import com.lcworld.entity.TsjyfwCommentEntity;
import com.lcworld.service.TsjyfwCommentService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 图书借阅服务-评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
@RestController
@RequestMapping("tsjyfwcomment")
public class TsjyfwCommentController {
	@Autowired
	private TsjyfwCommentService tsjyfwCommentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TsjyfwCommentEntity> tsjyfwCommentList = tsjyfwCommentService.queryList(query);
		int total = tsjyfwCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tsjyfwCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TsjyfwCommentEntity tsjyfwComment = tsjyfwCommentService.queryObject(id);
		
		return R.ok().put("tsjyfwComment", tsjyfwComment);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/commnetinfo/{id}")
	public R commnetinfo(@PathVariable("id") Integer id){
	    TsjyfwCommentEntity tsjyfwComment = tsjyfwCommentService.queryObject(id);
	    if(tsjyfwComment != null){
            List<String> list = tsjyfwComment.getImgs()==null?new ArrayList<String>():Arrays.asList(tsjyfwComment.getImgs().split(","));
            tsjyfwComment.setImglist(list);
        }
	    return R.ok().put("tsjyfwComment", tsjyfwComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TsjyfwCommentEntity tsjyfwComment){
		tsjyfwCommentService.save(tsjyfwComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TsjyfwCommentEntity tsjyfwComment){
		tsjyfwCommentService.update(tsjyfwComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tsjyfwCommentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
