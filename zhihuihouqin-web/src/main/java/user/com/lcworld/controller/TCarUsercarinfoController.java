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

import com.lcworld.dto.UserCarDTO;
import com.lcworld.entity.TCarUsercarinfoEntity;
import com.lcworld.service.TCarUsercarinfoService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 用户车辆信息表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-28 17:21:21
 */
@RestController
@RequestMapping("tcarusercarinfo")
public class TCarUsercarinfoController {
	@Autowired
	private TCarUsercarinfoService tCarUsercarinfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TCarUsercarinfoEntity> tCarUsercarinfoList = tCarUsercarinfoService.queryList(query);
		int total = tCarUsercarinfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tCarUsercarinfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TCarUsercarinfoEntity tCarUsercarinfo = tCarUsercarinfoService.queryObject(id);
		
		return R.ok().put("tCarUsercarinfo", tCarUsercarinfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TCarUsercarinfoEntity tCarUsercarinfo){
		tCarUsercarinfoService.save(tCarUsercarinfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TCarUsercarinfoEntity tCarUsercarinfo){
		tCarUsercarinfoService.update(tCarUsercarinfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tCarUsercarinfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
	/**
     * 列表
     */
    @RequestMapping("/carinfolist")
    public R carinfolist(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<UserCarDTO> tCarUsercarinfoList = tCarUsercarinfoService.queryUserCarinfoList(query);
        int total = tCarUsercarinfoService.queryTotal(query);
        
        PageUtils pageUtil = new PageUtils(tCarUsercarinfoList, total, query.getLimit(), query.getPage());
        
        return R.ok().put("page", pageUtil);
    }
	
}
