package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lcworld.vo.ScheduleVo;



/**
 * 医疗服务专家坐诊—医生计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public class YlfwZjzzDoctorScheduleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//医生id
	private Integer doctorid;
	//创建时间
	private Date createtime;
	//时间段id
	private Integer consultperiodid;
	//预约日期
	private Date scheduledate;
	//预约状态 0:未约，1：已约
	private Integer status;
	private Integer count;
private String  startdate;
    
    private String enddate;
    private List<ScheduleVo> periodCountList;
    
    

    public String getStartdate() {
        return startdate;
    }
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
    public String getEnddate() {
        return enddate;
    }
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    public List<ScheduleVo> getPeriodCountList() {
        return periodCountList;
    }
    public void setPeriodCountList(List<ScheduleVo> periodCountList) {
        this.periodCountList = periodCountList;
    }
	

	public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    /**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：医生id
	 */
	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}
	/**
	 * 获取：医生id
	 */
	public Integer getDoctorid() {
		return doctorid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：时间段id
	 */
	public void setConsultperiodid(Integer consultperiodid) {
		this.consultperiodid = consultperiodid;
	}
	/**
	 * 获取：时间段id
	 */
	public Integer getConsultperiodid() {
		return consultperiodid;
	}
	/**
	 * 设置：预约日期
	 */
	public void setScheduledate(Date scheduledate) {
		this.scheduledate = scheduledate;
	}
	/**
	 * 获取：预约日期
	 */
	public Date getScheduledate() {
		return scheduledate;
	}
	/**
	 * 设置：预约状态 0:未约，1：已约
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：预约状态 0:未约，1：已约
	 */
	public Integer getStatus() {
		return status;
	}
}
