package com.lcworld.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcworld.entity.TalkVo;
import com.lcworld.service.TalkService;
@Service("talkService")
public class TalkServiceImpl implements TalkService {
	@Override
	public List<TalkVo> queryTalkPage(Map<String, Object> params) {
		List<TalkVo> talkList = new ArrayList<>();
		return talkList;
	}
	@Override
	public List<TalkVo> changeToTalkVo(List<?> List, Integer uid) {
		return null;
	}
	@Override
	public void setreaded(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
