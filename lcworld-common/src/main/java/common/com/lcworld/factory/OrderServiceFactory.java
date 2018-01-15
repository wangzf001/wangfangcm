package com.lcworld.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lcworld.service.BgypfwCommentService;
import com.lcworld.service.BgypfwMenderService;
import com.lcworld.service.BgypfwOrderService;
import com.lcworld.service.BgypfwProductService;
import com.lcworld.service.DcfwGzcOrderService;
import com.lcworld.service.DeliverymanService;
import com.lcworld.service.DsfwCommentService;
import com.lcworld.service.DsfwMenderService;
import com.lcworld.service.DsfwOrderService;
import com.lcworld.service.HysfwCommentService;
import com.lcworld.service.HysfwManagerService;
import com.lcworld.service.HysfwOrderService;
import com.lcworld.service.ICollectionService;
import com.lcworld.service.IOperationUserService;
import com.lcworld.service.IOrderService;
import com.lcworld.service.LffwBarberService;
import com.lcworld.service.LffwBarberWorksService;
import com.lcworld.service.LffwOrderService;
import com.lcworld.service.TBxwxCommentService;
import com.lcworld.service.TBxwxMenderService;
import com.lcworld.service.TBxwxOrderService;
import com.lcworld.service.TDcfwOrderService;
import com.lcworld.service.TGxdfwOrderService;
import com.lcworld.service.TNoticeService;
import com.lcworld.service.TVisitUserService;
import com.lcworld.service.TdhdActivityService;
import com.lcworld.service.TsjyfwBookService;
import com.lcworld.service.TsjyfwOrderService;
import com.lcworld.service.UserCfczOrderService;
import com.lcworld.service.UserWalleorderService;
import com.lcworld.service.YlfwYyghOrderService;
import com.lcworld.service.YlfwZjzzExpertService;
import com.lcworld.service.YlfwZjzzOrderService;

@Component("orderServiceFactory")
public class OrderServiceFactory {
    @Autowired
    private LffwOrderService lffwOrderService;
    @Autowired
    private BgypfwOrderService bgypfwOrderService;
    @Autowired
    private TDcfwOrderService tDcfwOrderService;
    @Autowired
    private YlfwYyghOrderService ylfwYyghOrderService;
    @Autowired
    private TGxdfwOrderService tGxdfwOrderService;
    @Autowired
    private TBxwxOrderService tBxwxOrderService;
    @Autowired
    private DsfwOrderService dsfwOrderService;
    @Autowired
    private DcfwGzcOrderService dcfwGzcOrderService;
    @Autowired
    private UserWalleorderService userWalleorderService;
    @Autowired
    private UserCfczOrderService userCfczOrderService;
    @Autowired
    private TsjyfwOrderService tsjyfwOrderService;
    @Autowired
    private HysfwOrderService hysfwOrderService;
    @Autowired
    private YlfwZjzzOrderService ylfwZjzzOrderService;
    @Autowired
    private TVisitUserService tVisitUserService;
    @Autowired
    private TBxwxMenderService bxwxMenderService;
    @Autowired
    private TBxwxCommentService bxwxCommentService;
    @Autowired
    private DsfwMenderService dsfwMenderService;
    @Autowired
    private BgypfwMenderService bgypfwMenderService;
    @Autowired
    private HysfwManagerService hysfwManagerService;
    @Autowired
    DeliverymanService deliverymanService;

    public IOrderService getService(int type) {
        IOrderService service = null;
        switch (type) {
        case 1:
            service = tBxwxOrderService;
            break;// 报修
        case 4:
            service = userCfczOrderService;
            break;// 餐费
        case 3:
        	service = tsjyfwOrderService;
        	break;// 餐费
        case 6:
            service = lffwOrderService;
            break;// 理发
        case 7:
        	service = hysfwOrderService;
        	break;// 会议室
        case 8:
            service = bgypfwOrderService;
            break;// 办公用品
        case 9:
            service = tDcfwOrderService;
            break;// 订餐服务
        case 15:
            service = dcfwGzcOrderService;
            break;// 工作餐
        case 16:
        	service = tGxdfwOrderService;
        	break;// 干洗店
        case 17:
            service = ylfwYyghOrderService;
            break;// 预约医疗
        case 18:
            service = dsfwOrderService;
            break;// 订水
        case 19:
        case 20:
        	service = userWalleorderService;
        	break;// 用户钱包
        case 14: service=ylfwZjzzOrderService;break;
        case 21 : service = tVisitUserService;break;
        
        
        
        case 61:
            service = dsfwOrderService;
            break;// 订水
        default:
            ;
        }
        return service;

    }
    
    public IOperationUserService<?> getUserInfo(int type) {
    	IOperationUserService service = null;
        switch (type) {
        case 1:
            service = bxwxMenderService;
            break;// 报修
        case 18:
            service =  deliverymanService;
            break;// 订水服务
        case 7:
            service = deliverymanService;
            break;// 会议室服务
        case 8:
            service = deliverymanService;
            break;// 办公用品服务
        default:
            ;
        }
        return service;

    }
    
    
    public IOperationUserService<?> getCommentService(int type) {
    	IOperationUserService service = null;
        switch (type) {
        case 1:
            service = bxwxMenderService;
            break;// 报修
        case 18:
            service =  dsfwMenderService;
            break;// 订水服务
        case 7:
            service = hysfwManagerService;
            break;// 会议室服务
        case 8:
            service = bgypfwMenderService;
            break;// 办公用品服务
        default:
            ;
        }
        return service;

    }
}
