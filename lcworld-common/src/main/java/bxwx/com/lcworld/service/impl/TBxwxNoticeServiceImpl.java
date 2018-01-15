package com.lcworld.service.impl;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lcworld.consts.APPConstant;
import com.lcworld.dao.TBxwxNoticeDao;
import com.lcworld.entity.JyfwFeedbackMsgEntity;
import com.lcworld.entity.LffwNoticeEntity;
import com.lcworld.entity.TBxwxNoticeEntity;
import com.lcworld.entity.TalkVo;
import com.lcworld.service.TBxwxNoticeService;
import com.lcworld.utils.ValidateUtil;



@Service("tBxwxNoticeService")
public class TBxwxNoticeServiceImpl implements TBxwxNoticeService {
	@Autowired
	private TBxwxNoticeDao tBxwxNoticeDao;
	
	@Override
	public TBxwxNoticeEntity queryObject(Integer id){
		return tBxwxNoticeDao.queryObject(id);
	}
	
	@Override
	public List<TBxwxNoticeEntity> queryList(Map<String, Object> map){
		return tBxwxNoticeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tBxwxNoticeDao.queryTotal(map);
	}
	
	@Override
	public void save(TBxwxNoticeEntity tBxwxNotice){
		tBxwxNoticeDao.save(tBxwxNotice);
	}
	
	@Override
	public void update(TBxwxNoticeEntity tBxwxNotice){
		tBxwxNoticeDao.update(tBxwxNotice);
	}
	
	@Override
	public void delete(Integer id){
		tBxwxNoticeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tBxwxNoticeDao.deleteBatch(ids);
	}
	@Override
	public List<TalkVo> queryTalkPage(Map<String,Object> params) {
		params.put("sidx", "createtime");
    	params.put("order", "DESC");
		return changeToTalkVo(queryList(params), MapUtils.getInteger(params, "uid"));
	}
	@Override
	public List<TalkVo> changeToTalkVo(List<?> List,Integer uid){
		List<TalkVo> list = new ArrayList<>();
		if (ValidateUtil.isValid(List)) {
			for (Object object : List) {
				TBxwxNoticeEntity obj = (TBxwxNoticeEntity)object;
				TalkVo vo  = new TalkVo();
				vo.setNewContent(obj.getContent());
				//获取服务端用户头像
				vo.setUid(uid);
				if (obj.getUtype().intValue()==1) {
					vo.setServiceUid(obj.getGetid());
				}else{
					vo.setServiceUid(obj.getPostid());
				}
				vo.setServiceUsername("维修人员");
				vo.setServicePhoto("http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201709201505903809464.png");
				vo.setTargetId(obj.getOrderid());
				vo.setTargetType(APPConstant.TYPE_BXFW);
				list.add(vo);
			}
		}
		return list;
	}

	  @Override
		public void setreaded(Integer id) {
			if(ValidateUtil.isValid(id)){
				TBxwxNoticeEntity notice = new TBxwxNoticeEntity();
				notice.setId(id);
				notice.setGetreadstatus(1);
				update(notice);
			}
			
		}
}
