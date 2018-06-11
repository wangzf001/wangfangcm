package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 广告管理
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2018-01-17 20:32:57
 */
public class WfAdInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//广告标题
	private String adtitle;
	//广告描述
	private String addesc;
	//广告图片地址
	private String imgurl;
	//广告状态(1.正常 0.下线)
	private Integer adstatus;
	//广告开始时间
	private Date adstarttime;
	//广告结束时间
	private Date adendtime;
	//广告类型（1.h5 页面 2.课程视频 3.秒杀活动4.商品5.年卡包 6.章节）
	private Integer adtype;
	//h5链接(广告类型为1时，此字段不允许为空）
	private String h5url;
	//课程id(如果广告类型是2，此字段不允许为空)
	private String courseid;
	//排序
	private Integer ordernum;

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
	 * 设置：广告标题
	 */
	public void setAdtitle(String adtitle) {
		this.adtitle = adtitle;
	}
	/**
	 * 获取：广告标题
	 */
	public String getAdtitle() {
		return adtitle;
	}
	/**
	 * 设置：广告描述
	 */
	public void setAddesc(String addesc) {
		this.addesc = addesc;
	}
	/**
	 * 获取：广告描述
	 */
	public String getAddesc() {
		return addesc;
	}
	/**
	 * 设置：广告图片地址
	 */
	public void setImgurl(String imgurl) {
		//http://wangfang.oss-cn-qingdao.aliyuncs.com/wf_course/imgPath/5bd087d55fd64fe9a31b3c90f39610e8.jpg
		this.imgurl = imgurl;
	}
	/**
	 * 获取：广告图片地址
	 */
	public String getImgurl() {
		return imgurl;
	}
	/**
	 * 设置：广告状态(1.正常 0.下线)
	 */
	public void setAdstatus(Integer adstatus) {
		this.adstatus = adstatus;
	}
	/**
	 * 获取：广告状态(1.正常 0.下线)
	 */
	public Integer getAdstatus() {
		return adstatus;
	}
	/**
	 * 设置：广告开始时间
	 */
	public void setAdstarttime(Date adstarttime) {
		this.adstarttime = adstarttime;
	}
	/**
	 * 获取：广告开始时间
	 */
	public Date getAdstarttime() {
		return adstarttime;
	}
	/**
	 * 设置：广告结束时间
	 */
	public void setAdendtime(Date adendtime) {
		this.adendtime = adendtime;
	}
	/**
	 * 获取：广告结束时间
	 */
	public Date getAdendtime() {
		return adendtime;
	}
	/**
	 * 设置：广告类型（1.h5 页面 2.课程视频 3.秒杀活动4.商品5.年卡包 6.章节）
	 */
	public void setAdtype(Integer adtype) {
		this.adtype = adtype;
	}
	/**
	 * 获取：广告类型（1.h5 页面 2.课程视频 3.秒杀活动4.商品5.年卡包 6.章节）
	 */
	public Integer getAdtype() {
		return adtype;
	}
	/**
	 * 设置：h5链接(广告类型为1时，此字段不允许为空）
	 */
	public void setH5url(String h5url) {
		this.h5url = h5url;
	}
	/**
	 * 获取：h5链接(广告类型为1时，此字段不允许为空）
	 */
	public String getH5url() {
		return h5url;
	}
	/**
	 * 设置：课程id(如果广告类型是2，此字段不允许为空)
	 */
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	/**
	 * 获取：课程id(如果广告类型是2，此字段不允许为空)
	 */
	public String getCourseid() {
		return courseid;
	}
	/**
	 * 设置：排序
	 */
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrdernum() {
		return ordernum;
	}
}
