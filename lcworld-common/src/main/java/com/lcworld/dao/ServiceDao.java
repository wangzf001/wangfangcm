package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.utils.Query;

/**
 * 服务表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:19:28
 */
public interface ServiceDao extends BaseDao<ServiceEntity> {
	/**
	 * 更新用户服务
	 * @param params
	 */
	void addUserService(Map<String, Object> params);
	/**
	 * 删除所有用户服务
	 * @param params
	 */
	void deleteAllUserservice(Map<String, Object> params);
	
	//系统角色类
		List<ServiceEntity> queryAllSysRoles(Map<String, Object> query);
		List<ServiceEntity> queryAllSysRolesByUid(Map<String, Object> params);
        List<ServiceEntity> queryBackList(Query query);
        List<ServiceEntity> queryObjectByName(Map<String, Object> map);
        int queryBackTotal(Query query);
        int queryAllSysRolesByUidtotal(Map<String,Object> query);
		List<ServiceEntity> getServiceList(JSONObject obj);
	
}
