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

import com.lcworld.entity.YlfwHospitalEntity;
import com.lcworld.service.YlfwHospitalService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务医院表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-20 13:09:47
 */
@RestController
@RequestMapping("ylfwhospital")
public class YlfwHospitalController {
	@Autowired
	private YlfwHospitalService ylfwHospitalService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwhospital:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwHospitalEntity> ylfwHospitalList = ylfwHospitalService.queryList(query);
		int total = ylfwHospitalService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwHospitalList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwhospital:info")
	public R info(@PathVariable("id") Integer id){
		YlfwHospitalEntity ylfwHospital = ylfwHospitalService.queryObject(id);
		
		return R.ok().put("ylfwHospital", ylfwHospital);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwhospital:save")
	public R save(@RequestBody YlfwHospitalEntity ylfwHospital){
		ylfwHospitalService.save(ylfwHospital);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwhospital:update")
	public R update(@RequestBody YlfwHospitalEntity ylfwHospital){
		ylfwHospitalService.update(ylfwHospital);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwhospital:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwHospitalService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
