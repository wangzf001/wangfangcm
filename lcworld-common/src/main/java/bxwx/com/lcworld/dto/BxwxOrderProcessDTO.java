package com.lcworld.dto;

public class BxwxOrderProcessDTO {
    //
    private Integer id;
    //1:下单，2: 审核中，3：审核通过，4：审核不通过，5：派工，6： 接单
    private Integer status;
    //详情信息
    private String detail;
    //订单编号
    private Integer orderid;
    //时间
    private String createtime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public Integer getOrderid() {
        return orderid;
    }
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
