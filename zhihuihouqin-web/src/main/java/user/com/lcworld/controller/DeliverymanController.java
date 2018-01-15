package com.lcworld.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcworld.entity.*;
import com.lcworld.service.*;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.factory.OrderServiceFactory;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 
 * 服务人员
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 15:13:39
 */
@RestController
@RequestMapping("deliveryman")
public class DeliverymanController {
	@Autowired
	private DeliverymanService deliverymanService;
	@Autowired
	private DeliveryOrderService deliveryOrderService;
	@Autowired
	private TbMessageOrderMenderService orderMenderService;
	@Autowired
	private  TbMessageOrderWebService orderWebService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		String deliverytime = MapUtils.getString(params, "deliverytime");
		if (ValidateUtil.isValid(deliverytime)) {
			Date dDate = DateUtils.parse(deliverytime, "yyyy-MM-dd hh:mm");
			Calendar c = Calendar.getInstance();
			c.setTime(dDate);
			c.add(Calendar.HOUR, 1);
			params.put("endtime", c.getTime());
			c.add(Calendar.HOUR, -2);
			params.put("starttime", c.getTime());
		}
        Query query = new Query(params);

		List<DeliverymanEntity> deliverymanList = deliverymanService.queryList(query);
		int total = deliverymanService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(deliverymanList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 列表
	 */
	@RequestMapping("/queryDmanList")
	public R queryDmanList(@RequestBody String biz){
		JSONObject params = JSONObject.parseObject(biz);
		
		//查询列表数据
		Date dDate;
		if (ValidateUtil.isValid(params.getString("deliverytime"))) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				dDate = sdf.parse(params.getString("deliverytime"));
			} catch (Exception e) {
				dDate = new Date();
				e.printStackTrace();
			}
			
		}else{
			dDate = new Date();
		}
		
		Calendar c = Calendar.getInstance();
		c.setTime(dDate);
		c.add(Calendar.HOUR, 1);
		params.put("endtime", c.getTime());
		
		c.add(Calendar.HOUR, -2);
		params.put("starttime", c.getTime());
		
		params.put("servicetypeid", params.get("type"));
		params.put("limit", 10000);
		Query query = new Query(params);
		
		List<DeliverymanEntity> deliverymanList = deliverymanService.queryList(query);
		int total = deliverymanService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(deliverymanList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
//		return R.ok();
	}
	/**
	 * 设置配送员
	 */
	@RequestMapping("/setDeliveryman")
	public R setDeliveryman(@RequestBody String biz){
		JSONObject params = JSONObject.parseObject(biz);
		Integer oid = params.getInteger("oid");
		int type = params.getInteger("type");
		Integer did = params.getInteger("did");
		DeliveryOrderEntity dorder = new DeliveryOrderEntity();
		dorder.setCreatetime(new Date());
		dorder.setDid(did);
		dorder.setOrderid(oid);
		//dorder.setStatus(APPConstant.TYPE_DELIVERY_RECEIVED);
		dorder.setStatus(0);
		dorder.setOrdertype(type);
		deliveryOrderService.save(dorder);
		TbMessageOrderMenderEntity orderMenderEntity = new TbMessageOrderMenderEntity();
		orderMenderEntity.setServerTypeId(type);
		orderMenderEntity.setUid(dorder.getDid());
		orderMenderEntity.setOrderId(dorder.getOrderid());
		orderMenderEntity.setMessageTitle("系统为您分配了一个订单！");
		orderMenderEntity.setMessageContent("系统为您分配了一个订单！请注意查收！");
		orderMenderEntity.setCreateTime(new Date());
		orderMenderService.save(orderMenderEntity);
		TbMessageOrderWebEntity orderWebEntity = orderWebService.queryObject(new Long(dorder.getOrderid()).longValue());
		orderWebEntity.setIsRead(1);
		orderWebService.update(orderWebEntity);
		return R.ok();
	}


	/**
	 * 服务人员列表
	 */
	@RequestMapping("/manlist")
	@RequiresPermissions("deliveryman:manlist")
	public R manlist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		List<DeliverymanEntity> deliverymanList = deliverymanService.queryList(query);
		int total = deliverymanService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(deliverymanList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("deliveryman:info")
	public R info(@PathVariable("id") Integer id){
		DeliverymanEntity deliveryman = deliverymanService.queryObject(id);

		return R.ok().put("deliveryman", deliveryman);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("deliveryman:save")
	public R save(@RequestBody DeliverymanEntity deliveryman){
		deliveryman.setPassword("e10adc3949ba59abbe56e057f20f883e");
		deliveryman.setCreatetime(new Date());
		deliverymanService.save(deliveryman);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("deliveryman:update")
	public R update(@RequestBody DeliverymanEntity deliveryman){
		deliverymanService.update(deliveryman);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("deliveryman:delete")
	public R delete(@RequestBody Integer[] ids){
		deliverymanService.deleteBatch(ids);

		return R.ok();
	}
	
}
