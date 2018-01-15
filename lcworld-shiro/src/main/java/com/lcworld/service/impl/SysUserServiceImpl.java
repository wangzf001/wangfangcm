package com.lcworld.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcworld.dao.SysUserDao;
import com.lcworld.entity.SysRoleEntity;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.service.SysRoleService;
import com.lcworld.service.SysUserRoleService;
import com.lcworld.service.SysUserService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.Query;
import com.lcworld.utils.RRException;



/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;

	@Override
	public List<String> queryAllPerms(Long userId) {
		return sysUserDao.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return sysUserDao.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return sysUserDao.queryByUserName(username);
	}
	
	@Override
	public SysUserEntity queryObject(Long userId) {
		return sysUserDao.queryObject(userId);
	}

	@Override
	public List<SysUserEntity> queryList(Map<String, Object> map){
		List<SysUserEntity> userList = sysUserDao.queryList(map);
		for (int i = 0; i < userList.size(); i++) {
			SysUserEntity user = userList.get(i);
			List<Long> roleIdList = sysUserRoleService.queryRoleIdList(user.getUserId());
			user.setRoleIdList(roleIdList);
			for (Long roleId : roleIdList) {
				SysRoleEntity role = sysRoleService.queryObject(roleId);
				user.getRoleList().add(role);
			}
		}
		return userList;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysUserDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		sysUserDao.save(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		}
		sysUserDao.update(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] userId) {
		sysUserDao.deleteBatch(userId);
	}

	@Override
	public int updatePassword(Long userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return sysUserDao.updatePassword(map);
	}
	
	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user){
//		//如果不是超级管理员，则需要判断用户的角色是否自己创建
//		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
//			return ;
//		}
//		//查询用户创建的角色列表
//		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());
//		
//		//判断是否越权
//		if(!roleIdList.containsAll(user.getRoleIdList())){
//			throw new RRException("新增用户所选角色，不是本人创建");
//		}
	}

	@Override
	public List<SysUserEntity> queryUsersByUid(Map<String, Object> params) {
		return sysUserDao.queryUsersByUid(params);
	}

	@Override
	public int queryTotalByUid(Map<String, Object> params) {
		return sysUserDao.queryTotalByUid(params);
	}

    @Override
    public List<SysUserEntity> queryUsers(Query query) {
        return sysUserDao.queryUsers(query);
    }

    @Override
    public int queryUsersTotal(Map<String, Object> params) {
        return sysUserDao.queryUsersTotal(params);
    }
}
