package com.lcworld.dto;

public class LffwBarberScheduleDTO {
    private Integer scheduleid;
    private Integer periodtypeid;
    private String starttime; 
    private String periodtypename; 
    private String endtime; 
    private Integer  status;
    private Integer barberid;
    public Integer getBarberid() {
        return barberid;
    }
    public void setBarberid(Integer barberid) {
        this.barberid = barberid;
    }
    public Integer getScheduleid() {
        return scheduleid;
    }
    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }
    public Integer getPeriodtypeid() {
        return periodtypeid;
    }
    public void setPeriodtypeid(Integer periodtypeid) {
        this.periodtypeid = periodtypeid;
    }
    public String getStarttime() {
        return starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getPeriodtypename() {
        return periodtypename;
    }
    public void setPeriodtypename(String periodtypename) {
        this.periodtypename = periodtypename;
    }
    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    } 
    
}
