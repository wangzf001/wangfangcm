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

import com.lcworld.entity.BgypfwCartEntity;
import com.lcworld.service.BgypfwCartService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 办公用品服务-购物车
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
@RestController
@RequestMapping("bgypfwcart")
public class BgypfwCartController {
	@Autowired
	private BgypfwCartService bgypfwCartService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
		public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwCartEntity> bgypfwCartList = bgypfwCartService.queryList(query);
		int total = bgypfwCartService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwCartList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
		public R info(@PathVariable("id") Integer id){
		BgypfwCartEntity bgypfwCart = bgypfwCartService.queryObject(id);
		
		return R.ok().put("bgypfwCart", bgypfwCart);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
		public R save(@RequestBody BgypfwCartEntity bgypfwCart){
		bgypfwCartService.save(bgypfwCart);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
		public R update(@RequestBody BgypfwCartEntity bgypfwCart){
		bgypfwCartService.update(bgypfwCart);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
		public R delete(@RequestBody Integer[] ids){
		bgypfwCartService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
