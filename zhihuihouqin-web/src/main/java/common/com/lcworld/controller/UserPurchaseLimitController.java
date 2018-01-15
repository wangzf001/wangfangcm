package com.lcworld.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.lcworld.entity.UserPurchaseLimitEntity;
import com.lcworld.service.DeparpurchaseCountService;
import com.lcworld.service.OfficePurchaseCountService;
import com.lcworld.service.UserPurchaseLimitService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;
import com.lcworld.utils.util.ValidateUtil;


/**
 * 用户账户额度限制
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 10:08:45
 */
@RestController
@RequestMapping("userpurchaselimit")
public class UserPurchaseLimitController {
	@Autowired
	private UserPurchaseLimitService userPurchaseLimitService;
	@Autowired 
	private DeparpurchaseCountService deparpurchaseCountService;
	@Autowired
	private OfficePurchaseCountService officePurchaseCountService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
	    Query query= null;
	    SysUserEntity user = ShiroUtils.getUserEntity() ;
	    List<UserPurchaseLimitEntity> list = null;
	    int total = 0;
	    if(Constant.SUPER_ADMIN == user.getUserId()){
            query = new Query(params);
            list = userPurchaseLimitService.queryList(query);
            total = userPurchaseLimitService.queryTotal(query);
            
        }else{
            params.put("uid", user.getUserId());
            query = new Query(params);
            list = userPurchaseLimitService.queryUPList(query);
            total = userPurchaseLimitService.queryUPTotal(query);
        }
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/limitlist")
	public R limitlist(@RequestParam Map<String, Object> params){
        //purchasetypeid ，departid purchaseid
	    if(!ValidateUtil.isValid(params.get("purchaseid"))){
	        return R.ok();
	    }
	    Integer purchaseid= Integer.parseInt(String.valueOf(params.get("purchaseid")));
	    Integer type = Integer.parseInt(String.valueOf(params.get("type")));
	    try {
            setparams(type,purchaseid,params);
        } catch (Exception e) {
            return R.error("账户级别有误 type:"+type);
        }
	    Query q = new Query(params);
	    List<UserPurchaseLimitEntity> list = userPurchaseLimitService.querylimitList(q);
	    int total = userPurchaseLimitService.querylimitTotal(params);
	    PageUtils pageUtil = new PageUtils(list, total, q.getLimit(), q.getPage());
        return R.ok().put("page", pageUtil);
	    
	}
	
	
	/**
	 * 自动分配账户额度
	 */
	/**
	 * @param params
	 * @return
	 */
	@RequestMapping("/depart")
	public R depart(@RequestParam Map<String, Object> params){
	    if(!ValidateUtil.isValid(params.get("purchaseid"))){
	        return R.ok();
	    }
	    //账户id
	    Integer purchaseid= Integer.parseInt(String.valueOf(params.get("purchaseid")));
	    //账户类型 1：司局，2：处室
	    Integer type = Integer.parseInt(String.valueOf(params.get("type")));
	    try {
            setparams(type,purchaseid,params);
        } catch (Exception e) {
            return R.error("账户级别有误 type:"+type);
        }
	    List<UserPurchaseLimitEntity> uplmtlist = userPurchaseLimitService.depart(params,type);
	    Query q = new Query(params);
	    int total = userPurchaseLimitService.departTotal(q, type);
	    PageUtils pageUtil = new PageUtils(uplmtlist, total,q.getLimit(), q.getPage());
	    return R.ok().put("page", pageUtil);
	    
	}
	/**
	 * 确认自动分配账户额度
	 */
	@RequestMapping("/confirmDepart")
	public R confirmDepart(String purchaseId,Integer type){
	    if(!ValidateUtil.isValid(purchaseId)){
	        return R.error();
	    }
	    //账户id
	    Integer purchaseid= Integer.parseInt(purchaseId);
	    //账户类型 1：司局，2：处室
	    //Integer type = Integer.parseInt(String.valueOf(params.get("type")));
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("purchaseid", purchaseId);
	    params.put("type", type);
	    try {
            setparams(type,purchaseid,params);
        } catch (Exception e) {
            return R.error("账户级别有误 type:"+type);
        }
	    List<UserPurchaseLimitEntity> uplmtlist = userPurchaseLimitService.depart(params,type);
	    if (uplmtlist.size()>0) {
	    	if (type == 1) {
	    		DeparpurchaseCountEntity dpc = deparpurchaseCountService
	    				.queryObject(purchaseid);
				BigDecimal remain = dpc.getRemain();
				BigDecimal total = new BigDecimal(0.00);
				for (UserPurchaseLimitEntity upl : uplmtlist) {
					BigDecimal purchasecount = upl.getHighlimit();
					total = total.add(new BigDecimal(purchasecount.toString()));
				}
				dpc.setRemain(remain.subtract(total));
				deparpurchaseCountService.update(dpc);
			} else if(type == 2){
				OfficePurchaseCountEntity dpc = officePurchaseCountService
						.queryObject(purchaseid);
				BigDecimal remain = dpc.getRemain();
				BigDecimal total = new BigDecimal(0.00);
				for (UserPurchaseLimitEntity upl : uplmtlist) {
					BigDecimal purchasecount = upl.getHighlimit();
					total = total.add(new BigDecimal(purchasecount.toString()));
				}
				dpc.setRemain(remain.subtract(total));
				officePurchaseCountService.update(dpc);
			}
	    	userPurchaseLimitService.saveBatch(uplmtlist);
		}
	    return R.ok();
	}
	
