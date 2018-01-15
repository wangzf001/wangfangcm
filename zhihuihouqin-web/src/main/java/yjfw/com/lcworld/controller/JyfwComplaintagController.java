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

import com.lcworld.entity.JyfwComplaintagEntity;
import com.lcworld.service.JyfwComplaintagService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 14:53:40
 */
@RestController
@RequestMapping("jyfwcomplaintag")
public class JyfwComplaintagController {
	@Autowired
	private JyfwComplaintagService jyfwComplaintagService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<JyfwComplaintagEntity> jyfwComplaintagList = jyfwComplaintagService.queryList(query);
		int total = jyfwComplaintagService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(jyfwComplaintagList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		JyfwComplaintagEntity jyfwComplaintag = jyfwComplaintagService.queryObject(id);
		
		return R.ok().put("jyfwComplaintag", jyfwComplaintag);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody JyfwComplaintagEntity jyfwComplaintag){
		jyfwComplaintagService.save(jyfwComplaintag);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody JyfwComplaintagEntity jyfwComplaintag){
		jyfwComplaintagService.update(jyfwComplaintag);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		jyfwComplaintagService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
