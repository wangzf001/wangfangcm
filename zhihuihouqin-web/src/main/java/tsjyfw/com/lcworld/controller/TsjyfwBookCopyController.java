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

import com.lcworld.entity.TsjyfwBookCopyEntity;
import com.lcworld.service.TsjyfwBookCopyService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 17:48:09
 */
@RestController
@RequestMapping("tsjyfwbookcopy")
public class TsjyfwBookCopyController {
	@Autowired
	private TsjyfwBookCopyService tsjyfwBookCopyService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TsjyfwBookCopyEntity> tsjyfwBookCopyList = tsjyfwBookCopyService.queryList(query);
		int total = tsjyfwBookCopyService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tsjyfwBookCopyList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TsjyfwBookCopyEntity tsjyfwBookCopy = tsjyfwBookCopyService.queryObject(id);
		
		return R.ok().put("tsjyfwBookCopy", tsjyfwBookCopy);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TsjyfwBookCopyEntity tsjyfwBookCopy){
	    tsjyfwBookCopy.setBorrowcount(0);
		tsjyfwBookCopyService.save(tsjyfwBookCopy);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TsjyfwBookCopyEntity tsjyfwBookCopy){
		tsjyfwBookCopyService.update(tsjyfwBookCopy);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tsjyfwBookCopyService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
