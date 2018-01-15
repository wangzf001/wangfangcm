package com.lcworld.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lcworld.service.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.FavorConst;
import com.lcworld.consts.RedisConst;
import com.lcworld.entity.NationEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TUserEntity;
import com.lcworld.entity.TdhdActivityEntity;
import com.lcworld.entity.TdhdActivitysignEntity;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.FastJSONUtils;
import com.lcworld.utils.OSSConstantKey;
import com.lcworld.utils.OSSUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;

@RestController
@RequestMapping("appuser/tdhdfw")
public class TdhdController {
    private Logger log = LoggerFactory.getLogger(TdhdController.class);
    @Autowired
    private TdhdActivityService tdhdActivityService;
    @Autowired
    private TdhdActivitysignService tdhdActivitysignService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private TUserService tUserService;
    @Autowired
    private NationService nationService;
    @Autowired
    private TFavorService tFavorService;

    @IgnoreSign
    @IgnoreToken
    @PostMapping("/index")
    public R index(String biz){
        log.debug(biz);
        R result = new R();
        ServiceEntity service = serviceService.queryService(APPConstant.TYPE_TDHD);
        result.put("activitylist", tdhdActivityService.queryActivityList(new JSONObject()));
        result.put("picture", service.getTopphoto()==null?"":service.getTopphoto());
        return result;
    }

    @RequestMapping("/detail")
    @ResponseBody
    public R detail(HttpServletRequest request,String biz){
        R result = new R();
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        
        Integer activityid = params.getInteger("activityid");
        Integer uid = WebUtils.getUid(request);
        params.put("activityid", activityid);
        
        List<Map<String, Object>> list;
        result.put("favorStatus", tFavorService.queryFavorStatus(uid, FavorConst.FAVORTYPE_ACTIVITY, activityid));
        result.put("memberlist", list = tdhdActivitysignService.queryuserList1(params));
        result.put("membersize", ValidateUtil.isValid(list)?list.size():0);
        
        return result;
    }
    
    @RequestMapping("/sign")
    @ResponseBody
    public R sign(HttpServletRequest request){
        String biz = request.getParameter("biz");
        log.debug(biz);
        R result = new R();
        Integer uid = WebUtils.getUid(request);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        TdhdActivitysignEntity sign = new TdhdActivitysignEntity();
        Integer activityid;
        sign.setAId(activityid = params.getInteger("activityid"));
        sign.setUserId(uid);
        TdhdActivityEntity activity = tdhdActivityService.queryObject(activityid);

        if(activity.getaTotal() <= activity.getaSignCount()){
            result= R.error(1301, "该活动报名已满 ，报名失败");
        }else{
            TdhdActivitysignEntity t_sign = tdhdActivitysignService.queryExistMember(sign);
            if(t_sign != null){
                result= R.error(1300, "您已报过该活动 ，不可重复报名");
            }else{
                TUserEntity user = tUserService.queryObject(uid);
                if(user != null){
                    sign.setAsAvatar(user.getPhoto());
                    if(!ValidateUtil.isValid(sign.getAsMebile())){
                        sign.setAsMebile(user.getMobile());
                    }
                    if(!ValidateUtil.isValid(sign.getAsUsername())){
                        sign.setAsUsername(user.getRealname());
                    }
                }
                tdhdActivitysignService.saveSign(sign,activity);
            }
            
        }
        return result;
    }
    
    
    @RequestMapping("/pubactivity")
    @ResponseBody
    public R pubactivity(@RequestParam("photos") MultipartFile[] photos,@RequestParam("imgs") MultipartFile[] imgs,HttpServletRequest request) throws IOException, Exception{
        String biz = request.getParameter("biz");
        log.debug(biz);
        R result = new R();
        Integer uid = WebUtils.getUid(request);
        
        TdhdActivityEntity activity = FastJSONUtils.getObject(biz, TdhdActivityEntity.class);
        
        if(photos != null && photos.length > 0){
        	
        	/*StringBuilder sb = new StringBuilder();
        	for(MultipartFile file:photos){
                String fileName = file.getOriginalFilename();// 文件原名称
                String filePath = DateUtils.format(new Date())+"/"+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."), fileName.length());
                OSSUtils.uploadFile(filePath, file.getInputStream());
                String imgUrl = OSSConstantKey.OSS_BASE_URL+filePath;
                sb.append(",").append(imgUrl);
        	}
        	if(!StringUtils.isBlank(sb.toString())){
        		activity.setaPhoto(sb.toString().substring(1));
        	}*/
        	
            JSONObject obj = WebUtils.uploadFiles(photos, request, RedisConst.REDIS_TDHD_IMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
            if (0 == obj.getIntValue("errorCode")) {
                activity.setaPhoto(obj.getString("data"));
            }
        }
        if(imgs != null && imgs.length > 0){
        	/*StringBuilder sb = new StringBuilder();
        	for(MultipartFile file:imgs){
                String fileName = file.getOriginalFilename();// 文件原名称
                String filePath = DateUtils.format(new Date())+"/"+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."), fileName.length());
                OSSUtils.uploadFile(filePath, file.getInputStream());
                String imgUrl = OSSConstantKey.OSS_BASE_URL+filePath;
                sb.append(",").append(imgUrl);
        	}
        	if(!StringUtils.isBlank(sb.toString())){
        		activity.setaImg(sb.toString().substring(1));
        	}*/
        	
            JSONObject obj = WebUtils.uploadFiles(imgs, request, RedisConst.REDIS_TDHD_IMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
            if (0 == obj.getIntValue("errorCode")) {
                activity.setaImg(obj.getString("data"));
            }
        }
        
        activity.setaUserId(uid);
        activity.setaCreateTime(new Date());
        activity.setaStatus(1);
        activity.setaIsChecked(0);
        activity.setaSignCount(0);
        tdhdActivityService.save(activity);
        return result;
    }
    
    @RequestMapping("/memberdetail")
    @ResponseBody
    public R signmemberlist(String biz){
        log.debug(biz);
        R result = new R();
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        Integer signmemberid = params.getInteger("signmemberid");
        TdhdActivitysignEntity signuser = tdhdActivitysignService.queryObject(signmemberid);
        TUserEntity user = tUserService.queryObject(signuser.getUserId());
        if(user == null){
            return R.error(1800, "用户不存在");
        }
        String nationStr = "";
        if(ValidateUtil.isValid(user.getNation())){
            NationEntity nation = nationService.queryObject(user.getNation());
            nationStr = nation.getName();
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username", signuser.getAsUsername());
        map.put("mobile", signuser.getAsMebile());
        map.put("nation", nationStr);
        result.putAll(map);
        return result;
    }

   
}
