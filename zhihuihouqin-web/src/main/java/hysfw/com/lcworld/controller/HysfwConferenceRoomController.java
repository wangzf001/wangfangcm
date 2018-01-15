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

import com.lcworld.entity.HysfwConferenceRoomEntity;
import com.lcworld.service.HysfwConferenceRoomService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-30 18:43:48
 */
@RestController
@RequestMapping("hysfwconferenceroom")
public class HysfwConferenceRoomController {
	@Autowired
	private HysfwConferenceRoomService hysfwConferenceRoomService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("hysfwconferenceroom:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<HysfwConferenceRoomEntity> hysfwConferenceRoomList = hysfwConferenceRoomService.queryList(query);
		int total = hysfwConferenceRoomService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(hysfwConferenceRoomList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hysfwconferenceroom:info")
	public R info(@PathVariable("id") Integer id){
		HysfwConferenceRoomEntity hysfwConferenceRoom = hysfwConferenceRoomService.queryObject(id);
		
		return R.ok().put("hysfwConferenceRoom", hysfwConferenceRoom);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("hysfwconferenceroom:save")
	public R save(@RequestBody HysfwConferenceRoomEntity hysfwConferenceRoom){
		hysfwConferenceRoom.setStatus(1);
		hysfwConferenceRoom.setUsenumber(0);
		hysfwConferenceRoomService.save(hysfwConferenceRoom);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("hysfwconferenceroom:update")
	public R update(@RequestBody HysfwConferenceRoomEntity hysfwConferenceRoom){
		hysfwConferenceRoomService.update(hysfwConferenceRoom);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("hysfwconferenceroom:delete")
	public R delete(@RequestBody Integer[] ids){
		hysfwConferenceRoomService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
