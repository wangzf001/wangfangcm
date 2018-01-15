package com.lcworld.processor;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.service.impl.PayinfoServiceImpl;
import com.lcworld.vo.PayOrderVo;

/**订单业务处理
 * @author Administrator
 *
 */
public class OrderBusiness {
    private HttpServletRequest req;
    private PayOrderVo order;
    private IOrderprocessor processorchedule;
    private boolean exist;
    public OrderBusiness(HttpServletRequest req,PayOrderVo order, IOrderprocessor processorchedule,boolean exist){
        this.req = req;
        this.order = order;
        this.exist = exist;
        this.processorchedule = processorchedule;
    }
    
    public JSONObject  excute(PayinfoServiceImpl payinfoServiceImpl){
        JSONObject res = new JSONObject();
        res.put("errCode", 0);
        try {
            processorchedule.before(order, exist);
            
            res = payinfoServiceImpl.savedopay(req, order);
            
            processorchedule.after(order, exist);
        } catch (Exception e) {
            processorchedule.excepion(order, exist);
        }
        
        return res;
        
    }
    

    public HttpServletRequest getReq() {
        return req;
    }
    public void setReq(HttpServletRequest req) {
        this.req = req;
    }
    public PayOrderVo getOrder() {
        return order;
    }
    public void setOrder(PayOrderVo order) {
        this.order = order;
    }
    public IOrderprocessor getProcessorchedule() {
        return processorchedule;
    }
    public void setProcessorchedule(IOrderprocessor processorchedule) {
        this.processorchedule = processorchedule;
    }
    
}
