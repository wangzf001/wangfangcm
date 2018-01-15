package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TGxdfwOrderDao;
import com.lcworld.dao.TGxdfwOrderdetailDao;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.entity.LffwOrderdetailEntity;
import com.lcworld.entity.TGxdfwOrderEntity;
import com.lcworld.entity.TGxdfwOrderdetailEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.TGxdfwOrderdetailService;
import com.lcworld.utils.ValidateUtil;



@Service("tGxdfwOrderdetailService")
public class TGxdfwOrderdetailServiceImpl implements TGxdfwOrderdetailService {
	@Autowired
	private TGxdfwOrderdetailDao tGxdfwOrderdetailDao;
	@Autowired
	private TGxdfwOrderDao tGxdfwOrderDao;
	
	@Override
	public TGxdfwOrderdetailEntity queryObject(Integer id){
		return tGxdfwOrderdetailDao.queryObject(id);
	}
	
	@Override
	public List<TGxdfwOrderdetailEntity> queryList(Map<String, Object> map){
		return tGxdfwOrderdetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGxdfwOrderdetailDao.queryTotal(map);
	}
	
	@Override
	public void save(TGxdfwOrderdetailEntity tGxdfwOrderdetail){
		tGxdfwOrderdetailDao.save(tGxdfwOrderdetail);
	}
	
	@Override
	public void update(TGxdfwOrderdetailEntity tGxdfwOrderdetail){
		tGxdfwOrderdetailDao.update(tGxdfwOrderdetail);
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
    public void savedetail(TGxdfwOrderdetailEntity tGxdfwOrderdetail) {
         TGxdfwOrderEntity order = tGxdfwOrderDao.queryObject(tGxdfwOrderdetail.getOrderid());
        if(1 == order.getCheckstatus()){
            throw new ZHHQException(500,"改订单已审核通过，不允添加");
        }
        JSONObject obj = new JSONObject();
        obj.put("orderid", tGxdfwOrderdetail.getOrderid());
        List<TGxdfwOrderdetailEntity> dlist = tGxdfwOrderdetailDao.queryList(obj);
        BigDecimal total = new BigDecimal(0);
        if(ValidateUtil.isValid(dlist)){
            for(TGxdfwOrderdetailEntity de : dlist){
                total = total.add(de.getTotalprice());
            }
        }
        total =total.add(tGxdfwOrderdetail.getTotalprice());
        order.setTotalprice(total);
        tGxdfwOrderDao.update(order);
        tGxdfwOrderdetailDao.save(tGxdfwOrderdetail);
        
    }

    @Override
    public void updatedetail(TGxdfwOrderdetailEntity tGxdfwOrderdetail) {
        TGxdfwOrderEntity order = tGxdfwOrderDao.queryObject(tGxdfwOrderdetail.getOrderid());
        if(1 == order.getCheckstatus()){
            throw new ZHHQException(500,"改订单已审核通过，不允修改");
        }
        JSONObject obj = new JSONObject();
        obj.put("orderid", tGxdfwOrderdetail.getOrderid());
        List<TGxdfwOrderdetailEntity> dlist = tGxdfwOrderdetailDao.queryList(obj);
        BigDecimal total = new BigDecimal(0);
        if(ValidateUtil.isValid(dlist)){
            for(TGxdfwOrderdetailEntity de : dlist){
                if(de.getId().intValue() == tGxdfwOrderdetail.getId() ){
                    total = total.add(tGxdfwOrderdetail.getTotalprice());
                }else{
                    total =total.add(de.getTotalprice());
                }
            }
        }
        order.setTotalprice(total);
        tGxdfwOrderDao.update(order);
        tGxdfwOrderdetailDao.update(tGxdfwOrderdetail);
        
    }
    
    private void delete(Integer[] ids){
        TGxdfwOrderdetailEntity detail = tGxdfwOrderdetailDao.queryObject(ids[0]);
        TGxdfwOrderEntity order = tGxdfwOrderDao.queryObject(detail.getOrderid());
         if(1 == order.getCheckstatus()){
                throw new ZHHQException(500,"改订单已审核通过，不允许删除");
            }
            JSONObject obj = new JSONObject();
            obj.put("orderid", order.getId());
            List<TGxdfwOrderdetailEntity> dlist = tGxdfwOrderdetailDao.queryList(obj);
            BigDecimal total = new BigDecimal(0);
            List<Integer> idlist = Arrays.asList(ids);
            if(ValidateUtil.isValid(dlist)){
                for(TGxdfwOrderdetailEntity de : dlist){
                        if(!idlist.contains(de.getId().intValue())){
                            total = total.add(de.getTotalprice());
                        }
                }
            }
            order.setTotalprice(total);
            tGxdfwOrderDao.update(order);
            tGxdfwOrderdetailDao.deleteBatch(ids);
    }
    
    
    
	
}
