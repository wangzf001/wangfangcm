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

import com.lcworld.entity.DcfwGzcOrdercancelReasonEntity;
import com.lcworld.service.DcfwGzcOrdercancelReasonService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:12
 */
@RestController
@RequestMapping("dcfwgzcordercancelreason")
public class DcfwGzcOrdercancelReasonController {
	@Autowired
	private DcfwGzcOrdercancelReasonService dcfwGzcOrdercancelReasonService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DcfwGzcOrdercancelReasonEntity> dcfwGzcOrdercancelReasonList = dcfwGzcOrdercancelReasonService.queryList(query);
		int total = dcfwGzcOrdercancelReasonService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dcfwGzcOrdercancelReasonList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DcfwGzcOrdercancelReasonEntity dcfwGzcOrdercancelReason = dcfwGzcOrdercancelReasonService.queryObject(id);
		
		return R.ok().put("dcfwGzcOrdercancelReason", dcfwGzcOrdercancelReason);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DcfwGzcOrdercancelReasonEntity dcfwGzcOrdercancelReason){
		dcfwGzcOrdercancelReasonService.save(dcfwGzcOrdercancelReason);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DcfwGzcOrdercancelReasonEntity dcfwGzcOrdercancelReason){
		dcfwGzcOrdercancelReasonService.update(dcfwGzcOrdercancelReason);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dcfwGzcOrdercancelReasonService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
