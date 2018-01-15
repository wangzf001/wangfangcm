package com.lcworld.controller;

import java.util.ArrayList;
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

import com.lcworld.entity.BgypfwProductimgEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.service.ServiceService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;


/**
 * 服务表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:00:55
 */
@RestController
@RequestMapping("service")
public class ServiceController {
	@Autowired
	private ServiceService serviceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("service:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
	    Query query = null;
	    SysUserEntity user = ShiroUtils.getUserEntity();
	    List<ServiceEntity> serviceList = null;
	    int total = 0 ;
	    if(Constant.SUPER_ADMIN ==user.getUserId()){
	        query = new Query(params);
	        serviceList = serviceService.queryList(query);
	        total = serviceService.queryTotal(query);
        }else{
            params.put("uid", user.getUserId().intValue());
            query = new Query(params);
            serviceList = serviceService.queryAllSysRolesByUid(user.getUserId(),query);
            total = serviceService.queryAllSysRolesByUidtotal(query);
        }
		PageUtils pageUtil = new PageUtils(serviceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/servicelist")
	@RequiresPermissions("service:list")
	public R servicelist(@RequestParam Map<String, Object> params){
	    List<ServiceEntity> serviceList = serviceService.queryList(new HashMap<String,Object>());
	    return R.ok().put("serviceList", serviceList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("service:info")
	public R info(@PathVariable("id") Integer id){
		ServiceEntity service = serviceService.queryObject(id);
		
		return R.ok().put("service", service);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("service:save")
	public R save(@RequestBody ServiceEntity service){
		service.setImgEntityList(service.getImgEntityList());
		serviceService.save(service);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("service:update")
	public R update(@RequestBody ServiceEntity service){
		service.setImgEntityList(service.getImgEntityList());
		serviceService.update(service);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("service:delete")
	public R delete(@RequestBody Integer[] ids){
		serviceService.deleteBatch(ids);
		
		return R.ok();
	}
	
	
	/**
	 * 获取权限类型
	 */
	@RequestMapping("/roletypelist")
	public R roletypelist(){
		SysUserEntity user = ShiroUtils.getUserEntity();
	   List<ServiceEntity> roletypelist = serviceService.queryAllSysRolesByUid(user.getUserId());
	   return R.ok().put("roletypelist", roletypelist);
	}
	
	
	
}
