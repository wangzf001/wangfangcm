package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.LffwOrderDao;
import com.lcworld.dao.LffwOrderdetailDao;
import com.lcworld.dao.LffwServiceitemDao;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.entity.LffwOrderdetailEntity;
import com.lcworld.entity.LffwServiceitemEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.LffwOrderdetailService;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;



@Service("lffwOrderdetailService")
public class LffwOrderdetailServiceImpl implements LffwOrderdetailService {
	@Autowired
	private LffwOrderdetailDao lffwOrderdetailDao;
	@Autowired
	private LffwOrderDao lffwOrderDao;
	@Autowired
	private LffwServiceitemDao lffwServiceitemDao;
	
	@Override
	public LffwOrderdetailEntity queryObject(Integer id){
		return lffwOrderdetailDao.queryObject(id);
	}
	
	@Override
	public List<LffwOrderdetailEntity> queryList(Map<String, Object> map){
		return lffwOrderdetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwOrderdetailDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwOrderdetailEntity lffwOrderdetail){
		lffwOrderdetailDao.save(lffwOrderdetail);
	}
	
	@Override
	public void update(LffwOrderdetailEntity lffwOrderdetail){
		lffwOrderdetailDao.update(lffwOrderdetail);
	}
	
	@Override
	public void delete(Integer id){
	    delete(new Integer[]{id});
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
	    delete(ids);
		
	}

    @Override
    public List<Map<String, Object>> querydetailList(Map<String,Object> query) {
        return lffwOrderdetailDao.querydetailList(query);
    }

    @Override
    public int querydetailTotal(Query query) {
        return lffwOrderdetailDao.querydetailTotal(query);
    }

    @Override
    public void updateOrder(LffwOrderdetailEntity lffwOrderdetail) {
        LffwOrderEntity order = lffwOrderDao.queryObject(lffwOrderdetail.getOrderid());
        if(1 == order.getCheckstatus() ){
            throw new ZHHQException(500,"改订单已审核通过，不允许改价");
        }
        JSONObject obj = new JSONObject();
        obj.put("orderid", lffwOrderdetail.getOrderid());
        List<LffwOrderdetailEntity> dlist = lffwOrderdetailDao.queryList(obj);
        BigDecimal total = new BigDecimal(0);
        if(ValidateUtil.isValid(dlist)){
            for(LffwOrderdetailEntity de : dlist){
                if(de.getId().intValue() == lffwOrderdetail.getId() ){
                    total = total.add(lffwOrderdetail.getPrice());
                }else{
                    total =total.add(de.getPrice());
                }
            }
        }
        order.setPrice(total);
        lffwOrderDao.update(order);
        lffwOrderdetailDao.update(lffwOrderdetail);
        
    }

    @Override
    public void saveOrder(LffwOrderdetailEntity lffwOrderdetail) {
        LffwOrderEntity order = lffwOrderDao.queryObject(lffwOrderdetail.getOrderid());
        if(1 == order.getCheckstatus()){
            throw new ZHHQException(500,"改订单已审核通过，不允添加");
        }
        JSONObject obj = new JSONObject();
        obj.put("orderid", lffwOrderdetail.getOrderid());
        List<LffwOrderdetailEntity> dlist = lffwOrderdetailDao.queryList(obj);
        BigDecimal total = new BigDecimal(0);
        if(ValidateUtil.isValid(dlist)){
            for(LffwOrderdetailEntity de : dlist){
                total = total.add(de.getPrice());
            }
        }
        total =total.add(lffwOrderdetail.getPrice());
        order.setPrice(total);
        lffwOrderDao.update(order);
        lffwOrderdetailDao.save(lffwOrderdetail);
        
    }
    
    private void delete(Integer[] ids){
        LffwOrderdetailEntity detail = lffwOrderdetailDao.queryObject(ids[0]);
        LffwOrderEntity order = lffwOrderDao.queryObject(detail.getOrderid());
         if(1 == order.getCheckstatus()){
                throw new ZHHQException(500,"改订单已审核通过，不允许删除");
            }
            JSONObject obj = new JSONObject();
            obj.put("orderid", order.getId());
            List<LffwOrderdetailEntity> dlist = lffwOrderdetailDao.queryList(obj);
            BigDecimal total = new BigDecimal(0);
            List<Integer> idlist = Arrays.asList(ids);
            if(ValidateUtil.isValid(dlist)){
                for(LffwOrderdetailEntity de : dlist){
                        if(!idlist.contains(de.getId().intValue())){
                            total = total.add(de.getPrice());
                        }
                }
            }
            order.setPrice(total);
            lffwOrderDao.update(order);
            lffwOrderdetailDao.deleteBatch(ids);
    }
	
}
