package com.lcworld.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.SysRoleDao;
import com.lcworld.entity.SysRoleEntity;
import com.lcworld.service.SysRoleMenuService;
import com.lcworld.service.SysRoleService;
import com.lcworld.service.SysUserRoleService;
import com.lcworld.service.SysUserService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.Query;
import com.lcworld.utils.RRException;



/**
 * 角色
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserService sysUserService;

	@Override
	public SysRoleEntity queryObject(Long roleId) {
		return sysRoleDao.queryObject(roleId);
	}

	@Override
	public List<SysRoleEntity> queryList(Map<String, Object> map) {
		return sysRoleDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysRoleDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysRoleEntity role) {
		role.setCreateTime(new Date());
		sysRoleDao.save(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void update(SysRoleEntity role) {
		sysRoleDao.update(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] roleIds) {
		sysRoleDao.deleteBatch(roleIds);
	}
	
	@Override
	public List<Long> queryRoleIdList(Long createUserId) {
		return sysRoleDao.queryRoleIdList(createUserId);
	}

	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRoleEntity role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
//		//查询用户所拥有的菜单列表
//		List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
//		
//		//判断是否越权
//		if(!menuIdList.containsAll(role.getMenuIdList())){
//			throw new RRException("新增角色的权限，已超出你的权限范围");
//		}
	}

	@Override
	public Integer queryMinRoleSort(JSONObject param) {
		return sysRoleDao.queryMinRoleSort(param);
	}

	@Override
	public List<SysRoleEntity> queryRolelistByUid(Map<String, Object> map) {
		return sysRoleDao.queryRolelistByUid(map);
	}

	@Override
	public List<SysRoleEntity> querypubList(Map<String, Object> query) {
		return sysRoleDao.querypubList(query);
	}

	@Override
	public int querypubTotal(Query query) {
		return sysRoleDao.querypubTotal(query);
	}

    @Override
    public List<SysRoleEntity> queryListByUid(Map<String, Object> map) {
       return sysRoleDao.queryListByUid(map);
    }

    @Override
    public int queryNPUserRoleMin(Map<String, Object> map) {
       return sysRoleDao.queryNPUserRoleMin(map);
    }

    @Override
    public int queryTotalByUid(Map<String, Object> map) {
       return sysRoleDao.queryTotalByUid(map);
    }

    @Override
    public List<SysRoleEntity> querypubList1(JSONObject param) {
      return sysRoleDao.querypubList1(param);
    }

    @Override
    public int querypubTotal1(JSONObject param) {
       return sysRoleDao.querypubTotal1(param);
    }
}
