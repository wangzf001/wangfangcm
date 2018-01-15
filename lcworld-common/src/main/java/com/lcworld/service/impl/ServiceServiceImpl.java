package com.lcworld.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.ServiceDao;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.service.ServiceService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;

import sun.util.logging.resources.logging;



@Service("serviceService")
public class ServiceServiceImpl implements ServiceService {
	@Autowired
	private ServiceDao serviceDao;
	
	@Override
	public ServiceEntity queryObject(Integer id){
		ServiceEntity entity = serviceDao.queryObject(id);
		String dcfwtokenordertime = entity.getDcfwtokenordertime();
		if (ValidateUtil.isValid(dcfwtokenordertime)) {
			try {
				String[] split = dcfwtokenordertime.split("-");
				String weekdaystr = split[0];
				String timestr = split[1];
				String[] weekdayArr = weekdaystr.split("\\+");
				//每周可预约时间
				for (String weekday : weekdayArr) {
					entity.getOrderweek().add(Integer.parseInt(weekday));
				}
				//每日可预约时间
				String[] timeArr = timestr.split("~");
				entity.setStarttime(timeArr[0]);
				entity.setEndtime(timeArr[1]);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return entity;
	}
	
	@Override
	public List<ServiceEntity> queryList(Map<String, Object> map){
		return serviceDao.queryList(map);
	}
	
	@Override
	public List<ServiceEntity> queryObjectByName(Map<String, Object> map){
		return serviceDao.queryObjectByName(map);
	}
	
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return serviceDao.queryTotal(map);
	}
	
	@Override
	public void save(ServiceEntity service){
		setDcfwtokenordertime(service);
		serviceDao.save(service);
	}
	
	@Override
	public void update(ServiceEntity service){
		setDcfwtokenordertime(service);
		serviceDao.update(service);
	}
	
	private void setDcfwtokenordertime(ServiceEntity service) {
		List<Integer> orderweek = service.getOrderweek();
		String str = "";
		for (Integer weekday : orderweek) {
			str += weekday+"+";
		}
		str += "-"+service.getStarttime()+"~"+service.getEndtime();
		System.out.println(str);
		service.setDcfwtokenordertime(str);
	}

	@Override
	public void delete(Integer id){
		serviceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		serviceDao.deleteBatch(ids);
	}

    @Override
    public ServiceEntity queryService(Integer typeJkzx) {
        JSONObject params = new JSONObject();
        params.put("sign", typeJkzx);
        List<ServiceEntity> service = queryList(params);
        return ValidateUtil.isValid(service)?service.get(0):new ServiceEntity();
    }

	@Override
	public void updateUserService(JSONObject params) {
		serviceDao.deleteAllUserservice(params);
		//添加用户选择
		JSONArray serviceidList = params.getJSONArray("serviceidList");
		if (ValidateUtil.isValid(serviceidList)) {
			Integer uid = params.getInteger("uid");
			Map<String,Object> map = new HashMap<>();
			map.put("uid", uid);
			map.put("serviceidList", serviceidList.toArray());
			serviceDao.addUserService(map);
		}
	}

	@Override
	public List<ServiceEntity> queryAllSysRolesByUid(Long uid ) {
	    JSONObject obj = new JSONObject();
	    obj.put("uid", uid);
	    Query q = new Query(obj);
	    return queryAllSysRolesByUid(uid,q);
	}

    @Override
    public List<ServiceEntity> queryBackList(Query query) {
        return serviceDao.queryBackList(query);
    }

    @Override
    public int queryBackTotal(Query query) {
        return serviceDao.queryBackTotal(query);
    }

    @Override
    public int queryAllSysRolesByUidtotal(Query query) {
       return serviceDao.queryAllSysRolesByUidtotal(query);
    }

    @Override
    public List<ServiceEntity> queryAllSysRolesByUid(Long userId, Map<String,Object> query) {
        query.put("uid", userId.intValue());
        if(  Constant.SUPER_ADMIN == userId.intValue() ){
            return serviceDao.queryAllSysRoles(query);
        }
        return serviceDao.queryAllSysRolesByUid(query);
    }

	@Override
	public List<ServiceEntity> getServiceList(Long uid) {
		return queryAllSysRolesByUid(uid,new JSONObject());
	}

	@Override
	public List<String> getServiceTypeList(Long uid) {
		List<String> typelist = new ArrayList<String>();
		List<ServiceEntity> list = getServiceList(uid);
		if(ValidateUtil.isValid(list)){
			for(ServiceEntity s: list){
				if(ValidateUtil.isValid(s.getSign())){
					typelist.add(s.getSign());
				}
			}
		}
		return typelist;
	}
}
