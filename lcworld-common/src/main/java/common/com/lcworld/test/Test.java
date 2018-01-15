package com.lcworld.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lcworld.dao.TestDao;
import com.lcworld.entity.TestEntity;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:spring-jdbc.xml"})  
public class Test {
	@Resource
	private TestDao testDao;
	@org.junit.Test
	public void tests(){
		List<TestEntity> queryList = testDao.queryListByUser();
		System.out.println(queryList);
	}
}
