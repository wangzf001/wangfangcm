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

import com.lcworld.entity.TsjyfwBookEntity;
import com.lcworld.service.TsjyfwBookService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 图书借阅服务-图书
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
@RestController
@RequestMapping("tsjyfwbook")
public class TsjyfwBookController {
	@Autowired
	private TsjyfwBookService tsjyfwBookService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tsjyfwbook:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TsjyfwBookEntity> tsjyfwBookList = tsjyfwBookService.queryList(query);
		int total = tsjyfwBookService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tsjyfwBookList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tsjyfwbook:info")
	public R info(@PathVariable("id") Integer id){
		TsjyfwBookEntity tsjyfwBook = tsjyfwBookService.queryObject(id);
		
		return R.ok().put("tsjyfwBook", tsjyfwBook);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tsjyfwbook:save")
	public R save(@RequestBody TsjyfwBookEntity tsjyfwBook){
	    tsjyfwBook.setBorrowcount(0);
	    tsjyfwBook.setFavorcount(0);
	    if(null == tsjyfwBook.getBollowstatus())
	    tsjyfwBook.setBollowstatus(1);
		tsjyfwBookService.save(tsjyfwBook);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tsjyfwbook:update")
	public R update(@RequestBody TsjyfwBookEntity tsjyfwBook){
		tsjyfwBookService.update(tsjyfwBook);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tsjyfwbook:delete")
	public R delete(@RequestBody Integer[] ids){
		tsjyfwBookService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
