package com.lcworld.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.SysLog;
import com.lcworld.entity.SysRoleEntity;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.service.SysRoleMenuService;
import com.lcworld.service.SysRoleService;
import com.lcworld.service.SysUserRoleService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.validator.ValidatorUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
    private SysUserRoleService sysUserRoleService;
	
	
	/**
	 * 角色列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public R list(@RequestParam Map<String, Object> params){
	    Map<String, Object> map = new HashMap<>();
	    map.putAll(params);
	    
        List<SysRoleEntity> list = null;
        
        //如果不是超级管理员，则只查询自己所拥有的角色列表
        int total = 0;
        Query query  = null;
        if(getUserId() != Constant.SUPER_ADMIN){
            map.put("uid", getUserId());
            int urmin =  sysRoleService.queryNPUserRoleMin(map);
            
            map.put("min", urmin);
            query = new Query(map);
            list = sysRoleService.queryListByUid(query);
            total = sysRoleService.queryTotalByUid(map);
        }else{
            query = new Query(map);
            list = sysRoleService.queryList(query);
            total = sysRoleService.queryTotal(map);
        }
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 角色列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public R select(){
		Map<String, Object> map = new HashMap<>();
		List<SysRoleEntity> list = null;
		//如果不是超级管理员，则只查询自己所拥有的角色列表
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("uid", getUserId());
			int urmin =  sysRoleService.queryNPUserRoleMin(map);
			map.put("min", urmin);
			list = sysRoleService.queryListByUid(map);
		}else{
		    list = sysRoleService.queryList(new HashMap<String,Object>());
		}
		
		return R.ok().put("list", list);
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 角色列表
	 */
	@RequestMapping("/publist")
	@RequiresPermissions("sys:role:list")
	public R publist(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的角色列表
		if(getUserId() != Constant.SUPER_ADMIN){
			//params.put("createUserId", getUserId());
			params.put("uid", getUserId().intValue());
		}
		params.put("type", 2);
		//查询列表数据
		
		Query query = new Query(params);
		List<SysRoleEntity> list = sysRoleService.querypubList(query);
		int total = sysRoleService.querypubTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 角色列表
	 */
	@RequestMapping("/pubselect")
	@RequiresPermissions("sys:role:select")
	public R pubselect(){
		Map<String, Object> map = new HashMap<>();
		map.put("type", 2);
		//如果不是超级管理员，则只查询自己所拥有的角色列表
		List<SysRoleEntity> list=null;
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("uid", getUserId());
			 list= sysRoleService.queryRolelistByUid(map);
		}else{
			list = sysRoleService.queryList(map);
		}
		return R.ok().put("list", list);
	}
	
	/**
	 * 角色信息
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.queryObject(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		
		return R.ok().put("role", role);
	}
	
	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleEntity role){
		ValidatorUtils.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.save(role);
		
		return R.ok();
	}
	
	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRoleEntity role){
		ValidatorUtils.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.update(role);
		
		return R.ok();
	}
	
	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return R.ok();
	}
	
	/**
	 * 获取权限级别
	 */
	@RequestMapping("/pubrolesortlist")
	public R pubrolesortlist(String type ){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Integer min=Integer.MAX_VALUE;
		SysUserEntity user = ShiroUtils.getUserEntity();
		if( Constant.SUPER_ADMIN == user.getUserId()){
			min =0;
		}else{
			//获取指定服务类型的权限最小级
			JSONObject param = new JSONObject();
			param.put("uid", user.getUserId().intValue());
			param.put("servicecode", type);
			min = sysRoleService.queryMinRoleSort(param);
			if(min == null){
				min = 0;
			}
		}
		for(int i = min+1; i < 3;i++ ){
			Map<String,Object> map= new HashMap<String,Object>();
			map.put("id", i);
			map.put("name", getStr(i));
			list.add(map);
		}
		
		return R.ok().put("rolesortlist", list);
	}
	
	/**
	 * 获取权限级别
	 */
	@RequestMapping("/rolesortlist")
	public R rolesortlist( ){
	    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	    Integer min=Integer.MAX_VALUE;
	    SysUserEntity user = ShiroUtils.getUserEntity();
	    if( Constant.SUPER_ADMIN == user.getUserId()){
	        min =0;
	    }else{
	        //获取指定服务类型的权限最小级
	        min =3;
	        JSONObject param = new JSONObject();
	        param.put("uid", user.getUserId().intValue());
	        List<SysRoleEntity> rlist = sysUserRoleService.queryRoles(param);
	        if(ValidateUtil.isValid(rlist)){
	            for(SysRoleEntity r : rlist){
	                if(r.getRolesort() != null && r.getRolesort() != null  && r.getRolesort() < min ){
	                    min = r.getRolesort();
	                }
	            }
	        }
	        if(min == null){
	            min = 0;
	        }
	    }
	    for(int i = min+1; i < 3;i++ ){
	        Map<String,Object> map= new HashMap<String,Object>();
	        map.put("id", i);
	        map.put("name", getStr(i));
	        list.add(map);
	    }
	    if(Constant.SUPER_ADMIN == user.getUserId()){
	        Map<String,Object> map= new HashMap<String,Object>();
            map.put("id","10");
            map.put("name", "服务部门管理人员");
            list.add(map);
	    }
	    
	    return R.ok().put("rolesortlist", list);
	}

	private String getStr(int i) {
		String str = "司局管理员";
		switch(i){
		case 1: str = "司局管理员";break;
		case 2: str = "处室管理员";break;
		}
		return str;
	}
	
}
