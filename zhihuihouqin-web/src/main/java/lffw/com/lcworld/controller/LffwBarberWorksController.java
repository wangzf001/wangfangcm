package com.lcworld.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.LffwBarberWorksEntity;
import com.lcworld.service.LffwBarberWorksService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务-理发师作品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
@RestController
@RequestMapping("lffwbarberworks")
public class LffwBarberWorksController {
	@Autowired
	private LffwBarberWorksService lffwBarberWorksService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwBarberWorksEntity> lffwBarberWorksList = lffwBarberWorksService.queryList(query);
		int total = lffwBarberWorksService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwBarberWorksList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		LffwBarberWorksEntity lffwBarberWorks = lffwBarberWorksService.queryObject(id);
		
		return R.ok().put("lffwBarberWorks", lffwBarberWorks);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody LffwBarberWorksEntity lffwBarberWorks){
	    lffwBarberWorks.setCreatetime(new Date());
	    if(lffwBarberWorks.getShowonindex() == null){
	        lffwBarberWorks.setShowonindex(0);
	    }
		lffwBarberWorksService.save(lffwBarberWorks);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody LffwBarberWorksEntity lffwBarberWorks){
		lffwBarberWorksService.update(lffwBarberWorks);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		lffwBarberWorksService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
