package com.lcworld.controller;

import java.text.ParseException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.FavorConst;
import com.lcworld.consts.RedisConst;
import com.lcworld.dto.ZjzzExpertDTO;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TBxwxCommentEntity;
import com.lcworld.entity.TBxwxNoticeEntity;
import com.lcworld.entity.TBxwxOrderEntity;
import com.lcworld.entity.YlfwYyghCommentEntity;
import com.lcworld.entity.YlfwYyghDoctorEntity;
import com.lcworld.entity.YlfwYyghDoctorScheduleEntity;
import com.lcworld.entity.YlfwYyghOrderEntity;
import com.lcworld.entity.YlfwZjzzCommentEntity;
import com.lcworld.entity.YlfwZjzzDoctorScheduleEntity;
import com.lcworld.entity.YlfwZjzzNoticeEntity;
import com.lcworld.entity.YlfwZjzzOrderEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.HealthyinfoService;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TFavorService;
import com.lcworld.service.YlfwYyghDoctorScheduleService;
import com.lcworld.service.YlfwYyghCommentService;
import com.lcworld.service.YlfwYyghDoctorService;
import com.lcworld.service.YlfwYyghOrderService;
import com.lcworld.service.YlfwZjzzCommentService;
import com.lcworld.service.YlfwZjzzDoctorScheduleService;
import com.lcworld.service.YlfwZjzzExpertService;
import com.lcworld.service.YlfwZjzzNoticeService;
import com.lcworld.service.YlfwZjzzOrderService;
import com.lcworld.service.YlfwZjzzPeriodService;
import com.lcworld.service.impl.YlfwZjzzDoctorScheduleServiceImpl;
import com.lcworld.test.queue.OrderQueueSingleton;
import com.lcworld.test.queue.RemindQueue;
import com.lcworld.test.queue.vo.RemindOrderVo;
import com.lcworld.utils.FastJSONUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;

@RestController
@RequestMapping("appuser/ylfw")
public class YlfwController {
	private Logger log = LoggerFactory.getLogger(YlfwController.class);
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private HealthyinfoService healthyinfoService;
	@Autowired
	private YlfwYyghDoctorService ylfwYyghDoctorService;
	@Autowired
	private YlfwYyghDoctorScheduleService ylfwYyghDoctorScheduleService;
	@Autowired
	private TFavorService tFavorService;
	@Autowired
	private YlfwYyghCommentService ylfwYyghCommentService;
	@Autowired
	private YlfwYyghOrderService ylfwYyghOrderService;
	@Autowired
	private YlfwZjzzCommentService ylfwZjzzCommentService;
	@Autowired
	private YlfwZjzzDoctorScheduleService ylfwZjzzDoctorScheduleService;
	@Autowired
	private YlfwZjzzExpertService ylfwZjzzExpertService;
	@Autowired
	private YlfwZjzzNoticeService ylfwZjzzNoticeService;
	@Autowired
	private YlfwZjzzOrderService ylfwZjzzOrderService;
	@Autowired
	private YlfwZjzzPeriodService ylfwZjzzPeriodService;

