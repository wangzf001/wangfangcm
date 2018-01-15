package com.lcworld.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.lcworld.annotation.LoginUser;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.FavorConst;
import com.lcworld.consts.RedisConst;
import com.lcworld.dto.LffwOrderDTO;
import com.lcworld.entity.LffwBarberEntity;
import com.lcworld.entity.LffwBarberScheduleEntity;
import com.lcworld.entity.LffwBarberWorksEntity;
import com.lcworld.entity.LffwCommentEntity;
import com.lcworld.entity.LffwNoticeEntity;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.entity.LffwPeriodEntity;
import com.lcworld.entity.LffwVoucherEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TUserEntity;
import com.lcworld.service.LffwBarberScheduleService;
import com.lcworld.service.LffwBarberService;
import com.lcworld.service.LffwBarberWorksService;
import com.lcworld.service.LffwCommentService;
import com.lcworld.service.LffwNoticeService;
import com.lcworld.service.LffwOrderService;
import com.lcworld.service.LffwOrderdetailService;
import com.lcworld.service.LffwPeriodService;
import com.lcworld.service.LffwPeriodtypeService;
import com.lcworld.service.LffwServiceitemtypeService;
import com.lcworld.service.LffwVoucherService;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TFavorService;
import com.lcworld.utils.FastJSONUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.LffwOrderVO;

@RestController
@RequestMapping("appuser/lffw")
public class LffwController {
	private Logger log = LoggerFactory.getLogger(LffwController.class);
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private LffwBarberService lffwBarberService;
	@Autowired
	private LffwBarberWorksService lffwBarberWorksService;
	@Autowired
	private TFavorService tFavorService;
	@Autowired
	private LffwNoticeService lffwNoticeService;
	@Autowired
	private LffwCommentService lffwCommentService;
	@Autowired
	private LffwOrderService lffwOrderService;
	@Autowired
	private LffwServiceitemtypeService lffwServiceitemtypeService;
	@Autowired
	private LffwPeriodtypeService lffwPeriodtypeService;
	@Autowired
	private LffwBarberScheduleService lffwBarberScheduleService;
	@Autowired
	private LffwPeriodService lffwPeriodService;
	@Autowired
	private LffwVoucherService lffwVoucherService;
	@Autowired
	private LffwOrderdetailService lffwOrderdetailservice;