	/**设置查询参数
	 * @param type
	 * @param purchaseid
	 * @param params
	 */
	private void setparams(Integer type, Integer purchaseid, Map<String, Object> params) {
	    if(type == 1){
            //depart
            DeparpurchaseCountEntity dpc = deparpurchaseCountService.queryObject(purchaseid);
            params.put("departid", dpc.getDepartid());
            params.put("purchasetypeid",dpc.getTypeid());
            
        }else if(type == 2){
            //office
            OfficePurchaseCountEntity opc = officePurchaseCountService.queryObject(purchaseid);
            params.put("departid", opc.getDepartid());
            params.put("officeid", opc.getOfficeid());
            params.put("purchasetypeid",opc.getTypeid());
        }else{
            throw new RuntimeException();
        }
        
    }

    /**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		UserPurchaseLimitEntity userPurchaseLimit = userPurchaseLimitService.queryObject(id);
		
		return R.ok().put("userPurchaseLimit", userPurchaseLimit);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/uinfo")
	public R uinfo(Integer uid,Integer typeid){
	    JSONObject params = new JSONObject();
	    params.put("uid", uid);
	    params.put("typeid", typeid);
	    List<UserPurchaseLimitEntity> userPurchaseLimitlist = userPurchaseLimitService.queryList(params);
	    if(ValidateUtil.isValid(userPurchaseLimitlist) && userPurchaseLimitlist.size() > 1){
	        return R.error("该用户一个服务账户类型包含多个账户额度");
	    }
	    UserPurchaseLimitEntity userPurchaseLimit = ValidateUtil.isValid(userPurchaseLimitlist)?userPurchaseLimitlist.get(0):new UserPurchaseLimitEntity();
	    return R.ok().put("userPurchaseLimit",userPurchaseLimit);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info1")
	public R info1(@RequestBody UserPurchaseLimitEntity userPurchaseLimit){
	    R result = new R();
	    JSONObject obj = new JSONObject();
	    obj.put("uid", userPurchaseLimit.getUid());
	    obj.put("purchasetypeid", userPurchaseLimit.getPurchasetypeid());
	    List<UserPurchaseLimitEntity> list = userPurchaseLimitService.queryList1(obj);
	          result.put("userPurchaseLimit",ValidateUtil.isValid(list)?list.get(0):null);
	   
	    return result;
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存用户对公账户额度")
	@RequestMapping("/save")
	public R save(@RequestBody UserPurchaseLimitEntity userPurchaseLimit){
		userPurchaseLimitService.save(userPurchaseLimit);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("更新用户对公账户额度")
	@RequestMapping("/update")
	public R update(@RequestBody UserPurchaseLimitEntity userPurchaseLimit){
		userPurchaseLimitService.update(userPurchaseLimit);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除用户对公账户额度")
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		userPurchaseLimitService.deleteBatch(ids);
		
		return R.ok();
	}
	/**
	 * 删除
	 */
	@SysLog("删除用户对公账户额度")
	@RequestMapping("/udelete")
	public R udelete(@RequestBody UserPurchaseLimitEntity userPurchaseLimit){
	    String[] idarray = userPurchaseLimit.getIds().split(",");
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("uidarray", idarray);
	    map.put("typeid", userPurchaseLimit.getPurchasetypeid());
	    userPurchaseLimitService.deleteBatchByuid(map);
	    return R.ok();
	}
}
