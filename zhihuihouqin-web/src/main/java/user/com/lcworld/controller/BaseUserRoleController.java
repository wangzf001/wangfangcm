package com.lcworld.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.UserFrontRolesDTO;
import com.lcworld.entity.BaseFrontrolesEntity;
import com.lcworld.entity.BaseUserRoleEntity;
import com.lcworld.entity.SysRoleEntity;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.service.BaseFrontrolesService;
import com.lcworld.service.BaseUserRoleService;
import com.lcworld.service.SysRoleService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;
import com.lcworld.utils.util.ValidateUtil;


/**
 * 用户角色表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-31 11:23:51
 */
@RestController
@RequestMapping("baseuserrole")
public class BaseUserRoleController {
	@Autowired
	private BaseUserRoleService baseUserRoleService;
	@Autowired
	private BaseFrontrolesService baseFrontrolesService;
	@Autowired
    private SysRoleService sysRoleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("baseuserrole:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BaseUserRoleEntity> baseUserRoleList = baseUserRoleService.queryList(query);
		int total = baseUserRoleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(baseUserRoleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("baseuserrole:info")
	public R info(@PathVariable("id") Integer id){
		BaseUserRoleEntity baseUserRole = baseUserRoleService.queryObject(id);
		
		return R.ok().put("baseUserRole", baseUserRole);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("baseuserrole:save")
	public R save(@RequestBody BaseUserRoleEntity baseUserRole){
		baseUserRoleService.save(baseUserRole);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("baseuserrole:update")
	public R update(@RequestBody BaseUserRoleEntity baseUserRole){
		baseUserRoleService.update(baseUserRole);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("baseuserrole:delete")
	public R delete(@RequestBody Integer[] ids){
		baseUserRoleService.deleteBatch(ids);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/userrolelist")
	public R userrolelist(Integer uid){
	    if(uid == null){
	        return R.ok();
	    }else{
	        JSONObject params = new JSONObject();
	        params.put("uid", uid);
	        
	        JSONObject param = new JSONObject();
	        SysUserEntity user = ShiroUtils.getUserEntity();
	        List<SysRoleEntity> list= null;
	        if(Constant.SUPER_ADMIN == user.getUserId()){
	            param.put("type", 2);
	            list = sysRoleService.queryList(param);
	        }else{
	            param.put("uid", ShiroUtils.getUserId().intValue());
	            param.put("type", 2);
	            list = sysRoleService.querypubList1(param);
	            
	        }
	        List<String> servicecodelist = getids(list);
	        PageUtils pageUtil= null;
            params.put("servicecodelist", servicecodelist);
	        Query q = new Query(params);
	        if(!ValidateUtil.isValid(servicecodelist)){
	            pageUtil = new PageUtils(packageRoleList(null,null), 0, q.getLimit(), q.getPage());
	        }else{
	            q = new Query(params);
	            List<UserFrontRolesDTO> froses = baseFrontrolesService.queryRoleList(q);
	            int total = baseFrontrolesService.queryTotal(q);
	            List<UserFrontRolesDTO> baseUserRoleList = baseUserRoleService.queryUserRoleList(q);
	            pageUtil = new PageUtils(packageRoleList(froses,baseUserRoleList), total, q.getLimit(), q.getPage());
	        }
	       
	       
	        return R.ok().put("page", pageUtil).put("uid", uid);
	    }
	}
	
	private List<String> getids(List<SysRoleEntity> list) {
        List<String> ids = new ArrayList<String>();
	    if(ValidateUtil.isValid(list)){
            for(SysRoleEntity r : list){
                ids.add(r.getRoletype().toString());
            }
        }
	    return ids;
    }


    /**
	 * 更新权限
	 */
	@RequestMapping("/updateuserrole")
	public R updateuserrole(Integer uid,String ids,String selectids){
	    JSONObject params = new JSONObject();
	    params.put("roleids", Arrays.asList(ids.split(",")));
	    params.put("uid", uid);
	    if(ValidateUtil.isValid(uid) && ValidateUtil.isValid(ids) ){
	        baseUserRoleService.deleteByParams(params);
	        if(ValidateUtil.isValid(selectids)){
	            List<BaseUserRoleEntity> rlist = new ArrayList<BaseUserRoleEntity>();
	            for(String roleid : selectids.split(",")){
	                BaseUserRoleEntity ur = new BaseUserRoleEntity();
	                ur.setRoleid(Integer.parseInt(roleid));
	                ur.setUid(uid);
	                rlist.add(ur);
	            }
	            baseUserRoleService.savebench(rlist);
	        }
	    }else{
	        return R.error(400, "没有可选的角色");
	    }
	    return R.ok();
	    
	}


    

    private List<UserFrontRolesDTO> packageRoleList(List<UserFrontRolesDTO> froses, List<UserFrontRolesDTO> baseUserRoleList) {
        if(ValidateUtil.isValid(froses) && ValidateUtil.isValid(baseUserRoleList)){
                for( UserFrontRolesDTO role : froses){
                    role.setStatus(0);
                    for(UserFrontRolesDTO ur :baseUserRoleList){
                        if(ur.getId() == role.getId()){
                            role.setStatus(1);
                            break;
                        }
                    }
                }
        }
        return froses;
    }


}
