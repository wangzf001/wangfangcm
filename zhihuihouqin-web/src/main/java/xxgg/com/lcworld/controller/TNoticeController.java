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

import com.lcworld.entity.TNoticeEntity;
import com.lcworld.service.TNoticeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 公告
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
@RestController
@RequestMapping("notice")
public class TNoticeController {
	@Autowired
	private TNoticeService tNoticeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		int i =1/0;
		if ("".equals((String)params.get("typeid"))) {
			params.remove("typeid");
		}
		//查询列表数据
        Query query = new Query(params);
		List<TNoticeEntity> tNoticeList = tNoticeService.queryList(query);
		int total = tNoticeService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tNoticeList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TNoticeEntity tNotice = tNoticeService.queryObject(id);
		
		return R.ok().put("notice", tNotice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TNoticeEntity tNotice){
		tNoticeService.save(tNotice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TNoticeEntity tNotice){
		tNoticeService.update(tNotice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tNoticeService.deleteBatch(ids);
		return R.ok();
	}
	
}
