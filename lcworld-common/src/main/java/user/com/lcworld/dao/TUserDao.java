package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.dto.UserDTO;
import com.lcworld.entity.TUserEntity;
import com.lcworld.utils.Query;

/**
 * 用户表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 14:55:26
 */
public interface TUserDao extends BaseDao<TUserEntity> {

    List<UserDTO> queryUserList(Query query);

    int queryUserTotal(Query query);

	List<Map<String, Object>> queryPositionList();

    List<UserDTO> queryUserList1(Query query);

    int queryUserTotal1(Query query);

    TUserEntity queryByAvaliableUid(int uid);
	
}
