package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 团队活动系统-活动
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 16:14:47
 */
public class TdhdActivityEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//活动id
	private Integer aId;
	//主题
	private String aTitle;
	//地址
	private String aPlace;
	//电话
	private String aContactMobile;
	//时间
	private String aTime;
	//小图片
	private String aImg;
	//已报名人数
	private Integer aSignCount;
	//总人数
	private Integer aTotal;
	//状态 1： 进行中，2：结束报名
	private Integer aStatus;
	//内容
	private String aContent;
	//主办方
	private String aSponsor;
	//
	private Date aCreateTime;
	//审核 -1 不过，0： 待审核。1：过
	private Integer aIsChecked;
	//发布人id
	private Integer aUserId;
	//活动图片
	private String aPhoto;
	//发布人姓名
	private String aRealname;
	//发布人电话
	private String aMobile;
	//民族
	private String aNation;
	private Integer failurereasonid;
	private String failurereason;
	private TdhdActivityDemandEntity demand;
	
	public Integer getFailurereasonid() {
        return failurereasonid;
    }
    public void setFailurereasonid(Integer failurereasonid) {
        this.failurereasonid = failurereasonid;
    }
    public String getFailurereason() {
        return failurereason;
    }
    public void setFailurereason(String failurereason) {
        this.failurereason = failurereason;
    }
    private List<String> imgEntityList = new ArrayList<String>();
    public Integer getaId() {
        return aId;
    }
    public void setaId(Integer aId) {
        this.aId = aId;
    }
    public String getaTitle() {
        return aTitle;
    }
    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }
    public String getaPlace() {
        return aPlace;
    }
    public void setaPlace(String aPlace) {
        this.aPlace = aPlace;
    }
    public String getaContactMobile() {
        return aContactMobile;
    }
    public void setaContactMobile(String aContactMobile) {
        this.aContactMobile = aContactMobile;
    }
    public String getaTime() {
        return aTime;
    }
    public void setaTime(String aTime) {
        this.aTime = aTime;
    }
    public String getaImg() {
        return aImg;
    }
    public void setaImg(String aImg) {
        this.aImg = aImg;
    }
    public Integer getaSignCount() {
        return aSignCount;
    }
    public void setaSignCount(Integer aSignCount) {
        this.aSignCount = aSignCount;
    }
    public Integer getaTotal() {
        return aTotal;
    }
    public void setaTotal(Integer aTotal) {
        this.aTotal = aTotal;
    }
    public Integer getaStatus() {
        return aStatus;
    }
    public void setaStatus(Integer aStatus) {
        this.aStatus = aStatus;
    }
    public String getaContent() {
        return aContent;
    }
    public void setaContent(String aContent) {
        this.aContent = aContent;
    }
    public String getaSponsor() {
        return aSponsor;
    }
    public void setaSponsor(String aSponsor) {
        this.aSponsor = aSponsor;
    }
    public Date getaCreateTime() {
        return aCreateTime;
    }
    public void setaCreateTime(Date aCreateTime) {
        this.aCreateTime = aCreateTime;
    }
    public Integer getaIsChecked() {
        return aIsChecked;
    }
    public void setaIsChecked(Integer aIsChecked) {
        this.aIsChecked = aIsChecked;
    }
    public Integer getaUserId() {
        return aUserId;
    }
    public void setaUserId(Integer aUserId) {
        this.aUserId = aUserId;
    }
    public String getaPhoto() {
        return aPhoto;
    }
    public void setaPhoto(String aPhoto) {
        this.aPhoto = aPhoto;
    }
    public String getaRealname() {
        return aRealname;
    }
    public void setaRealname(String aRealname) {
        this.aRealname = aRealname;
    }
    public String getaMobile() {
        return aMobile;
    }
    public void setaMobile(String aMobile) {
        this.aMobile = aMobile;
    }
    public String getaNation() {
        return aNation;
    }
    public void setaNation(String aNation) {
        this.aNation = aNation;
    }
    public List<String> getImgEntityList() {
        return imgEntityList;
    }
    public void setImgEntityList(List<String> imgEntityList) {
        this.imgEntityList = imgEntityList;
    }

    public TdhdActivityDemandEntity getDemand() {
        return demand;
    }

    public void setDemand(TdhdActivityDemandEntity demand) {
        this.demand = demand;
    }
}
