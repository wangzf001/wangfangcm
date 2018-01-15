package com.lcworld.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.TGxdfwClothestypeEntity;
import com.lcworld.service.TGxdfwClothestypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 干洗店服务-衣服类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:55
 */
@RestController
@RequestMapping("gxdfwclothestype")
public class TGxdfwClothestypeController {
	@Autowired
	private TGxdfwClothestypeService tGxdfwClothestypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TGxdfwClothestypeEntity> tGxdfwClothestypeList = tGxdfwClothestypeService.queryList(query);
		int total = tGxdfwClothestypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tGxdfwClothestypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/typelist")
	public R typelist(@RequestParam Map<String, Object> params){
	    List<TGxdfwClothestypeEntity> tGxdfwClothestypeList = tGxdfwClothestypeService.queryList(params);
	    return R.ok().put("typelist", tGxdfwClothestypeList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TGxdfwClothestypeEntity tGxdfwClothestype = tGxdfwClothestypeService.queryObject(id);
		
		return R.ok().put("gxdfwClothestype", tGxdfwClothestype);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TGxdfwClothestypeEntity tGxdfwClothestype){
		tGxdfwClothestypeService.save(tGxdfwClothestype);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TGxdfwClothestypeEntity tGxdfwClothestype){
		tGxdfwClothestypeService.update(tGxdfwClothestype);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tGxdfwClothestypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
