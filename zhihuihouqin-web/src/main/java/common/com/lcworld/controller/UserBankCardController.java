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

import com.lcworld.entity.UserBankCardEntity;
import com.lcworld.service.UserBankCardService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 用户银行卡
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
@RestController
@RequestMapping("userbankcard")
public class UserBankCardController {
	@Autowired
	private UserBankCardService userBankCardService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userbankcard:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserBankCardEntity> userBankCardList = userBankCardService.queryList(query);
		int total = userBankCardService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userBankCardList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("userbankcard:info")
	public R info(@PathVariable("id") Integer id){
		UserBankCardEntity userBankCard = userBankCardService.queryObject(id);
		
		return R.ok().put("userBankCard", userBankCard);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("userbankcard:save")
	public R save(@RequestBody UserBankCardEntity userBankCard){
		userBankCardService.save(userBankCard);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("userbankcard:update")
	public R update(@RequestBody UserBankCardEntity userBankCard){
		userBankCardService.update(userBankCard);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("userbankcard:delete")
	public R delete(@RequestBody Integer[] ids){
		userBankCardService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
