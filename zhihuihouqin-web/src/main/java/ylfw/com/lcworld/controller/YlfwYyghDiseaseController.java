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

import com.lcworld.entity.YlfwYyghDiseaseEntity;
import com.lcworld.service.YlfwYyghDiseaseService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务预约挂号疾病表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:52
 */
@RestController
@RequestMapping("ylfwyyghdisease")
public class YlfwYyghDiseaseController {
	@Autowired
	private YlfwYyghDiseaseService ylfwYyghDiseaseService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwyyghdisease:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwYyghDiseaseEntity> ylfwYyghDiseaseList = ylfwYyghDiseaseService.queryList(query);
		int total = ylfwYyghDiseaseService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwYyghDiseaseList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwyyghdisease:info")
	public R info(@PathVariable("id") Integer id){
		YlfwYyghDiseaseEntity ylfwYyghDisease = ylfwYyghDiseaseService.queryObject(id);
		
		return R.ok().put("ylfwYyghDisease", ylfwYyghDisease);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwyyghdisease:save")
	public R save(@RequestBody YlfwYyghDiseaseEntity ylfwYyghDisease){
		ylfwYyghDiseaseService.save(ylfwYyghDisease);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwyyghdisease:update")
	public R update(@RequestBody YlfwYyghDiseaseEntity ylfwYyghDisease){
		ylfwYyghDiseaseService.update(ylfwYyghDisease);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwyyghdisease:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwYyghDiseaseService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
