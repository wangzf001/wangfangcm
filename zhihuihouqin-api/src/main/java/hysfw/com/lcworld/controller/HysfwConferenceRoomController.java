package com.lcworld.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.util.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.HysfwConferenceEquipmentEntity;
import com.lcworld.entity.HysfwConferenceRoomEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.service.HysfwConferenceEquipmentService;
import com.lcworld.service.HysfwConferenceRoomService;
import com.lcworld.service.HysfwConferenceServiceService;
import com.lcworld.service.ServiceService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;

import com.lcworld.entity.HysfwConferenceServiceEntity;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
@RestController
@RequestMapping("appuser/hysfwconferenceroom")
public class HysfwConferenceRoomController {
	@Autowired
	private HysfwConferenceRoomService hysfwConferenceRoomService;
	@Autowired
	private HysfwConferenceEquipmentService hysfwConferenceEquipmentService;
	@Autowired
	private HysfwConferenceServiceService hysfwConferenceServiceService;
	@Autowired
	private ServiceService serviceService;
	
	/**
	 * 查询会议室列表
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findConferenceList")
	public R findConferenceList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		JSONObject params = JSONObject.parseObject(biz);
		//查询列表数据
        Query query = new Query(params);
		List<HysfwConferenceRoomEntity> list = hysfwConferenceRoomService.queryList(query);
		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_HYSFW);
		String str = ValidateUtil.isValid(service)?service.getTopphoto():"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201710151508061945457.jpg";
		if (ValidateUtil.isValid(service)) {
			str = service.getTopphoto();
		}
		return R.ok().put("list", list).put("topphoto", str);
	}
	/**
	 * 查询会议室详情
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findConferenceDetail")
	public R findConferenceDetail(HttpServletRequest req){
		String biz = req.getParameter("biz");
		JSONObject params = JSONObject.parseObject(biz);
		//查询列表数据
		Integer id = params.getInteger("id");
		HysfwConferenceRoomEntity conferenceRoom = hysfwConferenceRoomService.queryObject(id);
		List<HysfwConferenceEquipmentEntity> elist = hysfwConferenceEquipmentService.queryList(new HashMap<String,Object>());
		List<HysfwConferenceServiceEntity> slist = hysfwConferenceServiceService.queryList(new HashMap<String,Object>());
		Set<Map<String, Object>> eSet = changeToSet(elist,conferenceRoom);
		Set<Map<String, Object>> sSet = changeToSet(slist,conferenceRoom);
		conferenceRoom.setEnameList(eSet);
		conferenceRoom.setSlist(sSet);
		if (ValidateUtil.isValid(conferenceRoom.getImgs())) {
			String imgs = conferenceRoom.getImgs();
			String[] imgArr = imgs.split(",");
			conferenceRoom.getImgList().addAll(Arrays.asList(imgArr));
		}
		return R.ok().put("roomDetail", conferenceRoom);
	}
	private Set<Map<String, Object>> changeToSet(List<?> elist,HysfwConferenceRoomEntity conferenceRoom) {
		Set<Map<String, Object>> eSet = new TreeSet<Map<String, Object>>(new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				// TODO Auto-generated method stub
				return MapUtils.getIntValue(o1, "id")-MapUtils.getIntValue(o2, "id");
			}
		});
		int type = 0;
		for (Object e : elist) {
			Map<String, Object> map = new HashMap<>();
			if (e instanceof HysfwConferenceEquipmentEntity) {
				HysfwConferenceEquipmentEntity t = ((HysfwConferenceEquipmentEntity) e);
				map.put("id", t.getId());
				map.put("name", t.getName());
				map.put("estatus", 0);
				type = 0;
			}else if (e instanceof HysfwConferenceServiceEntity) {
				HysfwConferenceServiceEntity t = ((HysfwConferenceServiceEntity) e);
				map.put("id", t.getId());
				map.put("name", t.getName());
				map.put("estatus", 0);
				type = 1;
			}
			eSet.add(map);
		}
		String[] nameArr = {};
		if (ValidateUtil.isValid(conferenceRoom.getEnames())&&type==0) {
			String enames = conferenceRoom.getEnames();
			nameArr = enames.split(",");
		}else if (ValidateUtil.isValid(conferenceRoom.getSnames())&&type==1){
			String enames = conferenceRoom.getSnames();
			nameArr = enames.split(",");
		}
		for (String ename : nameArr) {
			if(StringUtils.isEmpty(ename)){
				continue;
			}
			Map<String,Object> map = new HashMap<>();
			String[] split = ename.split("-");
			map.put("id", split[0]);
			map.put("name", split[1]);
			map.put("estatus", 1);
			eSet.remove(map);
			eSet.add(map);
		}
		return eSet;
	}
	/**
	 * 查询会议室内可以预定的设备以及服务
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findAvailableEquipment")
	public R findAvailableEquipment(HttpServletRequest req){
		String biz = req.getParameter("biz");
		JSONObject params = JSONObject.parseObject(biz);
		//查询列表数据
		Integer id = params.getInteger("id");
		List<HysfwConferenceEquipmentEntity> elist = hysfwConferenceRoomService.queryAvailableEquipmentList(id);
		List<HysfwConferenceServiceEntity> slist = hysfwConferenceRoomService.queryAvailableServiceList(id);
		List<Map<Integer,String>> typeList = hysfwConferenceRoomService.queryConferenceType();
		return R.ok().put("equipmentList", elist).put("typeList", typeList).put("serviceList", slist);
	}
}
