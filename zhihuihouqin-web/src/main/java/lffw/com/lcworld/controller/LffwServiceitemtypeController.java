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

import com.lcworld.entity.LffwPeriodtypeEntity;
import com.lcworld.entity.LffwServiceitemtypeEntity;
import com.lcworld.service.LffwServiceitemtypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务-服务项目分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:13
 */
@RestController
@RequestMapping("lffwserviceitemtype")
public class LffwServiceitemtypeController {
	@Autowired
	private LffwServiceitemtypeService lffwServiceitemtypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("lffwserviceitemtype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwServiceitemtypeEntity> lffwServiceitemtypeList = lffwServiceitemtypeService.queryList(query);
		int total = lffwServiceitemtypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwServiceitemtypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
     * 列表
     */
    @RequestMapping("/typelist")
    public R typelist(@RequestParam Map<String, Object> params){
        //查询列表数据
        List<LffwServiceitemtypeEntity> lffwPeriodtypeList = lffwServiceitemtypeService.queryList(params);
        return R.ok().put("typelist", lffwPeriodtypeList);
    }
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("lffwserviceitemtype:info")
	public R info(@PathVariable("id") Integer id){
		LffwServiceitemtypeEntity lffwServiceitemtype = lffwServiceitemtypeService.queryObject(id);
		
		return R.ok().put("lffwServiceitemtype", lffwServiceitemtype);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("lffwserviceitemtype:save")
	public R save(@RequestBody LffwServiceitemtypeEntity lffwServiceitemtype){
		lffwServiceitemtypeService.save(lffwServiceitemtype);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("lffwserviceitemtype:update")
	public R update(@RequestBody LffwServiceitemtypeEntity lffwServiceitemtype){
		lffwServiceitemtypeService.update(lffwServiceitemtype);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("lffwserviceitemtype:delete")
	public R delete(@RequestBody Integer[] ids){
		lffwServiceitemtypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
