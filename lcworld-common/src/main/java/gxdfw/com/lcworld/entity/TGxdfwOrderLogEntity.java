package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 干洗店服务-订单记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:52
 */
public class TGxdfwOrderLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//状态 1: 下单，2：代送件，3：洗涤中，4：已完成，5：取件，6：支付，7：评价
	private Integer status;
	//创建时间
	private Date createtime;
	//订单id
	private Integer orderid;

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
	 * 设置：状态 1: 下单，2：代送件，3：洗涤中，4：已完成，5：取件，6：支付，7：评价
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1: 下单，2：代送件，3：洗涤中，4：已完成，5：取件，6：支付，7：评价
	 */
	public Integer getStatus() {
		return status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 设置：订单id
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：订单id
	 */
	public Integer getOrderid() {
		return orderid;
	}
}
