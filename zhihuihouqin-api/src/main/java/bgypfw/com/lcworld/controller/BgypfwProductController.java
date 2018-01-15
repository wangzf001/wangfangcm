package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.BgypfwCategoryEntity;
import com.lcworld.entity.BgypfwCommentEntity;
import com.lcworld.entity.BgypfwProductEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.BgypfwCategoryService;
import com.lcworld.service.BgypfwCommentService;
import com.lcworld.service.BgypfwProductService;
import com.lcworld.service.BgypfwSkuidService;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TFavorService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;



/**
 * 办公用品服务-商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
@RestController
@RequestMapping("appuser/bgypfwproduct")
public class BgypfwProductController {
	private Logger log = LoggerFactory.getLogger(BgypfwProductController.class);
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private BgypfwProductService bgypfwProductService;
	@Autowired
	private BgypfwCommentService bgypfwCommentService;
	@Autowired
	private TFavorService tFavorService;
	@Autowired
	private BgypfwSkuidService bgypfwSkuidService;
	@Autowired
	private BgypfwCategoryService bgypfwCategoryService;
	/**
	 * 办公用品首页
	 * @param biz
	 * @return
	 */
	@RequestMapping("/index")
	public R index(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		params.put("cgrade", 1);
		//1、头部图
		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_BGYPFW);
		//2、中间的类型
		List<BgypfwCategoryEntity> categoryList = bgypfwCategoryService.queryList(params);
		//3、下方的新到商品
		//只查询上架商品
		params.put("status", APPConstant.TYPE_BGYPFW_ONSALE);
		//查询列表数据
		params.put("page", 1);
		params.put("limit", 4);
        Query query = new Query(params);
		List<BgypfwProductEntity> list = bgypfwProductService.queryList(query);
		HashMap<String,Object> resultMap = new HashMap<>();
		resultMap.put("topphoto", service.getTopphoto());
		resultMap.put("categoryList", categoryList);
		resultMap.put("newProductList", list);
		return R.ok(resultMap);
	}
	/**
	 * 查询商品类型
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findCategroyList")
	public R findCategroyList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		Integer cgrade = params.getInteger("cgrade");
		Integer pid = params.getInteger("pid");
		List<BgypfwCategoryEntity> categoryList = bgypfwCategoryService.queryList(params);
		return R.ok().put("categoryList", categoryList);
	}
	/**
	 * 查询商品
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findProductList")
	public R findProductList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		Integer categoryOneId = params.getInteger("categoryOneId");
		Integer categoryTwoId = params.getInteger("categoryTwoId");
		//只查询上架商品
		params.put("status", APPConstant.TYPE_BGYPFW_ONSALE);
		//查询列表数据
        Query query = new Query(params);
		List<BgypfwProductEntity> productList = bgypfwProductService.queryList(query);
		int total = bgypfwProductService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(productList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 查询商品详情
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findProductDetail")
	public R findProductDetail(HttpServletRequest req, String biz){
		JSONObject params = JSONObject.parseObject(biz);
		params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
		
		//参数
		Integer productId = params.getInteger("productId");
		Integer limit = params.getInteger("limit");
		
		//1、查询商品
		BgypfwProductEntity product = bgypfwProductService.queryObject(productId);
		
		//2、查询商品用户评论
		Query query = new Query(params);
		
		//默认三个
		if (limit==null)params.put("limit", 3);
		List<BgypfwCommentEntity> commentList = bgypfwCommentService.queryList(query);
		int total = bgypfwCommentService.queryTotal(query);
		
		//3、好评度查询
		Double rate = bgypfwCommentService.queryRateHighStars(query);
		
		//4、当前用户是否收藏点赞
		params.put("type", APPConstant.FAVOR_TYPE_BGYP);
		params.put("status", APPConstant.TYPE_STATUS_HAS);
		params.put("entityid", productId);
		Integer favorNum = tFavorService.queryTotal(params);
		
		//5、获取当前总剩余
		Integer remain = bgypfwSkuidService.queryProductRemain(params);
		HashMap<String,Object> resultMap = new HashMap<>();
		resultMap.put("product", product);
		resultMap.put("commentList", commentList);
		resultMap.put("commentTotal", total);
		resultMap.put("rateGood", rate);
		resultMap.put("favorNum", favorNum);
		
		//是否有货
		resultMap.put("productRemain", remain);
		
		return R.ok(resultMap);
	}
	/**
	 * 查询商品评价
	 * @param biz
	 * @return
	 */
    @RequestMapping("/findCommentList")
    public R findCommentList(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        JSONObject params = JSONObject.parseObject(biz);
        //接口参数
        Integer productId = params.getInteger("productId");
        //1好评2中评3差评
        Integer commentType = params.getInteger("commentType");
        if (ValidateUtil.isValid(commentType)) {
        	switch (commentType.intValue()) {
			case 1:
				params.put("minScore", APPConstant.TYPE_BGYPFW_GOOD_MINSCORE);
	            params.put("maxScore", 100);
				break;
			case 2:
				params.put("minScore", APPConstant.TYPE_BGYPFW_MIDDLE_MINSCORE);
	            params.put("maxScore", APPConstant.TYPE_BGYPFW_GOOD_MINSCORE);
				break;
			case 3:
				params.put("minScore", -1);
	            params.put("maxScore", APPConstant.TYPE_BGYPFW_MIDDLE_MINSCORE);
				break;
			default:
				break;
			}
		}
        //查询评论列表
        params.put("sidx", "c.createtime");
        params.put("order", "desc");
        Query query = new Query(params);
        List<BgypfwCommentEntity> commentList = bgypfwCommentService.queryList(query);
        //查询
        int total = bgypfwCommentService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(commentList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }
    /**
     * 查询评价人数
     * @param biz
     * @return
     */
    @RequestMapping("/findCommentRate")
    public R findCommentRate(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        JSONObject paramMap = JSONObject.parseObject(biz);
        //接口参数
        Integer productId = paramMap.getInteger("productId");
        //查询
        int total = bgypfwCommentService.queryTotal(paramMap);
        paramMap.put("minScore", -1);
        paramMap.put("maxScore", APPConstant.TYPE_BGYPFW_MIDDLE_MINSCORE);
        int badNum = bgypfwCommentService.queryStarsCountWithin(paramMap);
        paramMap.put("minScore", APPConstant.TYPE_BGYPFW_MIDDLE_MINSCORE);
        paramMap.put("maxScore", APPConstant.TYPE_BGYPFW_GOOD_MINSCORE);
        int middleNum = bgypfwCommentService.queryStarsCountWithin(paramMap);
        paramMap.put("minScore", APPConstant.TYPE_BGYPFW_GOOD_MINSCORE);
        paramMap.put("maxScore", 100);
        int goodNum = bgypfwCommentService.queryStarsCountWithin(paramMap);
        String badPersent = percent(badNum*1.0,total*1.0);
        String middlePersent = percent(middleNum*1.0,total*1.0);
        String goodPersent = percent(goodNum*1.0,total*1.0);
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("badNum", badNum);
        resultMap.put("middleNum", middleNum);
        resultMap.put("goodNum", goodNum);
        return R.ok(resultMap);
    }
	private static String percent(double dividend, double divisor) {
		double percent10 = dividend*1000/divisor;
		double percent = (int)percent10/10.0;
		String percentStr = String.valueOf(percent);
		return percentStr+"%";
	}
	
}
