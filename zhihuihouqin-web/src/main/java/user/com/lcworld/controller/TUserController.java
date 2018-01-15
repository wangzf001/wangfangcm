package com.lcworld.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.UserDTO;
import com.lcworld.entity.OfficeEntity;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.entity.TUserEntity;
import com.lcworld.service.DepartService;
import com.lcworld.service.NationService;
import com.lcworld.service.OfficeService;
import com.lcworld.service.SysRoleService;
import com.lcworld.service.TUserPositionService;
import com.lcworld.service.TUserService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.utils.wxutil.ConstantUtil;
import com.lcworld.utils.wxutil.MD5Util;


/**
 * 用户表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 14:55:26
 */
@RestController
@RequestMapping("tuser")
public class TUserController {
	@Autowired
	private TUserService tUserService;
	   @Autowired
	    private NationService nationService;
	    @Autowired
	    private TUserPositionService tUserPositionService;
	    @Autowired
	    private DepartService departService;
	    @Autowired
	    private OfficeService officeService;
	    @Autowired
	    private SysRoleService sysRoleService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tuser:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
	    
        SysUserEntity user = ShiroUtils.getUserEntity();
        Query query=null;
        int total=0;
        List<UserDTO> tUserList=null;
        if(Constant.SUPER_ADMIN ==user.getUserId()){
            query = new Query(params);
            tUserList = tUserService.queryUserList(query);
            addisumanager(tUserList);
            total = tUserService.queryUserTotal(query);
        }else{
            params.put("uid", user.getUserId().intValue());
            query = new Query(params);
            tUserList = tUserService.queryUserList1(query);
            addisumanager(tUserList);
            total = tUserService.queryUserTotal1(query);
        }
		PageUtils pageUtil = new PageUtils(tUserList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	
	private void addisumanager(List<UserDTO> tUserList) {
	        if(ValidateUtil.isValid(tUserList)){
	            for(UserDTO u : tUserList){
	                u.setHascumanager(ShiroUtils.getSubject().isPermitted("commonuser:manage")?1:0);
	                u.setHaspumanager(ShiroUtils.getSubject().isPermitted("pubuser:manage")?1:0);
	            }
	        }
    }


   


    /**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tuser:info")
	public R info(@PathVariable("id") Integer id){
		TUserEntity tUser = tUserService.queryObject(id);
		return R.ok().put("tUser", tUser);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tuser:save")
	public R save(@RequestBody TUserEntity tUser){
	    tUser.setCreatetime(new Date());
		tUserService.save(tUser);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tuser:update")
	public R update(@RequestBody TUserEntity tUser){
		tUserService.update(tUser);
		return R.ok();
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("/updatepass")
	public R updatepass(@RequestBody TUserEntity tUser){
	    tUser.setPassword(MD5Util.MD5Encode(tUser.getPassword(), "UTF-8"));
	    tUserService.update(tUser);
	    return R.ok().put("tUser", tUser);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tuser:delete")
	public R delete(@RequestBody Integer[] ids){
		tUserService.deleteBatch(ids);
		return R.ok();
	}
	
	/**
	 * pn 
	 */
	@RequestMapping("/getpnlist")
	public R getpnlist(){
	    R result = new R();
	    result.put("nationlist", nationService.queryList(new HashMap<String,Object>()));
        result.put("positionlist", tUserPositionService.queryList(new HashMap<String,Object>()));
        SysUserEntity user = ShiroUtils.getUserEntity();
        if(Constant.SUPER_ADMIN == user.getUserId()){
            result.put("departlist", departService.queryList(new HashMap<String,Object>()));
            result.put("officelist", officeService.queryList(new HashMap<String,Object>()));
        }else{
            JSONObject obj1 = new JSONObject();
            obj1.put("uid", user.getUserId());
            int min= sysRoleService.queryNPUserRoleMin(obj1);
            if(1 == min  || 2 == min ){
                JSONObject obj = new JSONObject();
                obj.put("departid", user.getDepartid());
                result.put("departlist", departService.queryList(obj));
                if(2 == min){
                    obj.put("officeid", user.getOfficeid());
                }
                result.put("officelist", officeService.queryList(obj));
            }
        }
        
	    return result;
	}
	
	 /**
     * 获取职位列表
     * @param req
     * @return
     */
    @RequestMapping("/getofficelist")
    public R getofficelist(Integer id){
        SysUserEntity user = ShiroUtils.getUserEntity();
        List<OfficeEntity> officelist = null;
        JSONObject obj = new JSONObject();
        obj.put("departid", id);
        if(Constant.SUPER_ADMIN == user.getUserId()){
            officelist =  officeService.queryList(obj);
        }else{
            JSONObject obj1 = new JSONObject();
            obj1.put("uid", user.getUserId());
            int min= sysRoleService.queryNPUserRoleMin(obj1);
            if(1 == min  || 2 == min ){
                obj.put("departid", id);
                if(2 == min){
                    obj.put("officeid", user.getOfficeid());
                }
                officelist = officeService.queryList(obj);
            }
        }
        return R.ok().put("officelist", officelist);
    }
    
   
}
