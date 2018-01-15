package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.jiguang.common.utils.StringUtils;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public class HysfwOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//会议室id
	private Integer roomid;
	//设备id
	private String equipmentids;
	//服务id
	private String serviceids;
	//会议室类型
	private Integer conferencetype;
	//会议类型名称
	private String conferencetypename;
	//会议名称
	private String conferencename;
	//参会人数
	private Integer attendnum;
	//参会领导(逗号分隔)
	private String attentdleader;
	//备注
	private String remark;
	//图片
	private String imgs;
	private List<String>  imglist;
	//订单号
	private String ordercode;
	//用户id
	private Integer uid;
	//订单状态1待服务2服务中3已完成4待评价5已取消6已删除
	private Integer status;
	//
	private String reasoncontent;
	//
	private Integer checkstatus;
	//
	private String username;
	//
	private String mobile;
	//
	private Date createtime;
	//
	private String failedcontent;
	private String serviceListStr;
	private String equipmentListStr;
	//会议室名称
	private String roomname;
	//服务端已取消订单
	private Integer realdel;
	//服务人员id
	private Integer menderid;
	
	public List<String> getImglist() {
		return imglist;
	}

	public void setImglist(String imgs) {
		List<String> imglist = new ArrayList<String>();
		if(StringUtils.isNotEmpty(imgs)){
			String[] imgArr = imgs.split(",");
	    	imglist = Arrays.asList(imgArr);
		}
	    this.imglist = imglist;
	}
	
	public Integer getMenderid() {
		return menderid;
	}

	public void setMenderid(Integer menderid) {
		this.menderid = menderid;
	}

	public Integer getRealdel() {
		return realdel;
	}

	public void setRealdel(Integer realdel) {
		this.realdel = realdel;
	}

	public String getConferencetypename() {
		return conferencetypename;
	}

	public void setConferencetypename(String conferencetypename) {
		this.conferencetypename = conferencetypename;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getServiceListStr() {
		return serviceListStr;
	}
	public void setServiceListStr(String serviceListStr) {
		this.serviceListStr = serviceListStr;
	}
	public String getEquipmentListStr() {
		return equipmentListStr;
	}
	public void setEquipmentListStr(String equipmentListStr) {
		this.equipmentListStr = equipmentListStr;
	}
	public String getServiceids() {
		return serviceids;
	}
	public void setServiceids(String serviceids) {
		this.serviceids = serviceids;
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
	 * 设置：会议室id
	 */
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	/**
	 * 获取：会议室id
	 */
	public Integer getRoomid() {
		return roomid;
	}
	
	public String getEquipmentids() {
		return equipmentids;
	}
	public void setEquipmentids(String equipmentids) {
		this.equipmentids = equipmentids;
	}
	/**
	 * 设置：会议室类型
	 */
	public void setConferencetype(Integer conferencetype) {
		this.conferencetype = conferencetype;
	}
	/**
	 * 获取：会议室类型
	 */
	public Integer getConferencetype() {
		return conferencetype;
	}
	/**
	 * 设置：会议名称
	 */
	public void setConferencename(String conferencename) {
		this.conferencename = conferencename;
	}
	/**
	 * 获取：会议名称
	 */
	public String getConferencename() {
		return conferencename;
	}
	/**
	 * 设置：参会人数
	 */
	public void setAttendnum(Integer attendnum) {
		this.attendnum = attendnum;
	}
	/**
	 * 获取：参会人数
	 */
	public Integer getAttendnum() {
		return attendnum;
	}
	/**
	 * 设置：参会领导(逗号分隔)
	 */
	public void setAttentdleader(String attentdleader) {
		this.attentdleader = attentdleader;
	}
	/**
	 * 获取：参会领导(逗号分隔)
	 */
	public String getAttentdleader() {
		return attentdleader;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：图片
	 */
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	/**
	 * 获取：图片
	 */
	public String getImgs() {
		return imgs;
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
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 设置：订单状态1待服务2服务中3已完成4待评价5已取消6已删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态1待服务2服务中3已完成4待评价5已取消6已删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setReasoncontent(String reasoncontent) {
		this.reasoncontent = reasoncontent;
	}
	/**
	 * 获取：
	 */
	public String getReasoncontent() {
		return reasoncontent;
	}
	/**
	 * 设置：
	 */
	public void setCheckstatus(Integer checkstatus) {
		this.checkstatus = checkstatus;
	}
	/**
	 * 获取：
	 */
	public Integer getCheckstatus() {
		return checkstatus;
	}
	/**
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：
	 */
	public void setFailedcontent(String failedcontent) {
		this.failedcontent = failedcontent;
	}
	/**
	 * 获取：
	 */
	public String getFailedcontent() {
		return failedcontent;
	}
}
