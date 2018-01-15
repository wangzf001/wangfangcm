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

import com.lcworld.entity.TsjyfwBookEntity;
import com.lcworld.entity.TsjyfwOrderEntity;
import com.lcworld.service.TsjyfwBookService;
import com.lcworld.service.TsjyfwOrderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 图书借阅服务--借阅图书
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
@RestController
@RequestMapping("tsjyfworder")
public class TsjyfwOrderController {
	@Autowired
	private TsjyfwOrderService tsjyfwOrderService;
	@Autowired
	private TsjyfwBookService tsjyfwBookService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tsjyfworder:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TsjyfwOrderEntity> tsjyfwOrderList = tsjyfwOrderService.queryList(query);
		int total = tsjyfwOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tsjyfwOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/orderlist")
	public R orderlist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    
	    List<Map<String,Object>> tsjyfwOrderList = tsjyfwOrderService.queryorderList(query);
	    int total = tsjyfwOrderService.queryorderTotal(query);
	    
	    PageUtils pageUtil = new PageUtils(tsjyfwOrderList, total, query.getLimit(), query.getPage());
	    
	    return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tsjyfworder:info")
	public R info(@PathVariable("id") Integer id){
		TsjyfwOrderEntity tsjyfwOrder = tsjyfwOrderService.queryObject(id);
		TsjyfwBookEntity book = tsjyfwBookService.queryObject(tsjyfwOrder.getBookid());
		return R.ok().put("tsjyfwOrder", tsjyfwOrder).put("bookname", book.getTitle());
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tsjyfworder:save")
	public R save(@RequestBody TsjyfwOrderEntity tsjyfwOrder){
		tsjyfwOrderService.save(tsjyfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tsjyfworder:update")
	public R update(@RequestBody TsjyfwOrderEntity tsjyfwOrder){
	    
	    TsjyfwBookEntity book = new TsjyfwBookEntity();
	    book.setId(tsjyfwOrder.getBookid());
	    if(3 == tsjyfwOrder.getStatus() || 5 == tsjyfwOrder.getStatus()){
	        book.setBollowstatus(1);
	    }else if(2 == tsjyfwOrder.getStatus() ){
	        book.setBollowstatus(2);
	    }
	    tsjyfwBookService.update(book);
		tsjyfwOrderService.update(tsjyfwOrder);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tsjyfworder:delete")
	public R delete(@RequestBody Integer[] ids){
		tsjyfwOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
