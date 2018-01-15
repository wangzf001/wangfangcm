package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 17:48:09
 */
public class TsjyfwBookCopyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String searchid;
	//是否正本 1：是 0：否
	private Integer original;
	//图书id
	private Integer bookid;
	//借阅状态 1:在架上，2：已借出
	private Integer borrowstatus;
	private String bookname;
	private Integer borrowcount;
	private String title;
	private String subtitle;
	
	
	

	public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public Integer getBorrowcount() {
        return borrowcount;
    }
    public void setBorrowcount(Integer borrowcount) {
        this.borrowcount = borrowcount;
    }
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
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
	 * 设置：
	 */
	public void setSearchid(String searchid) {
		this.searchid = searchid;
	}
	/**
	 * 获取：
	 */
	public String getSearchid() {
		return searchid;
	}
	/**
	 * 设置：是否正本 1：是 0：否
	 */
	public void setOriginal(Integer original) {
		this.original = original;
	}
	/**
	 * 获取：是否正本 1：是 0：否
	 */
	public Integer getOriginal() {
		return original;
	}
	/**
	 * 设置：图书id
	 */
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	/**
	 * 获取：图书id
	 */
	public Integer getBookid() {
		return bookid;
	}
	/**
	 * 设置：借阅状态 1:在架上，2：已借出
	 */
	public void setBorrowstatus(Integer borrowstatus) {
		this.borrowstatus = borrowstatus;
	}
	/**
	 * 获取：借阅状态 1:在架上，2：已借出
	 */
	public Integer getBorrowstatus() {
		return borrowstatus;
	}
}
