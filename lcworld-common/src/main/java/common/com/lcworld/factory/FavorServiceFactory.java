package com.lcworld.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lcworld.service.ICollectionService;

@Component("favorServiceFactory")
public class FavorServiceFactory {
	

    public ICollectionService<?> getService(int type) {
    	ICollectionService<?> service = null;
        switch (type) {
        
        default:
            ;
        }
        return service;
    }
}
