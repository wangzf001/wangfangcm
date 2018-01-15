package com.lcworld.dao;

import java.util.List;

import com.lcworld.entity.TestEntity;

/**
 * 菜单管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:33:01
 */
public interface TestDao extends BaseDao<TestEntity> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<TestEntity> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<TestEntity> queryNotButtonList();
	
	/**
	 * 查询用户的权限列表
	 */
	List<TestEntity> queryUserList(Long userId);
	
	
	List<TestEntity> queryListByUser();
}
