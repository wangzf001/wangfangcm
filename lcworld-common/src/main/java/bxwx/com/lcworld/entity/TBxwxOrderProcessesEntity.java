package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 订单流程表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public class TBxwxOrderProcessesEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//1:下单，2: 审核中，3：审核通过，4：审核不通过，5：派工，6： 接单
	private Integer status;
	//详情信息
	private String detail;
	//订单编号
	private Integer orderid;
	//时间
	private Date createtime;
	private Integer failurereasonid;
	private String failurereason;
	private String menderName;
	private Integer menderid;
	
	
	 
	
	
	

	public Integer getMenderid() {
        return menderid;
    }
    public void setMenderid(Integer menderid) {
        this.menderid = menderid;
    }
    public String getMenderName() {
        return menderName;
    }
    public void setMenderName(String menderName) {
        this.menderName = menderName;
    }
    public Integer getFailurereasonid() {
        return failurereasonid;
    }
    public void setFailurereasonid(Integer failurereasonid) {
        this.failurereasonid = failurereasonid;
    }
    public String getFailurereason() {
        return failurereason;
    }
    public void setFailurereason(String failurereason) {
        this.failurereason = failurereason;
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
	 * 设置：1:下单，2: 审核中，3：审核通过，4：审核不通过，5：派工，6： 接单
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1:下单，2: 审核中，3：审核通过，4：审核不通过，5：派工，6： 接单
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：详情信息
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * 获取：详情信息
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：订单编号
	 */
	public Integer getOrderid() {
		return orderid;
	}
	/**
	 * 设置：时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
}
