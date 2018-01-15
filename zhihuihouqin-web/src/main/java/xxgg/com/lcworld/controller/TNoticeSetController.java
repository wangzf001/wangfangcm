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

import com.lcworld.entity.TNoticeSetEntity;
import com.lcworld.service.TNoticeSetService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 消息设置
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
@RestController
@RequestMapping("noticeset")
public class TNoticeSetController {
	@Autowired
	private TNoticeSetService tNoticeSetService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TNoticeSetEntity> tNoticeSetList = tNoticeSetService.queryList(query);
		int total = tNoticeSetService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tNoticeSetList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TNoticeSetEntity tNoticeSet = tNoticeSetService.queryObject(id);
		
		return R.ok().put("noticeSet", tNoticeSet);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TNoticeSetEntity tNoticeSet){
		tNoticeSetService.save(tNoticeSet);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TNoticeSetEntity tNoticeSet){
		tNoticeSetService.update(tNoticeSet);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tNoticeSetService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