	@RequestMapping("/ylfwindex")
	@ResponseBody
	public R ylfwindex(String biz) {
		log.debug(biz);
		R result = new R();
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		params.put("sidx", "createtime");
		params.put("order", "desc");
		params.put("limit", 5);
		Query q = new Query(params);
		result.put("infolist", healthyinfoService.queryInfoList(q));

		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_JKZX);
		result.put("picture", service.getTopphoto() == null ? "" : service.getTopphoto());
		return result;
	}

	@RequestMapping("/doctorlist")
	@ResponseBody
	@IgnoreSign
	public R doctorlist(String biz) {
		log.debug(biz);
		R result = new R();
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		Query q = new Query(params);
		result.put("doctorlist", ylfwYyghDoctorService.queryDoctorList(q));
		return result;
	}

	/**
	 * 预约时间列表
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/schedules")
	@ResponseBody
	public R schedules(HttpServletRequest request) {

		R result = new R();
		String biz = request.getParameter("biz");
		log.debug(biz);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		Integer doctorid = params.getInteger("doctorid");
		Integer uid = WebUtils.getUid(request);
		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_JKZX);
		result.put("schedules", ylfwYyghDoctorScheduleService.queryDocSchedules(params));
		YlfwYyghDoctorEntity doctor = ylfwYyghDoctorService.queryObject(doctorid);
		result.put("brief", doctor.getBrief());
		result.put("totalcommentcount", ylfwYyghCommentService.queryTotal(params));
		result.put("status", tFavorService.queryFavorStatus(uid, FavorConst.FAVORTYPE_DOCTOR, doctorid));
		result.put("ghprice", service.getGhprice() == null ? "" : service.getGhprice());
		return result;
	}

	/**
	 * 下单
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/addOrder")
	@ResponseBody
	public R addOrder(HttpServletRequest request) {
		String biz = request.getParameter("biz");
		log.debug(biz);
		R result = new R();
		Integer uid = WebUtils.getUid(request);
		YlfwYyghOrderEntity order = FastJSONUtils.getObject(biz, YlfwYyghOrderEntity.class);
		order.setUid(uid);
		YlfwYyghDoctorScheduleEntity schedule = ylfwYyghDoctorScheduleService.queryObject(order.getScheduleid());
		if (schedule == null || (schedule != null && schedule.getStatus().intValue() == 1)) {
			result = R.error(1104, "该时间段已被预约,请选择其他时间段");
		}else{
			order = ylfwYyghOrderService.saveorder(order);
			result.put("orderid", order.getId());
			result.put("ordertype", APPConstant.TYPE_YLFW);
			result.put("price", order.getGhprice());
		}
		return result;
	}

	/**
	 * 取消订单
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/cancelorder")
	@ResponseBody
	public R cancelorder(String biz) {
		log.debug(biz);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		YlfwYyghOrderEntity order = new YlfwYyghOrderEntity();
		order.setId(params.getInteger("orderid"));
		order.setCancelreason(params.getString("cancelreason"));
		order.setStatus(5);
		//创建时间不能为空,取消时设置为取消的时间
		order.setCreatetime(new Date());
		ylfwYyghOrderService.update(order);
		return R.ok();
	}

	/**
	 * 获取订单列表
	 * 
	 * @param biz
	 * @return
	 */
	@IgnoreSign
	@IgnoreToken
	@RequestMapping("/orderlist")
	@ResponseBody
	public R orderlist(HttpServletRequest request) {
		R result = new R();
		String biz = request.getParameter("biz");
		log.debug(biz);
		//Integer uid = WebUtils.getUid(request);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		params.put("statuslist", addTypeParams(params));
		params.put("uid", 44);
		Query q = new Query(params);
		result.put("orderlist", ylfwYyghOrderService.queryOrderlist(q));
		return result;
	}

	/**
	 * 获取订单详情
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/orderdetail")
	@ResponseBody
	public R orderlist(String biz) {
		log.debug(biz);
		R result = new R();
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		YlfwYyghOrderEntity order = new YlfwYyghOrderEntity();
		order.setId(params.getInteger("orderid"));
		order.setChanges(0);
		ylfwYyghOrderService.update(order);
		result.put("detail", ylfwYyghOrderService.queryOrderdetail(params));
		return result;
	}

	private List<Integer> addTypeParams(JSONObject params) {
		int type = params.getInteger("type").intValue();
		List<Integer> statuslist = new ArrayList<Integer>();
		switch (type) {
		case 3:
			statuslist.add(3);
			statuslist.add(4);
			break;
		default:
			statuslist.add(type);
			break;
		}
		return statuslist;

	}

	/**
	 * 获取评论
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/getcomment")
	@ResponseBody
	public R getcomment(String biz) {
		log.debug(biz);
		R result = new R();
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		params.put("sidx", "createtime");
		params.put("order", "desc");
		Query q = new Query(params);
		result.put("commentlist", ylfwYyghCommentService.queryCommentList(q));
		result.put("totalcount", ylfwYyghCommentService.queryTotal(params));
		return result;
	}

	/**
	 * 添加评论
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/addcomment")
	@ResponseBody
	public R addcomment(@RequestParam MultipartFile[] files, HttpServletRequest request) {
		String biz = request.getParameter("biz");
		log.debug(biz);
		YlfwYyghCommentEntity comment = FastJSONUtils.getObject(biz, YlfwYyghCommentEntity.class);
		YlfwYyghOrderEntity order = ylfwYyghOrderService.queryObject(comment.getOrderid());
		if (order.getStatus().intValue() == 4) {
			JSONObject obj = WebUtils.uploadFiles(files, request, RedisConst.USER_YLFW_YYGH_ORDERIMG_PRE,
					RedisConst.UPLOAD_IMG_FILTER);
			if (0 == obj.getIntValue("errorCode")) {
				comment.setImgs(obj.getString("data"));
			}
			comment.setCreatetime(new Date());
			comment.setDoctorid(order.getDoctorid());
			comment.setUid(order.getUid());
			ylfwYyghCommentService.savecomment(comment, order);
			return R.ok();
		} else if (order.getStatus() == 3) {
			return R.error(1103, "该订单已进行过评论,不可重复评论");
		} else {
			return R.error(1102, "该订单还没有完成，不能进行评价");
		}
	}

	//////////////////////////////// expert///////////////////////////////////

	/**
	 * 专家列表
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/expertdetail")
	@ResponseBody
	public R expertdetail(String biz) {
		log.debug(biz);
		R result = new R();
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		Query q = new Query(params);
		List<ZjzzExpertDTO> expertlist = ylfwZjzzExpertService.queryDoctorList(q);
		if (ValidateUtil.isValid(expertlist)) {
			result.put("expert", expertlist.get(0));
			params.put("doctorid", expertlist.get(0).getId());
			result.put("schedules", ylfwZjzzDoctorScheduleService.queryDocSchedules(params));
			result.put("totalcommentcount", ylfwZjzzCommentService.queryTotal(params));
			result.put("commentlist", ylfwZjzzCommentService.queryCommentList(q));
		} else {
			throw new ZHHQException(1106, "没有可约的医生");
		}
		return result;
	}

	/**
	 * 获取评论
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/getexpertcomment")
	@ResponseBody
	public R getexpertcomment(String biz) {
		log.debug(biz);
		R result = new R();
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		params.put("sidx", "createtime");
		params.put("order", "desc");
		Query q = new Query(params);
		result.put("commentlist", ylfwZjzzCommentService.queryCommentList(q));
		result.put("totalcount", ylfwZjzzCommentService.queryTotal(params));
		return result;
	}

	/**
	 * 添加评论
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/addexpertcomment")
	@ResponseBody
	public R addexpertcomment(@RequestParam MultipartFile[] files, HttpServletRequest request) {
		String biz = request.getParameter("biz");
		log.debug(biz);
		YlfwZjzzCommentEntity comment = FastJSONUtils.getObject(biz, YlfwZjzzCommentEntity.class);
		YlfwZjzzOrderEntity order = ylfwZjzzOrderService.queryObject(comment.getOrderid());
		if (order.getStatus().intValue() == 4) {
			JSONObject obj = WebUtils.uploadFiles(files, request, RedisConst.USER_YLFW_YYGH_ORDERIMG_PRE,
					RedisConst.UPLOAD_IMG_FILTER);
			if (0 == obj.getIntValue("errorCode")) {
				comment.setImgs(obj.getString("data"));
			}
			comment.setCreatetime(new Date());
			comment.setDoctorid(order.getDoctorid());
			comment.setUid(order.getUid());
			ylfwZjzzCommentService.savecomment(comment, order);
			return R.ok();
		} else if (order.getStatus() == 3) {
			return R.error(1103, "该订单已进行过评论,不可重复评论");
		} else {
			return R.error(1102, "该订单还没有完成，不能进行评价");
		}
	}

	/**
	 * 获取消息
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("expertnoticelist")
	@ResponseBody
	public R noticelist(HttpServletRequest request) {
		R result = new R();
		String biz = request.getParameter("biz");
		log.debug(biz);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		params.put("uid", WebUtils.getUid(request));
		params.put("sidx", "createtime");
		params.put("order", "asc");
		Query q = new Query(params);
		List<YlfwZjzzNoticeEntity> list = ylfwZjzzNoticeService.queryNoticeList(q);
		Collections.reverse(list);
		result.put("noticelist", list);
		result.put("total", ylfwZjzzNoticeService.queryNoticeListTotal(q));
		return result;
	}

	/**
	 * 添加消息
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("addexpertnotice")
	@ResponseBody
	public R addnotice(HttpServletRequest request) {
		String biz = request.getParameter("biz");
		log.debug(biz);
		YlfwZjzzNoticeEntity notice = FastJSONUtils.getObject(biz, YlfwZjzzNoticeEntity.class);
		notice.setCreatetime(new Date());
		notice.setGetreadstatus(0);
		notice.setGettype(2);
		notice.setUtype(1);
		notice.setPostid(WebUtils.getUid(request));
		ylfwZjzzNoticeService.save(notice);
		return R.ok();
	}

	/**
	 * 下单
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/addexpertOrder")
	@ResponseBody
	public R addexpertOrder(HttpServletRequest request) {
		String biz = request.getParameter("biz");
		log.debug(biz);
		R result = new R();
		Integer uid = WebUtils.getUid(request);
		YlfwZjzzOrderEntity order = FastJSONUtils.getObject(biz, YlfwZjzzOrderEntity.class);
		order.setUid(uid);
		YlfwZjzzDoctorScheduleEntity schedule = ylfwZjzzDoctorScheduleService.queryObject(order.getScheduleid());
		JSONObject params = new JSONObject();
        params.put("uid", uid);
        params.put("doctorid", order.getDoctorid());
        params.put("notstatus", 3);
        List<YlfwZjzzOrderEntity> olist = ylfwZjzzOrderService.queryList(params);
		if(olist != null && olist.size() > 0){
		    result = R.error(1120, "您已预约过该专家，不可重复预约");
		}else 
        if (schedule == null || (schedule != null && schedule.getStatus().intValue() == 1)) {
			result = R.error(1104, "该时间段已被预约或取消,请选择其他时间段");
		} else{
			try {
				order = ylfwZjzzOrderService.saveorder(order);
			} catch (ParseException e) {
				return R.error();
			}
			result.put("orderid", order.getId());
			result.put("ordertype", APPConstant.TYPE_YLFW_ZJZZ);
		}
		return result;
	}

	/**
	 * 获取订单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/expertorderlist")
	@ResponseBody
	public R expertorderlist(HttpServletRequest request) {
		R result = new R();
		String biz = request.getParameter("biz");
		log.debug(biz);
		Integer uid = WebUtils.getUid(request);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		params.put("statuslist", addTypeParams(params));
		params.put("uid", uid);
		Query q = new Query(params);
		result.put("orderlist", ylfwZjzzOrderService.queryOrderlist(q));
		result.put("serviceInfo", serviceService.queryService(APPConstant.TYPE_YLFW_ZJZZ));
		return result;
	}

	/**
	 * 获取订单详情
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/expertorderdetail")
	@ResponseBody
	public R expertorderdetail(String biz) {
		log.debug(biz);
		R result = new R();
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		YlfwZjzzOrderEntity order = new YlfwZjzzOrderEntity();
		order.setId(params.getInteger("orderid"));
		order.setChanges(0);
		ylfwZjzzOrderService.update(order);
		result.put("detail", ylfwZjzzOrderService.queryOrderdetail(params));
		return result;
	}

	/**
	 * 取消订单
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/cancelexpertorder")
	@ResponseBody
	public R cancelexpertorder(String biz) {
		log.debug(biz);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		YlfwZjzzOrderEntity order = new YlfwZjzzOrderEntity();
		order.setId(params.getInteger("orderid"));
		order.setCancelreason(params.getString("cancelreason"));
		order.setStatus(5);
		order.setChanges(1);
		
		ylfwZjzzOrderService.update(order);
		return R.ok();
	}

	/**
	 * 删除订单
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/delexpertorder")
	@ResponseBody
	public R delexpertorder(String biz) {
		log.debug(biz);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		YlfwZjzzOrderEntity order = new YlfwZjzzOrderEntity();
		order.setId(params.getInteger("orderid"));
		order.setChanges(0);
		order.setIsdel(1);
		ylfwZjzzOrderService.update(order);
		return R.ok();
	}

}
