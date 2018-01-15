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

import com.lcworld.entity.YlfwYyghOrderEntity;
import com.lcworld.service.YlfwYyghOrderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务预约挂号订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
@RestController
@RequestMapping("ylfwyyghorder")
public class YlfwYyghOrderController {
	@Autowired
	private YlfwYyghOrderService ylfwYyghOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwyyghorder:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwYyghOrderEntity> ylfwYyghOrderList = ylfwYyghOrderService.queryList(query);
		int total = ylfwYyghOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwYyghOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwyyghorder:info")
	public R info(@PathVariable("id") Integer id){
		YlfwYyghOrderEntity ylfwYyghOrder = ylfwYyghOrderService.queryObject(id);
		
		return R.ok().put("ylfwYyghOrder", ylfwYyghOrder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwyyghorder:save")
	public R save(@RequestBody YlfwYyghOrderEntity ylfwYyghOrder){
		ylfwYyghOrderService.save(ylfwYyghOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwyyghorder:update")
	public R update(@RequestBody YlfwYyghOrderEntity ylfwYyghOrder){
		ylfwYyghOrderService.update(ylfwYyghOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwyyghorder:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwYyghOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
