package com.lcworld.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.BgypfwApplyproductEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.BgypfwApplyproductService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 办公用品服务-申请商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
@RestController
@RequestMapping("appuser/bgypfwapplyproduct")
public class BgypfwApplyproductController {
    private Logger log = LoggerFactory.getLogger(BgypfwApplyproductController.class);
    @Autowired
    private BgypfwApplyproductService bgypfwApplyproductService;
    /**
     * 生成申请订单
     * @param biz
     * @return
     */
    @RequestMapping("/generateOrder")
    public R generateOrder(HttpServletRequest req){
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
        //接口参数
        String realname = params.getString("realname");
        String mobile = params.getString("mobile");
        String productName = params.getString("productName");
        Integer count = params.getInteger("count");
        String remark = params.getString("remark");
        BgypfwApplyproductEntity ap = new BgypfwApplyproductEntity();
        ap.setCreatetime(new Date());
        ap.setMobile(mobile);
        ap.setProductcount(count);
        ap.setProductname(productName);
        ap.setRemark(remark);
        ap.setUsername(realname);
        ap.setUid(params.getInteger("uid"));
        ap.setStatus(APPConstant.TYPE_BGYPFW_AP_STATUS_UNDERREVIEW);
        bgypfwApplyproductService.save(ap);
        log.debug("result:id="+ap.getId());
        return R.ok();
    }
    /**
     * 查询需求申请
     * @param biz
     * @return
     */
    @RequestMapping("/findApplyList")
    public R findApplyList(HttpServletRequest req){
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
        Query query = new Query(params);
        List<BgypfwApplyproductEntity> list = bgypfwApplyproductService.queryList(query);
        int total = bgypfwApplyproductService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
    }
}
