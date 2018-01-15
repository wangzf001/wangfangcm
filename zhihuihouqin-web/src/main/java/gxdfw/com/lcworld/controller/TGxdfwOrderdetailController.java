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

import com.lcworld.entity.TGxdfwOrderdetailEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.TGxdfwOrderdetailService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 干洗店服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:51
 */
@RestController
@RequestMapping("gxdfworderdetail")
public class TGxdfwOrderdetailController {
	@Autowired
	private TGxdfwOrderdetailService tGxdfwOrderdetailService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TGxdfwOrderdetailEntity> tGxdfwOrderdetailList = tGxdfwOrderdetailService.queryList(query);
		int total = tGxdfwOrderdetailService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tGxdfwOrderdetailList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TGxdfwOrderdetailEntity tGxdfwOrderdetail = tGxdfwOrderdetailService.queryObject(id);
		
		return R.ok().put("gxdfwOrderdetail", tGxdfwOrderdetail);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TGxdfwOrderdetailEntity tGxdfwOrderdetail){
		try {
            tGxdfwOrderdetailService.savedetail(tGxdfwOrderdetail);
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
	public R update(@RequestBody TGxdfwOrderdetailEntity tGxdfwOrderdetail){
		try {
            tGxdfwOrderdetailService.updatedetail(tGxdfwOrderdetail);
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
            tGxdfwOrderdetailService.deleteBatch(ids);
        } catch (Exception e) {
            if(e instanceof ZHHQException){
                return R.error(((ZHHQException) e).getMsg());
            }
        }
		
		return R.ok();
	}
	
}
