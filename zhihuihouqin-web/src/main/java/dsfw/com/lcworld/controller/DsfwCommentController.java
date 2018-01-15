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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.DsfwCommentEntity;
import com.lcworld.entity.TDcfwCommentEntity;
import com.lcworld.service.DsfwCommentService;
import com.lcworld.util.POIUtil;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;


/**
 * 订水服务-评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:22
 */
@RestController
@RequestMapping("dsfwcomment")
public class DsfwCommentController {
	@Autowired
	private DsfwCommentService dsfwCommentService;
	//poi导入导出映射关系
	private DualHashBidiMap titleMapping = new DualHashBidiMap();
	{
		titleMapping.put("id","id");
		titleMapping.put("imgs","图片");
		titleMapping.put("content","评价内容");
		titleMapping.put("orderid","订单编号");
		titleMapping.put("uid","用户编号");
		titleMapping.put("supportid","上传人编号");
		titleMapping.put("createtime","创建时间");
		titleMapping.put("score","总分");
		titleMapping.put("anonymous","是否匿名1是,0不是");
		titleMapping.put("username","用户名");
		titleMapping.put("photo","用户头像");
	}
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        if(Constant.SUPER_ADMIN == ShiroUtils.getUserId()){
        	query.put("auth", 1);
        }else{
        	query.put("auth", 0);
        }
        
		List<DsfwCommentEntity> dsfwCommentList = dsfwCommentService.queryList(query);
		int total = dsfwCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dsfwCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DsfwCommentEntity dsfwComment = dsfwCommentService.queryObject(id);
		
		return R.ok().put("dsfwComment", dsfwComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DsfwCommentEntity dsfwComment){
		dsfwCommentService.save(dsfwComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DsfwCommentEntity dsfwComment){
		dsfwCommentService.update(dsfwComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dsfwCommentService.deleteBatch(ids);
		
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
	public R exportExcel(Integer[] mIds,String[] mapkeys, HttpServletResponse response) throws Exception {
		DualHashBidiMap mappingExcel = new DualHashBidiMap();
		for (int i = 0; i < mapkeys.length; i++) {
			mappingExcel.put(mapkeys[i], titleMapping.get(mapkeys[i]));
		}
		HashMap<String, Object> params = new HashMap<>();
		params.put("ids", mIds);
		List<DsfwCommentEntity> queryList = dsfwCommentService.queryList(params);
		POIUtil.generateExcel(mappingExcel, queryList, response);
		return R.ok();
	}
}
