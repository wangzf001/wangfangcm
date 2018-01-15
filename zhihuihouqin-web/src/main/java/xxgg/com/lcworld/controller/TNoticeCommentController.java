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

import com.lcworld.entity.TNoticeCommentEntity;
import com.lcworld.service.TNoticeCommentService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;


/**
 * 公告评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
@RestController
@RequestMapping("noticecomment")
public class TNoticeCommentController {
	@Autowired
	private TNoticeCommentService tNoticeCommentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        if(Constant.SUPER_ADMIN == ShiroUtils.getUserId()){
        	query.put("auth", 1);
        }else{
        	query.put("auth", 0);
        }
		List<TNoticeCommentEntity> tNoticeCommentList = tNoticeCommentService.queryList(query);
		int total = tNoticeCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tNoticeCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TNoticeCommentEntity tNoticeComment = tNoticeCommentService.queryObject(id);
		
		return R.ok().put("noticeComment", tNoticeComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TNoticeCommentEntity tNoticeComment){
		tNoticeCommentService.save(tNoticeComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TNoticeCommentEntity tNoticeComment){
		tNoticeCommentService.update(tNoticeComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tNoticeCommentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
