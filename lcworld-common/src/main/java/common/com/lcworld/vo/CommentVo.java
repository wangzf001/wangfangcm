package com.lcworld.vo;

import java.util.Date;

public class CommentVo {
		//编号
		private Integer id;
		//图片
		private String imgs;
		//评价内容
		private String content;
		//用户编号
		private Integer uid;
		//总分
		private Double score;
		//服务分数
	    private Double servicescore;
	    //产品分
	    private Double productscore;
	    //是否匿名(1:是，0：不是)
	    private Integer anonymous;
	    private String type;
	    
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
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
		public Integer getUid() {
			return uid;
		}
		public void setUid(Integer uid) {
			this.uid = uid;
		}
		public Double getScore() {
			return score;
		}
		public void setScore(Double score) {
			this.score = score;
		}
		public Double getServicescore() {
			return servicescore;
		}
		public void setServicescore(Double servicescore) {
			this.servicescore = servicescore;
		}
		public Double getProductscore() {
			return productscore;
		}
		public void setProductscore(Double productscore) {
			this.productscore = productscore;
		}
		public Integer getAnonymous() {
			return anonymous;
		}
		public void setAnonymous(Integer anonymous) {
			this.anonymous = anonymous;
		}
	    
}
