package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 基础数据-办公室
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
public class RoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//房间号
	private String code;
	//办公采购账号id
	private Integer purchaseid;
	//订水采购账号id
	private Integer dspurchaseid;
	private Integer buildnum;
	
	

	public Integer getDspurchaseid() {
        return dspurchaseid;
    }
    public void setDspurchaseid(Integer dspurchaseid) {
        this.dspurchaseid = dspurchaseid;
    }
    public Integer getBuildnum() {
        return buildnum;
    }
    public void setBuildnum(Integer buildnum) {
        this.buildnum = buildnum;
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
	 * 设置：房间号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：房间号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：采购账号id
	 */
	public void setPurchaseid(Integer purchaseid) {
		this.purchaseid = purchaseid;
	}
	/**
	 * 获取：采购账号id
	 */
	public Integer getPurchaseid() {
		return purchaseid;
	}
}
