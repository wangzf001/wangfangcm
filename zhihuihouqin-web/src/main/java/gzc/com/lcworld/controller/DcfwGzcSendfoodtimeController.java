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

import com.lcworld.entity.DcfwGzcSendfoodtimeEntity;
import com.lcworld.service.DcfwGzcSendfoodtimeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订餐服务-工作餐预定-送餐时间
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:13
 */
@RestController
@RequestMapping("dcfwgzcsendfoodtime")
public class DcfwGzcSendfoodtimeController {
	@Autowired
	private DcfwGzcSendfoodtimeService dcfwGzcSendfoodtimeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DcfwGzcSendfoodtimeEntity> dcfwGzcSendfoodtimeList = dcfwGzcSendfoodtimeService.queryList(query);
		int total = dcfwGzcSendfoodtimeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dcfwGzcSendfoodtimeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DcfwGzcSendfoodtimeEntity dcfwGzcSendfoodtime = dcfwGzcSendfoodtimeService.queryObject(id);
		
		return R.ok().put("dcfwGzcSendfoodtime", dcfwGzcSendfoodtime);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DcfwGzcSendfoodtimeEntity dcfwGzcSendfoodtime){
		dcfwGzcSendfoodtimeService.save(dcfwGzcSendfoodtime);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DcfwGzcSendfoodtimeEntity dcfwGzcSendfoodtime){
		dcfwGzcSendfoodtimeService.update(dcfwGzcSendfoodtime);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dcfwGzcSendfoodtimeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
