package com.lcworld.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.entity.HealthyinfoEntity;
import com.lcworld.entity.InfoCommentEntity;
import com.lcworld.service.HealthyinfoService;
import com.lcworld.service.InfoCommentService;
import com.lcworld.utils.FastJSONUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;

@RestController
@RequestMapping("appuser/healthyinfo")
public class HealthyInfoController {
    private Logger log = LoggerFactory.getLogger(HealthyInfoController.class);
    @Autowired
    private HealthyinfoService healthyinfoService;
    @Autowired
    private InfoCommentService infoCommentService;
    
    @RequestMapping("/list")
    @ResponseBody
    @IgnoreSign
    @IgnoreToken
    public R list(String biz){
       R result = new R();
       JSONObject params = FastJSONUtils.getJSONObject(biz);
       params.put("sidx", "createtime");
       params.put("order", "desc");
       Query q = new Query(params);
       result.put("infolist", healthyinfoService.queryInfoList(q));
       return result;
    }
    
    @RequestMapping("/detail")
    @ResponseBody
    @IgnoreSign
    @IgnoreToken
    public R detail(String biz){
        R result = new R();
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        HealthyinfoEntity info = healthyinfoService.queryObject(params.getInteger("id"));
        return result.put("info", info);
    }
    
    @RequestMapping("/addcomment")
    @ResponseBody
    public R addcomment(HttpServletRequest request){
        String biz = request.getParameter("biz");
        log.debug(biz);
        InfoCommentEntity comment = FastJSONUtils.getObject(biz,InfoCommentEntity.class);
        comment.setCreatetime(new Date());
        comment.setUid(WebUtils.getUid(request));
        infoCommentService.save(comment);
        return R.ok();
      
    }
    
}
