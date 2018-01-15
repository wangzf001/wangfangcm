package com.lcworld.service.impl;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lcworld.consts.APPConstant;
import com.lcworld.dao.YlfwZjzzNoticeDao;
import com.lcworld.entity.LffwNoticeEntity;
import com.lcworld.entity.TBxwxNoticeEntity;
import com.lcworld.entity.TalkVo;
import com.lcworld.entity.YlfwZjzzNoticeEntity;
import com.lcworld.service.YlfwZjzzNoticeService;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;



@Service("ylfwZjzzNoticeService")
public class YlfwZjzzNoticeServiceImpl implements YlfwZjzzNoticeService {
	@Autowired
	private YlfwZjzzNoticeDao ylfwZjzzNoticeDao;
	
	@Override
	public YlfwZjzzNoticeEntity queryObject(Integer id){
		return ylfwZjzzNoticeDao.queryObject(id);
	}
	
	@Override
	public List<YlfwZjzzNoticeEntity> queryList(Map<String, Object> map){
		return ylfwZjzzNoticeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwZjzzNoticeDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwZjzzNoticeEntity ylfwZjzzNotice){
		ylfwZjzzNoticeDao.save(ylfwZjzzNotice);
	}
	
	@Override
	public void update(YlfwZjzzNoticeEntity ylfwZjzzNotice){
		ylfwZjzzNoticeDao.update(ylfwZjzzNotice);
	}
	
	@Override
	public void delete(Integer id){
		ylfwZjzzNoticeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwZjzzNoticeDao.deleteBatch(ids);
	}

    @Override
    public List<YlfwZjzzNoticeEntity> queryNoticeList(Query q) {
        return ylfwZjzzNoticeDao.queryNoticeList(q);
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
				YlfwZjzzNoticeEntity obj = (YlfwZjzzNoticeEntity)object;
				TalkVo vo  = new TalkVo();
				vo.setNewContent(obj.getContent());
				//获取服务端用户头像
				vo.setUid(uid);
				if (obj.getUtype().intValue()==1) {
					vo.setServiceUid(obj.getGetid());
				}else{
					vo.setServiceUid(obj.getPostid());
				}
				vo.setServiceUsername("专家");
				vo.setServicePhoto("http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201709201505903809464.png");
				vo.setTargetId(vo.getServiceUid());
				vo.setTargetType(APPConstant.TYPE_YLFW_ZJZZ);
				list.add(vo);
			}
		}
		return list;
	}

    @Override
    public int queryNoticeListTotal(Query q) {
        return ylfwZjzzNoticeDao.queryNoticeListTotal(q);
    }

    @Override
	public void setreaded(Integer id) {
		if(ValidateUtil.isValid(id)){
			YlfwZjzzNoticeEntity notice = new YlfwZjzzNoticeEntity();
			notice.setId(id);
			notice.setGetreadstatus(1);
			update(notice);
		}
		
	}
}
