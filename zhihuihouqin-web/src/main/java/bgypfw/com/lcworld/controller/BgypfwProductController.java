package com.lcworld.controller;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.lcworld.utils.POIUtil;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.BgypfwProductEntity;
import com.lcworld.entity.TDcfwFoodEntity;
import com.lcworld.service.BgypfwProductService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 办公用品服务-商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
@RestController
@RequestMapping("bgypfwproduct")
public class BgypfwProductController {
	@Autowired
	private BgypfwProductService bgypfwProductService;
	// poi导入导出映射关系
	private DualHashBidiMap titleMapping = new DualHashBidiMap();
	{
		titleMapping.put("id", "id");
		titleMapping.put("productCode", "商品编号");
		titleMapping.put("productname", "商品名");
		titleMapping.put("pricerange", "价格区间");
		titleMapping.put("mainskuid", "主规格编号");
		titleMapping.put("productImg", "图片");
		titleMapping.put("categoryOneId", "一级分类id");
		titleMapping.put("categoryOneName", "一级分类名");
		titleMapping.put("categoryTwoId", "二级分类id");
		titleMapping.put("categoryTwoName", "二级分类名");
		titleMapping.put("imgs", "图片组");
		titleMapping.put("supplierName", "供应单位");
		titleMapping.put("onsaleTime", "上架时间");
		titleMapping.put("status", "上架状态");
		titleMapping.put("createtime", "创建时间");
	}
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		if ("".equals((String)params.get("categoryOneId"))) {
			params.remove("categoryOneId");
		}
		if ("".equals((String)params.get("categoryTwoId"))) {
			params.remove("categoryTwoId");
		}
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwProductEntity> bgypfwProductList = bgypfwProductService.queryList(query);
		int total = bgypfwProductService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwProductList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
		public R info(@PathVariable("id") Integer id){
		BgypfwProductEntity bgypfwProduct = bgypfwProductService.queryObject(id);
		
		return R.ok().put("bgypfwProduct", bgypfwProduct);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
		public R save(@RequestBody BgypfwProductEntity bgypfwProduct){
		bgypfwProduct.setCreatetime(new Date());
		bgypfwProduct.setFavorNum(0);
		bgypfwProduct.setMainskuid(0);
		bgypfwProduct.setStatus(APPConstant.TYPE_BGYPFW_NOTSALE);
		bgypfwProduct.setPricerange("￥0-0");
		bgypfwProductService.save(bgypfwProduct);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
		public R update(@RequestBody BgypfwProductEntity bgypfwProduct){
		bgypfwProductService.update(bgypfwProduct);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
		public R delete(@RequestBody Integer[] ids){
		bgypfwProductService.deleteBatch(ids);
		
		return R.ok();
	}
	/**
	 * 设置主规格
	 */
	@RequestMapping("/setMainKC")
	public R setMainKC(@RequestBody String biz){
		JSONObject params = JSONObject.parseObject(biz);
		Integer id = params.getInteger("id");
		Integer productId = params.getInteger("productId");
		BgypfwProductEntity product = new BgypfwProductEntity();
		product.setId(productId);
		product.setMainskuid(id);
		bgypfwProductService.update1(product);
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
	public R exportExcel(Integer[] mIds, HttpServletResponse response){
		HashMap<String, Object> params = new HashMap<>();
		params.put("ids", mIds);
		List<BgypfwProductEntity> queryList = bgypfwProductService.queryList(params);
		bgypfwProductService.exportExcel(titleMapping, queryList, response);
		//POIUtil.generateExcel(titleMapping, queryList, response);
		return R.ok();
	}

	/**
	 * 生成excel模板
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/downloadExcel")
	public R downloadExcel(HttpServletResponse response){
		DualHashBidiMap titles = titleMapping;
		titles.remove("id");
		List<BgypfwProductEntity> queryList = new ArrayList<>();
		queryList.add(new BgypfwProductEntity());
		bgypfwProductService.exportExcel(titles, queryList, response);
		return R.ok();
	}

	/**
	 * 导入excel
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/inportExcel")
	public R inportExcel(@RequestParam("file") MultipartFile file){
		//List<TDcfwFoodEntity> list = POIUtil.readExcel(TDcfwFoodEntity.class,titleMapping,file);
		DualHashBidiMap title = new DualHashBidiMap();
		{
			title.put("productCode", "商品编号");
			title.put("productname", "商品名");
			title.put("pricerange", "价格区间");
			title.put("mainskuid", "主规格编号");
			title.put("productImg", "图片");
			title.put("categoryOneName", "一级分类名");
			title.put("categoryTwoName", "二级分类名");
			title.put("imgs", "图片组");
			title.put("supplierName", "供应单位");
			title.put("onsaleTime", "上架时间");
			title.put("status", "上架状态");
			title.put("createtime", "创建时间");
		}
		try {
			List<JSONObject> mapList = POIUtil.readExcel(JSONObject.class, titleMapping, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//bgypfwProductService.importExcel(BgypfwProductEntity.class, titleMapping, file);
		List<BgypfwProductEntity> list = bgypfwProductService.importExcel(BgypfwProductEntity.class, titleMapping, file);
		System.err.println("个数"+list);
		bgypfwProductService.saveBatch(list);
		return R.ok();
	}
}
