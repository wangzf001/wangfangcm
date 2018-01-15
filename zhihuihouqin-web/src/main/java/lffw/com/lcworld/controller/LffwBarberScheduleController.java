package com.lcworld.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.dto.LffwBarberScheduleEntityDTO;
import com.lcworld.entity.LffwBarberScheduleEntity;
import com.lcworld.service.LffwBarberScheduleService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务- 理发师计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
@RestController
@RequestMapping("lffwbarberschedule")
public class LffwBarberScheduleController {
	@Autowired
	private LffwBarberScheduleService lffwBarberScheduleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwBarberScheduleEntity> lffwBarberScheduleList = lffwBarberScheduleService.queryList(query);
		int total = lffwBarberScheduleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwBarberScheduleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		LffwBarberScheduleEntity lffwBarberSchedule = lffwBarberScheduleService.queryObject(id);
		
		return R.ok().put("lffwBarberSchedule", lffwBarberSchedule);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody LffwBarberScheduleEntity lffwBarberSchedule){
	    try {
            lffwBarberScheduleService.saveschedule(lffwBarberSchedule);
        } catch (ParseException e) {
            return R.error("保存失败");
        }
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody LffwBarberScheduleEntity lffwBarberSchedule){
		
	    lffwBarberScheduleService.update(lffwBarberSchedule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		lffwBarberScheduleService.deleteBatch(ids);
		
		return R.ok();
	}
	
	/**
     * 计划列表
     */
    @RequestMapping("/schedulelist")
    public R schedulelist(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<LffwBarberScheduleEntityDTO> lffwBarberScheduleList = lffwBarberScheduleService.queryScheduleList(query);
        int total = lffwBarberScheduleService.queryScheduleTotal(query);
        
        PageUtils pageUtil = new PageUtils(lffwBarberScheduleList, total, query.getLimit(), query.getPage());
        
        return R.ok().put("page", pageUtil);
    }
    
	
}
