package com.lcworld.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.ServiceUserDTO;
import com.lcworld.dto.UserDTO;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;

public interface IOperationUserService<T> {
	/**
	 * 查询用户信息
	 */
	public R getUserinfo(Integer uid);
	

	/*
	 * 通过用户名查询用户
	 */
	public ServiceUserDTO queryByUsername(String uname,Integer servicetypeid);
	/**
	 * 保存用户
	 * @param tuser
	 * @return
	 */
	public ServiceUserDTO saveUser(ServiceUserDTO tuser);
	public ServiceUserDTO changeToUserDTO(T t);
	/**
	 * 修改用户
	 * @param user
	 */
	public void update(ServiceUserDTO user);
	/**
	 * 评价列表
	 * @param user
	 */
	List<?> CommentList(Query q);
	/**
	 * 评价详情
	 * @param user
	 */
	<T> T CommentDetail(JSONObject params);
	/**
	 * 查所有
	 * @param user
	 */
	int queryCommentTotal(Query query);

	public R forgot(ServiceUserDTO user);


	Integer queryTotal(JSONObject params);
}
