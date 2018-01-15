package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.BaseDao;
import com.lcworld.entity.SysRoleEntity;
import com.lcworld.utils.Query;

/**
 * 角色管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:33:33
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);

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
