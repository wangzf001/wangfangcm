package com.lcworld.controller;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lcworld.entity.VisiuserLogEntity;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.UserVisibyEntity;
import com.lcworld.entity.VisiuserEntity;
import com.lcworld.service.UserVisibyService;
import com.lcworld.service.VisiuserLogService;
import com.lcworld.service.VisiuserService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;


/**
 * 来访人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-07 14:49:00
 */
@RestController
@RequestMapping("visiuser")
public class VisiuserController {
	@Autowired
	private VisiuserService visiuserService;
	@Autowired
	private UserVisibyService userVisibyService;
	@Autowired
    private VisiuserLogService visiuserLogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("visiuser:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Long uid = ShiroUtils.getUserEntity().getUserId();
		if(Constant.SUPER_ADMIN != uid.intValue()){
			try {
				ShiroUtils.getSubject().checkPermission("visiuser:check");
				params.put("rylx", 3);//安保管理
			} catch (AuthorizationException e) {
				System.out.println();
				//人员类型 2： 安保
				params.put("rylx", 2);
			}
		}
	    
        Query query = new Query(params);

		List<VisiuserEntity> visiuserList = visiuserService.queryList(query);
		int total = visiuserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(visiuserList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/bylist")
	public R bylist(@RequestParam Map<String, Object> params){
		
		Query query = new Query(params);
		
		List<UserVisibyEntity> visiuserList = userVisibyService.queryList(query);
		int total = userVisibyService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(visiuserList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("visiuser:info")
	public R info(@PathVariable("id") Integer id){
		VisiuserEntity visiuser = visiuserService.queryObject(id);
		
		return R.ok().put("visiuser", visiuser);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("visiuser:save")
	public R save(@RequestBody VisiuserEntity visiuser){
		visiuserService.save(visiuser);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("visiuser:update")
	public R update(@RequestBody VisiuserEntity visiuser){
		visiuserService.update(visiuser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("visiuser:delete")
	public R delete(@RequestBody Integer[] ids){
		visiuserService.deleteBatch(ids);
		
		return R.ok();
	}
	
	/**
	 * 审核通过
	 */
	@RequestMapping("/check")
	@RequiresPermissions("visiuser:check")
	public R check(@RequestBody Integer[] ids){
		for (Integer id:ids) {
			VisiuserEntity visiuserEntity = visiuserService.queryObject(id);
			VisiuserLogEntity visiuserLogEntity = visiuserLogService.queryObjectByCode(visiuserEntity.getOrdercode());
			
			if(visiuserLogEntity == null){
				continue;
			}
			
			visiuserLogEntity.setChecked(1);
			visiuserLogService.update(visiuserLogEntity);
		}
		visiuserService.checkBatch(ids);
	    return R.ok();
	}
	/**
	 * 审核不通过
	 */
	@RequestMapping("/uncheck")
	@RequiresPermissions("visiuser:check")
	public R uncheck(@RequestBody Map<String,Object> map){
		String ids = (String) map.get("ids");
		String[] idlist = ids.split(",");
		for (String id:idlist) {
			VisiuserEntity visiuserEntity = visiuserService.queryObject(Integer.valueOf(id));
			VisiuserLogEntity visiuserLogEntity = visiuserLogService.queryObjectByCode(visiuserEntity.getOrdercode());
			
			if(visiuserLogEntity == null){
				continue;
			}
			visiuserLogEntity.setChecked(2);
			visiuserLogService.update(visiuserLogEntity);
		}
		JSONObject obj = new JSONObject();
		obj.put("ids", idlist);
		obj.put("reason", map.get("reason"));
	    visiuserService.uncheckBatch(obj);
	    visiuserLogService.uncheckBatchbyuvids(obj);
	    return R.ok();
	}
	
}
