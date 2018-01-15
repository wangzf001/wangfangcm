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
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.TYytcCommentEntity;
import com.lcworld.entity.TYytcMealEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.TFavorService;
import com.lcworld.service.TPraiseService;
import com.lcworld.service.TYytcCommentService;
import com.lcworld.service.TYytcMealService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
@RestController
@RequestMapping("appuser/tyytcmeal")
public class TYytcMealController {
    private Logger log = LoggerFactory.getLogger(TYytcMealController.class);
    @Autowired
    private TYytcMealService tYytcMealService;
    @Autowired
    private TYytcCommentService tYytcCommentService;
    @Autowired
    private TFavorService tFavorService;
    @Autowired
    private TPraiseService tPraiseService;
    public static void main(String[] args) {
        TYytcMealController yytc = new TYytcMealController();
        yytc.log.debug("我啦啦啦");
    }
    /**
     * 查询菜单列表
     * @param biz
     * @param sign
     * @return
     */
    @RequestMapping("/findMealList")
    public R findMealList(HttpServletRequest req,String sign){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //接口参数APP
        Integer timeType = params.getInteger("timeType");
        Integer mType = params.getInteger("type");
        String keyword = params.getString("keyword");
        Query query = new Query(params);
        List<TYytcMealEntity> mealList = tYytcMealService.queryList(query);
        int total = tYytcMealService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(mealList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }
    /**
     * 获取菜单信息
     * @param biz
     * @param sign
     * @return
     */
    @RequestMapping("/getMealInfo")
    public R getMealInfo(HttpServletRequest req,String sign){
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
        Integer mid = params.getInteger("mid");
        //1、获取菜单信息
        TYytcMealEntity meal = tYytcMealService.queryObject(mid);
        //2、用户评论
        List<TYytcCommentEntity> commentList = tYytcCommentService.queryList(params);
        //3、当前用户是否收藏点赞
        params.put("type", APPConstant.TYPE_YYTC);
        params.put("status", APPConstant.TYPE_STATUS_HAS);
        params.put("entityid", mid);
        Integer praiseNum = tPraiseService.queryTotal(params);
        Integer favorNum = tFavorService.queryTotal(params);
        //封装返回结果
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("mealInfo", meal);
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
        Integer mid = params.getInteger("mid");
        String content = params.getString("content");
        TYytcCommentEntity comment = new TYytcCommentEntity();
        comment.setContent(content);
        comment.setCreatetime(new Date());
        comment.setUid(params.getInteger("uid"));
        comment.setMId(mid);
        tYytcCommentService.save(comment);
        return R.ok();
    }
}
