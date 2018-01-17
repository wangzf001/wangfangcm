package com.lcworld.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lcworld.service.ICollectionService;
import com.lcworld.service.IOperationUserService;
import com.lcworld.service.IOrderService;
import com.lcworld.service.TVisitUserService;
import com.lcworld.service.UserCfczOrderService;
import com.lcworld.service.UserWalleorderService;

@Component("orderServiceFactory")
public class OrderServiceFactory {
    @Autowired
    private UserWalleorderService userWalleorderService;
    @Autowired
    private UserCfczOrderService userCfczOrderService;
    @Autowired
    private TVisitUserService tVisitUserService;

    public IOrderService getService(int type) {
        IOrderService service = null;
        switch (type) {
        case 4:
            service = userCfczOrderService;
            break;// 餐费
        case 19:
        case 20:
        	service = userWalleorderService;
        	break;// 用户钱包
        case 21 : service = tVisitUserService;break;
        default:
            ;
        }
        return service;

    }
    
    public IOperationUserService<?> getUserInfo(int type) {
    	IOperationUserService service = null;
        switch (type) {
        default:
            ;
        }
        return service;

    }
    
    
    public IOperationUserService<?> getCommentService(int type) {
    	IOperationUserService service = null;
        switch (type) {
        default:
            ;
        }
        return service;

    }
}
