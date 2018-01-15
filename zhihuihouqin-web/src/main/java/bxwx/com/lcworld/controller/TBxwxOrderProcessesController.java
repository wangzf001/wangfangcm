package com.lcworld.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lcworld.consts.APPConstant;
import com.lcworld.entity.TbMessageOrderMenderEntity;
import com.lcworld.entity.TbMessageOrderWebEntity;
import com.lcworld.service.TbMessageOrderMenderService;
import com.lcworld.service.TbMessageOrderWebService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TBxwxOrderProcessesEntity;
import com.lcworld.service.TBxwxOrderProcessesService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订单流程表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
@RestController
@RequestMapping("tbxwxorderprocesses")
public class TBxwxOrderProcessesController {
	@Autowired
	private TBxwxOrderProcessesService tBxwxOrderProcessesService;
	@Autowired
	private TbMessageOrderMenderService menderService;
	@Autowired
	private TbMessageOrderWebService orderWebService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TBxwxOrderProcessesEntity> tBxwxOrderProcessesList = tBxwxOrderProcessesService.queryList(query);
		int total = tBxwxOrderProcessesService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tBxwxOrderProcessesList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TBxwxOrderProcessesEntity tBxwxOrderProcesses = tBxwxOrderProcessesService.queryObject(id);
		
		return R.ok().put("tBxwxOrderProcesses", tBxwxOrderProcesses);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TBxwxOrderProcessesEntity tBxwxOrderProcesses){
	    JSONObject param  = new JSONObject();
	    param.put("orderid", tBxwxOrderProcesses.getOrderid());
	    param.put("status", tBxwxOrderProcesses.getStatus());
	    List<TBxwxOrderProcessesEntity> list = tBxwxOrderProcessesService.queryList(param);
	    if(list == null || list.size()== 0){
	        tBxwxOrderProcesses.setDetail(tBxwxOrderProcessesService.getDatail(tBxwxOrderProcesses));
	        tBxwxOrderProcesses.setCreatetime(new Date());
	        tBxwxOrderProcessesService.saveProcesses(tBxwxOrderProcesses);
	        
			TbMessageOrderMenderEntity orderMenderEntity = new TbMessageOrderMenderEntity();
			orderMenderEntity.setCreateTime(new Date());
			orderMenderEntity.setMessageTitle("系统为你分配了订单");
			orderMenderEntity.setOrderId(tBxwxOrderProcesses.getOrderid());
			orderMenderEntity.setServerTypeId(APPConstant.TYPE_BXFW);
			orderMenderEntity.setUid(tBxwxOrderProcesses.getMenderid());
			orderMenderEntity.setMessageContent("系统为您分配了报修维修的订单，请注意查收！");
			orderMenderEntity.setIsRead(0);
			orderMenderEntity.setIsDelete(0);
			menderService.save(orderMenderEntity);
			
			TbMessageOrderWebEntity orderWebEntity = orderWebService.queryObject(new Long(tBxwxOrderProcesses.getOrderid()).longValue());
			if(orderWebEntity != null){
				orderWebEntity.setIsRead(1);
				orderWebService.update(orderWebEntity);
			}
	    }else{
	        return R.error("您已进行过相关操作");
	    }
		return R.ok();
	}
	

    /**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TBxwxOrderProcessesEntity tBxwxOrderProcesses){
		tBxwxOrderProcessesService.update(tBxwxOrderProcesses);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tBxwxOrderProcessesService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
