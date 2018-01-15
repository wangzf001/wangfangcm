package com.lcworld.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 办公用品服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public class BgypfwOrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//订单主键
	private Integer id;
	//用户id
	private Integer uid;
	//订单号
	private String code;
	//创建时间
	private Date createtime;
	//期望送达时间
	private Date expertarrivaltme;
	//下单人姓名
	private String username;
	//预留电话
	private String mobile;
	//订单状态1:已下单，2：配送中，3：已完成，4：待评价，5：已取消
	private Integer status;
	//地址
	private String address;
	//图片
	private String img;
	//数量
	private String count;
	//总价
	private String totalprice;
	//备注
	private String remark;
	//商品名称
	private String productname;
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getExpertarrivaltme() {
		return expertarrivaltme;
	}
	public void setExpertarrivaltme(Date expertarrivaltme) {
		this.expertarrivaltme = expertarrivaltme;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}
