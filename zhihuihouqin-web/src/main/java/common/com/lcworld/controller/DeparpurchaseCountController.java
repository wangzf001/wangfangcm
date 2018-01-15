package com.lcworld.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.velocity.tools.view.WebappUberspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.SysLog;
import com.lcworld.entity.DeparpurchaseCountEntity;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.service.DeparpurchaseCountService;
import com.lcworld.service.PurchaseTypeService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.utils.wxutil.MD5Util;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-10 11:43:17
 */
@RestController
@RequestMapping("deparpurchasecount")
public class DeparpurchaseCountController {
	@Autowired
	private DeparpurchaseCountService deparpurchaseCountService;
	@Autowired
	private PurchaseTypeService purchaseTypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("deparpurchasecount:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
	    SysUserEntity user = ShiroUtils.getUserEntity();
	    List<DeparpurchaseCountEntity> deparpurchaseCountList= null;
	    int total=0;
	    Query query = null;
	    if(Constant.SUPER_ADMIN == user.getUserId()){
	        query = new Query(params);
	        deparpurchaseCountList = deparpurchaseCountService.queryList(query);
	        total = deparpurchaseCountService.queryTotal(query);
	        
	    }else{
	        params.put("uid", user.getUserId());
	        query = new Query(params);
            deparpurchaseCountList = deparpurchaseCountService.queryDPCList(query);
            total = deparpurchaseCountService.queryDPCTotal(query);
	    }
		PageUtils pageUtil = new PageUtils(deparpurchaseCountList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("deparpurchasecount:info")
	public R info(@PathVariable("id") Integer id){
		DeparpurchaseCountEntity deparpurchaseCount = deparpurchaseCountService.queryObject(id);
		
		return R.ok().put("deparpurchaseCount", deparpurchaseCount);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存司局账户")
	@RequestMapping("/save")
	@RequiresPermissions("deparpurchasecount:save")
	public R save(@RequestBody DeparpurchaseCountEntity deparpurchaseCount){
	    JSONObject obj = new JSONObject();
	    obj.put("typeid", deparpurchaseCount.getTypeid());
	    obj.put("departid", deparpurchaseCount.getDepartid());
	    List<DeparpurchaseCountEntity> dc = deparpurchaseCountService.queryList(obj);
	    if(ValidateUtil.isValid(dc)){
	        return R.error("同一个部门一类账户只允许创建一个");
	    }
	    deparpurchaseCount.setCreateuid(ShiroUtils.getUserId().intValue());
	    deparpurchaseCount.setCreatetime(new Date());
	    deparpurchaseCount.setPaypass(MD5Util.MD5Encode(deparpurchaseCount.getSourcepaypass(), "UTF-8"));
		deparpurchaseCountService.save(deparpurchaseCount);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("更新司局账户")
	@RequestMapping("/update")
	@RequiresPermissions("deparpurchasecount:update")
	public R update(@RequestBody DeparpurchaseCountEntity deparpurchaseCount){
	    deparpurchaseCount.setPaypass(MD5Util.MD5Encode(deparpurchaseCount.getSourcepaypass(), "UTF-8"));
	    deparpurchaseCountService.update(deparpurchaseCount);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除司局账户")
	@RequestMapping("/delete")
	@RequiresPermissions("deparpurchasecount:delete")
	public R delete(@RequestBody Integer[] ids){
		deparpurchaseCountService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
