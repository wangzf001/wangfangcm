package com.lcworld.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.TNoticeCommentEntity;
import com.lcworld.entity.TNoticeEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.TFavorService;
import com.lcworld.service.TNoticeCommentService;
import com.lcworld.service.TNoticeService;
import com.lcworld.service.TPraiseService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 公告
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
@RestController
@RequestMapping("appuser/tnotice")
public class TNoticeController {
    private Logger log = LoggerFactory.getLogger(TNoticeController.class);
    @Autowired
    private TNoticeService tNoticeService;
    @Autowired
    private TNoticeCommentService tNoticeCommentService;
    @Autowired
    private TFavorService tFavorService;
    @Autowired
    private TPraiseService tPraiseService;
    /**
     * 查询信息列表
     * @param biz
     * @param sign
     * @return
     */
    @RequestMapping("/findNoticeList")
    public R findNoticeList(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //接口参数APP
        Query query = new Query(params);
        List<TNoticeEntity> mealList = tNoticeService.queryList(query);
        int total = tNoticeService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(mealList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }
    /**
     * 获取公告信息
     * @param biz
     * @param sign
     * @return
     */
    @RequestMapping("/getNoticeInfo")
    public R getNoticeInfo(HttpServletRequest req,String biz){
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        params.put("auth", 1);
        //添加uid到params
        params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
        
        //查询列表数据
        //接口参数
        Integer nid = params.getInteger("nid");
        
        //1、获取菜单信息
        TNoticeEntity notice = tNoticeService.queryObject(nid);
        
        //2、用户评论
        List<TNoticeCommentEntity> commentList = tNoticeCommentService.queryList(params);
        
        //3、当前用户是否收藏点赞
        params.put("type", APPConstant.TYPE_XXGG);
        params.put("status", APPConstant.TYPE_STATUS_HAS);
        params.put("entityid", nid);
        Integer praiseNum = tPraiseService.queryTotal(params);
        
        params.put("type", APPConstant.FAVOR_TYPE_XXGG);
        Integer favorNum = tFavorService.queryTotal(params);
        //封装返回结果
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("noticeInfo", notice);
        resultMap.put("commentList", commentList);
        resultMap.put("favorNum", favorNum);
        resultMap.put("praiseNum", praiseNum);
        //预留静态化
        return R.ok(resultMap);
    }
    /**
     * 添加评论
     * @param biz
     * @return
     */
    @RequestMapping("/addComment")
    public R addComment(HttpServletRequest req){
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
        //查询列表数据
        //接口参数
        Integer nid = params.getInteger("nid");
        String content = params.getString("content");
        TNoticeCommentEntity comment = new TNoticeCommentEntity();
        comment.setContent(content);
        comment.setCreatetime(new Date());
        comment.setUid(params.getInteger("uid"));
        comment.setNoticeid(nid);
        tNoticeCommentService.save(comment);
        return R.ok();
    }
}
