package com.lcworld.vo;

import java.util.List;

import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.entity.LffwOrderdetailEntity;

public class LffwOrderVO {
    private LffwOrderEntity order;
    private List<LffwOrderdetailEntity> detaillist;
    private String starttime;
    
    public String getStarttime() {
        return starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public LffwOrderEntity getOrder() {
        return order;
    }
    public void setOrder(LffwOrderEntity order) {
        this.order = order;
    }
    public List<LffwOrderdetailEntity> getDetaillist() {
        return detaillist;
    }
    public void setDetaillist(List<LffwOrderdetailEntity> detaillist) {
        this.detaillist = detaillist;
    }
    
}
