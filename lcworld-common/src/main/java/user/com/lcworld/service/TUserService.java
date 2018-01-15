package com.lcworld.service;

import com.lcworld.dto.UserDTO;
import com.lcworld.entity.TUserEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 14:55:26
 */
public interface TUserService {
	
	TUserEntity queryObject(Integer id);
	
	List<TUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TUserEntity tUser);
	
	void update(TUserEntity tUser);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    TUserEntity queryByUserName(String userName);

    TUserEntity getUserByThird(Integer type, String thirdNum);

    void setThirdNumByType(TUserEntity u, Integer type, String thirdNum);

    String getThirdnumByType(TUserEntity user, Integer type);

    void saveUser(TUserEntity tuser);

    List<UserDTO> queryUserList(Query query);

    int queryUserTotal(Query query);
    /**
     * 获取职位列表
     * @return
     */
	List<Map<String, Object>> queryPositionList();

 
    List<UserDTO> queryUserList1(Query query);

    int queryUserTotal1(Query query);

    /**获取有效的用户
     * @param intValue
     * @return
     */
    TUserEntity queryByAvaliableUid(int uid);

}
