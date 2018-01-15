package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 10:51:23
 */
/**
 * @author Administrator
 *
 */
public class TUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户名
	private String userName;
	//密码
	private String password;
	//创建时间
	private Date createtime;
	//更新时间
	private Date updatetime;
	//微信号
	private String wxnum;
	//微博号
	private String wbnum;
	//qq号
	private String qqnum;
	//手机号
	private String mobile;
	//用户昵称
	private String nickname;
	//是否有效   1:有效，0 无效
	private Integer valid;
	//办公室座机
	private String tel;
	//房间号
	private String roomnum;
	//性别 1:男 0：女
	private Integer sex;
	//头像
	private String photo;
	//职位id
	private Integer positionid;
	//职位
	private String position;
	//登录状态
	private Integer loginstatus;
	//登录后服务端唯一标识
	private String signid;
	//真实姓名
	private String realname;
	//民族
	private Integer nation;
	//楼号
	private String buildnum;

	private String buildname;

	private Integer departid;
	private Integer officeid;
	//认证状态
    private Integer authStatus;
    private Date deadline;

	public String getBuildname() {
		return buildname;
	}

	public void setBuildname(String buildname) {
		this.buildname = buildname;
	}

	public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public Integer getOfficeid() {
        return officeid;
    }
    public void setOfficeid(Integer officeid) {
        this.officeid = officeid;
    }
    public Integer getDepartid() {
        return departid;
    }
    public void setDepartid(Integer departid) {
        this.departid = departid;
    }
	public Integer getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBuildnum() {
        return buildnum;
    }
    public void setBuildnum(String buildnum) {
        this.buildnum = buildnum;
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
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
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
	 * 设置：更新时间
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置：微信号
	 */
	public void setWxnum(String wxnum) {
		this.wxnum = wxnum;
	}
	/**
	 * 获取：微信号
	 */
	public String getWxnum() {
		return wxnum;
	}
	/**
	 * 设置：微博号
	 */
	public void setWbnum(String wbnum) {
		this.wbnum = wbnum;
	}
	/**
	 * 获取：微博号
	 */
	public String getWbnum() {
		return wbnum;
	}
	/**
	 * 设置：qq号
	 */
	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}
	/**
	 * 获取：qq号
	 */
	public String getQqnum() {
		return qqnum;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：用户昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：用户昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：是否有效   1:有效，0 无效
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	/**
	 * 获取：是否有效   1:有效，0 无效
	 */
	public Integer getValid() {
		return valid;
	}
	/**
	 * 设置：办公室座机
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：办公室座机
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：房间号
	 */
	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	/**
	 * 获取：房间号
	 */
	public String getRoomnum() {
		return roomnum;
	}
	/**
	 * 设置：性别 1:男 0：女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别 1:男 0：女
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：头像
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 获取：头像
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * 设置：职位id
	 */
	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}
	/**
	 * 获取：职位id
	 */
	public Integer getPositionid() {
		return positionid;
	}
	/**
	 * 设置：登录状态
	 */
	public void setLoginstatus(Integer loginstatus) {
		this.loginstatus = loginstatus;
	}
	/**
	 * 获取：登录状态
	 */
	public Integer getLoginstatus() {
		return loginstatus;
	}
	/**
	 * 设置：登录后服务端唯一标识
	 */
	public void setSignid(String signid) {
		this.signid = signid;
	}
	/**
	 * 获取：登录后服务端唯一标识
	 */
	public String getSignid() {
		return signid;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * 设置：民族
	 */
	public void setNation(Integer nation) {
		this.nation = nation;
	}
	/**
	 * 获取：民族
	 */
	public Integer getNation() {
		return nation;
	}
}
