package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 医疗服务专家坐诊医生表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public class YlfwZjzzExpertEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//医生名称
	private String realname;
	//职称
	private Integer positionid;
	//电话
	private String mobile;
	//医院
	private String hospitalid;
	//问诊量
	private Integer consultcount;
	//分数
	private Double score;
	//创建时间
	private Date createtime;
	//是否有效 1:有效，0-：无效
	private Integer valid;
	//简介
	private String brief;
	//图片
	private String photo;
	//预约人数
	private Integer invitetotalcount;
	//已预约人数
	private Integer remaincount;
	//工作时间
	private String worktime;
	//擅长
	private String skilledinfo;

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
	 * 设置：医生名称
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * 获取：医生名称
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * 设置：职称
	 */
	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}
	/**
	 * 获取：职称
	 */
	public Integer getPositionid() {
		return positionid;
	}
	/**
	 * 设置：电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：电话
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：医院
	 */
	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}
	/**
	 * 获取：医院
	 */
	public String getHospitalid() {
		return hospitalid;
	}
	/**
	 * 设置：问诊量
	 */
	public void setConsultcount(Integer consultcount) {
		this.consultcount = consultcount;
	}
	/**
	 * 获取：问诊量
	 */
	public Integer getConsultcount() {
		return consultcount;
	}
	/**
	 * 设置：分数
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	/**
	 * 获取：分数
	 */
	public Double getScore() {
		return score;
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
	 * 设置：是否有效 1:有效，0-：无效
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	/**
	 * 获取：是否有效 1:有效，0-：无效
	 */
	public Integer getValid() {
		return valid;
	}
	/**
	 * 设置：简介
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}
	/**
	 * 获取：简介
	 */
	public String getBrief() {
		return brief;
	}
	/**
	 * 设置：图片
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 获取：图片
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * 设置：预约人数
	 */
	public void setInvitetotalcount(Integer invitetotalcount) {
		this.invitetotalcount = invitetotalcount;
	}
	/**
	 * 获取：预约人数
	 */
	public Integer getInvitetotalcount() {
		return invitetotalcount;
	}
	/**
	 * 设置：已预约人数
	 */
	public void setRemaincount(Integer remaincount) {
		this.remaincount = remaincount;
	}
	/**
	 * 获取：已预约人数
	 */
	public Integer getRemaincount() {
		return remaincount;
	}
	/**
	 * 设置：工作时间
	 */
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	/**
	 * 获取：工作时间
	 */
	public String getWorktime() {
		return worktime;
	}
	/**
	 * 设置：擅长
	 */
	public void setSkilledinfo(String skilledinfo) {
		this.skilledinfo = skilledinfo;
	}
	/**
	 * 获取：擅长
	 */
	public String getSkilledinfo() {
		return skilledinfo;
	}
}
