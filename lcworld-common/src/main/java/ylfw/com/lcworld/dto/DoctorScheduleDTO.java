package com.lcworld.dto;

public class DoctorScheduleDTO {
    private Integer id;
    //开始时间
    private String starttime;
    //结束时间
    private String endtime;
    //时间段类型 1: 上午，2： 下午
    private Integer periodtype;
    //预约状态 0:未约，1：已约
    private Integer status;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStarttime() {
        return starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public Integer getPeriodtype() {
        return periodtype;
    }
    public void setPeriodtype(Integer periodtype) {
        this.periodtype = periodtype;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
