package com.lcworld.dto;

public class YlfwCommentDTO {
    
    //编号
    private Integer id;
    //图片
    private String imgs;
    //评价内容
    private String content;
    //订单编号
    private Integer orderid;
    //用户编号
    private Integer uid;
    //医生编号
    private Integer doctorid;
    //创建时间
    private String createtime;
    //总分
    private Double score;
    //是否匿名(1:是，0：不是)
    private Integer anonymous;
    //服务分数
    private Double servicescore;
    
    private String photo;
    
    private String realname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

    public Double getServicescore() {
        return servicescore;
    }

    public void setServicescore(Double servicescore) {
        this.servicescore = servicescore;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    
}
