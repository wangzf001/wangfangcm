package com.lcworld.dto;

import java.math.BigDecimal;

public class PurchaseDTO {
	private Integer id;
    private String typename;
    private Integer type;
    private BigDecimal money;
    //对公
    private Integer ispub=1;
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIspub() {
        return ispub;
    }
    public void setIspub(Integer ispub) {
        this.ispub = ispub;
    }
    public String getTypename() {
        return typename;
    }
    public void setTypename(String typename) {
        this.typename = typename;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
}
