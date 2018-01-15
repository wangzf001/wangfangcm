package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户对公账户额度日志表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-26 17:31:54
 */
public class UserPurchaseLimilogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//服务号
	private String servicecode;
	//金额
	private String money;
	//创建时间
	private Date createtime;
	//用户id
	private Integer uid;
	private Integer limitid;
	private Integer usetype;
	

	public Integer getLimitid() {
        return limitid;
    }
    public void setLimitid(Integer limitid) {
        this.limitid = limitid;
    }
    public Integer getUsetype() {
        return usetype;
    }
    public void setUsetype(Integer usetype) {
        this.usetype = usetype;
    }
    /**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：服务号
	 */
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	/**
	 * 获取：服务号
	 */
	public String getServicecode() {
		return servicecode;
	}
	/**
	 * 设置：金额
	 */
	public void setMoney(String money) {
		this.money = money;
	}
	/**
	 * 获取：金额
	 */
	public String getMoney() {
		return money;
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
}
