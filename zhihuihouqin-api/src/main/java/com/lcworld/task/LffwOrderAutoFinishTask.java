package com.lcworld.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.lcworld.service.LffwOrderService;

public class LffwOrderAutoFinishTask {
    @Autowired
    private LffwOrderService lffwOrderService;
    public void finish(){
        lffwOrderService.autofinishOrder();
    }
}
