package com.lcworld.dto;

import java.math.BigDecimal;
import java.util.List;

import com.lcworld.entity.LffwServiceitemEntity;

public class LffwServiceItemDTO {
    //
    private Integer typeid;
    //
    private String name;
    //
    private BigDecimal minprice;
    //
    private BigDecimal maxprice;
    //服务项
    private List<LffwServiceitemEntity> itemlist;
  
    public Integer getTypeid() {
        return typeid;
    }
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getMinprice() {
        return minprice;
    }
    public void setMinprice(BigDecimal minprice) {
        this.minprice = minprice;
    }
    public BigDecimal getMaxprice() {
        return maxprice;
    }
    public void setMaxprice(BigDecimal maxprice) {
        this.maxprice = maxprice;
    }
    public List<LffwServiceitemEntity> getItemlist() {
        return itemlist;
    }
    public void setItemlist(List<LffwServiceitemEntity> itemlist) {
        this.itemlist = itemlist;
    }
}
