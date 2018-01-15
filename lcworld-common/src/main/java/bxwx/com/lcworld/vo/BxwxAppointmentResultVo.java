package com.lcworld.vo;

public class BxwxAppointmentResultVo {
	private Integer intervalid;
	private String durationstr;
	//1可预约2已预约3不可预约
	private Integer status;
	public Integer getIntervalid() {
		return intervalid;
	}
	public void setIntervalid(Integer intervalid) {
		this.intervalid = intervalid;
	}
	public String getDurationstr() {
		return durationstr;
	}
	public void setDurationstr(String durationstr) {
		this.durationstr = durationstr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "HysfwAppointmentResultVo [intervalid=" + intervalid + ", durationstr=" + durationstr + ", status="
				+ status + "]";
	}
	
}
