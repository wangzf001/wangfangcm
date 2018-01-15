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

import com.lcworld.entity.LffwOrderdetailEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.LffwOrderdetailService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
@RestController
@RequestMapping("lffworderdetail")
public class LffwOrderdetailController {
	@Autowired
	private LffwOrderdetailService lffwOrderdetailService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwOrderdetailEntity> lffwOrderdetailList = lffwOrderdetailService.queryList(query);
		int total = lffwOrderdetailService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwOrderdetailList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

	
	/**
	 * 列表
	 */
	@RequestMapping("/detaillist")
	public R detaillist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    
	    List<Map<String,Object>> lffwOrderdetailList = lffwOrderdetailService.querydetailList(query);
	    int total = lffwOrderdetailService.querydetailTotal(query);
	    
	    PageUtils pageUtil = new PageUtils(lffwOrderdetailList, total, query.getLimit(), query.getPage());
	    
	    return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		LffwOrderdetailEntity lffwOrderdetail = lffwOrderdetailService.queryObject(id);
		
		return R.ok().put("lffwOrderdetail", lffwOrderdetail);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody LffwOrderdetailEntity lffwOrderdetail){
		
	    try {
            lffwOrderdetailService.saveOrder(lffwOrderdetail);
        } catch (Exception e) {
           if(e instanceof ZHHQException){
               return R.error(((ZHHQException) e).getMsg());
           }
        }
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody LffwOrderdetailEntity lffwOrderdetail){
		try {
            lffwOrderdetailService.updateOrder(lffwOrderdetail);
        } catch (Exception e) {
            if(e instanceof ZHHQException){
                return R.error(((ZHHQException) e).getMsg());
            }
        }
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		try {
            lffwOrderdetailService.deleteBatch(ids);
        } catch (Exception e) {
            if(e instanceof ZHHQException){
                return R.error(((ZHHQException) e).getMsg());
            }
        }
		
		return R.ok();
	}
	
}
