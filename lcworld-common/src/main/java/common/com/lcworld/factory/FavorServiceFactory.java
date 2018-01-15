package com.lcworld.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lcworld.service.BgypfwProductService;
import com.lcworld.service.ICollectionService;
import com.lcworld.service.LffwBarberService;
import com.lcworld.service.LffwBarberWorksService;
import com.lcworld.service.TNoticeService;
import com.lcworld.service.TdhdActivityService;
import com.lcworld.service.TsjyfwBookService;
import com.lcworld.service.YlfwZjzzExpertService;

@Component("favorServiceFactory")
public class FavorServiceFactory {
	@Autowired
    private TNoticeService tNoticeService;
    @Autowired
    private BgypfwProductService bgypfwProductService;
    @Autowired
    private LffwBarberWorksService lffwBarberWorksService;
    @Autowired
    private TsjyfwBookService tsjyfwBookService;
    @Autowired
    private YlfwZjzzExpertService ylfwZjzzExpertService;
    @Autowired
    private TdhdActivityService tdhdActivityService;
    @Autowired
    private LffwBarberService lffwBarberService;

    public ICollectionService<?> getService(int type) {
    	ICollectionService<?> service = null;
        switch (type) {
        case 1:
            service = tNoticeService;
            break;// 公告
        case 2:
            service = bgypfwProductService;
            break;// 办公用品
        case 3:
        	service = lffwBarberWorksService;
        	break;// 理发作品
        case 4:
            service = tsjyfwBookService;
            break;// 图书
        case 5:
        	service = ylfwZjzzExpertService;
        	break;// 专家
        case 6:
            service = tdhdActivityService;
            break;// 活动
        case 7:
            service = lffwBarberService;
            break;// 理发师
        default:
            ;
        }
        return service;
    }
}
