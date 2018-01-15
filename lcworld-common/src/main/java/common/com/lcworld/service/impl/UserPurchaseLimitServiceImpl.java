package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.DeparpurchaseCountDao;
import com.lcworld.dao.OfficePurchaseCountDao;
import com.lcworld.dao.UserPurchaseLimitDao;
import com.lcworld.entity.UserPurchaseLimitEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.DeparpurchaseCountService;
import com.lcworld.service.OfficePurchaseCountService;
import com.lcworld.service.UserPurchaseLimilogService;
import com.lcworld.service.UserPurchaseLimitService;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;



@Service("userPurchaseLimitService")
public class UserPurchaseLimitServiceImpl implements UserPurchaseLimitService {
	@Autowired
	private UserPurchaseLimitDao userPurchaseLimitDao;
	@Autowired
	private UserPurchaseLimilogService userPurchaseLimilogService;
	@Autowired
	private DeparpurchaseCountService deparpurchaseCountService;
	@Autowired
	private OfficePurchaseCountService officePurchaseCountService;
	
	
	@Override
	public UserPurchaseLimitEntity queryObject(Integer id){
		return userPurchaseLimitDao.queryObject(id);
	}
	
	@Override
	public List<UserPurchaseLimitEntity> queryList(Map<String, Object> map){
		return userPurchaseLimitDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userPurchaseLimitDao.queryTotal(map);
	}
	
	@Override
	public void save(UserPurchaseLimitEntity userPurchaseLimit){
		userPurchaseLimitDao.save(userPurchaseLimit);
	}
	
	@Override
	public void update(UserPurchaseLimitEntity userPurchaseLimit){
		userPurchaseLimitDao.update(userPurchaseLimit);
	}
	
	@Override
	public void delete(Integer id){
		userPurchaseLimitDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userPurchaseLimitDao.deleteBatch(ids);
	}

    @Override
    public List<UserPurchaseLimitEntity> queryUPList(Query query) {
       return userPurchaseLimitDao.queryUPList(query);
    }

    @Override
    public int queryUPTotal(Query query) {
        return userPurchaseLimitDao.queryUPTotal(query);
    }

    @Override
    public List<UserPurchaseLimitEntity> queryList1(Map<String, Object> params) {
       return userPurchaseLimitDao.queryList1(params);
    }

    @Override
    public void savePurchaselimit(Integer uid, BigDecimal paymoney,int servicecode, int usetype) {
        JSONObject obj = new JSONObject();
        obj.put("uid", uid);
        obj.put("servicecode", servicecode);
        List<UserPurchaseLimitEntity> limlist = userPurchaseLimitDao.queryUserLimitList(obj);
        if(ValidateUtil.isValid(limlist)  ){
            if(limlist.size() == 1){
                UserPurchaseLimitEntity limit = limlist.get(0);
                updatelimit(limit,paymoney,usetype);
                userPurchaseLimilogService.savelimitlog(limit.getId(),uid,servicecode,paymoney,usetype);
            }else{
                throw new ZHHQException(1422, "该用户存在多个对公账户额度，请设置唯一额度，不可支付");
            }
        }else{
            throw new ZHHQException(1421, "该用户不存在对公账户额度，不可支付");
        }
        
    }

    /**更新额度
     * @param limit
     * @param paymoney
     * @param usetype
     */
    private void updatelimit(UserPurchaseLimitEntity limit, BigDecimal paymoney, int usetype) {
        BigDecimal sub;
        if(1 == usetype){
            //out 
            if(ValidateUtil.isValid(limit.getRemain())){
                if((sub =limit.getRemain().subtract(paymoney)).doubleValue() >= 0){
                    limit.setRemain(sub);
                }else{
                    throw new ZHHQException(1423, "该用户对公账户剩余额度不足，不可支付");
                }
            }else{
                throw new ZHHQException(1423, "该用户对公账户额度未设置，不可支付");
            }
           
         }else if(2 == usetype){
             //in
             if(!ValidateUtil.isValid(limit.getRemain())){
                 limit.setRemain(new BigDecimal("0"));
             }
             limit.setRemain(limit.getRemain().add(paymoney));
            
            
         }else{
             throw new ZHHQException(1424, "传人参数usetype 异常"); 
         }
        userPurchaseLimitDao.update(limit);
        
    }

    @Override
    public List<UserPurchaseLimitEntity> querylimitList(Query q) {
        return userPurchaseLimitDao.querylimitList(q);
    }

    @Override
    public int querylimitTotal(Map<String, Object> params) {
        return userPurchaseLimitDao.querylimitTotal(params);
    }

    @Override
    public void deleteBatchByuid(Map<String, Object> map) {
        userPurchaseLimitDao.deleteBatchByuid(map);
        
    }

    @Override
    public List<UserPurchaseLimitEntity> depart(Map<String, Object> params, Integer type) {
        List<UserPurchaseLimitEntity> list = null;
        if(type == 1){
            list = deparpurchaseCountService.depart(params);
        }else if(type == 2){
            list = officePurchaseCountService.depart(params);
        }
        return list;
    }

	@Override
	public void saveBatch(List<UserPurchaseLimitEntity> uplmtlist) {
		// TODO Auto-generated method stub
		userPurchaseLimitDao.saveBatch(uplmtlist);
	}

	@Override
	public int departTotal(Query query,Integer type) {
		// TODO Auto-generated method stub
		int total = 0;
		if (type == 1) {
			total = deparpurchaseCountService.departTotal(query);
		} else if(type == 2){
			total = officePurchaseCountService.departTotal(query);
		}
		return total;
	}

   

   
	
}
