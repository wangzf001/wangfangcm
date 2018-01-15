package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 用户账户额度限制
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 10:08:45
 */
public class UserPurchaseLimitEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户id
	private Integer uid;
	//额度
	private BigDecimal highlimit;
	//剩余
	private BigDecimal remain;
	private Integer purchasetypeid;
	private String positionName;
	private String officename;
	private String departname;
	private String mobile;
	private String purchasetypename;
	private String username;
	private String realname;
	private String ids;
	//账户级别 1：司局，2：处室
	private Integer type ;
	
	
	
	
	
	

	public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getIds() {
        return ids;
    }
    public void setIds(String ids) {
        this.ids = ids;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public String getOfficename() {
        return officename;
    }
    public void setOfficename(String officename) {
        this.officename = officename;
    }
    public String getDepartname() {
        return departname;
    }
    public void setDepartname(String departname) {
        this.departname = departname;
    }
    public String getMobile() {
        return mobile;
    } 
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPurchasetypename() {
        return purchasetypename;
    }
    public void setPurchasetypename(String purchasetypename) {
        this.purchasetypename = purchasetypename;
    }
    public Integer getPurchasetypeid() {
        return purchasetypeid;
    }
    public void setPurchasetypeid(Integer purchasetypeid) {
        this.purchasetypeid = purchasetypeid;
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
	 * 设置：用户id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：额度
	 */
	public void setHighlimit(BigDecimal highlimit) {
		this.highlimit = highlimit;
	}
	/**
	 * 获取：额度
	 */
	public BigDecimal getHighlimit() {
		return highlimit;
	}
	/**
	 * 设置：剩余
	 */
	public void setRemain(BigDecimal remain) {
		this.remain = remain;
	}
	/**
	 * 获取：剩余
	 */
	public BigDecimal getRemain() {
		return remain;
	}
}
