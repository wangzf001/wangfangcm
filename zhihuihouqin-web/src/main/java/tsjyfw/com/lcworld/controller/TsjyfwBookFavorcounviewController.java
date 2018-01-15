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

import com.lcworld.entity.TsjyfwBookFavorcounviewEntity;
import com.lcworld.service.TsjyfwBookFavorcounviewService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * VIEW
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:24
 */
@RestController
@RequestMapping("tsjyfwbookfavorcounview")
public class TsjyfwBookFavorcounviewController {
	@Autowired
	private TsjyfwBookFavorcounviewService tsjyfwBookFavorcounviewService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tsjyfwbookfavorcounview:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TsjyfwBookFavorcounviewEntity> tsjyfwBookFavorcounviewList = tsjyfwBookFavorcounviewService.queryList(query);
		int total = tsjyfwBookFavorcounviewService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tsjyfwBookFavorcounviewList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{bookid}")
	@RequiresPermissions("tsjyfwbookfavorcounview:info")
	public R info(@PathVariable("bookid") Integer bookid){
		TsjyfwBookFavorcounviewEntity tsjyfwBookFavorcounview = tsjyfwBookFavorcounviewService.queryObject(bookid);
		
		return R.ok().put("tsjyfwBookFavorcounview", tsjyfwBookFavorcounview);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tsjyfwbookfavorcounview:save")
	public R save(@RequestBody TsjyfwBookFavorcounviewEntity tsjyfwBookFavorcounview){
		tsjyfwBookFavorcounviewService.save(tsjyfwBookFavorcounview);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tsjyfwbookfavorcounview:update")
	public R update(@RequestBody TsjyfwBookFavorcounviewEntity tsjyfwBookFavorcounview){
		tsjyfwBookFavorcounviewService.update(tsjyfwBookFavorcounview);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tsjyfwbookfavorcounview:delete")
	public R delete(@RequestBody Integer[] bookids){
		tsjyfwBookFavorcounviewService.deleteBatch(bookids);
		
		return R.ok();
	}
	
}
