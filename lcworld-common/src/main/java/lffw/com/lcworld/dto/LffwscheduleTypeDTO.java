package com.lcworld.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lcworld.entity.LffwBarberScheduleEntity;
import com.lcworld.entity.LffwPeriodtypeEntity;

public class LffwscheduleTypeDTO {
    private LffwPeriodtypeEntity type;
    private List<LffwBarberScheduleEntity> schedulelist = new ArrayList<LffwBarberScheduleEntity>();
    public LffwPeriodtypeEntity getType() {
        return type;
    }
    public void setType(LffwPeriodtypeEntity type) {
        this.type = type;
    }
    public List<LffwBarberScheduleEntity> getSchedulelist() {
        return schedulelist;
    }
    public void setSchedulelist(List<LffwBarberScheduleEntity> schedulelist) {
        this.schedulelist = schedulelist;
    }
    
}
