package com.lcworld.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.PurchaseTypeEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.service.PurchaseTypeService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-30 15:30:18
 */
@RestController
@RequestMapping("purchasetype")
public class PurchaseTypeController {
	@Autowired
	private PurchaseTypeService purchaseTypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("purchasetype:list")
	public R list(@RequestParam Map<String, Object> params){
	    params.put("servicecodelist", getpurchaseServicecodelist());
        Query query = new Query(params);
		List<PurchaseTypeEntity> purchaseTypeList = purchaseTypeService.queryList(query);
		int total = purchaseTypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(purchaseTypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	private List<Integer> getpurchaseServicecodelist() {
	    List<Integer> idlist = new ArrayList<Integer>();
	    Subject sub = ShiroUtils.getSubject();
	    for(Map.Entry<String, Integer> entry : APPConstant.getPurchaseTypeRightMap().entrySet()){
	        try {
                sub.checkPermission(entry.getKey());
                if(!idlist.contains(entry.getValue())){
                    idlist.add(entry.getValue());
                }
            } catch (AuthorizationException e) {
            }
	    }
	    return idlist;
    }
    /**
	 * 列表
	 */
	@RequestMapping("/typelist")
	public R typelist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    List<Integer> servicecodelist=getpurchaseServicecodelist();
        params.put("servicecodelist", servicecodelist);
	    List<PurchaseTypeEntity> typelist = purchaseTypeService.queryList(params);
	    return R.ok().put("typelist", typelist);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/roletypelist")
	public R roletypelist(@RequestParam Map<String, Object> params){
		SysUserEntity user = ShiroUtils.getUserEntity();
		   List<PurchaseTypeEntity> roletypelist = purchaseTypeService.queryAllSysRolesByUid(user.getUserId());
		   return R.ok().put("roletypelist", roletypelist);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("purchasetype:info")
	public R info(@PathVariable("id") Integer id){
		PurchaseTypeEntity purchaseType = purchaseTypeService.queryObject(id);
		
		return R.ok().put("purchaseType", purchaseType);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("purchasetype:save")
	public R save(@RequestBody PurchaseTypeEntity purchaseType){
		purchaseTypeService.save(purchaseType);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("purchasetype:update")
	public R update(@RequestBody PurchaseTypeEntity purchaseType){
		purchaseTypeService.update(purchaseType);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("purchasetype:delete")
	public R delete(@RequestBody Integer[] ids){
		purchaseTypeService.deleteBatch(ids);
		return R.ok();
	}
	
}
