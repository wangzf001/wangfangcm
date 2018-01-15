package com.lcworld.service;

import com.lcworld.entity.TCarUsercarinfoEntity;
import com.lcworld.entity.TUserAuthEntity;
import com.lcworld.vo.TUserAuthVO;

import java.util.List;
import java.util.Map;

/**
 * 认证表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:42
 */
public interface TUserAuthService {
	
	TUserAuthEntity queryObject(Integer id);
	
	List<TUserAuthEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TUserAuthEntity tUserAuth);
	
	void update(TUserAuthEntity tUserAuth);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void saveauth(TUserAuthVO authVo);

    void saveauthinfo(TUserAuthEntity tUserAuth);

    void setauthscuess(TUserAuthEntity tUserAuth);

    void updateAuth(TUserAuthEntity auth);
}
