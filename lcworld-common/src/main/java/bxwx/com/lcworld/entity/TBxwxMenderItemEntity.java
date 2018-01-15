package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 维修人维修项
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public class TBxwxMenderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//维修人id
	private Integer menderid;
	//维修项id
	private Integer itemid;
	private String itemname;

	public String getItemname() {
        return itemname;
    }
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
    /**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：维修人id
	 */
	public void setMenderid(Integer menderid) {
		this.menderid = menderid;
	}
	/**
	 * 获取：维修人id
	 */
	public Integer getMenderid() {
		return menderid;
	}
	/**
	 * 设置：维修项id
	 */
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	/**
	 * 获取：维修项id
	 */
	public Integer getItemid() {
		return itemid;
	}
}
