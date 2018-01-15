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

import com.lcworld.consts.APPConstant;
import com.lcworld.entity.HysfwCommentEntity;
import com.lcworld.entity.TGxdfwCommentEntity;
import com.lcworld.service.HysfwCommentService;
import com.lcworld.service.ServiceService;
import com.lcworld.util.POIUtil;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;


/**
 * 报修维修评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-30 18:43:48
 */
@RestController
@RequestMapping("hysfwcomment")
public class HysfwCommentController {
	@Autowired
	private HysfwCommentService hysfwCommentService;
	@Autowired
	private ServiceService serviceService;
	//poi导入导出映射关系
	private DualHashBidiMap titleMapping = new DualHashBidiMap();
	{
		titleMapping.put("id","id");
		titleMapping.put("imgs","图片");
		titleMapping.put("content","评价内容");
		titleMapping.put("orderid","订单编号");
		titleMapping.put("uid","用户编号");
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
	@RequiresPermissions("hysfwcomment:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        
        /*List<String> typeList = serviceService.getServiceTypeList(ShiroUtils.getUserId());*/
        if(Constant.SUPER_ADMIN == ShiroUtils.getUserId()){
        	query.put("auth", 1);
        }else{
        	query.put("auth", 0);
        }
		List<HysfwCommentEntity> hysfwCommentList = hysfwCommentService.queryList(query);
		int total = hysfwCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(hysfwCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hysfwcomment:info")
	public R info(@PathVariable("id") Integer id){
		HysfwCommentEntity hysfwComment = hysfwCommentService.queryObject(id);
		
		return R.ok().put("hysfwComment", hysfwComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("hysfwcomment:save")
	public R save(@RequestBody HysfwCommentEntity hysfwComment){
		hysfwCommentService.save(hysfwComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("hysfwcomment:update")
	public R update(@RequestBody HysfwCommentEntity hysfwComment){
		hysfwCommentService.update(hysfwComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("hysfwcomment:delete")
	public R delete(@RequestBody Integer[] ids){
		hysfwCommentService.deleteBatch(ids);
		
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
		List<HysfwCommentEntity> queryList = hysfwCommentService.queryList(params);
		POIUtil.generateExcel(titleMapping, queryList, response);
		return R.ok();
	}
}
