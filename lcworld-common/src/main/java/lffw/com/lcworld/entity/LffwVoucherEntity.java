package com.lcworld.entity;
 
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 理发服务--代金券
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-01 11:39:39
 */
public class LffwVoucherEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//房间号
	private Integer roomnum;
	//人员id
	private Integer uid;
	//代金券金额
	private BigDecimal price;
	//创建时间
	private Date createtime;
	//截止时间
	private Date endtime;
	//是否有效 1：是，0：否
	private Integer valid;
	//总数量
	private Integer tatal;
	//剩余数量
	private Integer remain;
	//支付密码
	private String paypass;
	//锁定数量
	private Integer lockcount;
	
	private LffwBarberEntity user;

	public LffwBarberEntity getUser() {
		return user;
	}
	public void setUser(LffwBarberEntity user) {
		this.user = user;
	}
	public Integer getLockcount() {
        return lockcount;
    }
    public void setLockcount(Integer lockcount) {
        this.lockcount = lockcount;
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
	public void setRoomnum(Integer roomnum) {
		this.roomnum = roomnum;
	}
	/**
	 * 获取：房间号
	 */
	public Integer getRoomnum() {
		return roomnum;
	}
	/**
	 * 设置：人员id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：人员id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：代金券金额
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：代金券金额
	 */
	public BigDecimal getPrice() {
		return price;
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
	 * 设置：截止时间
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	/**
	 * 获取：截止时间
	 */
	public Date getEndtime() {
		return endtime;
	}
	/**
	 * 设置：是否有效 1：是，0：否
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	/**
	 * 获取：是否有效 1：是，0：否
	 */
	public Integer getValid() {
		return valid;
	}
	/**
	 * 设置：总数量
	 */
	public void setTatal(Integer tatal) {
		this.tatal = tatal;
	}
	/**
	 * 获取：总数量
	 */
	public Integer getTatal() {
		return tatal;
	}
	/**
	 * 设置：剩余数量
	 */
	public void setRemain(Integer remain) {
		this.remain = remain;
	}
	/**
	 * 获取：剩余数量
	 */
	public Integer getRemain() {
		return remain;
	}
	/**
	 * 设置：支付密码
	 */
	public void setPaypass(String paypass) {
		this.paypass = paypass;
	}
	/**
	 * 获取：支付密码
	 */
	public String getPaypass() {
		return paypass;
	}
}
