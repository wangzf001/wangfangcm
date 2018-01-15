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

import com.lcworld.entity.TUserAdviceEntity;
import com.lcworld.service.TUserAdviceService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 意见反馈
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:43
 */
@RestController
@RequestMapping("tuseradvice")
public class TUserAdviceController {
	@Autowired
	private TUserAdviceService tUserAdviceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tuseradvice:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TUserAdviceEntity> tUserAdviceList = tUserAdviceService.queryList(query);
		int total = tUserAdviceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tUserAdviceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tuseradvice:info")
	public R info(@PathVariable("id") Integer id){
		TUserAdviceEntity tUserAdvice = tUserAdviceService.queryObject(id);
		
		return R.ok().put("tUserAdvice", tUserAdvice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tuseradvice:save")
	public R save(@RequestBody TUserAdviceEntity tUserAdvice){
		tUserAdviceService.save(tUserAdvice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tuseradvice:update")
	public R update(@RequestBody TUserAdviceEntity tUserAdvice){
		tUserAdviceService.update(tUserAdvice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tuseradvice:delete")
	public R delete(@RequestBody Integer[] ids){
		tUserAdviceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
