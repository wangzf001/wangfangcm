package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;

import com.lcworld.utils.DateUtil;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.util.ValidateUtil;



/**
 * 公告
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
public class TNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//分类id
	private Integer typeid;
	//标题
	private String title;
	//创建时间
	private Date createtime;
	//来源
	private String source;
	//内容
	private String content;
	//图片
	private String img;
	//静态文件地址
	private String url;
	//是否推荐1推荐
	private Integer isRecommend;
	//收藏个数
	private Integer favorNum;
	//收藏个数
	private String typeName;
	

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getFavorNum() {
		return favorNum;
	}
	public void setFavorNum(Integer favorNum) {
		this.favorNum = favorNum;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
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
	 * 设置：分类id
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	/**
	 * 获取：分类id
	 */
	public Integer getTypeid() {
		return typeid;
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
	 * 设置：来源
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：来源
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：图片
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：图片
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：静态文件地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：静态文件地址
	 */
	public String getUrl() {
		return url;
	}
}
