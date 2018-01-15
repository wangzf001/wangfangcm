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

import com.lcworld.entity.ServiceTypeEntity;
import com.lcworld.service.ServiceTypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 服务分类表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:00:55
 */
@RestController
@RequestMapping("servicetype")
public class ServiceTypeController {
	@Autowired
	private ServiceTypeService serviceTypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("servicetype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ServiceTypeEntity> serviceTypeList = serviceTypeService.queryList(query);
		int total = serviceTypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(serviceTypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("servicetype:info")
	public R info(@PathVariable("id") Integer id){
		ServiceTypeEntity serviceType = serviceTypeService.queryObject(id);
		
		return R.ok().put("serviceType", serviceType);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("servicetype:save")
	public R save(@RequestBody ServiceTypeEntity serviceType){
		serviceTypeService.save(serviceType);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("servicetype:update")
	public R update(@RequestBody ServiceTypeEntity serviceType){
		serviceTypeService.update(serviceType);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("servicetype:delete")
	public R delete(@RequestBody Integer[] ids){
		serviceTypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
