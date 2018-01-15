package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 健康资讯
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:14:38
 */
public class HealthyinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//标题
	private String title;
	//创建时间
	private Date createtime;
	//资讯来源
	private String source;
	//内容
	private String content;
	//静态文件地址
	private String url;
	private String imgs;
	 

	public String getImgs() {
        return imgs;
    }
    public void setImgs(String imgs) {
        this.imgs = imgs;
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
	 * 设置：资讯来源
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：资讯来源
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：静态文件地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：静态文件地址
	 */
	public String getUrl() {
		return url;
	}
}
