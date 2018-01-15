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

import com.lcworld.entity.TNoticeTypeEntity;
import com.lcworld.service.TNoticeTypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 公告分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
@RestController
@RequestMapping("noticetype")
public class TNoticeTypeController {
	@Autowired
	private TNoticeTypeService tNoticeTypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TNoticeTypeEntity> tNoticeTypeList = tNoticeTypeService.queryList(query);
		int total = tNoticeTypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tNoticeTypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 获取信息类型列表
	 * @return
	 */
	@RequestMapping("/getNoticetypeList")
	public R getNoticetypeList(){
		List<TNoticeTypeEntity> typeList = tNoticeTypeService.queryList(new HashMap<String, Object>());
		return R.ok().put("noticetypeList", typeList);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TNoticeTypeEntity tNoticeType = tNoticeTypeService.queryObject(id);
		
		return R.ok().put("noticeType", tNoticeType);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TNoticeTypeEntity tNoticeType){
		tNoticeTypeService.save(tNoticeType);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TNoticeTypeEntity tNoticeType){
		tNoticeTypeService.update(tNoticeType);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tNoticeTypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
