
package com.lcworld.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.entity.TGxdfwOrderEntity;
import com.lcworld.service.TGxdfwOrderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 干洗店服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:53
 */
@RestController
@RequestMapping("gxdfworder")
public class TGxdfwOrderController {
	@Autowired
	private TGxdfwOrderService tGxdfwOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		if ("".equals((String)params.get("uid"))) {
			params.remove("uid");
		}
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
		//查询列表数据
        Query query = new Query(params);

		List<TGxdfwOrderEntity> tGxdfwOrderList = tGxdfwOrderService.queryList(query);
		int total = tGxdfwOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tGxdfwOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TGxdfwOrderEntity tGxdfwOrder = tGxdfwOrderService.queryObject(id);
		
		return R.ok().put("gxdfwOrder", tGxdfwOrder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TGxdfwOrderEntity tGxdfwOrder){
		tGxdfwOrderService.save(tGxdfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TGxdfwOrderEntity tGxdfwOrder){
		tGxdfwOrderService.update(tGxdfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tGxdfwOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
	/**
     * 信息
     */
    @RequestMapping("/checkDetail")
    public R checkDetail(Integer orderid){
        TGxdfwOrderEntity order = new TGxdfwOrderEntity();
        order.setCheckstatus(1);
        order.setId(orderid);
        tGxdfwOrderService.update(order);
        return R.ok();
    }
	
}
