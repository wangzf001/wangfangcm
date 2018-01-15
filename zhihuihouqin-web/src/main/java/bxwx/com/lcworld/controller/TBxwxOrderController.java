package com.lcworld.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.TBxwxItemEntity;
import com.lcworld.entity.TBxwxOrderEntity;
import com.lcworld.service.CheckfailureReasonService;
import com.lcworld.service.TBxwxItemService;
import com.lcworld.service.TBxwxMenderService;
import com.lcworld.service.TBxwxOrderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 报修维修订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 11:45:49
 */
@RestController
@RequestMapping("tbxwxorder")
public class TBxwxOrderController {
	@Autowired
	private TBxwxOrderService tBxwxOrderService;
	@Autowired
	private TBxwxItemService tBxwxItemService;
	@Autowired
	private TBxwxMenderService tBxwxMenderService;
	@Autowired
	private CheckfailureReasonService checkfailureReasonService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tbxwxorder:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TBxwxOrderEntity> tBxwxOrderList = tBxwxOrderService.queryList(query);
		int total = tBxwxOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tBxwxOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/orderlist")
	public R orderlist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    
	    List<Map<String,Object>> tBxwxOrderList = tBxwxOrderService.queryordermapList(query);
	    int total = tBxwxOrderService.queryordermapTotal(query);
	    
	    PageUtils pageUtil = new PageUtils(tBxwxOrderList, total, query.getLimit(), query.getPage());
	    
	    return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tbxwxorder:info")
	public R info(@PathVariable("id") Integer id){
		TBxwxOrderEntity tBxwxOrder = tBxwxOrderService.queryObject(id);
		List<TBxwxItemEntity> list = tBxwxItemService.queryList(new HashMap<String,Object>());
		JSONObject params = new JSONObject();
		params.put("servicecode", APPConstant.TYPE_BXFW);
		return R.ok().put("tBxwxOrder", tBxwxOrder).put("itemlist", list)
		        .put("mender", tBxwxMenderService.queryObject(tBxwxOrder.getMenderid()))
		        .put("imglist", tBxwxOrder.getMendimgs()==null?new ArrayList<String>():Arrays.asList(tBxwxOrder.getMendimgs().split(",")))
		        .put("reasonlist", checkfailureReasonService.queryList(params))
		        .put("menderlist", tBxwxMenderService.queryList(params));
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tbxwxorder:save")
	public R save(@RequestBody TBxwxOrderEntity tBxwxOrder){
		tBxwxOrderService.save(tBxwxOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tbxwxorder:update")
	public R update(@RequestBody TBxwxOrderEntity tBxwxOrder){
		tBxwxOrderService.update(tBxwxOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tbxwxorder:delete")
	public R delete(@RequestBody Integer[] ids){
		tBxwxOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
