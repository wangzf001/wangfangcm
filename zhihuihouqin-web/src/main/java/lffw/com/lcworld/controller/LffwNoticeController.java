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

import com.lcworld.entity.LffwNoticeEntity;
import com.lcworld.service.LffwNoticeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务-咨询
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
@RestController
@RequestMapping("lffwnotice")
public class LffwNoticeController {
	@Autowired
	private LffwNoticeService lffwNoticeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("lffwnotice:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwNoticeEntity> lffwNoticeList = lffwNoticeService.queryList(query);
		int total = lffwNoticeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwNoticeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("lffwnotice:info")
	public R info(@PathVariable("id") Integer id){
		LffwNoticeEntity lffwNotice = lffwNoticeService.queryObject(id);
		
		return R.ok().put("lffwNotice", lffwNotice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("lffwnotice:save")
	public R save(@RequestBody LffwNoticeEntity lffwNotice){
		lffwNoticeService.save(lffwNotice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("lffwnotice:update")
	public R update(@RequestBody LffwNoticeEntity lffwNotice){
		lffwNoticeService.update(lffwNotice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("lffwnotice:delete")
	public R delete(@RequestBody Integer[] ids){
		lffwNoticeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
