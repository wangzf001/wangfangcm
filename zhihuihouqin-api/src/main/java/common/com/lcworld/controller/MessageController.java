package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.entity.TbMessageOrderUserEntity;
import com.lcworld.service.TbMessageOrderUserService;
import com.lcworld.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.MessageEntity;
import com.lcworld.entity.PayinfoEntity;
import com.lcworld.entity.TalkVo;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.MessageService;
import com.lcworld.service.TalkService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-20 14:54:20
 */
@RestController
@RequestMapping("appuser/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private TalkService talkService;

	@Autowired
	private TbMessageOrderUserService messageOrderUserService;

	/**
	 * 获取消息列表
	 * @param req
	 * @return
	 */
	@PostMapping("/index")
	public R index(HttpServletRequest req){
		//查询列表数据
		Map<String,Object> params = new HashMap<>();
		//添加uid到params
		Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
		if (ValidateUtil.isValid(uid)) {
			params.put("uid", uid);
		}else{
			//return R.error(1,"未登录");
		}
		Query query = new Query(params);
		Map<String,Object> result = new HashMap<>();
		result.put("sysMessage",getNewsTotal(1,query));
		result.put("commMessage",getNewsTotal(2,query));
		result.put("payMessage",getNewsTotal(3,query));
		result.put("authMessage",getNewsTotal(4,query));
		result.put("activityMessage",getNewsTotal(5,query));
		result.put("orderMessage",getNewsTotal(6,query));
		result.put("feedbackMessage",getNewsTotal(7,query));
		result.put("conversation",null);
		return R.ok().put("data", result);
	}

	/**
	 * 获取消息对象
	 * @param messageType
	 * @param query
	 * @return
	 */
	private JSONObject getNewsTotal(Integer messageType,Query query){
		JSONObject resultObject = new JSONObject();
		query.put("messagetype",messageType);
		resultObject.put("messagetype",messageType);
		if(messageType == 3){
			resultObject.put("total",1);
		}else if (messageType == 6){
			resultObject.put("total",messageOrderUserService.queryTotal(query));
		}else {
			query.put("messagetype",messageType);
			resultObject.put("total",messageService.queryTotal(query));
		}
		return resultObject;
	}
	/**
     * 获取消息列表
     * @param req
     * @return
     */
	@IgnoreSign
	@IgnoreToken
    @PostMapping("/messageList")
    public R messageList(HttpServletRequest req){
        String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        //Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(52)) {
            params.put("uid", 52);
        }else{
            //return R.error(1,"未登录");
        }
        //参数
        Integer messagetype = params.getInteger("messagetype");
        Query query = new Query(params);
        if (ValidateUtil.isValid(messagetype)&&messagetype.intValue()==3) {
        	List<PayinfoEntity> payInfoList = null;
			return R.ok().put("data", payInfoList);
		}else if (ValidateUtil.isValid(messagetype)&&messagetype.intValue()==6) {
			List<TbMessageOrderUserEntity> payInfoList = messageOrderUserService.queryList(query);
			return R.ok().put("data", payInfoList);
		}{
			//查询列表数据
			List<MessageEntity> messageList = messageService.queryList(query);
			return R.ok().put("data", messageList);
		}
    }
    /**
     * 获取消息页
     * @param req
     * @return
     */
    @RequestMapping("/messagePage")
    public R messagePage(HttpServletRequest req,String biz){
    	//查询列表数据
    	JSONObject params = JSONObject.parseObject(biz);
    	//添加uid到params
		params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
		
    	//查询列表数据
    	List<MessageEntity> messageList = messageService.queryMessagePage(params);
    	List<TalkVo> talkList = talkService.queryTalkPage(params);
    	return R.ok().put("messageList", messageList).put("talkList", talkList);
    }
    /**
     * 获取订单详情
     * @param req
     * @return
     */
    @RequestMapping("/orderDetail")
    public R orderDetail(HttpServletRequest req){
    	String biz = req.getParameter("biz");
    	//查询列表数据
    	JSONObject params = JSONObject.parseObject(biz);
    	//添加uid到params
    	Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
    	if (ValidateUtil.isValid(uid)) {
    		params.put("uid", uid);
    	}else{
    		//return R.error(1,"未登录");
    	}
    	Integer id = params.getInteger("id");
    	//查询列表数据
    	MessageEntity messageDetail = messageService.queryMessageDetail(params);
    	//改变已读状态
    	if (ValidateUtil.isValid(messageDetail)&&messageDetail.getRead()==0) {
    		params.put("read", 1);
    		messageService.updateReadStatus(params);
		}
    	return R.ok().put("messageDetail", messageDetail);
    }
    /**
     * 删除消息
     * @param req
     * @return
     */
    @RequestMapping("/deleteMessage")
    public R deleteMessage(HttpServletRequest req){
    	String biz = req.getParameter("biz");
    	//查询列表数据
    	JSONObject params = JSONObject.parseObject(biz);
    	//添加uid到params
    	Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
    	if (ValidateUtil.isValid(uid)) {
    		params.put("uid", uid);
    	}else{
    		//return R.error(1,"未登录");
    	}
    	Integer id = params.getInteger("id");
    	messageService.delete(id);
    	return R.ok();
    }

	/**
	 * 读消息
	 * @param biz={autoId}
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/readMessage")
	public R readMessage(String biz, HttpServletRequest request){
		JSONObject p = JSONObject.parseObject(biz);
		Assert.isNull(p, "缺少参数---biz");
		Assert.isBlank(p.getString("autoId"), "缺少参数---autoId");
		TbMessageOrderUserEntity message = messageOrderUserService.queryObject(Long.valueOf(p.getString("autoId")));
		message.setIsRead(1);
		messageOrderUserService.update(message);
		return R.ok();
	}
}
