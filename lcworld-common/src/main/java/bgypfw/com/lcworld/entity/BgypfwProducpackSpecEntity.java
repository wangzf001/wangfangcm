package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 办公用品服务-包装规格表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 20:10:15
 */
public class BgypfwProducpackSpecEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//包装规格名称（例如：包、个、套）
	private String specName;

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
	 * 设置：包装规格名称（例如：包、个、套）
	 */
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	/**
	 * 获取：包装规格名称（例如：包、个、套）
	 */
	public String getSpecName() {
		return specName;
	}
}
