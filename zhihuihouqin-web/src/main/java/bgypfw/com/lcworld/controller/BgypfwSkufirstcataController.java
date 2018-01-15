package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.BgypfwSkufirstcataEntity;
import com.lcworld.entity.BgypfwSkuidEntity;
import com.lcworld.service.BgypfwSkufirstcataService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 办公用品服务-规格分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
@RestController
@RequestMapping("bgypfwskufirstcata")
public class BgypfwSkufirstcataController {
	@Autowired
	private BgypfwSkufirstcataService bgypfwSkufirstcataService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
		public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwSkufirstcataEntity> bgypfwSkufirstcataList = bgypfwSkufirstcataService.queryList(query);
		int total = bgypfwSkufirstcataService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwSkufirstcataList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 列表
	 */
	@RequestMapping("/getCataList")
	public R getCataList(){
		//查询列表数据
		List<BgypfwSkufirstcataEntity> list = bgypfwSkufirstcataService.queryList(new HashMap<String, Object>());
		return R.ok().put("cataList", list);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
		public R info(@PathVariable("id") Integer id){
		BgypfwSkufirstcataEntity bgypfwSkufirstcata = bgypfwSkufirstcataService.queryObject(id);
		
		return R.ok().put("bgypfwSkufirstcata", bgypfwSkufirstcata);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
		public R save(@RequestBody BgypfwSkufirstcataEntity bgypfwSkufirstcata){
		bgypfwSkufirstcataService.save(bgypfwSkufirstcata);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
		public R update(@RequestBody BgypfwSkufirstcataEntity bgypfwSkufirstcata){
		bgypfwSkufirstcataService.update(bgypfwSkufirstcata);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
		public R delete(@RequestBody Integer[] ids){
		bgypfwSkufirstcataService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
