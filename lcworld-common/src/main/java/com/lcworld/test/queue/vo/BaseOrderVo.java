package com.lcworld.test.queue.vo;

import java.util.Date;

public class BaseOrderVo implements Comparable<BaseOrderVo>{
	private String ordercode;
	private Integer ordertype;
	private Date timeAlarm;
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public Date getTimeAlarm() {
		return timeAlarm;
	}
	public void setTimeAlarm(Date timeAlarm) {
		this.timeAlarm = timeAlarm;
	}
	public Integer getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}
	@Override
	public int compareTo(BaseOrderVo o) {
		if (o != null && o.getTimeAlarm()!=null) {
			if (this.getTimeAlarm()!=null) {
				double d = this.getTimeAlarm().getTime()-o.getTimeAlarm().getTime();
				if (d==0) {
					return 0;
				}else{
					return d<0?-1:1;
				}
			}else{
				return 1;
			}
		}else{
			return -1;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ordercode == null) ? 0 : ordercode.hashCode());
		result = prime * result + ((ordertype == null) ? 0 : ordertype.hashCode());
		result = prime * result + ((timeAlarm == null) ? 0 : timeAlarm.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseOrderVo other = (BaseOrderVo) obj;
		if (ordercode == null) {
			if (other.ordercode != null)
				return false;
		} else if (!ordercode.equals(other.ordercode))
			return false;
		if (ordertype == null) {
			if (other.ordertype != null)
				return false;
		} else if (!ordertype.equals(other.ordertype))
			return false;
		if (timeAlarm == null) {
			if (other.timeAlarm != null)
				return false;
		} else if (!timeAlarm.equals(other.timeAlarm))
			return false;
		return true;
	}
}
