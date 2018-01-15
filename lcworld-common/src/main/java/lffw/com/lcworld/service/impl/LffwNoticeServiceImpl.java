package com.lcworld.service.impl;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lcworld.consts.APPConstant;
import com.lcworld.dao.LffwNoticeDao;
import com.lcworld.entity.LffwNoticeEntity;
import com.lcworld.entity.TBxwxNoticeEntity;
import com.lcworld.entity.TalkVo;
import com.lcworld.entity.VisiuserNoticeEntity;
import com.lcworld.service.LffwNoticeService;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;



@Service("lffwNoticeService")
public class LffwNoticeServiceImpl implements LffwNoticeService {
	@Autowired
	private LffwNoticeDao lffwNoticeDao;
	
	@Override
	public LffwNoticeEntity queryObject(Integer id){
		return lffwNoticeDao.queryObject(id);
	}
	
	@Override
	public List<LffwNoticeEntity> queryList(Map<String, Object> map){
		return lffwNoticeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwNoticeDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwNoticeEntity lffwNotice){
		lffwNoticeDao.save(lffwNotice);
	}
	
	@Override
	public void update(LffwNoticeEntity lffwNotice){
		lffwNoticeDao.update(lffwNotice);
	}
	
	@Override
	public void delete(Integer id){
		lffwNoticeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwNoticeDao.deleteBatch(ids);
	}

    @Override
    public List<LffwNoticeEntity> querynoticeList(Query q) {
        return lffwNoticeDao.querynoticeList(q);
    }

    @Override
	public List<TalkVo> queryTalkPage(Map<String,Object> params) {
    	params.put("sidx", "a.createtime");
    	params.put("order", "DESC");
		return changeToTalkVo(queryList(params), MapUtils.getInteger(params, "uid"));
	}
	@Override
	public List<TalkVo> changeToTalkVo(List<?> List,Integer uid){
		List<TalkVo> list = new ArrayList<>();
		if (ValidateUtil.isValid(List)) {
			for (Object object : List) {
				LffwNoticeEntity obj = (LffwNoticeEntity)object;
				TalkVo vo  = new TalkVo();
				vo.setNewContent(obj.getContent());
				//获取服务端用户头像
				vo.setUid(uid);
				if (obj.getUtype().intValue()==1) {
					vo.setServiceUid(obj.getGetid());
				}else{
					vo.setServiceUid(obj.getPostid());
				}
				vo.setServiceUsername("理发师");
				vo.setServicePhoto("http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201709201505903809464.png");
				vo.setTargetType(APPConstant.TYPE_LFFW_YYLF);
				vo.setTargetId(obj.getServiceuid());
				list.add(vo);
			}
		}
		return list;
	}

    @Override
    public int querynoticeListTotal(Query q) {
       return lffwNoticeDao.querynoticeListTotal(q);
    }

    @Override
	public void setreaded(Integer id) {
		if(ValidateUtil.isValid(id)){
			LffwNoticeEntity notice = new LffwNoticeEntity();
			notice.setId(id);
			notice.setGetreadstatus(1);
			lffwNoticeDao.update(notice);
		}
		
	}
	
}
