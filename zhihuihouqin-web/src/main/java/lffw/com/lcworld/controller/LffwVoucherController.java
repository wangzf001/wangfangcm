package com.lcworld.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.LffwVoucherEntity;
import com.lcworld.service.LffwVoucherService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务--代金券
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:13
 */
@RestController
@RequestMapping("lffwvoucher")
public class LffwVoucherController {
	@Autowired
	private LffwVoucherService lffwVoucherService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("lffwvoucher:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwVoucherEntity> lffwVoucherList = lffwVoucherService.queryList(query);
		int total = lffwVoucherService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwVoucherList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("lffwvoucher:info")
	public R info(@PathVariable("id") Integer id){
		LffwVoucherEntity lffwVoucher = lffwVoucherService.queryObject(id);
		
		return R.ok().put("lffwVoucher", lffwVoucher);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("lffwvoucher:save")
	public R save(@RequestBody LffwVoucherEntity lffwVoucher){
	    lffwVoucher.setLockcount(0);
	    lffwVoucher.setCreatetime(new Date());
	    lffwVoucher.setValid(1);
		lffwVoucherService.save(lffwVoucher);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("lffwvoucher:update")
	public R update(@RequestBody LffwVoucherEntity lffwVoucher){
		lffwVoucherService.update(lffwVoucher);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("lffwvoucher:delete")
	public R delete(@RequestBody Integer[] ids){
		lffwVoucherService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
