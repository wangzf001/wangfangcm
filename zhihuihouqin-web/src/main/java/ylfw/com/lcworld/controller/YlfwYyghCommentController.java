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

import com.lcworld.entity.YlfwYyghCommentEntity;
import com.lcworld.service.YlfwYyghCommentService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务预约挂号评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:52
 */
@RestController
@RequestMapping("ylfwyyghcomment")
public class YlfwYyghCommentController {
	@Autowired
	private YlfwYyghCommentService ylfwYyghCommentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwyyghcomment:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwYyghCommentEntity> ylfwYyghCommentList = ylfwYyghCommentService.queryList(query);
		int total = ylfwYyghCommentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwYyghCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwyyghcomment:info")
	public R info(@PathVariable("id") Integer id){
		YlfwYyghCommentEntity ylfwYyghComment = ylfwYyghCommentService.queryObject(id);
		
		return R.ok().put("ylfwYyghComment", ylfwYyghComment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwyyghcomment:save")
	public R save(@RequestBody YlfwYyghCommentEntity ylfwYyghComment){
		ylfwYyghCommentService.save(ylfwYyghComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwyyghcomment:update")
	public R update(@RequestBody YlfwYyghCommentEntity ylfwYyghComment){
		ylfwYyghCommentService.update(ylfwYyghComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwyyghcomment:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwYyghCommentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
