package com.lcworld.task;

import com.lcworld.service.EnergyCostService;
import com.lcworld.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EnergyTask {
    private static Logger log = LoggerFactory.getLogger(EnergyTask.class);
    @Autowired
    private EnergyCostService energyCostService;
    public void getEnergyCost(){
        log.debug("定时任务启动！！！/n 获取服务器中"+ DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN)+"的能耗");
        energyCostService.remoteGetEnergyCost();
    }
}
