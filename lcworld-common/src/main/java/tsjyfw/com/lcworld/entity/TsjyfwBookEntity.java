package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 图书借阅服务-图书
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 18:26:14
 */
public class TsjyfwBookEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //
    private Integer id;
    //标题
    private String title;
    //作者
    private String author;
    //出版社
    private String publisher;
    //作者简介
    private String authorbrief;
    //创建时间
    private Date createtime;
    //封面
    private String img;
    //内容简介
    private String brief;
    //图书分类id
    private Integer typeid;
    //借阅状态(1:在架上，2：已借出)
    private Integer bollowstatus;
    //分数
    private Double score;
    //列表页简介
    private String shortbrief;
    //静态页内容
    private String content;
    //静态页地址
    private String url;
    //收藏量
    private Integer favorcount;
    //借阅量
    private Integer borrowcount;
    //是否下架 1：是，0：否
    private Integer isdel;
    private String isbn;
    private String subtitle;
    private String keyword;
    private String barcode;
    private String searchid;
    
    
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getSearchid() {
        return searchid;
    }
    public void setSearchid(String searchid) {
        this.searchid = searchid;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
     * 设置：作者简介
     */
    public void setAuthorbrief(String authorbrief) {
        this.authorbrief = authorbrief;
    }
    /**
     * 获取：作者简介
     */
    public String getAuthorbrief() {
        return authorbrief;
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
     * 设置：封面
     */
    public void setImg(String img) {
        this.img = img;
    }
    /**
     * 获取：封面
     */
    public String getImg() {
        return img;
    }
    /**
     * 设置：内容简介
     */
    public void setBrief(String brief) {
        this.brief = brief;
    }
    /**
     * 获取：内容简介
     */
    public String getBrief() {
        return brief;
    }
    /**
     * 设置：图书分类id
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
    /**
     * 获取：图书分类id
     */
    public Integer getTypeid() {
        return typeid;
    }
    /**
     * 设置：借阅状态(1:在架上，2：已借出)
     */
    public void setBollowstatus(Integer bollowstatus) {
        this.bollowstatus = bollowstatus;
    }
    /**
     * 获取：借阅状态(1:在架上，2：已借出)
     */
    public Integer getBollowstatus() {
        return bollowstatus;
    }
    /**
     * 设置：分数
     */
    public void setScore(Double score) {
        this.score = score;
    }
    /**
     * 获取：分数
     */
    public Double getScore() {
        return score;
    }
    /**
     * 设置：列表页简介
     */
    public void setShortbrief(String shortbrief) {
        this.shortbrief = shortbrief;
    }
    /**
     * 获取：列表页简介
     */
    public String getShortbrief() {
        return shortbrief;
    }
    /**
     * 设置：静态页内容
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 获取：静态页内容
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：静态页地址
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * 获取：静态页地址
     */
    public String getUrl() {
        return url;
    }
    /**
     * 设置：收藏量
     */
    public void setFavorcount(Integer favorcount) {
        this.favorcount = favorcount;
    }
    /**
     * 获取：收藏量
     */
    public Integer getFavorcount() {
        return favorcount;
    }
    /**
     * 设置：借阅量
     */
    public void setBorrowcount(Integer borrowcount) {
        this.borrowcount = borrowcount;
    }
    /**
     * 获取：借阅量
     */
    public Integer getBorrowcount() {
        return borrowcount;
    }
    /**
     * 设置：是否下架 1：是，0：否
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
    /**
     * 获取：是否下架 1：是，0：否
     */
    public Integer getIsdel() {
        return isdel;
    }
}
