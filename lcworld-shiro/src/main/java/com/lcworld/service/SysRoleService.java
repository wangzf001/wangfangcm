package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.SysRoleEntity;
import com.lcworld.utils.Query;


/**
 * 角色
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface SysRoleService {
	
	SysRoleEntity queryObject(Long roleId);
	
	List<SysRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleEntity role);
	
	void update(SysRoleEntity role);
	
	void deleteBatch(Long[] roleIds);
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);

	/**获取权限排序最小值
	 * @param param
	 * @return
	 */
	Integer queryMinRoleSort(JSONObject param);

	List<SysRoleEntity> queryRolelistByUid(Map<String, Object> map);

	List<SysRoleEntity> querypubList(Map<String, Object> map);

	int querypubTotal(Query query);

    List<SysRoleEntity> queryListByUid(Map<String, Object> map);

    int queryNPUserRoleMin(Map<String, Object> map);

    int queryTotalByUid(Map<String, Object> map);

    List<SysRoleEntity> querypubList1(JSONObject param);

    int querypubTotal1(JSONObject param);
}
