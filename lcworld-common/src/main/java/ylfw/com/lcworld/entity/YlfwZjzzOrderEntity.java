package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 医疗服务专家坐诊订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public class YlfwZjzzOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//
	private Integer doctorid;
	//预约电话
	private String invitemobile;
	//预约计划id
	private Integer scheduleid;
	//创建时间
	private Date createtime;
	//订单状态改变 1: 改变，2： 未改变
	private Integer changes;
	//订单状态 1:已预约 ，2： 服务中（用户到店，医生点击），3：已完成（包含待评价和已评价），4：待评价，5：已取消
	private Integer status;
	//订单号
	private String ordercode;
	private Integer isdel;
	private String realname;
	private String mobile;
	 //取消原因
    private String cancelreason;
    
    private Date invitetime;
    
    

    public Date getInvitetime() {
        return invitetime;
    }
    public void setInvitetime(Date invitetime) {
        this.invitetime = invitetime;
    }
    public String getCancelreason() {
        return cancelreason;
    }
    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
    }

	
	
	public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    /**
     * 获取：0:不删，1：已删
     */
    public Integer getIsdel() {
        return isdel;
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
	 * 设置：
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：
	 */
	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}
	/**
	 * 获取：
	 */
	public Integer getDoctorid() {
		return doctorid;
	}
	/**
	 * 设置：预约电话
	 */
	public void setInvitemobile(String invitemobile) {
		this.invitemobile = invitemobile;
	}
	/**
	 * 获取：预约电话
	 */
	public String getInvitemobile() {
		return invitemobile;
	}
	/**
	 * 设置：预约计划id
	 */
	public void setScheduleid(Integer scheduleid) {
		this.scheduleid = scheduleid;
	}
	/**
	 * 获取：预约计划id
	 */
	public Integer getScheduleid() {
		return scheduleid;
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
	 * 设置：订单状态改变 1: 改变，2： 未改变
	 */
	public void setChanges(Integer changes) {
		this.changes = changes;
	}
	/**
	 * 获取：订单状态改变 1: 改变，2： 未改变
	 */
	public Integer getChanges() {
		return changes;
	}
	/**
	 * 设置：订单状态 1:已预约 ，2： 服务中（用户到店，医生点击），3：已完成（包含待评价和已评价），4：待评价，5：已取消
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态 1:已预约 ，2： 服务中（用户到店，医生点击），3：已完成（包含待评价和已评价），4：待评价，5：已取消
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：订单号
	 */
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	/**
	 * 获取：订单号
	 */
	public String getOrdercode() {
		return ordercode;
	}
}
