package com.lcworld.dto;

public class BxwxOrderDTO {
  //订单主键
    private Integer id;
    //维修项目id
    private Integer menditem;
    //维修人员名
    private String mendername;
    //维修项目
    private String itemname;
    
    //预约时间
    private String invitetime;
    private Integer buildId;
    private String buildName;
    //服务地点
    private String serviceplace;
    //维修人员id
    private Integer menderid;
    //1:待接单，2： 已接单，3：已完成，4:待评价，5：已取消
    private Integer orderstatus;
    //用户id
    private Integer uid;
    //下单时间
    private String createtime;
    //状态改变
    private Integer orderchange;
    //订单号
    private String ordercode;
    //维修人员电话
    private String mendermobile;
    //详情图片
    private String menderphoto;
    //分数
    private Double score;
    //问题描述
    private String mendcontent;
    //用户手机号
    private String phone;
    //用户名
    private String username;
    //用户头像
    private String avatar;
	/**
     * 是否支付 1：是，0：否
     */
    private Integer paystatus;
    //维修图片
    private String mendimgs;


    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getMendimgs() {
		return mendimgs;
	}
	public void setMendimgs(String mendimgs) {
		this.mendimgs = mendimgs;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMendcontent() {
		return mendcontent;
	}
	public void setMendcontent(String mendcontent) {
		this.mendcontent = mendcontent;
	}
    
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public Integer getPaystatus() {
        return paystatus;
    }
    public void setPaystatus(Integer paystatus) {
        this.paystatus = paystatus;
    }
  
    public String getInvitetime() {
        return invitetime;
    }
    public void setInvitetime(String invitetime) {
        this.invitetime = invitetime;
    }
    public String getServiceplace() {
        return serviceplace;
    }
    public void setServiceplace(String serviceplace) {
        this.serviceplace = serviceplace;
    }
    public Integer getMenderid() {
        return menderid;
    }
    public void setMenderid(Integer menderid) {
        this.menderid = menderid;
    }
    public Integer getOrderstatus() {
        return orderstatus;
    }
    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }
    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
 
    public Integer getOrderchange() {
        return orderchange;
    }
    public void setOrderchange(Integer orderchange) {
        this.orderchange = orderchange;
    }
    public String getOrdercode() {
        return ordercode;
    }
    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }
   
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMenditem() {
        return menditem;
    }
    public void setMenditem(Integer menditem) {
        this.menditem = menditem;
    }
    public String getMendername() {
        return mendername;
    }
    public void setMendername(String mendername) {
        this.mendername = mendername;
    }
    public String getItemname() {
        return itemname;
    }
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
    public String getMendermobile() {
        return mendermobile;
    }
    public void setMendermobile(String mendermobile) {
        this.mendermobile = mendermobile;
    }
    public String getMenderphoto() {
        return menderphoto;
    }
    public void setMenderphoto(String menderphoto) {
        this.menderphoto = menderphoto;
    }
    
}
