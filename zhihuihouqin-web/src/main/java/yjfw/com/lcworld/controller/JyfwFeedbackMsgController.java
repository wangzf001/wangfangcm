package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.consts.APPConstant;
import com.lcworld.entity.JyfwComplaintEntity;
import com.lcworld.entity.JyfwFeedbackMsgEntity;
import com.lcworld.service.JyfwComplaintService;
import com.lcworld.service.JyfwFeedbackMsgService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;
import com.lcworld.utils.ValidateUtil;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:01:18
 */
@RestController
@RequestMapping("jyfwfeedbackmsg")
public class JyfwFeedbackMsgController {
	@Autowired
	private JyfwFeedbackMsgService jyfwFeedbackMsgService;
	@Autowired
	private JyfwComplaintService jyfwComplaintService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JyfwFeedbackMsgEntity> jyfwFeedbackMsgList = jyfwFeedbackMsgService.queryList(query);
		int total = jyfwFeedbackMsgService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(jyfwFeedbackMsgList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		JyfwFeedbackMsgEntity jyfwFeedbackMsg = jyfwFeedbackMsgService.queryObject(id);
		
		return R.ok().put("jyfwFeedbackMsg", jyfwFeedbackMsg);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody JyfwFeedbackMsgEntity jyfwFeedbackMsg){
		Long userId = ShiroUtils.getUserId();
		if (ValidateUtil.isValid(userId)) {
			JyfwComplaintEntity complaint = jyfwComplaintService.queryObject(jyfwFeedbackMsg.getComplaintId());
			jyfwFeedbackMsg.setUtype(APPConstant.TYPE_USER_SERVICE);
			jyfwFeedbackMsg.setUid(complaint.getUid());
			jyfwFeedbackMsg.setServiceuid(userId.intValue());
			//必须验证用户端发来反馈的最后一条的serviceuid为当前用户的uid或者为null才能保存，否则不能保存
			HashMap<String,Object> params = new HashMap<>();
			params.put("cid", jyfwFeedbackMsg.getComplaintId());
			//加锁为了防止多个后台管理员同时进行反馈
			synchronized (this) {
				Integer serviceuid = jyfwFeedbackMsgService.queryServiceUid(params);
				if (!ValidateUtil.isValid(serviceuid)||serviceuid.intValue()==userId.intValue()) {
					jyfwFeedbackMsgService.save(jyfwFeedbackMsg);
				}else{
					return R.error(1,"已经 被修改");
				}
			}
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody JyfwFeedbackMsgEntity jyfwFeedbackMsg){
		jyfwFeedbackMsgService.update(jyfwFeedbackMsg);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		jyfwFeedbackMsgService.deleteBatch(ids);
		return R.ok();
	}
	
}
