package com.lcworld.vo;

public class ScheduleVo {
    private Integer id;
    private Integer count;
    //1:上午，2：下午，3：晚上
    private Integer periodtypeid;
    //开始时间
    private String starttime;
    //结束时间
    private String endtime;
    
    
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
    public Integer getPeriodtypeid() {
        return periodtypeid;
    }
    public void setPeriodtypeid(Integer periodtypeid) {
        this.periodtypeid = periodtypeid;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    
}
