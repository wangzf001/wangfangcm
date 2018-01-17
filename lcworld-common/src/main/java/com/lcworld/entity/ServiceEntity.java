package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lcworld.utils.util.ValidateUtil;



/**
 * 服务表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:19:28
 */
public class ServiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//服务名称
	private String name;
	//小图标
	private String smallimg;
	//头图
	private String topphoto;
	//详情图片
	private String detailphoto;
	//服务分类id
	private Integer servicetypeid;
	//客服电话
	private String mobile;
	//服务价格
	private String price;
	//服务地址
	private String place;
	//营业时间
	private String businesshour;
	//店名
	private String tradename;
	//唯一标识
	private String sign;
	//挂号费
	private BigDecimal ghprice;
	//工作餐固定餐
	private BigDecimal dcfwgzcfixedprice;
	//工作餐
	private BigDecimal dcfwgzcprice;
	//净菜取菜点
	private String dcfwtokenplace;
	//净菜可预约时间段
	private String dcfwtokenordertime;
	//温馨提示
	private String remind;
	//预约开始时间
	private String starttime;
	//预约结束时间
	private String endtime;
	//是否含有服务端 1： 有，0 非
	private Integer hasservice;
	//app   namen
	private String appname;
	//每周可预订天数
	private List<Integer> orderweek = new ArrayList<>();
	//借用了办公用品的多图接口
	private List<Object> imgEntityList = new ArrayList<>();
	
	
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public Integer getHasservice() {
        return hasservice;
    }
    public void setHasservice(Integer hasservice) {
        this.hasservice = hasservice;
    }
    public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public List<Integer> getOrderweek() {
		return orderweek;
	}
	public void setOrderweek(List<Integer> orderweek) {
		this.orderweek = orderweek;
	}
	public String getRemind() {
		return remind;
	}
	public void setRemind(String remind) {
		this.remind = remind;
	}
	public List<Object> getImgEntityList() {
		return imgEntityList;
	}
	public void setImgEntityList(List<Object> imgEntityList) {
		this.imgEntityList = imgEntityList;
		if (ValidateUtil.isValid(imgEntityList)) {
			String imgs = "";
			for (int i = 0; i < imgEntityList.size(); i++) {
				String img = "";
				if (i==imgEntityList.size()-1) {
					imgs += img;
				}else{
					imgs += img+",";
				}
			}
			this.topphoto = imgs;
		}
	}
	public String getDcfwtokenplace() {
		return dcfwtokenplace;
	}
	public void setDcfwtokenplace(String dcfwtokenplace) {
		this.dcfwtokenplace = dcfwtokenplace;
	}
	public String getDcfwtokenordertime() {
		return dcfwtokenordertime;
	}
	public void setDcfwtokenordertime(String dcfwtokenordertime) {
		this.dcfwtokenordertime = dcfwtokenordertime;
	}
	public BigDecimal getDcfwgzcfixedprice() {
		return dcfwgzcfixedprice;
	}
	public void setDcfwgzcfixedprice(BigDecimal dcfwgzcfixedprice) {
		this.dcfwgzcfixedprice = dcfwgzcfixedprice;
	}
	public BigDecimal getDcfwgzcprice() {
		return dcfwgzcprice;
	}
	public void setDcfwgzcprice(BigDecimal dcfwgzcprice) {
		this.dcfwgzcprice = dcfwgzcprice;
	}
	public BigDecimal getGhprice() {
        return ghprice;
    }
    public void setGhprice(BigDecimal ghprice) {
        this.ghprice = ghprice;
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
	 * 设置：服务名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：服务名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：小图标
	 */
	public void setSmallimg(String smallimg) {
		this.smallimg = smallimg;
	}
	/**
	 * 获取：小图标
	 */
	public String getSmallimg() {
		return smallimg;
	}
	/**
	 * 设置：头图
	 */
	public void setTopphoto(String topphoto) {
		this.topphoto = topphoto;
		//topphoto
		if (ValidateUtil.isValid(topphoto)&&imgEntityList.size()==0) {
			this.imgEntityList.clear();
			String[] split = topphoto.split(",");
			for (int i = 0; i < split.length; i++) {
				Object imgEntity = new Object();
				this.imgEntityList.add(imgEntity);
			}
		}
	}
	/**
	 * 获取：头图
	 */
	public String getTopphoto() {
//		if (ValidateUtil.isValid(imgEntityList)) {
//			String imgs = "";
//			for (int i = 0; i < imgEntityList.size(); i++) {
//				String img = imgEntityList.get(i).getImg();
//				if (i==imgEntityList.size()-1) {
//					imgs += img;
//				}else{
//					imgs += img+",";
//				}
//			}
//			topphoto = imgs;
//		}
		return topphoto;
	}
	/**
	 * 设置：详情图片
	 */
	public void setDetailphoto(String detailphoto) {
		this.detailphoto = detailphoto;
	}
	/**
	 * 获取：详情图片
	 */
	public String getDetailphoto() {
		return detailphoto;
	}
	/**
	 * 设置：服务分类id
	 */
	public void setServicetypeid(Integer servicetypeid) {
		this.servicetypeid = servicetypeid;
	}
	/**
	 * 获取：服务分类id
	 */
	public Integer getServicetypeid() {
		return servicetypeid;
	}
	/**
	 * 设置：客服电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：客服电话
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：服务价格
	 */
	
	/**
	 * 设置：服务地址
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * 获取：服务地址
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * 设置：营业时间
	 */
	public void setBusinesshour(String businesshour) {
		this.businesshour = businesshour;
	}
	/**
	 * 获取：营业时间
	 */
	public String getBusinesshour() {
		return businesshour;
	}
	/**
	 * 设置：店名
	 */
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
	/**
	 * 获取：店名
	 */
	public String getTradename() {
		return tradename;
	}
	/**
	 * 设置：唯一标识
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * 获取：唯一标识
	 */
	public String getSign() {
		return sign;
	}
}
