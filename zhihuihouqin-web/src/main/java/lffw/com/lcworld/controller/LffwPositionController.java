package com.lcworld.controller;

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

import com.lcworld.entity.LffwPositionEntity;
import com.lcworld.service.LffwPositionService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务职称
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 14:01:24
 */
@RestController
@RequestMapping("lffwposition")
public class LffwPositionController {
	@Autowired
	private LffwPositionService lffwPositionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("lffwposition:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwPositionEntity> lffwPositionList = lffwPositionService.queryList(query);
		int total = lffwPositionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwPositionList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("lffwposition:info")
	public R info(@PathVariable("id") Integer id){
		LffwPositionEntity lffwPosition = lffwPositionService.queryObject(id);
		
		return R.ok().put("lffwPosition", lffwPosition);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("lffwposition:save")
	public R save(@RequestBody LffwPositionEntity lffwPosition){
		lffwPositionService.save(lffwPosition);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("lffwposition:update")
	public R update(@RequestBody LffwPositionEntity lffwPosition){
		lffwPositionService.update(lffwPosition);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("lffwposition:delete")
	public R delete(@RequestBody Integer[] ids){
		lffwPositionService.deleteBatch(ids);
		
		return R.ok();
	}
	
	/**
     * 列表
     */
    @RequestMapping("/getplist")
    public R list(){
        return R.ok().put("positionlist", lffwPositionService.queryList(new HashMap<String,Object>()));
    }
	
}
