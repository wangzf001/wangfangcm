package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.TalkVo;

public interface TalkService {
	/**
	 * 查询聊天页数据
	 * @param params
	 * @return
	 */
	List<TalkVo> queryTalkPage(Map<String,Object> params);
	/**
	 * 将任意类型的list转换为TalkVo的list
	 * @param List
	 * @param uid
	 * @return
	 */
	List<TalkVo> changeToTalkVo(List<?> List,Integer uid);
	
	/**
	 * 设置已读
	 */
	void setreaded(Integer id);
}
