package com.lcworld.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lcworld.dao.TUserDao;
import com.lcworld.entity.TUserEntity;

@Component
public class TestTask {
	@Autowired
	private TUserDao userDao;
	public void test(TUserDao userDao){
		System.err.println("我执行了");
	}
	public void test2(){
		System.err.println(userDao);
		TUserEntity userEntity = userDao.queryObject(1);
		
		System.err.println("我执行了:API中的方法");
		System.err.println(userEntity);
	}
}
