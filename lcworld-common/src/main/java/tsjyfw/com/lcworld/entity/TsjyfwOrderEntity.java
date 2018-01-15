package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 图书借阅服务--借阅图书
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 19:59:50
 */
public class TsjyfwOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//借阅人id
	private Integer uid;
	//借阅人房间id
	private Integer roomid;
	//图书id
	private Integer bookid;
	//借阅状态借阅状态 1:已预订，2:已借阅,3：已归还，4：待评价，5：已取消,
	private Integer status;
	//下单时间
	private Date createtime;
	//预约取书时间
	private String invitegetbooktime;
	//借阅人姓名
	private String uname;
	//借阅人电话
	private String umobile;
	//订单号
	private String ordercode;
	//是否删除
	private Integer isdel;
	//订单状态改变 1:改变，0：没
	private Integer changes;
	
	 //取消原因
    private String cancelreason;
    private Integer copyid;
    private String searchid;
    
    
    

    public String getSearchid() {
        return searchid;
    }
    public void setSearchid(String searchid) {
        this.searchid = searchid;
    }
    public Integer getCopyid() {
        return copyid;
    }
    public void setCopyid(Integer copyid) {
        this.copyid = copyid;
    }
    public String getCancelreason() {
        return cancelreason;
    }
    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
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
	 * 设置：借阅人id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：借阅人id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：借阅人房间id
	 */
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	/**
	 * 获取：借阅人房间id
	 */
	public Integer getRoomid() {
		return roomid;
	}
	/**
	 * 设置：图书id
	 */
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	/**
	 * 获取：图书id
	 */
	public Integer getBookid() {
		return bookid;
	}
	/**
	 * 设置：借阅状态借阅状态 1:已预订，2:已借阅,3：已归还，4：待评价，5：已取消,
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：借阅状态借阅状态 1:已预订，2:已借阅,3：已归还，4：待评价，5：已取消,
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：下单时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：下单时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：预约取书时间
	 */
	public void setInvitegetbooktime(String invitegetbooktime) {
		this.invitegetbooktime = invitegetbooktime;
	}
	/**
	 * 获取：预约取书时间
	 */
	public String getInvitegetbooktime() {
		return invitegetbooktime;
	}
	/**
	 * 设置：借阅人姓名
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}
	/**
	 * 获取：借阅人姓名
	 */
	public String getUname() {
		return uname;
	}
	/**
	 * 设置：借阅人电话
	 */
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	/**
	 * 获取：借阅人电话
	 */
	public String getUmobile() {
		return umobile;
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
	/**
	 * 设置：是否删除
	 */
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getIsdel() {
		return isdel;
	}
	/**
	 * 设置：订单状态改变 1:改变，0：没
	 */
	public void setChanges(Integer changes) {
		this.changes = changes;
	}
	/**
	 * 获取：订单状态改变 1:改变，0：没
	 */
	public Integer getChanges() {
		return changes;
	}
}
