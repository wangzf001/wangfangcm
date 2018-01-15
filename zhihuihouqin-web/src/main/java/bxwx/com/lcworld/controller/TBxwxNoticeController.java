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

import com.lcworld.entity.TBxwxNoticeEntity;
import com.lcworld.service.TBxwxNoticeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 维修消息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 14:21:50
 */
@RestController
@RequestMapping("tbxwxnotice")
public class TBxwxNoticeController {
	@Autowired
	private TBxwxNoticeService tBxwxNoticeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tbxwxnotice:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TBxwxNoticeEntity> tBxwxNoticeList = tBxwxNoticeService.queryList(query);
		int total = tBxwxNoticeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tBxwxNoticeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tbxwxnotice:info")
	public R info(@PathVariable("id") Integer id){
		TBxwxNoticeEntity tBxwxNotice = tBxwxNoticeService.queryObject(id);
		
		return R.ok().put("tBxwxNotice", tBxwxNotice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tbxwxnotice:save")
	public R save(@RequestBody TBxwxNoticeEntity tBxwxNotice){
		tBxwxNoticeService.save(tBxwxNotice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tbxwxnotice:update")
	public R update(@RequestBody TBxwxNoticeEntity tBxwxNotice){
		tBxwxNoticeService.update(tBxwxNotice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tbxwxnotice:delete")
	public R delete(@RequestBody Integer[] ids){
		tBxwxNoticeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
