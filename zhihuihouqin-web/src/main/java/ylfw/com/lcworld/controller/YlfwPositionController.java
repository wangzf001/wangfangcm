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

import com.lcworld.entity.YlfwPositionEntity;
import com.lcworld.service.YlfwPositionService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务--职称
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-20 13:09:47
 */
@RestController
@RequestMapping("ylfwposition")
public class YlfwPositionController {
	@Autowired
	private YlfwPositionService ylfwPositionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwposition:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwPositionEntity> ylfwPositionList = ylfwPositionService.queryList(query);
		int total = ylfwPositionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwPositionList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/positionlist")
	public R positionlist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    List<YlfwPositionEntity> ylfwPositionList = ylfwPositionService.queryList(query);
	    return R.ok().put("positionlist", ylfwPositionList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwposition:info")
	public R info(@PathVariable("id") Integer id){
		YlfwPositionEntity ylfwPosition = ylfwPositionService.queryObject(id);
		
		return R.ok().put("ylfwPosition", ylfwPosition);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwposition:save")
	public R save(@RequestBody YlfwPositionEntity ylfwPosition){
		ylfwPositionService.save(ylfwPosition);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwposition:update")
	public R update(@RequestBody YlfwPositionEntity ylfwPosition){
		ylfwPositionService.update(ylfwPosition);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwposition:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwPositionService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
