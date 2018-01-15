package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.CheckfailureReasonDao;
import com.lcworld.dao.TBxwxMenderDao;
import com.lcworld.dao.TBxwxOrderDao;
import com.lcworld.dao.TBxwxOrderProcessesDao;
import com.lcworld.dto.BxwxOrderProcessDTO;
import com.lcworld.entity.TBxwxOrderEntity;
import com.lcworld.entity.TBxwxOrderProcessesEntity;
import com.lcworld.service.TBxwxOrderProcessesService;
import com.lcworld.service.TBxwxOrderService;
import com.lcworld.utils.DateUtil;



@Service("tBxwxOrderProcessesService")
public class TBxwxOrderProcessesServiceImpl implements TBxwxOrderProcessesService {
	@Autowired
	private TBxwxOrderProcessesDao tBxwxOrderProcessesDao;
	@Autowired
    private CheckfailureReasonDao checkfailureReasonDao;
	@Autowired
	private TBxwxOrderService tBxwxOrderService;
	@Autowired
	private TBxwxMenderDao tBxwxMenderDao;
	
	@Override
	public TBxwxOrderProcessesEntity queryObject(Integer id){
		return tBxwxOrderProcessesDao.queryObject(id);
	}
	
	@Override
	public List<TBxwxOrderProcessesEntity> queryList(Map<String, Object> map){
		return tBxwxOrderProcessesDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tBxwxOrderProcessesDao.queryTotal(map);
	}
	
	@Override
	public void save(TBxwxOrderProcessesEntity tBxwxOrderProcesses){
		tBxwxOrderProcessesDao.save(tBxwxOrderProcesses);
	}
	
	@Override
	public void update(TBxwxOrderProcessesEntity tBxwxOrderProcesses){
		tBxwxOrderProcessesDao.update(tBxwxOrderProcesses);
	}
	
	@Override
	public void delete(Integer id){
		tBxwxOrderProcessesDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tBxwxOrderProcessesDao.deleteBatch(ids);
	}

    @Override
    public List<BxwxOrderProcessDTO> queryProcessList(JSONObject params) {
        return tBxwxOrderProcessesDao.queryProcessList(params);
    }

    @Override
    public String getDatail(TBxwxOrderProcessesEntity tBxwxOrderProcesses) {
        String result = "";
        switch(tBxwxOrderProcesses.getStatus().intValue()){
        case 1: result="您于"+DateUtil.format(new Date(), "yyyy/MM/dd HH:mm:ss")+"通过移动终端报修";break;
        case 2: result="系统正在审核中";break;
        case 3: result="审核通过";
        case 4 : result =tBxwxOrderProcesses.getFailurereasonid() != null?checkfailureReasonDao.queryObject(tBxwxOrderProcesses.getFailurereasonid()).getReason():tBxwxOrderProcesses.getFailurereason();break;
        case 5: result = "系统自动派单给[机关物业]，等待受理人"+tBxwxMenderDao.queryObject(tBxwxOrderProcesses.getMenderid()).getRealname()+"进行受理";break;
        case 6: result = tBxwxOrderProcesses.getMenderName()+"进行了接单";break;
        default:;
        }
        return result;
    }

    @Override
    public void saveProcesses(TBxwxOrderProcessesEntity tBxwxOrderProcesses) {
        save(tBxwxOrderProcesses);
        TBxwxOrderEntity order = tBxwxOrderService.queryObject(tBxwxOrderProcesses.getOrderid());
        order.setMenderid(tBxwxOrderProcesses.getMenderid());
        tBxwxOrderService.update(order);
    }
	
}
