package com.lcworld.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcworld.dao.LffwBarberScheduleDao;
import com.lcworld.dao.LffwPeriodDao;
import com.lcworld.dto.LffwBarberScheduleEntityDTO;
import com.lcworld.entity.LffwBarberScheduleEntity;
import com.lcworld.entity.LffwPeriodEntity;
import com.lcworld.service.LffwBarberScheduleService;
import com.lcworld.utils.DateUtil;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.vo.ScheduleVo;

@Service("lffwBarberScheduleService")
public class LffwBarberScheduleServiceImpl implements LffwBarberScheduleService {
	@Autowired
	private LffwBarberScheduleDao lffwBarberScheduleDao;
	@Autowired
	private LffwPeriodDao lffwPeriodDao;
	
	@Override
	public LffwBarberScheduleEntity queryObject(Integer id){
		return lffwBarberScheduleDao.queryObject(id);
	}
	
	@Override
	public List<LffwBarberScheduleEntity> queryList(Map<String, Object> map){
		return lffwBarberScheduleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwBarberScheduleDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwBarberScheduleEntity lffwBarberSchedule){
		lffwBarberScheduleDao.save(lffwBarberSchedule);
	}
	
	@Override
	public void update(LffwBarberScheduleEntity lffwBarberSchedule){
		lffwBarberScheduleDao.update(lffwBarberSchedule);
	}
	
	@Override
	public void delete(Integer id){
		lffwBarberScheduleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwBarberScheduleDao.deleteBatch(ids);
	}

    @Override
    public List<LffwBarberScheduleEntityDTO> queryScheduleList(Query query) {
        return lffwBarberScheduleDao.queryScheduleList(query);
    }

    @Override
    public int queryScheduleTotal(Query query) {
        return lffwBarberScheduleDao.queryScheduleTotal(query);
    }

    @Override
    public void saveSchedule(LffwBarberScheduleEntity lffwBarberSchedule) {
        Integer periodid = lffwBarberSchedule.getConsultperiodid();
        if(!ValidateUtil.isValid(periodid)){
            LffwPeriodEntity period = new LffwPeriodEntity();
            period.setStarttime(lffwBarberSchedule.getStarttime());
            period.setEndtime(lffwBarberSchedule.getEndtime());
            lffwPeriodDao.save(period);
            periodid = period.getId();
        }
        
        lffwBarberSchedule.setConsultperiodid(periodid);
        lffwBarberSchedule.setCreatetime(new Date());
        lffwBarberSchedule.setStatus(0);
        lffwBarberScheduleDao.save(lffwBarberSchedule);
        
        
        
    }

    @Override
    public void updateSchedule(LffwBarberScheduleEntity lffwBarberSchedule) {
        if(0 == lffwBarberSchedule.getStatus()){
            
        }
        Integer periodid = lffwBarberSchedule.getConsultperiodid();
        if(!ValidateUtil.isValid(periodid)){
            LffwPeriodEntity period = new LffwPeriodEntity();
            period.setStarttime(lffwBarberSchedule.getStarttime());
            period.setEndtime(lffwBarberSchedule.getEndtime());
            lffwPeriodDao.save(period);
            periodid = period.getId();
        }
        
        lffwBarberSchedule.setConsultperiodid(periodid);
        lffwBarberSchedule.setCreatetime(new Date());
        lffwBarberSchedule.setStatus(0);
        lffwBarberScheduleDao.save(lffwBarberSchedule);
        
    }

    @Override
    public void saveschedule(LffwBarberScheduleEntity lffwBarberSchedule) throws ParseException {
        List<String> datelist = DateUtil.getBetweenDates(lffwBarberSchedule.getStartdate(), lffwBarberSchedule.getEnddate(), "yyyy-MM-dd");
        List<LffwBarberScheduleEntity> slist = new ArrayList<LffwBarberScheduleEntity>();
        List<LffwPeriodEntity> plist = new ArrayList<LffwPeriodEntity>();
        if(ValidateUtil.isValid(datelist)){
            for(ScheduleVo vo :lffwBarberSchedule.getPeriodCountList()){
                addperiod(plist,vo);
            }
            lffwPeriodDao.saveBatch(plist);
            
            for(int i = 0 ; i < datelist.size() ; i++){
                String date = datelist.get(i);
                for(int j=0;j<plist.size();j++){
                    LffwPeriodEntity p = plist.get(j);
                    LffwBarberScheduleEntity schedule = new LffwBarberScheduleEntity();
                    schedule.setCreatetime(new Date());
                    schedule.setStatus(0);
                    schedule.setBarberid(lffwBarberSchedule.getBarberid());
                    schedule.setConsultperiodid(p.getId());
                    schedule.setScheduledate(DateUtil.parse(date, "yyyy-MM-dd"));
                    slist.add(schedule);
                }
            }
            lffwBarberScheduleDao.saveBatch(slist);
            
        }else{
            throw new RuntimeException("日期段不对");
        }
    }
    
    
    public static void addperiod(List<LffwPeriodEntity> plist, ScheduleVo vo) throws ParseException {
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
             LffwPeriodEntity p = new LffwPeriodEntity();
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
