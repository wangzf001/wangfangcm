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

import com.lcworld.entity.TsjyfwUserrecommandEntity;
import com.lcworld.service.TsjyfwUserrecommandService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 图书借阅服务-读者推荐
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
@RestController
@RequestMapping("tsjyfwuserrecommand")
public class TsjyfwUserrecommandController {
	@Autowired
	private TsjyfwUserrecommandService tsjyfwUserrecommandService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tsjyfwuserrecommand:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TsjyfwUserrecommandEntity> tsjyfwUserrecommandList = tsjyfwUserrecommandService.queryList(query);
		int total = tsjyfwUserrecommandService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tsjyfwUserrecommandList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/recommandlist")
	public R recommandlist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    
	    List<Map<String,Object>> tsjyfwUserrecommandList = tsjyfwUserrecommandService.queryrecommandList(query);
	    int total = tsjyfwUserrecommandService.queryrecommandTotal(query);
	    
	    PageUtils pageUtil = new PageUtils(tsjyfwUserrecommandList, total, query.getLimit(), query.getPage());
	    
	    return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tsjyfwuserrecommand:info")
	public R info(@PathVariable("id") Integer id){
		TsjyfwUserrecommandEntity tsjyfwUserrecommand = tsjyfwUserrecommandService.queryObject(id);
		
		return R.ok().put("tsjyfwUserrecommand", tsjyfwUserrecommand);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tsjyfwuserrecommand:save")
	public R save(@RequestBody TsjyfwUserrecommandEntity tsjyfwUserrecommand){
		tsjyfwUserrecommandService.save(tsjyfwUserrecommand);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tsjyfwuserrecommand:update")
	public R update(@RequestBody TsjyfwUserrecommandEntity tsjyfwUserrecommand){
		tsjyfwUserrecommandService.update(tsjyfwUserrecommand);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tsjyfwuserrecommand:delete")
	public R delete(@RequestBody Integer[] ids){
		tsjyfwUserrecommandService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
