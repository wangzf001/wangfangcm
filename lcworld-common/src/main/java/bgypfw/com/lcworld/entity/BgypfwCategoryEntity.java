package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public class BgypfwCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//分类名称
	private String cname;
	//父分类id
	private Integer pid;
	//分类级别(这里就分两级)
	private Integer cgrade;
	//分类图片
	private String img;
	//父级名称
	private String pname;

	/**
	 * 设置：
	 */
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：分类名称
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 获取：分类名称
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * 设置：父分类id
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父分类id
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * 设置：分类级别(这里就分两级)
	 */
	public void setCgrade(Integer cgrade) {
		this.cgrade = cgrade;
	}
	/**
	 * 获取：分类级别(这里就分两级)
	 */
	public Integer getCgrade() {
		return cgrade;
	}
	/**
	 * 设置：分类图片
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：分类图片
	 */
	public String getImg() {
		return img;
	}
}
