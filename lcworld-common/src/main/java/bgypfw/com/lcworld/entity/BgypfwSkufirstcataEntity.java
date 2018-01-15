package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 办公用品服务-规格分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public class BgypfwSkufirstcataEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//规格分类名称
	private String skucataname;
	//规格分类父id 若为0 表示一级分类
	private Integer pskucataid;
	//创建时间
	private Date createtime;

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
	 * 设置：规格分类名称
	 */
	public void setSkucataname(String skucataname) {
		this.skucataname = skucataname;
	}
	/**
	 * 获取：规格分类名称
	 */
	public String getSkucataname() {
		return skucataname;
	}
	/**
	 * 设置：规格分类父id 若为0 表示一级分类
	 */
	public void setPskucataid(Integer pskucataid) {
		this.pskucataid = pskucataid;
	}
	/**
	 * 获取：规格分类父id 若为0 表示一级分类
	 */
	public Integer getPskucataid() {
		return pskucataid;
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
}
