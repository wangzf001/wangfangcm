package com.lcworld.dto;

import java.math.BigDecimal;
import java.util.List;

public class BgypProductDTO {
    //商品编号
    private String commodityNum;
    //商品名称
    private String commodityName;
    //颜色规格
    private String color;
    //大小规格
    private String size;
    //数量规格
    private String number;
    //一级分类名称
    private String classifyName;
    //二级分类名称
    private String smallClassifyName;
    //品牌名称
    private String brandName;
    //规格名称
    private String skuid;
    //包装规格
    private String packSpec;
    //库存
    private Integer store;
    //价格区间
    private String priceInterval;
    //价格
    private BigDecimal price;
    //商品首图
    private String mainPic;
    //商品轮播图
    private List<String> rollingPic;
    //商家名称
    private String supplierName;
    //商家地址
    private String supplierAddress;
    //商家联系方式
    private String supplierTel;

    public String getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(String commodityNum) {
        this.commodityNum = commodityNum;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getSmallClassifyName() {
        return smallClassifyName;
    }

    public void setSmallClassifyName(String smallClassifyName) {
        this.smallClassifyName = smallClassifyName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getPackSpec() {
        return packSpec;
    }

    public void setPackSpec(String packSpec) {
        this.packSpec = packSpec;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public List<String> getRollingPic() {
        return rollingPic;
    }

    public void setRollingPic(List<String> rollingPic) {
        this.rollingPic = rollingPic;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierTel() {
        return supplierTel;
    }

    public void setSupplierTel(String supplierTel) {
        this.supplierTel = supplierTel;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPriceInterval() {
        return priceInterval;
    }

    public void setPriceInterval(String priceInterval) {
        this.priceInterval = priceInterval;
    }
}
