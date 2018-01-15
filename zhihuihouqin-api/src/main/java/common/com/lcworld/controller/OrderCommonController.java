package com.lcworld.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.factory.OrderServiceFactory;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.IOrderService;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.vo.CommentVo;

@RestController
@RequestMapping("appuser/orderCommon")
public class OrderCommonController {
private Logger log = LoggerFactory.getLogger(CommonController.class);
    
	@Autowired
	private OrderServiceFactory orderServiceFactory;
	
    @RequestMapping("orderCancel")
    public R orderCancel(HttpServletRequest req){
    	String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		
		JSONObject params = JSONObject.parseObject(biz);
		//添加uid到params
    	Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
		params.put("uid", uid);
		
    	Integer type = params.getInteger("serviceType");
    	IOrderService service = orderServiceFactory.getService(type);
    	if (ValidateUtil.isValid(service)) {
    		return service.cancelOrder(params.getInteger("oid"),params.getString("reasoncontent"));
		}else{
			//服务不存在
			return R.error(1,"服务不存在");
		}
    }
    @RequestMapping("orderDelete")
    public R orderDelete(HttpServletRequest req){
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
    	Integer type = params.getInteger("serviceType");
    	IOrderService service = orderServiceFactory.getService(type);
    	if (ValidateUtil.isValid(service)) {
    		service.deleteOrder(params.getInteger("oid"));
		}else{
			//服务不存在
			return R.error(1,"服务不存在");
		}
    	return R.ok();
    }
    
    @RequestMapping("finishOrder")
    public R finishOrder(HttpServletRequest req){
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
        Integer type = params.getInteger("serviceType");
        IOrderService service = orderServiceFactory.getService(type);
        if (ValidateUtil.isValid(service)) {
            service.finishOrder(params.getInteger("oid"));
        }else{
            //服务不存在
            return R.error(1,"服务不存在");
        }
        return R.ok();
    }
    @RequestMapping("addComment")
    public R addComment(HttpServletRequest req,@RequestParam("files") MultipartFile[] files,String biz){
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//添加uid到params
    	params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
    	
    	CommentVo comment = JSONObject.parseObject(biz, CommentVo.class);
    	comment.setUid(params.getInteger("uid"));
    	
    	Integer type = params.getInteger("serviceType");
    	comment.setType(String.valueOf(type));
    	
    	IOrderService service = orderServiceFactory.getService(type);
    	if (ValidateUtil.isValid(service)) {
    		return service.addComment(comment,files);
		}else{
			//服务不存在
			return R.error(1,"服务不存在");
		}
    }
    
    
    
}
