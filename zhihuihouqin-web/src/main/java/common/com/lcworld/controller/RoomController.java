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

import com.lcworld.entity.RoomEntity;
import com.lcworld.service.RoomService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 基础数据-办公室
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
@RestController
@RequestMapping("room")
public class RoomController {
	@Autowired
	private RoomService roomService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("room:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RoomEntity> roomList = roomService.queryList(query);
		int total = roomService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(roomList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("room:info")
	public R info(@PathVariable("id") Integer id){
		RoomEntity room = roomService.queryObject(id);
		
		return R.ok().put("room", room);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("room:save")
	public R save(@RequestBody RoomEntity room){
		roomService.save(room);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("room:update")
	public R update(@RequestBody RoomEntity room){
		roomService.update(room);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("room:delete")
	public R delete(@RequestBody Integer[] ids){
		roomService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
