package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BaseDao;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.utils.Query;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:34:11
 */
public interface SysUserDao extends BaseDao<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);
	
	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);

	List<SysUserEntity> queryUsersByUid(Map<String, Object> params);

	int queryTotalByUid(Map<String, Object> params);

    List<SysUserEntity> queryUsers(Query query);

    int queryUsersTotal(Map<String, Object> params);
}
