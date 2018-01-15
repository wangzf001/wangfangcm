package com.lcworld.service.impl;

import java.util.*;
import java.util.Map.Entry;

import com.lcworld.utils.DateUtils;
import com.lcworld.vo.BxwxAppointmentResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.HysfwAppointmentDao;
import com.lcworld.entity.HysfwAppointmentEntity;
import com.lcworld.service.HysfwAppointmentService;
import com.lcworld.vo.HysfwAppointmentResultVo;
import com.lcworld.vo.HysfwAppointmentVo;



@Service("hysfwAppointmentService")
public class HysfwAppointmentServiceImpl implements HysfwAppointmentService {
	@Autowired
	private HysfwAppointmentDao hysfwAppointmentDao;
	public static Map<Integer,String> idToDurationMap = new HashMap<>();
	static {
		idToDurationMap.put(1, "8:00-9:00");
		idToDurationMap.put(2, "9:00-10:00");
		idToDurationMap.put(3, "10:00-11:00");
		idToDurationMap.put(4, "11:00-12:00");
		idToDurationMap.put(5, "14:00-15:00");
		idToDurationMap.put(6, "15:00-16:00");
		idToDurationMap.put(7, "16:00-17:00");
		idToDurationMap.put(8, "17:00-18:00");
	}
	@Override
	public HysfwAppointmentEntity queryObject(Integer id){
		return hysfwAppointmentDao.queryObject(id);
	}
	
	@Override
	public List<HysfwAppointmentEntity> queryList(Map<String, Object> map){
		return hysfwAppointmentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hysfwAppointmentDao.queryTotal(map);
	}
	
	@Override
	public void save(HysfwAppointmentEntity hysfwAppointment){
		hysfwAppointmentDao.save(hysfwAppointment);
	}
	
	@Override
	public void update(HysfwAppointmentEntity hysfwAppointment){
		hysfwAppointmentDao.update(hysfwAppointment);
	}
	
	@Override
	public void delete(Integer id){
		hysfwAppointmentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hysfwAppointmentDao.deleteBatch(ids);
	}
	
	@Override
	public Set<HysfwAppointmentResultVo> findAvailableTime(JSONObject params) {
		String date = params.getString("date");
		Integer roomid = params.getInteger("roomid");
		Integer uid = params.getInteger("uid");
		List<HysfwAppointmentVo> apointmentVoList = hysfwAppointmentDao.findAvailableTime(params);
		Set<HysfwAppointmentResultVo> resultSet = new TreeSet<>(new Comparator<HysfwAppointmentResultVo>() {
			@Override
			public int compare(HysfwAppointmentResultVo o1, HysfwAppointmentResultVo o2) {
				return o1.getIntervalid()-o2.getIntervalid();
			}
		});
		StringBuffer ids = new StringBuffer();
		for (Entry<Integer, String> entry : idToDurationMap.entrySet()) {
			HysfwAppointmentResultVo resultVo = new HysfwAppointmentResultVo();
			resultVo.setIntervalid(entry.getKey());
			resultVo.setDurationstr(entry.getValue());
			resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_AVAILABLE);
			resultSet.add(resultVo);
			String value = entry.getValue();
			String[] strings = value.split("-");
			if (!((DateUtils.compareTo(DateUtils.format(new Date(), "HH:mm"),strings[0],"HH:mm")>0&&DateUtils.compareTo(DateUtils.format(new Date(), "HH:mm"),strings[1],"HH:mm")<0) || DateUtils.compareTo(DateUtils.format(new Date(), "HH:mm"),strings[0],"HH:mm")<0)){
				ids.append(entry.getKey()).append(",");
			}
		}
		if(DateUtils.compareTo(params.getString("date"),DateUtils.format(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd") == 0){
			String[] asList = ids.toString().split(",");
			for (String key:asList) {
				int id = Integer.parseInt(key);
				HysfwAppointmentResultVo resultVo = new HysfwAppointmentResultVo();
				resultVo.setIntervalid(id);
				resultVo.setDurationstr(idToDurationMap.get(id));
				resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_UNAVAILABLE);
				resultSet.remove(resultVo);
				resultSet.add(resultVo);
			}
		}
		for (HysfwAppointmentVo atv : apointmentVoList) {
			String intervalids = atv.getIntervalids();
			String[] idStrs = intervalids.split(",");
			for (String idStr : idStrs) {
				int id = Integer.parseInt(idStr);
				HysfwAppointmentResultVo resultVo = new HysfwAppointmentResultVo();
				resultVo.setIntervalid(id);
				resultVo.setDurationstr(idToDurationMap.get(id));
				if (atv.getUid()==uid) {
					resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_SELF);
				}else{
					resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_UNAVAILABLE);
				}
				resultSet.remove(resultVo);
				resultSet.add(resultVo);
			}
		}
		return resultSet;
	}
	public static void main(String[] args) {
		Map<Integer,String> idToDurationMap = new HashMap<>();
		idToDurationMap.put(1, "8:00-9:00");
		idToDurationMap.put(2, "9:00-10:00");
		idToDurationMap.put(3, "10:00-11:00");
		idToDurationMap.put(4, "11:00-12:00");
		idToDurationMap.put(5, "14:00-15:00");
		idToDurationMap.put(6, "15:00-16:00");
		idToDurationMap.put(7, "16:00-17:00");
		idToDurationMap.put(8, "17:00-18:00");
		Set<HysfwAppointmentResultVo> resultSet = new TreeSet<>(new Comparator<HysfwAppointmentResultVo>() {
			@Override
			public int compare(HysfwAppointmentResultVo o1, HysfwAppointmentResultVo o2) {
				return o1.getIntervalid()-o2.getIntervalid();
			}
		});
		for (Entry<Integer, String> entry : idToDurationMap.entrySet()) {
			HysfwAppointmentResultVo resultVo = new HysfwAppointmentResultVo();
			resultVo.setIntervalid(entry.getKey());
			resultVo.setDurationstr(entry.getValue());
			resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_AVAILABLE);
			resultSet.add(resultVo);
		}
		String intervalids = "5,4,3";
		String[] idStrs = intervalids.split(",");
		for (String idStr : idStrs) {
			int id = Integer.parseInt(idStr);
			HysfwAppointmentResultVo resultVo = new HysfwAppointmentResultVo();
			resultVo.setIntervalid(id);
			resultVo.setDurationstr(idToDurationMap.get(id));
			resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_SELF);
			resultSet.remove(resultVo);
			resultSet.add(resultVo);
		}
		System.out.println(resultSet);
	}
}
