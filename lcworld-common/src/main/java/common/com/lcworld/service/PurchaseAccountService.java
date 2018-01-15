package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.entity.PayinfoEntity;
import com.lcworld.entity.PurchaseAccountEntity;
import com.lcworld.entity.TUserEntity;

import java.util.List;
import java.util.Map; 

/**
 * 采购账户
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:37
 */
public interface PurchaseAccountService {
	
	PurchaseAccountEntity queryObject(Integer id);
	
	List<PurchaseAccountEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PurchaseAccountEntity purchaseAccount);
	
	void update(PurchaseAccountEntity purchaseAccount);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    PurchaseAccountEntity queryGroupAcount(JSONObject param);

    void savePurchasePayed(PurchaseAccountEntity account, PayinfoEntity pay);
    
    /**
     * @param uid 用户id
     * @param servicecode 服务类型学
     * @return
     */
    List<PurchaseDTO> getPubPurchaseList(Integer uid,String servicecode);
    
    /**是否显示办公用品其他成员订单
     * @param user
     * @return
     */
    boolean showOtherMemberList(TUserEntity user);
   
    /**是否含有支付权限
     * @param user
     * @param typeBgypfw 
     * @return
     */
    boolean hasPayRight(TUserEntity user, int type) ;

  /**是否为三级管理
     * @param user
     * @return
     */
    boolean isthirdadmin(TUserEntity user);
}
