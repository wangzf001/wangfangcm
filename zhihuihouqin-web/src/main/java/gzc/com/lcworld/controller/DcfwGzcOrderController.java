package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.consts.APPConstant;
import com.lcworld.entity.DcfwGzcCommentEntity;
import com.lcworld.entity.DcfwGzcOrderEntity;
import com.lcworld.service.DcfwGzcOrderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订餐服务-工作餐订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:13
 */
@RestController
@RequestMapping("dcfwgzcorder")
public class DcfwGzcOrderController {
	@Autowired
	private DcfwGzcOrderService dcfwGzcOrderService;
	//poi导入导出映射关系
		private DualHashBidiMap titleMapping = new DualHashBidiMap();
		{
			titleMapping.put("id","id");
			titleMapping.put("ordercode","订单编号");
			titleMapping.put("uid","用户编号");
			titleMapping.put("realname","姓名");
			titleMapping.put("mobile","预留电话");
			titleMapping.put("count","预订数量");
			titleMapping.put("address","地址");
			titleMapping.put("status","订单状态");
			titleMapping.put("reasonContent","取消原因");
			titleMapping.put("createtime","创建时间");
		}
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		if ("".equals((String)params.get("orderStatus"))) {
			params.remove("orderStatus");
		}
		if ("".equals((String)params.get("payStatus"))) {
			params.remove("payStatus");
		}
		if ("".equals((String)params.get("createTimeStart"))) {
			params.remove("createTimeStart");
		}
		if ("".equals((String)params.get("createTimeEnd"))) {
			params.remove("createTimeEnd");
		}
		//查询列表数据
        Query query = new Query(params);

		List<DcfwGzcOrderEntity> dcfwGzcOrderList = dcfwGzcOrderService.queryList(query);
		int total = dcfwGzcOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dcfwGzcOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DcfwGzcOrderEntity dcfwGzcOrder = dcfwGzcOrderService.queryObject(id);
		
		return R.ok().put("dcfwGzcOrder", dcfwGzcOrder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DcfwGzcOrderEntity dcfwGzcOrder){
		dcfwGzcOrderService.save(dcfwGzcOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DcfwGzcOrderEntity dcfwGzcOrder){
		dcfwGzcOrderService.update(dcfwGzcOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dcfwGzcOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	/**
	 * 完成
	 */
	@RequestMapping("/updateBatch")
	public R updateBatch(@RequestBody Integer[] ids){
		dcfwGzcOrderService.updateBatch(ids,APPConstant.TYPE_ORDER_STATUS_EVALUATED);
		return R.ok();
	}
	/**
	 * 导出excel
	 * 
	 * @param mIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/exportExcel")
	public R exportExcel(Integer[] mIds, HttpServletResponse response) throws Exception {
		HashMap<String, Object> params = new HashMap<>();
		params.put("ids", mIds);
		params.put("orderStatus",3);
		List<DcfwGzcOrderEntity> queryList = dcfwGzcOrderService.queryList(params);
		dcfwGzcOrderService.exportExcel(titleMapping, queryList, response);
		return R.ok();
	}
}
