package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * VIEW
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:24
 */
public class TsjyfwBookFavorcounviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//实体id
	private Integer bookid;
	//
	private Long count;

	/**
	 * 设置：实体id
	 */
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	/**
	 * 获取：实体id
	 */
	public Integer getBookid() {
		return bookid;
	}
	/**
	 * 设置：
	 */
	public void setCount(Long count) {
		this.count = count;
	}
	/**
	 * 获取：
	 */
	public Long getCount() {
		return count;
	}
}
