package com.lcworld.dto;

import java.util.Date;

public class YlfwZjzzScheduleEntityDTO {
    //
    private Integer id;
    //创建时间
    private Date createtime;
    //时间段id
    private Integer consultperiodid;
    //预约日期
    private Date scheduledate;
    //预约状态 0:未约，1：已约
    private Integer status;
    
  //开始时间
    private String starttime;
    //结束时间
    private String endtime;
    //开始时间
    private String periodstarttime;
    //结束时间
    private String periodendtime;
    private String name;
    public String getPeriodstarttime() {
        return periodstarttime;
    }
    public void setPeriodstarttime(String periodstarttime) {
        this.periodstarttime = periodstarttime;
    }
    public String getPeriodendtime() {
        return periodendtime;
    }
    public void setPeriodendtime(String periodendtime) {
        this.periodendtime = periodendtime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //时间段类型 1: 上午，2： 下午 3,晚上
    private Integer periodtype;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
  
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Integer getConsultperiodid() {
        return consultperiodid;
    }
    public void setConsultperiodid(Integer consultperiodid) {
        this.consultperiodid = consultperiodid;
    }
    public Date getScheduledate() {
        return scheduledate;
    }
    public void setScheduledate(Date scheduledate) {
        this.scheduledate = scheduledate;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
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
    
    
}
