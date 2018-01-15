package com.lcworld.controller;

import java.util.Date;
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
import com.lcworld.annotation.SysLog;
import com.lcworld.entity.DeparpurchaseCountEntity;
import com.lcworld.entity.OfficePurchaseCountEntity;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.service.OfficePurchaseCountService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.utils.wxutil.MD5Util;


/**
 * 处室对公账户
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 18:37:02
 */
@RestController
@RequestMapping("officepurchasecount")
public class OfficePurchaseCountController {
	@Autowired
	private OfficePurchaseCountService officePurchaseCountService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("officepurchasecount:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
	    SysUserEntity user = ShiroUtils.getUserEntity();
	    int total=0;
        Query query = null;
        List<OfficePurchaseCountEntity> officePurchaseCountList;
        if(Constant.SUPER_ADMIN == user.getUserId()){
            query = new Query(params);
            officePurchaseCountList = officePurchaseCountService.queryList(query);
            total = officePurchaseCountService.queryTotal(query);
            
        }else{
            params.put("uid", user.getUserId().intValue());
            query = new Query(params);
            officePurchaseCountList = officePurchaseCountService.queryOPCList(query);
            total = officePurchaseCountService.queryOPCTotal(query);
        }
		
		PageUtils pageUtil = new PageUtils(officePurchaseCountList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("officepurchasecount:info")
	public R info(@PathVariable("id") Integer id){
		OfficePurchaseCountEntity officePurchaseCount = officePurchaseCountService.queryObject(id);
		
		return R.ok().put("officePurchaseCount", officePurchaseCount);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存处室账户")
	@RequestMapping("/save")
	@RequiresPermissions("officepurchasecount:save")
	public R save(@RequestBody OfficePurchaseCountEntity officePurchaseCount){
	    JSONObject obj = new JSONObject();
        obj.put("typeid", officePurchaseCount.getTypeid());
        obj.put("departid", officePurchaseCount.getDepartid());
        obj.put("officeid", officePurchaseCount.getOfficeid());
        List<OfficePurchaseCountEntity> dc = officePurchaseCountService.queryList(obj);
        if(ValidateUtil.isValid(dc)){
            return R.error("同一个部门同一处室的一类账户只允许创建一个");
        }
        officePurchaseCount.setCreatetime(new Date());
        officePurchaseCount.setPaypass(MD5Util.MD5Encode(officePurchaseCount.getSourcepaypass(), "UTF-8"));
        officePurchaseCount.setCreateuid(ShiroUtils.getUserId().intValue());
	    officePurchaseCountService.save(officePurchaseCount);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("更新处室账户")
	@RequestMapping("/update")
	@RequiresPermissions("officepurchasecount:update")
	public R update(@RequestBody OfficePurchaseCountEntity officePurchaseCount){
	    officePurchaseCount.setPaypass(MD5Util.MD5Encode(officePurchaseCount.getSourcepaypass(), "UTF-8"));
	    officePurchaseCountService.update(officePurchaseCount);
		
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除处室账户")
	@RequestMapping("/delete")
	@RequiresPermissions("officepurchasecount:delete")
	public R delete(@RequestBody Integer[] ids){
		officePurchaseCountService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
