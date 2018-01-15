package com.lcworld.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dto.BgypSkuDto;
import com.lcworld.entity.BgypfwSkuCataInfoEntity;
import com.lcworld.entity.BgypfwSkuidEntity;
import com.lcworld.service.BgypfwSkuCataInfoService;
import com.lcworld.service.BgypfwSkuidService;
import com.lcworld.utils.R;


/**
 * 办公用品服务-规格
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
@RestController
@RequestMapping("appuser/bgypfwskuid")
public class BgypfwSkuidController {
	private Logger log = LoggerFactory.getLogger(BgypfwSkuidController.class);
	@Autowired
	private BgypfwSkuidService bgypfwSkuidService;
	@Autowired
	private BgypfwSkuCataInfoService bgypfwSkuCataInfoService;
	/**
	 * 查询商品库存列表
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findSkuList")
	public R findSkuList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		Integer productId = params.getInteger("productId");
		//只查询上架商品
		params.put("status", APPConstant.TYPE_BGYPFW_ONSALE);
		List<BgypfwSkuidEntity> skuList = bgypfwSkuidService.queryList(params);
		List<BgypSkuDto> skuinfolist = bgypfwSkuCataInfoService.querySkuList(params);
		return R.ok().put("skuList", skuList).put("allskuinfolist", skuinfolist);
	}
}
