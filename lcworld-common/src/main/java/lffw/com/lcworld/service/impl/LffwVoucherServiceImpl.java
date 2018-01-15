package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lcworld.consts.APPConstant;
import com.lcworld.dao.LffwVoucherDao;
import com.lcworld.dao.LffwVoucherLogDao;
import com.lcworld.entity.LffwVoucherEntity;
import com.lcworld.entity.LffwVoucherLogEntity;
import com.lcworld.entity.PayinfoEntity;
import com.lcworld.entity.PurchaseAccounlogEntity;
import com.lcworld.service.LffwVoucherService;



@Service("lffwVoucherService")
public class LffwVoucherServiceImpl implements LffwVoucherService {
	@Autowired
	private LffwVoucherDao lffwVoucherDao;
	@Autowired
	private LffwVoucherLogDao lffwVoucherLogDao;
	
	@Override
	public LffwVoucherEntity queryObject(Integer id){
		return lffwVoucherDao.queryObject(id);
	}
	
	@Override
	public List<LffwVoucherEntity> queryList(Map<String, Object> map){
		return lffwVoucherDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwVoucherDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwVoucherEntity lffwVoucher){
		lffwVoucherDao.save(lffwVoucher);
	}
	
	@Override
	public void update(LffwVoucherEntity lffwVoucher){
		lffwVoucherDao.update(lffwVoucher);
	}
	
	@Override
	public void delete(Integer id){
		lffwVoucherDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwVoucherDao.deleteBatch(ids);
	}

    @Override
    public void saveVoucherPayed(LffwVoucherEntity voucher, PayinfoEntity pay) {
        update(voucher);
        LffwVoucherLogEntity log = new LffwVoucherLogEntity();
        log.setCreatetime(new Date());
        log.setCount(pay.getVouchercount());
        log.setVoucherid(voucher.getId());
        log.setOrderid(pay.getOrderid());
        log.setOrdertype(pay.getOrdertype());
        log.setOrdertypename(APPConstant.gettypeMap().get(pay.getOrdertype()));
        log.setUid(pay.getUid());
        log.setUsetype(2);//使用类型 1: 收入，2： 支出
        lffwVoucherLogDao.save(log);
        
    }

	@Override
	public LffwVoucherEntity queryByUid(Integer uid) {
	    LffwVoucherEntity voucher = lffwVoucherDao.queryByUid(uid);
	    int count = 0 ;
        if(voucher == null){
        	voucher = new LffwVoucherEntity();
            count = 0 ;
        }else{
            int lockcount = voucher.getLockcount() == null ?0:voucher.getLockcount();
            count = voucher.getRemain()== null?0:voucher.getRemain()-lockcount;
        }
        voucher.setRemain(count);
		return voucher;
	}
	
}
