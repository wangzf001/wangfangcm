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

import com.lcworld.entity.YlfwYyghDoctorSkilledEntity;
import com.lcworld.service.YlfwYyghDoctorSkilledService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务预约挂号医生擅长疾病
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
@RestController
@RequestMapping("ylfwyyghdoctorskilled")
public class YlfwYyghDoctorSkilledController {
	@Autowired
	private YlfwYyghDoctorSkilledService ylfwYyghDoctorSkilledService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwyyghdoctorskilled:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwYyghDoctorSkilledEntity> ylfwYyghDoctorSkilledList = ylfwYyghDoctorSkilledService.queryList(query);
		int total = ylfwYyghDoctorSkilledService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwYyghDoctorSkilledList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwyyghdoctorskilled:info")
	public R info(@PathVariable("id") Integer id){
		YlfwYyghDoctorSkilledEntity ylfwYyghDoctorSkilled = ylfwYyghDoctorSkilledService.queryObject(id);
		
		return R.ok().put("ylfwYyghDoctorSkilled", ylfwYyghDoctorSkilled);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwyyghdoctorskilled:save")
	public R save(@RequestBody YlfwYyghDoctorSkilledEntity ylfwYyghDoctorSkilled){
		ylfwYyghDoctorSkilledService.save(ylfwYyghDoctorSkilled);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwyyghdoctorskilled:update")
	public R update(@RequestBody YlfwYyghDoctorSkilledEntity ylfwYyghDoctorSkilled){
		ylfwYyghDoctorSkilledService.update(ylfwYyghDoctorSkilled);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwyyghdoctorskilled:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwYyghDoctorSkilledService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
