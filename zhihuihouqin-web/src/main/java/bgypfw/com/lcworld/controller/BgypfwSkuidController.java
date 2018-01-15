package com.lcworld.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.lcworld.entity.BgypfwProducattrcataEntity;
import com.lcworld.entity.BgypfwProductEntity;
import com.lcworld.entity.BgypfwSkuCataInfoEntity;
import com.lcworld.entity.BgypfwSkuidEntity;
import com.lcworld.service.BgypfwProducattrcataService;
import com.lcworld.service.BgypfwProductService;
import com.lcworld.service.BgypfwSkuCataInfoService;
import com.lcworld.service.BgypfwSkufirstcataService;
import com.lcworld.service.BgypfwSkuidService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;


/**
 * 办公用品服务-规格
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
@RestController
@RequestMapping("bgypfwskuid")
public class BgypfwSkuidController {
	@Autowired
	private BgypfwSkuidService bgypfwSkuidService;
	@Autowired
	private BgypfwProductService bgypfwProductService;
	@Autowired
	private BgypfwSkufirstcataService bgypfwSkufirstcataService;
	@Autowired
	private BgypfwSkuCataInfoService bgypfwSkuCataInfoService;
	@Autowired
	private BgypfwProducattrcataService bgypfwProducattrcataService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BgypfwSkuidEntity> bgypfwSkuidList = bgypfwSkuidService.queryList(query);
		int total = bgypfwSkuidService.queryTotal(query);
		//查询商品已经选择的主规格
//		if (ValidateUtil.isValid(bgypfwSkuidList)) {
//			BgypfwSkuidEntity sku = bgypfwSkuidList.get(0);
//			Integer productid = sku.getProductid();
//			BgypfwProductEntity product = bgypfwProductService.queryObject(productid);
//			Integer mainskuid = product.getMainskuid();
//			if (ValidateUtil.isValid(mainskuid)) {
//				for (int i = 0; i < bgypfwSkuidList.size(); i++) {
//					BgypfwSkuidEntity skuEntity = bgypfwSkuidList.get(i);
//					if (skuEntity.getId().intValue()==mainskuid.intValue()) {
//						skuEntity.setIsMain(1);
//					}
//				}
//			}
//		}
		PageUtils pageUtil = new PageUtils(bgypfwSkuidList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		BgypfwSkuidEntity bgypfwSkuid = bgypfwSkuidService.queryObject(id);
		JSONObject obj = new JSONObject();
		obj.put("productid", bgypfwSkuid.getProductid());
		List<BgypfwProducattrcataEntity> attrlist = bgypfwProducattrcataService.queryList(obj);
		obj.put("skuid", bgypfwSkuid.getId());
		List<BgypfwSkuCataInfoEntity> skuattrlist = bgypfwSkuCataInfoService.queryList(obj);
		dealattrlist(attrlist,skuattrlist,bgypfwSkuid);
		bgypfwSkuid.setCatainfolist(skuattrlist);
		return R.ok().put("bgypfwSkuid", bgypfwSkuid);
	}
	
	private void dealattrlist(List<BgypfwProducattrcataEntity> attrlist, List<BgypfwSkuCataInfoEntity> skuattrlist, BgypfwSkuidEntity bgypfwSkuid) {
		List<BgypfwSkuCataInfoEntity> addlist = new ArrayList<BgypfwSkuCataInfoEntity>();
		if(ValidateUtil.isValid(attrlist) && ValidateUtil.isValid(skuattrlist)){
			for(BgypfwProducattrcataEntity en : attrlist){
				boolean flag= true;
				for(BgypfwSkuCataInfoEntity info:skuattrlist){
					if(en.getCataid() == info.getCataid()){
						flag = false;
						info.setCataname(en.getCataname());
					}
				}
				if(flag){
					BgypfwSkuCataInfoEntity e = new BgypfwSkuCataInfoEntity();
					e.setCataid(en.getCataid());
					e.setProductid(bgypfwSkuid.getProductid());
					e.setSkuid(bgypfwSkuid.getId());
					e.setCataname(en.getCataname());
					addlist.add(e);
				}
			}
			skuattrlist.addAll(addlist);
		}
	}


	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody BgypfwSkuidEntity bgypfwSkuid){
		bgypfwSkuid.setCreatetime(new Date());
		bgypfwSkuid.setStatus(APPConstant.TYPE_BGYPFW_NOTSALE);
		bgypfwSkuidService.savesku(bgypfwSkuid);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody BgypfwSkuidEntity bgypfwSkuid){
		bgypfwSkuidService.updatesku(bgypfwSkuid);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		bgypfwSkuidService.deletesku(ids);
		
		return R.ok();
	}
	/**
	 * 修改商品上架状态
	 */
	@RequestMapping("/updateSaleStatus")
	public R updateSaleStatus(@RequestBody String biz){
		JSONObject params = JSONObject.parseObject(biz);
		Map<String,Object> map = new HashMap<>();
		map.put("ids", params.getJSONArray("ids").toArray());
		List<BgypfwSkuidEntity> list = bgypfwSkuidService.queryList(map);
		List<BgypfwSkuidEntity> onsale = new ArrayList<>();
		List<BgypfwSkuidEntity> notsale = new ArrayList<>();
		for (BgypfwSkuidEntity sku : list) {
			if (sku.getStatus().intValue()==APPConstant.TYPE_BGYPFW_ONSALE) {
				onsale.add(sku);
			}else if (sku.getStatus().intValue()==APPConstant.TYPE_BGYPFW_NOTSALE) {
				notsale.add(sku);
			}
		}
		if (params.getInteger("status").intValue()==APPConstant.TYPE_BGYPFW_ONSALE_CHECKING) {
			for (BgypfwSkuidEntity sku : notsale) {
				sku.setStatus(APPConstant.TYPE_BGYPFW_ONSALE_CHECKING);
				bgypfwSkuidService.update(sku);
			}
		}else if (params.getInteger("status").intValue()==APPConstant.TYPE_BGYPFW_NOTSALE_CHECKING) {
			for (BgypfwSkuidEntity sku : onsale) {
				sku.setStatus(APPConstant.TYPE_BGYPFW_NOTSALE_CHECKING);
				bgypfwSkuidService.update(sku);
			}
		}
		return R.ok();
	}
	/**
	 * 审核商品上下架状态
	 */
	@RequestMapping("/check")
		public R check(@RequestBody String biz){
		JSONObject params = JSONObject.parseObject(biz);
		System.err.println(biz);
		List<BgypfwSkuidEntity> list = bgypfwSkuidService.queryList(params);
		List<BgypfwSkuidEntity> onsaleCheck = new ArrayList<>();
		List<BgypfwSkuidEntity> notsaleCheck = new ArrayList<>();
		for (BgypfwSkuidEntity sku : list) {
			if (sku.getStatus().intValue()==APPConstant.TYPE_BGYPFW_ONSALE_CHECKING) {
				onsaleCheck.add(sku);
			}else if (sku.getStatus().intValue()==APPConstant.TYPE_BGYPFW_NOTSALE_CHECKING) {
				notsaleCheck.add(sku);
			}
		}
		if (params.getInteger("check").intValue()==1) {
			//通过
			BgypfwSkuidEntity ex = new BgypfwSkuidEntity();
			for (BgypfwSkuidEntity sku : onsaleCheck) {
				sku.setStatus(APPConstant.TYPE_BGYPFW_ONSALE);
				ex = sku;
				bgypfwSkuidService.update(sku);
			}
			for (BgypfwSkuidEntity sku : notsaleCheck) {
				sku.setStatus(APPConstant.TYPE_BGYPFW_NOTSALE);
				ex = sku;
				bgypfwSkuidService.update(sku);
			}
			//判断是否有上架库存
			Map<String,Object> map = new HashMap<>();
			BgypfwProductEntity product = new BgypfwProductEntity();
			product.setId(ex.getProductid());
			map.put("productId", ex.getProductid());
			map.put("status", APPConstant.TYPE_BGYPFW_ONSALE);
			int num = bgypfwSkuidService.queryTotal(map);
			if (num==0) {
				//无上架商品
				//修改商品为下线状态
				product.setPricerange("￥0-0");
				product.setStatus(APPConstant.TYPE_BGYPFW_NOTSALE);
			}else{
				//修改商品为上线状态
				Map<String,Object> range = bgypfwSkuidService.queryPricerange(map);
				Double maxprice = MapUtils.getDouble(range, "maxprice");
				Double minprice = MapUtils.getDouble(range, "minprice");
				if (ValidateUtil.isValid(maxprice)) {
					if (maxprice.intValue()==minprice.intValue()) {
						product.setPricerange("￥"+maxprice);
					}else{
						product.setPricerange("￥"+minprice+"-"+maxprice);
					}
				}
				product.setStatus(APPConstant.TYPE_BGYPFW_ONSALE);
			}
			bgypfwProductService.update(product);
		}else{
			System.err.println(params.getString("failedreason"));
			//不通过
			for (BgypfwSkuidEntity sku : onsaleCheck) {
				sku.setStatus(APPConstant.TYPE_BGYPFW_ONSALE_CHECK_FAILED);
				sku.setFailedreason(params.getString("failedreason"));
				bgypfwSkuidService.update(sku);
			}
			for (BgypfwSkuidEntity sku : notsaleCheck) {
				sku.setStatus(APPConstant.TYPE_BGYPFW_ONSALE);
				sku.setFailedreason(params.getString("failedreason"));
				bgypfwSkuidService.update(sku);
			}
		}
		return R.ok();
	}
}
