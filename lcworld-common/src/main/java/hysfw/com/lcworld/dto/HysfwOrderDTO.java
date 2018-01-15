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
public class HysfwOrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//
	private String ordercode;
	//下单时间
	private Date createtime;
	//期望送达时间
	private Date expertarrivaltme;
	//会议室名称
	private String conferencename;
	//参会领导
	private String attentdleader;
	//会议类型
	private String conferencetype;
	//会场设备名
	private String name;
	//会场人数
	private String attendnum;
	//下单人姓名
	private String username;
	//预留电话
	private String mobile;
	//订单状态1:已下单，2：配送中，3：已完成，4：待评价，5：已取消
	private Integer status;
	//图片
	private String imgs;
	//备注
	private String remark;
	//送达时间
	private String date;
	//设备id
	private String equipmentids;
	
	public String getEquipmentids() {
		return equipmentids;
	}
	public void setEquipmentids(String equipmentids) {
		this.equipmentids = equipmentids;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
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
	public String getConferencename() {
		return conferencename;
	}
	public void setConferencename(String conferencename) {
		this.conferencename = conferencename;
	}
	public String getAttentdleader() {
		return attentdleader;
	}
	public void setAttentdleader(String attentdleader) {
		this.attentdleader = attentdleader;
	}
	public String getConferencetype() {
		return conferencetype;
	}
	public void setConferencetype(String conferencetype) {
		this.conferencetype = conferencetype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttendnum() {
		return attendnum;
	}
	public void setAttendnum(String attendnum) {
		this.attendnum = attendnum;
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
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
	
	
}
