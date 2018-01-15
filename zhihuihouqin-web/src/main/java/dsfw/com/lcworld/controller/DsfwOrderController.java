package com.lcworld.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.DsfwOrderEntity;
import com.lcworld.service.DsfwOrderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订水服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:20
 */
@RestController
@RequestMapping("dsfworder")
public class DsfwOrderController {
	private Logger log = LoggerFactory.getLogger(DsfwOrderController.class);
	@Autowired
	private DsfwOrderService dsfwOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		if ("".equals((String)params.get("orderStatus"))) {
			params.remove("orderStatus");
		}
		if ("".equals((String)params.get("payStatus"))) {
			params.remove("payStatus");
		}
		if ("".equals((String)params.get("createTimeStart"))) {
			params.remove("createTimeStart");
		}
		if ("".equals((String)params.get("createTimeEnd"))) {
			params.remove("createTimeEnd");
		}
		params.put("webQuery", 1);
		params.put("ordertype", 18);
		//查询列表数据
        Query query = new Query(params);

		List<DsfwOrderEntity> dsfwOrderList = dsfwOrderService.queryList(query);
		log.debug("dsfwOrderList:"+dsfwOrderList.toString());
		int total = dsfwOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dsfwOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DsfwOrderEntity dsfwOrder = dsfwOrderService.queryObject(id);
		
		return R.ok().put("dsfwOrder", dsfwOrder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DsfwOrderEntity dsfwOrder){
		dsfwOrder.setRealdel(0);
		dsfwOrderService.save(dsfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DsfwOrderEntity dsfwOrder){
		dsfwOrderService.update(dsfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dsfwOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
