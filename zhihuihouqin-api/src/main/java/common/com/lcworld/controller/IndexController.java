package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.dto.TdhdActivityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.IndexCarouselEntity;
import com.lcworld.entity.OrderViewEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TBxwxOrderEntity;
import com.lcworld.entity.TNoticeEntity;
import com.lcworld.entity.TdhdActivityEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.IndexCarouselService;
import com.lcworld.service.OrderViewService;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TBxwxOrderService;
import com.lcworld.service.TNoticeService;
import com.lcworld.service.TdhdActivityService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;

@RestController
@RequestMapping("appuser/index")
public class IndexController {
	private Logger log = LoggerFactory.getLogger(PayController.class);
    @Autowired
    private IndexCarouselService indexCarouselService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private TNoticeService tNoticeService;
    @Autowired
    private TBxwxOrderService tBxwxOrderService;
    @Autowired
    private TdhdActivityService tdhdActivityService;
    @Autowired
	private OrderViewService orderViewService;
    /**
     * 查询首页信息
     * @param biz
     * @return
     */
    @RequestMapping("")
    public R findUserWallet(HttpServletRequest req,String biz){
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        
        //添加uid到params
        params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
        
        //1、轮播图
        List<IndexCarouselEntity> imgList = indexCarouselService.queryList(params);
        
        //2、用户的服务
        List<ServiceEntity> serviceList = serviceService.queryList(params);
        
        Query query = new Query(params);
        
        //3、最新公告
        List<TNoticeEntity> noticeList = tNoticeService.queryList(query);
        
        //4、最新订单
        List<OrderViewEntity> bxwxOrderList =  orderViewService.queryList(query);
        
        //List<TBxwxOrderEntity> bxwxOrderList = tBxwxOrderService.queryList(query);
        //5、最新活动
        List<TdhdActivityDTO> activityList = tdhdActivityService.queryDTOList(query);
        
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("carouselList", imgList);
        resultMap.put("serviceList", serviceList);
        resultMap.put("noticeList", noticeList);
        resultMap.put("bxwxOrderList", bxwxOrderList);
        resultMap.put("activityList", activityList);
        
        return R.ok(resultMap);
    }

    /**
     * 关键词搜索子模块
     * @param biz
     * @return
     */
    @IgnoreSign
    @IgnoreToken
    @RequestMapping("/search")
    public R findService(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
      //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        }else{
            //return R.error(1,"未登录");
        }
        //用户的服务
        List<ServiceEntity> serviceList = serviceService.queryObjectByName(params);

        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("serviceList", serviceList);
        return R.ok(resultMap);
    }
}
