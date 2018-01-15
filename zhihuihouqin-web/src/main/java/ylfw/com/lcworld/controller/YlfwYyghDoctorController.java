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

import com.lcworld.entity.YlfwYyghDoctorEntity;
import com.lcworld.service.YlfwYyghDoctorService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务医生表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:52
 */
@RestController
@RequestMapping("ylfwyyghdoctor")
public class YlfwYyghDoctorController {
	@Autowired
	private YlfwYyghDoctorService ylfwYyghDoctorService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwyyghdoctor:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwYyghDoctorEntity> ylfwYyghDoctorList = ylfwYyghDoctorService.queryList(query);
		int total = ylfwYyghDoctorService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwYyghDoctorList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwyyghdoctor:info")
	public R info(@PathVariable("id") Integer id){
		YlfwYyghDoctorEntity ylfwYyghDoctor = ylfwYyghDoctorService.queryObject(id);
		
		return R.ok().put("ylfwYyghDoctor", ylfwYyghDoctor);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwyyghdoctor:save")
	public R save(@RequestBody YlfwYyghDoctorEntity ylfwYyghDoctor){
		ylfwYyghDoctorService.save(ylfwYyghDoctor);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwyyghdoctor:update")
	public R update(@RequestBody YlfwYyghDoctorEntity ylfwYyghDoctor){
		ylfwYyghDoctorService.update(ylfwYyghDoctor);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwyyghdoctor:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwYyghDoctorService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
