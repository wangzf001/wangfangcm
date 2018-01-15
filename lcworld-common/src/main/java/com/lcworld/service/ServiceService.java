package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;

/**
 * 服务表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:19:28
 */
public interface ServiceService {
	
	ServiceEntity queryObject(Integer id);
	
	List<ServiceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ServiceEntity service);
	
	void update(ServiceEntity service);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    ServiceEntity queryService(Integer typeJkzx);

	void updateUserService(JSONObject params);
	
	//系统角色类
		List<ServiceEntity> queryAllSysRolesByUid(Long uid);

        List<ServiceEntity> queryBackList(Query query);

        int queryBackTotal(Query query);

        int queryAllSysRolesByUidtotal(Query query);

        List<ServiceEntity> queryAllSysRolesByUid(Long userId, Map<String,Object> query);
        
        /**获取指定管理员的服务类型列表
         * @param uid
         * @return
         */
        List<ServiceEntity> getServiceList(Long uid);
        
        /**获取指定管理员的服务类型sign标识列表
         * @param uid
         * @return
         */
        List<String> getServiceTypeList(Long uid);

        /**子模块模糊搜索
         * @param uid
         * @return
         */
		List<ServiceEntity> queryObjectByName(Map<String, Object> map);

}
