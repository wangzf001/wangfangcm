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
import com.lcworld.entity.TBxwxCommentEntity;
import com.lcworld.service.TBxwxCommentService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 报修维修评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
@RestController
@RequestMapping("tbxwxcomment")
public class TBxwxCommentController {
	@Autowired
	private TBxwxCommentService tBxwxCommentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TBxwxCommentEntity> tBxwxCommentList = tBxwxCommentService.queryList(query);
		int total = tBxwxCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tBxwxCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	   /**
     * 信息
     */
    @RequestMapping("/commnetinfo/{id}")
    public R commnetinfo(@PathVariable("id") Integer id){
        TBxwxCommentEntity Comment = tBxwxCommentService.queryObject(id);
        List<String> list =null;
        if(Comment != null){
             list = Comment.getImgs()==null?new ArrayList<String>():Arrays.asList(Comment.getImgs().split(","));
        }
        return R.ok().put("tBxwxComment", Comment).put("commentimglist", list);
    }
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TBxwxCommentEntity tBxwxComment = tBxwxCommentService.queryObject(id);
		
		return R.ok().put("tBxwxComment", tBxwxComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TBxwxCommentEntity tBxwxComment){
		tBxwxCommentService.save(tBxwxComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TBxwxCommentEntity tBxwxComment){
		tBxwxCommentService.update(tBxwxComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tBxwxCommentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
