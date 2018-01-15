package com.lcworld.service;

import com.lcworld.entity.UserCaptchaEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户验证码
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-05 11:05:36
 */
public interface UserCaptchaService {
	
	UserCaptchaEntity queryObject(Integer id);
	
	List<UserCaptchaEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserCaptchaEntity userCaptcha);
	
	void update(UserCaptchaEntity userCaptcha);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
