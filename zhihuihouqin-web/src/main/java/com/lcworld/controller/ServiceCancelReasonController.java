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

import com.lcworld.entity.ServiceCancelReasonEntity;
import com.lcworld.service.ServiceCancelReasonService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 取消原因表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-18 14:19:15
 */
@RestController
@RequestMapping("servicecancelreason")
public class ServiceCancelReasonController {
	@Autowired
	private ServiceCancelReasonService serviceCancelReasonService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("servicecancelreason:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ServiceCancelReasonEntity> serviceCancelReasonList = serviceCancelReasonService.queryList(query);
		int total = serviceCancelReasonService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(serviceCancelReasonList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("servicecancelreason:info")
	public R info(@PathVariable("id") Integer id){
		ServiceCancelReasonEntity serviceCancelReason = serviceCancelReasonService.queryObject(id);
		
		return R.ok().put("serviceCancelReason", serviceCancelReason);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("servicecancelreason:save")
	public R save(@RequestBody ServiceCancelReasonEntity serviceCancelReason){
		serviceCancelReasonService.save(serviceCancelReason);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("servicecancelreason:update")
	public R update(@RequestBody ServiceCancelReasonEntity serviceCancelReason){
		serviceCancelReasonService.update(serviceCancelReason);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("servicecancelreason:delete")
	public R delete(@RequestBody Integer[] ids){
		serviceCancelReasonService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
