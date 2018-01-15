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

import com.lcworld.entity.BgypfwProducpackSpecEntity;
import com.lcworld.service.BgypfwProducpackSpecService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 办公用品服务-包装规格表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 20:10:15
 */
@RestController
@RequestMapping("bgypfwproducpackspec")
public class BgypfwProducpackSpecController {
	@Autowired
	private BgypfwProducpackSpecService bgypfwProducpackSpecService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("bgypfwproducpackspec:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwProducpackSpecEntity> bgypfwProducpackSpecList = bgypfwProducpackSpecService.queryList(query);
		int total = bgypfwProducpackSpecService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwProducpackSpecList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("bgypfwproducpackspec:info")
	public R info(@PathVariable("id") Integer id){
		BgypfwProducpackSpecEntity bgypfwProducpackSpec = bgypfwProducpackSpecService.queryObject(id);
		
		return R.ok().put("bgypfwProducpackSpec", bgypfwProducpackSpec);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("bgypfwproducpackspec:save")
	public R save(@RequestBody BgypfwProducpackSpecEntity bgypfwProducpackSpec){
		bgypfwProducpackSpecService.save(bgypfwProducpackSpec);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("bgypfwproducpackspec:update")
	public R update(@RequestBody BgypfwProducpackSpecEntity bgypfwProducpackSpec){
		bgypfwProducpackSpecService.update(bgypfwProducpackSpec);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("bgypfwproducpackspec:delete")
	public R delete(@RequestBody Integer[] ids){
		bgypfwProducpackSpecService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
