package com.lcworld.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcworld.entity.TalkVo;
import com.lcworld.service.JyfwFeedbackMsgService;
import com.lcworld.service.LffwNoticeService;
import com.lcworld.service.TBxwxNoticeService;
import com.lcworld.service.TalkService;
import com.lcworld.service.YlfwZjzzNoticeService;
@Service("talkService")
public class TalkServiceImpl implements TalkService {
	@Autowired
	private JyfwFeedbackMsgService feedbackMsgService;
	@Autowired
	private LffwNoticeService LffwNoticeService;
	@Autowired
	private YlfwZjzzNoticeService ylfwZjzzNoticeService;
	@Autowired
	private TBxwxNoticeService tBxwxNoticeService;
	@Override
	public List<TalkVo> queryTalkPage(Map<String, Object> params) {
		List<TalkVo> talkList = new ArrayList<>();
		talkList.addAll(feedbackMsgService.queryTalkPage(params));
		talkList.addAll(LffwNoticeService.queryTalkPage(params));
		talkList.addAll(ylfwZjzzNoticeService.queryTalkPage(params));
		talkList.addAll(tBxwxNoticeService.queryTalkPage(params));
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
