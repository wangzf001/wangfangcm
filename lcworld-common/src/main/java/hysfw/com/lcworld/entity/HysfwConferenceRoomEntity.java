package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
/**
 * @author leojr
 *
 */
public class HysfwConferenceRoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private Integer id;
	//会议室名
	private String name;
	//容量
	private Integer capacity;
	//预定量
	private Integer usenumber;
	//地址
	private String address;
	//介绍
	private String introduce;
	//轮播图
	private String imgs;
	//轮播图
	private List<String> imgList = new ArrayList<>();
	//开放时段1上午2下午3全天
	private String area;
	//小图
	private String photo;
	//小图
	private Integer status;
	//设备名
	private String enames;
	//服务名
	private String snames;
	//设备列表
	private Set enameList;
	//服务列表
	private Set slist;
	//设备列表
	private List<Integer> equipmentList = new ArrayList<>();
	//服务列表
	private List<Integer> serviceList = new ArrayList<>();
	//图片列表
	private List<BgypfwProductimgEntity> imgEntityList = new ArrayList<>();
	
	public List<Integer> getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(List<Integer> equipmentList) {
		this.equipmentList = equipmentList;
	}
	public List<Integer> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<Integer> serviceList) {
		this.serviceList = serviceList;
	}
	public List<BgypfwProductimgEntity> getImgEntityList() {
		return imgEntityList;
	}
	public void setImgEntityList(List<BgypfwProductimgEntity> imgEntityList) {
		this.imgEntityList = imgEntityList;
	}
	public String getSnames() {
		return snames;
	}
	public void setSnames(String snames) {
		this.snames = snames;
	}
	public Set getSlist() {
		return slist;
	}
	public void setSlist(Set slist) {
		this.slist = slist;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	public String getEnames() {
		return enames;
	}
	public void setEnames(String enames) {
		this.enames = enames;
	}
	
	public Set getEnameList() {
		return enameList;
	}
	public void setEnameList(Set enameList) {
		this.enameList = enameList;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
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
	 * 设置：会议室名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：会议室名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：容量
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	/**
	 * 获取：容量
	 */
	public Integer getCapacity() {
		return capacity;
	}
	/**
	 * 设置：预定量
	 */
	public void setUsenumber(Integer usenumber) {
		this.usenumber = usenumber;
	}
	/**
	 * 获取：预定量
	 */
	public Integer getUsenumber() {
		return usenumber;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：介绍
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	/**
	 * 获取：介绍
	 */
	public String getIntroduce() {
		return introduce;
	}
	/**
	 * 设置：轮播图
	 */
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	/**
	 * 获取：轮播图
	 */
	public String getImgs() {
		return imgs;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}
