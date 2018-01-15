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

import com.lcworld.entity.TNoticeUserinterestEntity;
import com.lcworld.service.TNoticeUserinterestService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 用户感兴趣公告分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
@RestController
@RequestMapping("noticeuserinterest")
public class TNoticeUserinterestController {
	@Autowired
	private TNoticeUserinterestService tNoticeUserinterestService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TNoticeUserinterestEntity> tNoticeUserinterestList = tNoticeUserinterestService.queryList(query);
		int total = tNoticeUserinterestService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tNoticeUserinterestList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TNoticeUserinterestEntity tNoticeUserinterest = tNoticeUserinterestService.queryObject(id);
		
		return R.ok().put("noticeUserinterest", tNoticeUserinterest);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TNoticeUserinterestEntity tNoticeUserinterest){
		tNoticeUserinterestService.save(tNoticeUserinterest);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TNoticeUserinterestEntity tNoticeUserinterest){
		tNoticeUserinterestService.update(tNoticeUserinterest);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tNoticeUserinterestService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
