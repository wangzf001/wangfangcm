package com.lcworld.dto;

public class TsjyfwBookDTO {
    //
    private Integer id;
    //标题
    private String title;
    //作者
    private String author;
    //出版社
    private String publisher;
    //封面
    private String img;
    //图书分类id
    private Integer typeid;
    //借阅状态(1:在架上，2：已借出)
    private Integer bollowstatus;
    //分数
    private Double score;
    //列表页简介
    private String shortbrief;
    
    private String url;
    private String brief;
    
    public String getBrief() {
        return brief;
    }
    public void setBrief(String brief) {
        this.brief = brief;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public Integer getTypeid() {
        return typeid;
    }
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
    public Integer getBollowstatus() {
        return bollowstatus;
    }
    public void setBollowstatus(Integer bollowstatus) {
        this.bollowstatus = bollowstatus;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public String getShortbrief() {
        return shortbrief;
    }
    public void setShortbrief(String shortbrief) {
        this.shortbrief = shortbrief;
    }
    
}
