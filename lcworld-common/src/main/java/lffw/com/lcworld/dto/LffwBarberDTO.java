package com.lcworld.dto;

public class LffwBarberDTO {
    //
    private Integer id;
    //
    private String photo;
    //姓名
    private String name;
    //名称  默认手机号
    private String userName;
	//职称
    private Integer positionid;
    //分数
    private Double score;
    //电话
    private String mobile;

    //是否有效 1:有效，0：无
    private Integer valid;
    
    private String positionName;
    
    private Integer remaincount;
    
    private Integer tatalcount;
    //地址
  	private String address;
  	
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getRemaincount() {
        return remaincount;
    }

    public void setRemaincount(Integer remaincount) {
        this.remaincount = remaincount;
    }

    public Integer getTatalcount() {
        return tatalcount;
    }

    public void setTatalcount(Integer tatalcount) {
        this.tatalcount = tatalcount;
    }
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
