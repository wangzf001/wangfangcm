package com.lcworld.service.impl;

import com.lcworld.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.BxwxAppointmentDao;
import com.lcworld.entity.BxwxAppointmentEntity;
import com.lcworld.service.BxwxAppointmentService;
import com.lcworld.vo.BxwxAppointmentResultVo;
import com.lcworld.vo.BxwxAppointmentVo;



@Service("bxwxAppointmentService")
public class BxwxAppointmentServiceImpl implements BxwxAppointmentService {
	@Autowired
	private BxwxAppointmentDao bxwxAppointmentDao;
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
	public BxwxAppointmentEntity queryObject(Integer id){
		return bxwxAppointmentDao.queryObject(id);
	}
	
	@Override
	public List<BxwxAppointmentEntity> queryList(Map<String, Object> map){
		return bxwxAppointmentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bxwxAppointmentDao.queryTotal(map);
	}
	
	@Override
	public void save(BxwxAppointmentEntity bxwxAppointment){
		bxwxAppointmentDao.save(bxwxAppointment);
	}
	
	@Override
	public void update(BxwxAppointmentEntity bxwxAppointment){
		bxwxAppointmentDao.update(bxwxAppointment);
	}
	
	@Override
	public void delete(Integer id){
		bxwxAppointmentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bxwxAppointmentDao.deleteBatch(ids);
	}

	@Override
	public Set<BxwxAppointmentResultVo> findAvailableTime(JSONObject params) {
		Integer uid = params.getInteger("uid");
		List<BxwxAppointmentVo> apointmentVoList = bxwxAppointmentDao.findAvailableTime(params);
		Set<BxwxAppointmentResultVo> resultSet = new TreeSet<>(new Comparator<BxwxAppointmentResultVo>() {
			@Override
			public int compare(BxwxAppointmentResultVo o1, BxwxAppointmentResultVo o2) {
				return o1.getIntervalid()-o2.getIntervalid();
			}
		});
		StringBuffer ids = new StringBuffer();
		for (Entry<Integer, String> entry : idToDurationMap.entrySet()) {
			BxwxAppointmentResultVo resultVo = new BxwxAppointmentResultVo();
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
				BxwxAppointmentResultVo resultVo = new BxwxAppointmentResultVo();
				resultVo.setIntervalid(id);
				resultVo.setDurationstr(idToDurationMap.get(id));
				resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_UNAVAILABLE);
				resultSet.remove(resultVo);
				resultSet.add(resultVo);
			}
		}
		for (BxwxAppointmentVo atv : apointmentVoList) {
			String intervalids = atv.getIntervalids();
			String[] idStrs = intervalids.split(",");
			for (String idStr : idStrs) {
				int id = Integer.parseInt(idStr);
				BxwxAppointmentResultVo resultVo = new BxwxAppointmentResultVo();
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

}
