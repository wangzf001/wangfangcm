package com.lcworld.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.JyfwComplaintEntity;
import com.lcworld.entity.JyfwComplaintagEntity;
import com.lcworld.entity.JyfwFeedbackMsgEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.JyfwComplaintService;
import com.lcworld.service.JyfwComplaintagService;
import com.lcworld.service.JyfwFeedbackMsgService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 建议服务
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:01:19
 */
@RestController
@RequestMapping("appuser/jyfwcomplaint")
public class JyfwComplaintController {
	private Logger log = LoggerFactory.getLogger(JyfwComplaintController.class);
	@Autowired
	private JyfwComplaintService jyfwComplaintService;
	@Autowired
	private JyfwComplaintagService jyfwComplaintagService;
	@Autowired
	private JyfwFeedbackMsgService jyfwFeedbackMsgService;
	/**
	 * 查询意见标签
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findComplanitTag")
	public R findComplanitTag(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		List<JyfwComplaintagEntity> tagList = jyfwComplaintagService.queryList(params);
		return R.ok().put("tagList", tagList);
	}
	/**
	 * 提交意见
	 * @param biz
	 * @return
	 */
	@RequestMapping("/commitComplanit")
	public R commitComplanit(HttpServletRequest req,@RequestParam("imgFile") MultipartFile[] imgFile) throws Exception{
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
		Integer anonymous = params.getInteger("anonymous");
		String complaintContent = params.getString("complaintContent");
		String suggestContent = params.getString("suggestContent");
		Integer tagid = params.getInteger("tagid");
		
		JyfwComplaintEntity complaintEntity = new JyfwComplaintEntity();
		complaintEntity.setAnonymous(anonymous);
		complaintEntity.setComplaintContent(complaintContent);
		complaintEntity.setCreatetime(new Date());
		complaintEntity.setSuggestContent(suggestContent);
		complaintEntity.setTagid(tagid);
		complaintEntity.setUid(params.getInteger("uid"));
		jyfwComplaintService.addComplaint(complaintEntity,imgFile);
		return R.ok();
	}
	/**
	 * 查询历史意见
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findComplanitList")
	public R findComplanitList(HttpServletRequest req){
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
		Query query = new Query(params);
		
		List<JyfwComplaintEntity> complaintList = jyfwComplaintService.queryList(query);
		int total = jyfwComplaintService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(complaintList, total, query.getLimit(), query.getPage());
		log.debug("result:"+pageUtil);
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 查询意见详情
	 * @param biz
	 * @return
	 */
	@RequestMapping("/getComplanitDetail")
	public R getComplanitDetail(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		Integer cid = params.getInteger("cid");
		JyfwComplaintEntity complaintEntity = jyfwComplaintService.queryObject(cid);
		return R.ok().put("complaint", complaintEntity);
	}
	/**
	 * 查询反馈列表
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findFeedbackList")
	public R findFeedbackList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
//		//添加uid到params
//		Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
//		if (ValidateUtil.isValid(uid)) {
//			params.put("uid", uid);
//		}else{
//			//return R.error(1,"未登录");
//		}
		//接口参数
		Integer cid = params.getInteger("cid");
		
		Query query = new Query(params);
		List<JyfwFeedbackMsgEntity> msgList = jyfwFeedbackMsgService.queryList(query);
		//设置为已读状态
		jyfwFeedbackMsgService.updateListRead(msgList);
		int total = jyfwFeedbackMsgService.queryTotal(query);
		PageUtils page0 = new PageUtils(msgList, total, query.getLimit(), query.getPage());
		if (query.getPage()==page0.getTotalPage()) {
			JyfwComplaintEntity complaintEntity = jyfwComplaintService.queryObject(cid);
			JyfwFeedbackMsgEntity msgLast = new JyfwFeedbackMsgEntity();
			msgLast.setContent(complaintEntity.getComplaintContent());
			msgLast.setImgs(complaintEntity.getImgs());
			msgList.add(msgLast);
		}
		PageUtils page = new PageUtils(msgList, total, query.getLimit(), query.getPage());
		log.debug("result:"+page);
		return R.ok().put("page", page);
	}
	/**
	 * 添加反馈
	 * @param req
	 * @return
	 */
	@RequestMapping("/addFeedback")
	public R addFeedback(HttpServletRequest req){
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
		String content = params.getString("content");
		Integer cid = params.getInteger("cid");
//		String utype = params.getString("utype");
		JyfwFeedbackMsgEntity msg = new JyfwFeedbackMsgEntity();
		msg.setComplaintId(cid);
		msg.setContent(content);
		msg.setCreatetime(new Date());
		msg.setReadstatus(0);
		msg.setUtype(APPConstant.TYPE_USER_USER);
		//如果是第一次反馈则为空，如果服务端已经有人反馈过了则serviceUid为当前反馈的服务端人员id
		Integer serviceUid = jyfwFeedbackMsgService.queryServiceUid(params);
		if (ValidateUtil.isValid(serviceUid)) {
			msg.setServiceuid(serviceUid);
		}
		msg.setServiceuid(serviceUid);
		msg.setUid(params.getInteger("uid"));
		jyfwFeedbackMsgService.save(msg);
		return R.ok();
	}
}
