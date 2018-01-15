package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 处室
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 18:37:02
 */
public class OfficeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//处室名称
	private Integer id;
	//
	private String name;
	//部门
	private Integer departid;
	//办公用品权限下放 1: 下放，0：否
	private Integer bgypright;
	private String departname;
	private String typename;
	private Integer uid;
	private String realname;
	
	
	
	

	public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getTypename() {
        return typename;
    }
    public void setTypename(String typename) {
        this.typename = typename;
    }
    public String getDepartname() {
        return departname;
    }
    public void setDepartname(String departname) {
        this.departname = departname;
    }
    /**
	 * 设置：处室名称
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：处室名称
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：部门
	 */
	public void setDepartid(Integer departid) {
		this.departid = departid;
	}
	/**
	 * 获取：部门
	 */
	public Integer getDepartid() {
		return departid;
	}
	/**
	 * 设置：办公用品权限下放 1: 下放，0：否
	 */
	public void setBgypright(Integer bgypright) {
		this.bgypright = bgypright;
	}
	/**
	 * 获取：办公用品权限下放 1: 下放，0：否
	 */
	public Integer getBgypright() {
		return bgypright;
	}
}
