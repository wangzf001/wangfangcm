package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
/**
 * @author leojr
 *
 */
public class TYytcMealEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	//
	private Integer mId;
	//题目
	private String mTitle;
	//创建时间
	private Date mCreateTime;
	//来源
	private String mSource;
	//菜单类型(1今日菜谱2营养套餐)
	private Integer mType;
	//时间类型(1早2中3晚)
	private Integer mTimeType;
	//菜单配图
	private String mImg;
	//菜单简介
	private String mContent;
	//浏览人数
	private Integer mScanNum;
	//点赞人数
	private Integer mPraiseNum;
	//收藏人数
	private Integer mFavorNum;
	//摄入热量(卡路里)
	private String mCalories;
	//步骤
	private String stepStr;
	//食材
	private String ingredientStr;
	//推荐1推荐
	private Integer mRecommend;
	//静态地址
	private String mUrl;
	//消耗方式
	private String mConsumeWay;
	//功效
	private String mHealthyFunction;
	//食材
	private List<TYytcIngredientEntity> ingredientList = new ArrayList<TYytcIngredientEntity>();
	//步骤
	private List<TYytcStepEntity> stepList = new ArrayList<TYytcStepEntity>();
	
	
	public Integer getmRecommend() {
		return mRecommend;
	}
	public void setmRecommend(Integer mRecommend) {
		this.mRecommend = mRecommend;
	}
	public String getmUrl() {
		return mUrl;
	}
	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}
	public String getmConsumeWay() {
		return mConsumeWay;
	}
	public void setmConsumeWay(String mConsumeWay) {
		this.mConsumeWay = mConsumeWay;
	}
	public String getmHealthyFunction() {
		return mHealthyFunction;
	}
	public void setmHealthyFunction(String mHealthyFunction) {
		this.mHealthyFunction = mHealthyFunction;
	}
	public String getStepStr() {
		return stepStr;
	}
	public void setStepStr(String stepStr) {
		this.stepStr = stepStr;
	}
	public String getIngredientStr() {
		return ingredientStr;
	}
	public void setIngredientStr(String ingredientStr) {
		this.ingredientStr = ingredientStr;
	}
	public List<TYytcStepEntity> getStepList() {
		return stepList;
	}
	public void setStepList(List<TYytcStepEntity> stepList) {
		this.stepList = stepList;
	}
	public List<TYytcIngredientEntity> getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(List<TYytcIngredientEntity> ingredientList) {
		this.ingredientList = ingredientList;
	}
	public Integer getmTimeType() {
		return mTimeType;
	}
	public void setmTimeType(Integer mTimeType) {
		this.mTimeType = mTimeType;
	}
	/**
	 * 设置：
	 */
	public void setMId(Integer mId) {
		this.mId = mId;
	}
	/**
	 * 获取：
	 */
	public Integer getMId() {
		return mId;
	}
	/**
	 * 设置：题目
	 */
	public void setMTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	/**
	 * 获取：题目
	 */
	public String getMTitle() {
		return mTitle;
	}
	/**
	 * 设置：创建时间
	 */
	public void setMCreateTime(Date mCreateTime) {
		this.mCreateTime = mCreateTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getMCreateTime() {
		return mCreateTime;
	}
	/**
	 * 设置：来源
	 */
	public void setMSource(String mSource) {
		this.mSource = mSource;
	}
	/**
	 * 获取：来源
	 */
	public String getMSource() {
		return mSource;
	}
	/**
	 * 设置：菜单类型(1早2中3晚)
	 */
	public void setMType(Integer mType) {
		this.mType = mType;
	}
	/**
	 * 获取：菜单类型(1早2中3晚)
	 */
	public Integer getMType() {
		return mType;
	}
	/**
	 * 设置：菜单配图
	 */
	public void setMImg(String mImg) {
		this.mImg = mImg;
	}
	/**
	 * 获取：菜单配图
	 */
	public String getMImg() {
		return mImg;
	}
	/**
	 * 设置：菜单简介
	 */
	public void setMContent(String mContent) {
		this.mContent = mContent;
	}
	/**
	 * 获取：菜单简介
	 */
	public String getMContent() {
		return mContent;
	}
	/**
	 * 设置：浏览人数
	 */
	public void setMScanNum(Integer mScanNum) {
		this.mScanNum = mScanNum;
	}
	/**
	 * 获取：浏览人数
	 */
	public Integer getMScanNum() {
		return mScanNum;
	}
	/**
	 * 设置：点赞人数
	 */
	public void setMPraiseNum(Integer mPraiseNum) {
		this.mPraiseNum = mPraiseNum;
	}
	/**
	 * 获取：点赞人数
	 */
	public Integer getMPraiseNum() {
		return mPraiseNum;
	}
	/**
	 * 设置：收藏人数
	 */
	public void setMFavorNum(Integer mFavorNum) {
		this.mFavorNum = mFavorNum;
	}
	/**
	 * 获取：收藏人数
	 */
	public Integer getMFavorNum() {
		return mFavorNum;
	}
	/**
	 * 设置：摄入热量(卡路里)
	 */
	public void setMCalories(String mCalories) {
		this.mCalories = mCalories;
	}
	/**
	 * 获取：摄入热量(卡路里)
	 */
	public String getMCalories() {
		return mCalories;
	}
}
