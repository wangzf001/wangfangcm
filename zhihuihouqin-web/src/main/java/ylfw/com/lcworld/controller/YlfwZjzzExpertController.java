package com.lcworld.controller;

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

import com.lcworld.entity.YlfwZjzzExpertEntity;
import com.lcworld.service.YlfwZjzzExpertService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务专家坐诊医生表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
@RestController
@RequestMapping("ylfwzjzzexpert")
public class YlfwZjzzExpertController {
	@Autowired
	private YlfwZjzzExpertService ylfwZjzzExpertService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwzjzzexpert:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwZjzzExpertEntity> ylfwZjzzExpertList = ylfwZjzzExpertService.queryList(query);
		int total = ylfwZjzzExpertService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwZjzzExpertList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/expertlist")
	public R expertlist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    
	    List<Map<String,Object>> ylfwZjzzExpertList = ylfwZjzzExpertService.queryexpertList(query);
	    int total = ylfwZjzzExpertService.queryexpertTotal(query);
	    
	    PageUtils pageUtil = new PageUtils(ylfwZjzzExpertList, total, query.getLimit(), query.getPage());
	    
	    return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwzjzzexpert:info")
	public R info(@PathVariable("id") Integer id){
		YlfwZjzzExpertEntity ylfwZjzzExpert = ylfwZjzzExpertService.queryObject(id);
		
		return R.ok().put("ylfwZjzzExpert", ylfwZjzzExpert);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwzjzzexpert:save")
	public R save(@RequestBody YlfwZjzzExpertEntity ylfwZjzzExpert){
	    ylfwZjzzExpert.setCreatetime(new Date());
		ylfwZjzzExpertService.save(ylfwZjzzExpert);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwzjzzexpert:update")
	public R update(@RequestBody YlfwZjzzExpertEntity ylfwZjzzExpert){
		ylfwZjzzExpertService.update(ylfwZjzzExpert);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwzjzzexpert:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwZjzzExpertService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
