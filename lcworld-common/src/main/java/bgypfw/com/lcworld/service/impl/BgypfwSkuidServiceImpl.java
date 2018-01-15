package com.lcworld.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcworld.dao.BgypfwProductDao;
import com.lcworld.dao.BgypfwSkuidDao;
import com.lcworld.entity.BgypfwSkuCataInfoEntity;
import com.lcworld.entity.BgypfwSkuidEntity;
import com.lcworld.service.BgypfwProducattrcataService;
import com.lcworld.service.BgypfwSkuCataInfoService;
import com.lcworld.service.BgypfwSkuidService;
import com.lcworld.utils.util.ValidateUtil;



@Service("bgypfwSkuidService")
public class BgypfwSkuidServiceImpl implements BgypfwSkuidService {
	@Autowired
	private BgypfwSkuidDao bgypfwSkuidDao;
	@Autowired
	private BgypfwProductDao bgypfwProductDao;
	@Autowired
	private BgypfwSkuCataInfoService bgypfwSkuCataInfoService;

	@Override
	public BgypfwSkuidEntity queryObject(Integer id){
		return bgypfwSkuidDao.queryObject(id);
	}

	@Override
	public BgypfwSkuidEntity querySkuidByName(String skuidName) {
		return bgypfwSkuidDao.querySkuidByName(skuidName);
	}

	@Override
	public List<BgypfwSkuidEntity> queryList(Map<String, Object> map){
		return bgypfwSkuidDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwSkuidDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwSkuidEntity bgypfwSkuid){
		bgypfwSkuidDao.save(bgypfwSkuid);
	}
	
	@Override
	public void update(BgypfwSkuidEntity bgypfwSkuid){
		bgypfwSkuidDao.update(bgypfwSkuid);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwSkuidDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwSkuidDao.deleteBatch(ids);
	}

	@Override
	public Integer queryProductRemain(Map<String, Object> params1) {
		return bgypfwSkuidDao.queryProductRemain(params1);
	}

//	@Override
//	public void updateSaleStatus(JSONObject params) {
//		bgypfwSkuidDao.updateSaleStatus(params);
//		JSONArray ids = params.getJSONArray("ids");
//		if (ValidateUtil.isValid(ids)) {
//			BgypfwSkuidEntity sku = bgypfwSkuidDao.queryObject(ids.get(0));
//			params.put("productId", sku.getProductid());
//			params.put("status", APPConstant.TYPE_BGYPFW_ONSALE);
//			int num = bgypfwSkuidDao.queryTotal(params);
//			BgypfwProductEntity product = new BgypfwProductEntity();
//			product.setId(MapUtils.getInteger(params, "productId"));
//			if (num==0) {
//				//修改商品为下线状态
//				product.setPricerange("￥0-0");
//				product.setStatus(APPConstant.TYPE_BGYPFW_NOTSALE);
//			}else{
//				//修改商品为上线状态
//				Map<String,Object> map = bgypfwSkuidDao.queryPricerange(params);
//				Integer maxprice = MapUtils.getInteger(map, "maxprice");
//				Integer minprice = MapUtils.getInteger(map, "minprice");
//				if (ValidateUtil.isValid(maxprice)) {
//					if (maxprice.intValue()==minprice.intValue()) {
//						product.setPricerange("￥"+maxprice);
//					}else{
//						product.setPricerange("￥"+minprice+"-"+maxprice);
//					}
//				}
//				product.setStatus(APPConstant.TYPE_BGYPFW_ONSALE);
//			}
//			bgypfwProductDao.update(product);
//		}
//	}

	@Override
	public Map<String, Object> queryPricerange(Map<String,Object> params) {
		return bgypfwSkuidDao.queryPricerange(params);
	}

	@Override
	public void savesku(BgypfwSkuidEntity bgypfwSkuid) {
		save(bgypfwSkuid);
		if(ValidateUtil.isValid(bgypfwSkuid.getCatainfolist())){
			for(BgypfwSkuCataInfoEntity info : bgypfwSkuid.getCatainfolist()){
				info.setProductid(bgypfwSkuid.getProductid());
				info.setSkuid(bgypfwSkuid.getId());
			}
		}
		bgypfwSkuCataInfoService.saveBench(bgypfwSkuid.getCatainfolist());
		
	}

	@Override
	public void updatesku(BgypfwSkuidEntity bgypfwSkuid) {
		update(bgypfwSkuid);
		bgypfwSkuCataInfoService.deletebyskuid(bgypfwSkuid.getId());
		if(ValidateUtil.isValid(bgypfwSkuid.getCatainfolist())){
			for(BgypfwSkuCataInfoEntity info : bgypfwSkuid.getCatainfolist()){
				info.setProductid(bgypfwSkuid.getProductid());
				info.setSkuid(bgypfwSkuid.getId());
			}
		}
		bgypfwSkuCataInfoService.saveBench(bgypfwSkuid.getCatainfolist());
	}

	@Override
	public void deletesku(Integer[] ids) {
		deleteBatch(ids);
		for(Integer id: ids){
			if(ValidateUtil.isValid(id))
			bgypfwSkuCataInfoService.deletebyskuid(id);
		}
		
	}
}
