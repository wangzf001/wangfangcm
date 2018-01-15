package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.PeriodDao;
import com.lcworld.dao.YlfwZjzzDoctorScheduleDao;
import com.lcworld.dao.YlfwZjzzPeriodDao;
import com.lcworld.dto.DoctorScheduleDTO;
import com.lcworld.dto.ExpertScheduleDTO;
import com.lcworld.dto.YlfwZjzzScheduleEntityDTO;
import com.lcworld.entity.LffwBarberScheduleEntity;
import com.lcworld.entity.LffwPeriodEntity;
import com.lcworld.entity.PeriodEntity;
import com.lcworld.entity.YlfwZjzzDoctorScheduleEntity;
import com.lcworld.entity.YlfwZjzzPeriodEntity;
import com.lcworld.service.YlfwZjzzDoctorScheduleService;
import com.lcworld.utils.DateUtil;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.vo.ScheduleVo;



@Service("ylfwZjzzDoctorScheduleService")
public class YlfwZjzzDoctorScheduleServiceImpl implements YlfwZjzzDoctorScheduleService {
	@Autowired
	private YlfwZjzzDoctorScheduleDao ylfwZjzzDoctorScheduleDao;
	@Autowired
	private PeriodDao periodDao;
	@Autowired
	private YlfwZjzzPeriodDao ylfwZjzzPeriodDao;
	
	@Override
	public YlfwZjzzDoctorScheduleEntity queryObject(Integer id){
		return ylfwZjzzDoctorScheduleDao.queryObject(id);
	}
	
	@Override
	public List<YlfwZjzzDoctorScheduleEntity> queryList(Map<String, Object> map){
		return ylfwZjzzDoctorScheduleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwZjzzDoctorScheduleDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwZjzzDoctorScheduleEntity ylfwZjzzDoctorSchedule){
		ylfwZjzzDoctorScheduleDao.save(ylfwZjzzDoctorSchedule);
	}
	
	@Override
	public void update(YlfwZjzzDoctorScheduleEntity ylfwZjzzDoctorSchedule){
		ylfwZjzzDoctorScheduleDao.update(ylfwZjzzDoctorSchedule);
	}
	
	@Override
	public void delete(Integer id){
		ylfwZjzzDoctorScheduleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwZjzzDoctorScheduleDao.deleteBatch(ids);
	}

    @Override
    public List<ExpertScheduleDTO> queryDocSchedules(JSONObject params) {
        return ylfwZjzzDoctorScheduleDao.queryDocSchedules(params);
    }

    @Override
    public List<YlfwZjzzScheduleEntityDTO> queryscheduleList(Query query) {
       return ylfwZjzzDoctorScheduleDao.queryScheduleList(query);
    }

    @Override
    public int queryscheduleTotal(Query query) {
        return ylfwZjzzDoctorScheduleDao.queryScheduleTotal(query);
    }

    @Override
    public void saveSchedule(YlfwZjzzDoctorScheduleEntity ylfwZjzzDoctorSchedule) throws ParseException {
        List<String> datelist = DateUtil.getBetweenDates(ylfwZjzzDoctorSchedule.getStartdate(), ylfwZjzzDoctorSchedule.getEnddate(), "yyyy-MM-dd");
        List<YlfwZjzzDoctorScheduleEntity> slist = new ArrayList<YlfwZjzzDoctorScheduleEntity>();
        List<YlfwZjzzPeriodEntity> plist = new ArrayList<YlfwZjzzPeriodEntity>();
        if(ValidateUtil.isValid(datelist)){
            for(ScheduleVo vo :ylfwZjzzDoctorSchedule.getPeriodCountList()){
                addperiod(plist,vo);
            }
            ylfwZjzzPeriodDao.saveBatch(plist);
            
            for(int i = 0 ; i < datelist.size() ; i++){
                String date = datelist.get(i);
                for(int j=0;j<plist.size();j++){
                    YlfwZjzzPeriodEntity p = plist.get(j);
                    YlfwZjzzDoctorScheduleEntity schedule = new YlfwZjzzDoctorScheduleEntity();
                    schedule.setCreatetime(new Date());
                    schedule.setStatus(0);
                    schedule.setDoctorid(ylfwZjzzDoctorSchedule.getDoctorid());
                    schedule.setConsultperiodid(p.getId());
                    schedule.setScheduledate(DateUtil.parse(date, "yyyy-MM-dd"));
                    slist.add(schedule);
                }
            }
            ylfwZjzzDoctorScheduleDao.saveBatch(slist);
            
        }else{
            throw new RuntimeException("日期段不对");
        }
    }
    
    
    public static void addperiod(List<YlfwZjzzPeriodEntity> plist, ScheduleVo vo) throws ParseException {
        if(null == vo.getCount() || 0 == vo.getCount() ){
            vo.setCount(1);
        }
        SimpleDateFormat d = new SimpleDateFormat("HH:mm");
        Date sdate = d.parse(vo.getStarttime());
         Date edate = d.parse(vo.getEndtime());
         long l = edate.getTime()-sdate.getTime();
         int size = vo.getCount();
         int jc = (int) (l/size);
         for(int i=0 ;i < size;i++){
             YlfwZjzzPeriodEntity p = new YlfwZjzzPeriodEntity();
             long ss  = sdate.getTime()+jc*i;
             long ee  = sdate.getTime()+jc*(i+1);
             Date idate = new Date(ss);
             Date jdate = new Date(ee);
             String startstr = d.format(idate);
             String endstr = d.format(jdate);
             p.setStarttime(startstr);
             p.setEndtime(endstr);
             p.setPeriodtype(vo.getPeriodtypeid());
             plist.add(p);
         }
    }
}
