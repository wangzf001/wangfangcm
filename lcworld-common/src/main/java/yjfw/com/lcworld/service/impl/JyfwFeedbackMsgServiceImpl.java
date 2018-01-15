package com.lcworld.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcworld.consts.APPConstant;
import com.lcworld.dao.JyfwFeedbackMsgDao;
import com.lcworld.entity.JyfwFeedbackMsgEntity;
import com.lcworld.entity.TBxwxNoticeEntity;
import com.lcworld.entity.TalkVo;
import com.lcworld.service.JyfwFeedbackMsgService;
import com.lcworld.utils.ValidateUtil;



@Service("jyfwFeedbackMsgService")
public class JyfwFeedbackMsgServiceImpl implements JyfwFeedbackMsgService {
	@Autowired
	private JyfwFeedbackMsgDao jyfwFeedbackMsgDao;
	
	@Override
	public JyfwFeedbackMsgEntity queryObject(Integer id){
		return jyfwFeedbackMsgDao.queryObject(id);
	}
	
	@Override
	public List<JyfwFeedbackMsgEntity> queryList(Map<String, Object> map){
		return jyfwFeedbackMsgDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return jyfwFeedbackMsgDao.queryTotal(map);
	}
	
	@Override
	public void save(JyfwFeedbackMsgEntity jyfwFeedbackMsg){
		jyfwFeedbackMsg.setCreatetime(new Date());
		jyfwFeedbackMsgDao.save(jyfwFeedbackMsg);
	}
	
	@Override
	public void update(JyfwFeedbackMsgEntity jyfwFeedbackMsg){
		jyfwFeedbackMsgDao.update(jyfwFeedbackMsg);
	}
	
	@Override
	public void delete(Integer id){
		jyfwFeedbackMsgDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		jyfwFeedbackMsgDao.deleteBatch(ids);
	}

	@Override
	public void updateListRead(List<JyfwFeedbackMsgEntity> msgList) {
		ArrayList<Integer> complaintList = new ArrayList<>();
		if (ValidateUtil.isValid(msgList)) {
			for (int i = 0; i < msgList.size(); i++) {
				JyfwFeedbackMsgEntity msg = msgList.get(i);
				complaintList.add(msg.getComplaintId());
			}
			jyfwFeedbackMsgDao.updateListRead(complaintList.toArray(new Integer[1]));
		}
	}

	@Override
	public List<TalkVo> queryTalkPage(Map<String,Object> params) {
		params.put("sidx", "m.createtime");
    	params.put("order", "DESC");
		return changeToTalkVo(queryList(params), MapUtils.getInteger(params, "uid"));
	}
	@Override
	public List<TalkVo> changeToTalkVo(List<?> List,Integer uid){
		List<TalkVo> list = new ArrayList<>();
		if (ValidateUtil.isValid(List)) {
			for (Object object : List) {
				JyfwFeedbackMsgEntity obj = (JyfwFeedbackMsgEntity)object;
				TalkVo vo  = new TalkVo();
				vo.setNewContent(obj.getContent());
				//获取服务端用户头像
				vo.setUid(uid);
				vo.setServiceUid(obj.getServiceuid());
				vo.setServiceUsername("系统反馈人员");
				vo.setServicePhoto("http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201709201505903809464.png");
				vo.setTargetId(obj.getComplaintId());
				vo.setTargetType(APPConstant.TYPE_JYFK);
				list.add(vo);
			}
			return list;
		}else{
			return list;
		}
	}

	@Override
	public Integer queryServiceUid(Map<String, Object> params) {
		return jyfwFeedbackMsgDao.queryServiceUid(params);
	}

	 @Override
		public void setreaded(Integer id) {
			if(ValidateUtil.isValid(id)){
				JyfwFeedbackMsgEntity notice = new JyfwFeedbackMsgEntity();
				notice.setId(id);
				notice.setReadstatus(1);
				update(notice);
			}
			
		}
	
}
