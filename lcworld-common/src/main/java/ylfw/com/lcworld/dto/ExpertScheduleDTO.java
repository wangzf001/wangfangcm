package com.lcworld.dto;

import java.util.Date;
import java.util.List;

import com.lcworld.entity.YlfwZjzzPeriodEntity;

public class ExpertScheduleDTO {
    private Date scheduleDate;
    
    private List<YlfwZjzzPeriodEntity> schedulelist;
    
    
    public Date getScheduleDate() {
        return scheduleDate;
    }
    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
    public List<YlfwZjzzPeriodEntity> getSchedulelist() {
        return schedulelist;
    }
    public void setSchedulelist(List<YlfwZjzzPeriodEntity> schedulelist) {
        this.schedulelist = schedulelist;
    }
  
}
