package com.lcworld.service;

import com.lcworld.entity.PayinfoEntity;
import com.lcworld.entity.UserWalletEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户钱包
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
public interface UserWalletService {
	
	UserWalletEntity queryObject(Integer id);
	
	List<UserWalletEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserWalletEntity userWallet);
	
	void update(UserWalletEntity userWallet);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void saveWalletPayed(UserWalletEntity wallet, PayinfoEntity pay);
    /**
     * 通过用户id查用户钱包
     * @param integer
     * @return
     */
	UserWalletEntity queryByUid(Integer integer);

}
