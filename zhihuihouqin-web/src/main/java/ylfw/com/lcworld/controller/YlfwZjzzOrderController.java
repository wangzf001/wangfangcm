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

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.entity.YlfwZjzzOrderEntity;
import com.lcworld.service.YlfwZjzzOrderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;



/**
 * 医疗服务专家坐诊订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
@RestController
@RequestMapping("ylfwzjzzorder")
public class YlfwZjzzOrderController {
	@Autowired
	private YlfwZjzzOrderService ylfwZjzzOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwzjzzorder:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwZjzzOrderEntity> ylfwZjzzOrderList = ylfwZjzzOrderService.queryList(query);
		int total = ylfwZjzzOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwZjzzOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/orderlist")
	public R orderlist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    
	    List<Map<String,Object>> ylfwZjzzOrderList = ylfwZjzzOrderService.queryorderList(query);
	    int total = ylfwZjzzOrderService.queryorderTotal(query);
	    
	    PageUtils pageUtil = new PageUtils(ylfwZjzzOrderList, total, query.getLimit(), query.getPage());
	    
	    return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwzjzzorder:info")
	public R info(@PathVariable("id") Integer id){
		YlfwZjzzOrderEntity ylfwZjzzOrder = ylfwZjzzOrderService.queryObject(id);
		
		return R.ok().put("ylfwZjzzOrder", ylfwZjzzOrder);
	}
	
	   /**
     * 信息
     */
    @RequestMapping("/jd")
    public R jd(Integer orderid){
        YlfwZjzzOrderEntity order = new YlfwZjzzOrderEntity();
        order.setStatus(4);
        order.setId(orderid);
        ylfwZjzzOrderService.update(order);
        return R.ok();
    }
	
	/**
	 * 信息
	 */
	@RequestMapping("/orderinfo/{id}")
	public R orderinfo(@PathVariable("id") Integer id){
	    JSONObject params = new JSONObject();
	    params.put("orderid", id);
	    Query query = new Query(params);
	    List<Map<String,Object>> ylfwZjzzOrderList = ylfwZjzzOrderService.queryorderList(query);
	    return R.ok().put("ylfwZjzzOrder", ylfwZjzzOrderList.get(0));
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwzjzzorder:save")
	public R save(@RequestBody YlfwZjzzOrderEntity ylfwZjzzOrder){
		ylfwZjzzOrderService.save(ylfwZjzzOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwzjzzorder:update")
	public R update(@RequestBody YlfwZjzzOrderEntity ylfwZjzzOrder){
		ylfwZjzzOrderService.update(ylfwZjzzOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwzjzzorder:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwZjzzOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
