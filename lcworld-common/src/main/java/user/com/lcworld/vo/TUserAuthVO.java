package com.lcworld.vo;

import java.util.List;

import com.lcworld.entity.TCarUsercarinfoEntity;
import com.lcworld.entity.TUserAuthEntity;

public class TUserAuthVO {
    private TUserAuthEntity auth;
    private List<TCarUsercarinfoEntity> carList;
    public TUserAuthEntity getAuth() {
        return auth;
    }
    public void setAuth(TUserAuthEntity auth) {
        this.auth = auth;
    }
    public List<TCarUsercarinfoEntity> getCarList() {
        return carList;
    }
    public void setCarList(List<TCarUsercarinfoEntity> carList) {
        this.carList = carList;
    }
    
}
