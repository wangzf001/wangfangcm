package com.lcworld.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.vo.PurchaseCountVo;

public interface IPurchaseCountService {
    PurchaseCountVo getPurchaseCountVo(JSONObject obj);

    void savePurchaseCount(PurchaseCountVo vo);

    List<PurchaseDTO> getPubPurchaseList(Integer uid,Integer departid,Integer officeid, String servicecode);

    void savePurchaseCountRefund(PurchaseCountVo vo);
}
