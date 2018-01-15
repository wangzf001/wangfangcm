package com.lcworld.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lcworld.entity.LffwBarberScheduleEntity;

public class LffwscheduleDTO {
    private Date scheduleDate;
    private List<LffwBarberScheduleEntity> schedulelist = new ArrayList<LffwBarberScheduleEntity>();
    public Date getScheduleDate() {
        return scheduleDate;
    }
    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
    public List<LffwBarberScheduleEntity> getSchedulelist() {
        return schedulelist;
    }
    public void setSchedulelist(List<LffwBarberScheduleEntity> schedulelist) {
        this.schedulelist = schedulelist;
    }
    
}
