package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.JyfwComplaintEntity;
import com.lcworld.entity.TDcfwCommentEntity;
import com.lcworld.service.JyfwComplaintService;
import com.lcworld.util.POIUtil;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;


/**
 * 建议服务
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:01:19
 */
@RestController
@RequestMapping("jyfwcomplaint")
public class JyfwComplaintController {
	@Autowired
	private JyfwComplaintService jyfwComplaintService;
	//poi导入导出映射关系
	private DualHashBidiMap titleMapping = new DualHashBidiMap();
	{
		titleMapping.put("id","id");
		titleMapping.put("complaintContent","投诉内容");
		titleMapping.put("uid","用户id");
		titleMapping.put("tagid","标签id");
		titleMapping.put("suggestContent","建议内容");
		titleMapping.put("anonymous","1匿名0不匿名");
		titleMapping.put("createtime","创建时间");
		titleMapping.put("tagName","标签名称");
		titleMapping.put("imgsStr","上传图片");
		titleMapping.put("username","用户名");
		titleMapping.put("photo","头像");
	}
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		//将当前用户id加入参数
		params.put("serviceuid", ShiroUtils.getUserId());
		Query query = new Query(params);
		List<JyfwComplaintEntity> jyfwComplaintList = jyfwComplaintService.queryList(query);
		int total = jyfwComplaintService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(jyfwComplaintList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		JyfwComplaintEntity jyfwComplaint = jyfwComplaintService.queryObject(id);
		
		return R.ok().put("jyfwComplaint", jyfwComplaint);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody JyfwComplaintEntity jyfwComplaint){
		jyfwComplaintService.save(jyfwComplaint);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody JyfwComplaintEntity jyfwComplaint){
		jyfwComplaintService.update(jyfwComplaint);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		jyfwComplaintService.deleteBatch(ids);
		
		return R.ok();
	}
	/**
	 * 导出excel
	 * 
	 * @param mIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/exportExcel")
	public R exportExcel(Integer[] mIds, HttpServletResponse response) throws Exception {
		HashMap<String, Object> params = new HashMap<>();
		params.put("ids", mIds);
		List<JyfwComplaintEntity> queryList = jyfwComplaintService.queryList(params);
		POIUtil.generateExcel(titleMapping, queryList, response);
		return R.ok();
	}
}
