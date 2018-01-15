package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 图书借阅服务-读者推荐
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public class TsjyfwUserrecommandEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//标题
	private String title;
	//作者
	private String author;
	//出版社
	private String publisher;
	//备注
	private String remark;
	//
	private Integer uid;
	//创建时间
	private Date createtime;
	//处理状态(1: 未处理，2：已处理)
	private Integer status;

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
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：作者
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：出版社
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * 获取：出版社
	 */
	public String getPublisher() {
		return publisher;
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
	 * 设置：
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public Integer getUid() {
		return uid;
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
	 * 设置：处理状态(1: 未处理，2：已处理)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：处理状态(1: 未处理，2：已处理)
	 */
	public Integer getStatus() {
		return status;
	}
}
