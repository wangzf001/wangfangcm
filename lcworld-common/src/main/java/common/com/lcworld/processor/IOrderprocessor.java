package com.lcworld.processor;

import javax.servlet.http.HttpServletRequest;

import com.lcworld.vo.PayOrderVo;

public interface IOrderprocessor {
    void before(PayOrderVo order,boolean exist);
    
    void after(PayOrderVo order,boolean exist);
    
    void excepion(PayOrderVo order,boolean exist);
    
}
