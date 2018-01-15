package com.lcworld.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.utils.DateUtils;
import com.lcworld.vo.AppointmentResultVo;
import com.lcworld.vo.AppointmentVo;
import com.lcworld.vo.BxwxAppointmentResultVo;
import com.lcworld.vo.BxwxAppointmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.lcworld.dao.DsfwAppointmentDao;
import com.lcworld.entity.DsfwAppointmentEntity;
import com.lcworld.service.DsfwAppointmentService;


@Service("dsfwAppointmentService")
public class DsfwAppointmentServiceImpl implements DsfwAppointmentService {
    @Autowired
    private DsfwAppointmentDao dsfwAppointmentDao;

    public static Map<Integer, String> idToDurationMap = new HashMap<>();

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
    public DsfwAppointmentEntity queryObject(Integer id) {
        return dsfwAppointmentDao.queryObject(id);
    }

    @Override
    public List<DsfwAppointmentEntity> queryList(Map<String, Object> map) {
        return dsfwAppointmentDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return dsfwAppointmentDao.queryTotal(map);
    }

    @Override
    public void save(DsfwAppointmentEntity dsfwAppointment) {
        dsfwAppointmentDao.save(dsfwAppointment);
    }

    @Override
    public void update(DsfwAppointmentEntity dsfwAppointment) {
        dsfwAppointmentDao.update(dsfwAppointment);
    }

    @Override
    public void delete(Integer id) {
        dsfwAppointmentDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        dsfwAppointmentDao.deleteBatch(ids);
    }

    @Override
    public Set<AppointmentResultVo> findAvailableTime(JSONObject params) {
        Integer uid = params.getInteger("uid");
        //List<AppointmentVo> apointmentVoList = dsfwAppointmentDao.findAvailableTime(params);
        List<AppointmentVo> apointmentVoList = new ArrayList<>();
        Set<AppointmentResultVo> resultSet = new TreeSet<>(new Comparator<AppointmentResultVo>() {
            @Override
            public int compare(AppointmentResultVo o1, AppointmentResultVo o2) {
                return o1.getIntervalid() - o2.getIntervalid();
            }
        });
        StringBuffer ids = new StringBuffer();
        for (Map.Entry<Integer, String> entry : idToDurationMap.entrySet()) {
            AppointmentResultVo resultVo = new AppointmentResultVo();
            resultVo.setIntervalid(entry.getKey());
            resultVo.setDurationstr(entry.getValue());
            resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_AVAILABLE);
            resultSet.add(resultVo);
            String value = entry.getValue();
            String[] strings = value.split("-");
            if (!((DateUtils.compareTo(DateUtils.format(new Date(), "HH:mm"), strings[0], "HH:mm") > 0 && DateUtils.compareTo(DateUtils.format(new Date(), "HH:mm"), strings[1], "HH:mm") < 0) || DateUtils.compareTo(DateUtils.format(new Date(), "HH:mm"), strings[0], "HH:mm") < 0)) {
                ids.append(entry.getKey()).append(",");
            }
        }
        if (DateUtils.compareTo(params.getString("date"), DateUtils.format(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd") == 0) {
            String[] asList = ids.toString().split(",");
            for (String key : asList) {
                int id = Integer.parseInt(key);
                AppointmentResultVo resultVo = new AppointmentResultVo();
                resultVo.setIntervalid(id);
                resultVo.setDurationstr(idToDurationMap.get(id));
                resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_UNAVAILABLE);
                resultSet.remove(resultVo);
                resultSet.add(resultVo);
            }
        }
        for (AppointmentVo atv : apointmentVoList) {
            String intervalids = atv.getIntervalids();
            String[] idStrs = intervalids.split(",");
            for (String idStr : idStrs) {
                int id = Integer.parseInt(idStr);
                AppointmentResultVo resultVo = new AppointmentResultVo();
                resultVo.setIntervalid(id);
                resultVo.setDurationstr(idToDurationMap.get(id));
                if (atv.getUid() == uid) {
                    resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_SELF);
                } else {
                    resultVo.setStatus(APPConstant.TYPE_HYSFW_APPOINTMENT_UNAVAILABLE);
                }
                resultSet.remove(resultVo);
                resultSet.add(resultVo);
            }
        }
        return resultSet;
    }

}
