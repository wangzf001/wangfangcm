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

import com.lcworld.entity.RoomPurchaseCountEntity;
import com.lcworld.service.RoomPurchaseCountService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-30 15:30:18
 */
@RestController
@RequestMapping("roompurchasecount")
public class RoomPurchaseCountController {
	@Autowired
	private RoomPurchaseCountService roomPurchaseCountService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("roompurchasecount:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RoomPurchaseCountEntity> roomPurchaseCountList = roomPurchaseCountService.queryList(query);
		int total = roomPurchaseCountService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(roomPurchaseCountList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("roompurchasecount:info")
	public R info(@PathVariable("id") Integer id){
		RoomPurchaseCountEntity roomPurchaseCount = roomPurchaseCountService.queryObject(id);
		
		return R.ok().put("roomPurchaseCount", roomPurchaseCount);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("roompurchasecount:save")
	public R save(@RequestBody RoomPurchaseCountEntity roomPurchaseCount){
		roomPurchaseCountService.save(roomPurchaseCount);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("roompurchasecount:update")
	public R update(@RequestBody RoomPurchaseCountEntity roomPurchaseCount){
		roomPurchaseCountService.update(roomPurchaseCount);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("roompurchasecount:delete")
	public R delete(@RequestBody Integer[] ids){
		roomPurchaseCountService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
