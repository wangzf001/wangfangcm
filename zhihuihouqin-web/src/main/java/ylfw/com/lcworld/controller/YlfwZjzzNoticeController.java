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

import com.lcworld.entity.YlfwZjzzNoticeEntity;
import com.lcworld.service.YlfwZjzzNoticeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务专家坐诊-咨询
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
@RestController
@RequestMapping("ylfwzjzznotice")
public class YlfwZjzzNoticeController {
	@Autowired
	private YlfwZjzzNoticeService ylfwZjzzNoticeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwzjzznotice:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwZjzzNoticeEntity> ylfwZjzzNoticeList = ylfwZjzzNoticeService.queryList(query);
		int total = ylfwZjzzNoticeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwZjzzNoticeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwzjzznotice:info")
	public R info(@PathVariable("id") Integer id){
		YlfwZjzzNoticeEntity ylfwZjzzNotice = ylfwZjzzNoticeService.queryObject(id);
		
		return R.ok().put("ylfwZjzzNotice", ylfwZjzzNotice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwzjzznotice:save")
	public R save(@RequestBody YlfwZjzzNoticeEntity ylfwZjzzNotice){
		ylfwZjzzNoticeService.save(ylfwZjzzNotice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwzjzznotice:update")
	public R update(@RequestBody YlfwZjzzNoticeEntity ylfwZjzzNotice){
		ylfwZjzzNoticeService.update(ylfwZjzzNotice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwzjzznotice:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwZjzzNoticeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
