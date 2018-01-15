package com.lcworld.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lcworld.consts.APPConstant;
import com.lcworld.service.DeparpurchaseCountService;
import com.lcworld.service.IPurchaseCountService;
import com.lcworld.service.OfficePurchaseCountService;

@Component("purchaseCountServiceFactory")
public class PurchaseCountServiceFactory {
    @Autowired
    private DeparpurchaseCountService deparpurchaseCountService;
    @Autowired
    private OfficePurchaseCountService officePurchaseCountService;
    
    public IPurchaseCountService getPurchaseCountService(int type){
        IPurchaseCountService service= null ;
        if(APPConstant.TYPE_BGYPFW == type || APPConstant.TYPE_DSFW == type ){
            service = officePurchaseCountService;
        }else{
            service = deparpurchaseCountService;
        }
        return service;
        
    }
}