	/**
	 * 理发首页（店铺）
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/toindex")
	@ResponseBody
	public R towywx() {
		R result = new R();
		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_LFFW_YYLF);
		addshopinfo(result, service);
		JSONObject params = new JSONObject();
		params.put("valid", 1);
		result.put("barberlist", lffwBarberService.querybarberList(params));
		return result;
	}

	/**
	 * 理发师列表
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/barberlist")
	@ResponseBody
	public R barberlist() {
		R result = new R();
		JSONObject params = new JSONObject();
		params.put("valid", 1);
		result.put("barberlist", lffwBarberService.querybarberList(params));
		return result;
	}

	/**
	 * 理发师列表
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/barberdetail")
	@ResponseBody
	public R barberdetail(HttpServletRequest request) {
		R result = new R();
		String biz = request.getParameter("biz");
		log.debug(biz);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		LffwBarberEntity barber = lffwBarberService.queryObject(params.getInteger("barberid"));
		result.put("brief", barber.getBrief());
		Integer barberid = params.getInteger("barberid");
		Integer uid = WebUtils.getUid(request);
		result.put("favorstatus", tFavorService.queryFavorStatus(uid, FavorConst.FAVORTYPE_BARBER, barberid));
		result.put("worklist", lffwBarberWorksService.queryindexworkList(params));
		result.put("worktotalcount", lffwBarberWorksService.queryTotal(params));
		result.put("commenttotalcount", lffwCommentService.queryTotal(params));
		params.put("sidx", "createtime");
		params.put("order", "desc");
		Query q = new Query(params);
		result.put("commentlist", lffwCommentService.queryCommentList(q));
		return result;
	}

	/**
	 * 理发师作品列表
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/barberworklist")
	@ResponseBody
	public R barberworklist(String biz) {
		R result = new R();
		log.debug(biz);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		Query q = new Query(params);
		result.put("worklist", lffwBarberWorksService.queryList(q));
		return result;
	}

	/**
	 * 理发师作品列表
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/barberworkdetail")
	@ResponseBody
	public R barberworkdetail(HttpServletRequest request) {
		R result = new R();
		String biz = request.getParameter("biz");
		log.debug(biz);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		Integer uid = WebUtils.getUid(request);
		Integer workid = params.getInteger("workid");
		LffwBarberWorksEntity work = lffwBarberWorksService.queryObject(workid);
		work.setContent("");
		result.put("workid", work.getId());
		result.put("url", work.getUrl());
		result.put("favorstatus", tFavorService.queryFavorStatus(uid, FavorConst.FAVORTYPE_WORK, workid));
		return result;
	}

	/**
	 * 获取消息
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/noticelist")
	@ResponseBody
	public R noticelist(HttpServletRequest request) {
		String biz = request.getParameter("biz");
		log.debug(biz);
		R result = new R();
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		Integer uid = WebUtils.getUid(request);
		params.put("uid", uid);
		params.put("sidx", "createtime");
		params.put("order", "asc");
		Query q = new Query(params);
		List<LffwNoticeEntity> list = lffwNoticeService.querynoticeList(q);
		Collections.reverse(list);
		result.put("noticelist", list);
		result.put("total", lffwNoticeService.querynoticeListTotal(q));
		return result;
	}

	/**
	 * 添加消息
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/addnotice")
	@ResponseBody
	public R addnotice(HttpServletRequest request) {
		String biz = request.getParameter("biz");
		log.debug(biz);
		R result = new R();
		LffwNoticeEntity notice = FastJSONUtils.getObject(biz, LffwNoticeEntity.class);
		Integer uid = WebUtils.getUid(request);
		notice.setCreatetime(new Date());
		notice.setGetreadstatus(0);
		notice.setGettype(2);
		notice.setUtype(1);
		notice.setPostid(uid);
		lffwNoticeService.save(notice);
		return result;
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
		result.put("commentlist", lffwCommentService.queryCommentList(q));
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
		LffwCommentEntity comment = FastJSONUtils.getObject(biz, LffwCommentEntity.class);
		LffwOrderEntity order = lffwOrderService.queryObject(comment.getOrderid());
		if (order.getStatus().intValue() == 4) {
			JSONObject obj = WebUtils.uploadFiles(files, request, RedisConst.USER_YLFW_YYGH_ORDERIMG_PRE,
					RedisConst.UPLOAD_IMG_FILTER);
			if (0 == obj.getIntValue("errorCode")) {
				comment.setImgs(obj.getString("data"));
			}
			comment.setCreatetime(new Date());
			comment.setBarberid(order.getBarberid());
			comment.setUid(order.getUid());
			lffwCommentService.savecomment(comment, order);
			return R.ok();
		} else if (order.getStatus() == 3) {
			return R.error(1103, "该订单已进行过评论,不可重复评论");
		} else {
			return R.error(1102, "该订单还没有完成，不能进行评价");
		}
	}

	/**
	 * 下单前
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/preorder")
	@ResponseBody
	public R preorder(String biz) {
		log.debug(biz);
		R result = new R();
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		// 获取时间
		result.put("timelist", lffwPeriodtypeService.querytimelist(params));
		// 服务项目
		result.put("itemlist", lffwServiceitemtypeService.queryitemlist());
		return result;
	}

	/**
	 * 下单
	 * 
	 * @param biz
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/createorder")
	@ResponseBody
	public R createorder(HttpServletRequest request) {
		String biz = request.getParameter("biz");
		Integer uid = WebUtils.getUid(request);
		log.debug(biz);
		R result = new R();
		LffwOrderVO ordervo = FastJSONUtils.getObject(biz, LffwOrderVO.class);
		ordervo.getOrder().setUid(uid);
		LffwBarberScheduleEntity schedule = lffwBarberScheduleService.queryObject(ordervo.getOrder().getScheduleid());
		if (schedule == null || (schedule != null && schedule.getStatus().intValue() == 1)) {
			result = R.error(1200, "该时间段已被预约,请选择其他时间段");
		} else {
			LffwPeriodEntity period = lffwPeriodService.queryObject(schedule.getConsultperiodid());
			ordervo.setStarttime(period.getStarttime());
			LffwOrderEntity order;
			try {
				order = lffwOrderService.saveorder(ordervo);
				result.put("orderid", order.getId());
				result.put("createtime", order.getCreatetime());
				result.put("ordercode", order.getOrdercode());
				result.put("price", order.getPrice());
				result.put("ordertype", APPConstant.TYPE_LFFW_YYLF);
			} catch (Exception e) {
				log.error("理发下单异常", e);
				return R.error(500, "服务端异常");
			}
		}
		return result;
	}

	/**
	 * 下单
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/orderlist")
	@ResponseBody
	public R orderlist(HttpServletRequest request) {
		R result = new R();
		String biz = request.getParameter("biz");
		log.debug(biz);
		// 获取服务信息
		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_LFFW_YYLF);

		Integer uid = WebUtils.getUid(request);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		params.put("statuslist", addTypeParams(params));
		params.put("uid", uid);
		Query q = new Query(params);
		List<LffwOrderDTO> list = lffwOrderService.queryOrderlist(q);
		if (ValidateUtil.isValid(list)) {
			for (LffwOrderDTO o : list) {
				o.setAddr(service.getPlace());
			}
		}
		result.put("orderlist", list);
		result.put("serviceInfo", serviceService.queryService(APPConstant.TYPE_LFFW_YYLF));
		return result;
	}

	/**
	 * 订单详情
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/orderdetail")
	@ResponseBody
	public R orderdetail(String biz) {
		R result = new R();
		log.debug(biz);
		// 获取服务信息
		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_LFFW_YYLF);

		JSONObject params = FastJSONUtils.getJSONObject(biz);
		Query q = new Query(params);
		LffwOrderEntity order = lffwOrderService.queryObject(params.getInteger("orderid"));
		order.setChanges(0);
		lffwOrderService.update(order);
		List<LffwOrderDTO> list = lffwOrderService.queryOrderlist(q);
		LffwOrderDTO detail = ValidateUtil.isValid(list) ? list.get(0) : null;
		if (ValidateUtil.isValid(detail)) {
			List<Map<String, Object>> maplist = lffwOrderdetailservice.querydetailList(params);
			result.put("detaillist", maplist);
			detail.setAddr(service.getPlace());
		}
		result.put("detail", detail);
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
		Query q = new Query(params);
		LffwOrderEntity order = lffwOrderService.queryObject(params.getInteger("orderid"));
		order.setChanges(1);
		order.setCancelreason(params.getString("cancelreason"));
		order.setStatus(5);
		lffwOrderService.update(order);
		return R.ok();
	}

	/**
	 * 完成订单
	 * 
	 * @param biz
	 * @return
	 */
	@RequestMapping("/finishorder")
	@ResponseBody
	public R finishorder(String biz) {
		log.debug(biz);
		JSONObject params = FastJSONUtils.getJSONObject(biz);
		Query q = new Query(params);
		LffwOrderEntity order = lffwOrderService.queryObject(params.getInteger("orderid"));
		order.setChanges(1);
		order.setStatus(4);
		lffwOrderService.update(order);
		return R.ok();
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

	private void addshopinfo(R result, ServiceEntity service) {
		result.put("picture", service.getTopphoto() == null ? "" : service.getTopphoto());
		result.put("mobile", service.getMobile());
		result.put("place", service.getPlace());
		result.put("businesshour", service.getBusinesshour());
		result.put("tradename", service.getTradename());
	}
	
	

}
