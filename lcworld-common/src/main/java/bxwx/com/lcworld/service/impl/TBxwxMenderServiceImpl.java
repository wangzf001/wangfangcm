package com.lcworld.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.TBxwxCommentDao;
import com.lcworld.dao.TBxwxMenderDao;
import com.lcworld.dto.BxwxMenderDTO;
import com.lcworld.dto.ServiceUserDTO;
import com.lcworld.entity.DeliverymanEntity;
import com.lcworld.entity.TBxwxCommentEntity;
import com.lcworld.entity.TBxwxMenderEntity;
import com.lcworld.service.TBxwxCommentService;
import com.lcworld.service.TBxwxMenderService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.RRException;
import com.lcworld.utils.util.ValidateUtil;

import sun.awt.AppContext;



@Service("tBxwxMenderService")
public class TBxwxMenderServiceImpl implements TBxwxMenderService {
	@Autowired
	private TBxwxMenderDao tBxwxMenderDao;
	@Autowired
	private TBxwxCommentDao bxwxCommentDao;
	
	
	@Override
	public TBxwxMenderEntity queryObject(Integer id){
		return tBxwxMenderDao.queryObject(id);
	}
	
	@Override
	public List<TBxwxMenderEntity> queryList(Map<String, Object> map){
		return tBxwxMenderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tBxwxMenderDao.queryTotal(map);
	}
	
	@Override
	public void save(TBxwxMenderEntity tBxwxMender){
		tBxwxMenderDao.save(tBxwxMender);
	}
	
	@Override
	public void update(TBxwxMenderEntity tBxwxMender){
		tBxwxMenderDao.update(tBxwxMender);
	}
	
	@Override
	public void delete(Integer id){
		tBxwxMenderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tBxwxMenderDao.deleteBatch(ids);
	}

    @Override
    public List<TBxwxMenderEntity> queryMenderlist(Map<String,Object> query) {
       return tBxwxMenderDao.queryMenderlist(query);
    }

	@Override
	public R getUserinfo(Integer uid) {
		TBxwxMenderEntity bman = queryObject(uid);
		ServiceUserDTO dto = changeToUserDTO(bman);
		return R.ok().put("userInfo", dto);
	}

	@Override
	public ServiceUserDTO queryByUsername(String uname, Integer servicetypeid) {
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", uname);
		TBxwxMenderEntity dman = tBxwxMenderDao.queryByCondition(params);
		ServiceUserDTO user = changeToUserDTO(dman);
		return user;
	}

	@Override
	public ServiceUserDTO saveUser(ServiceUserDTO tuser) {
		TBxwxMenderEntity bman = new TBxwxMenderEntity();
		bman.setCount(0);
		bman.setScore(5.0);
		bman.setCreatetime(tuser.getCreatetime());
		bman.setMobile(tuser.getUsername());
		bman.setPassword(tuser.getPassword());
		bman.setPhoto("http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png");
		bman.setUsername(tuser.getUsername());
		bman.setRealname(tuser.getUsername());
		bman.setValid(1);
		save(bman);
		return changeToUserDTO(bman);
	}

	@Override
	public ServiceUserDTO changeToUserDTO(TBxwxMenderEntity t) {
		ServiceUserDTO userDTO = null;
		if (ValidateUtil.isValid(t)) {
			userDTO = new ServiceUserDTO();
			userDTO.setId(t.getId());
			userDTO.setPassword(t.getPassword());
			userDTO.setServicetypeid(APPConstant.TYPE_BXFW);
			userDTO.setUsername(t.getMobile());
			userDTO.setValid(t.getValid());
			userDTO.setPhoto(t.getPhoto());
			userDTO.setRealname(t.getRealname());
			userDTO.setMobile(t.getMobile());
		}
		return userDTO;
	}

	@Override
	public void update(ServiceUserDTO user) {
		if(!StringUtils.isBlank(user.getMobile())){
			if(tBxwxMenderDao.countByMobileAndServiceTypeAndId(user) > 0){
				throw new RRException("手机号重复");
			}
		}
		
		TBxwxMenderEntity tBxwxMenderEntity = new TBxwxMenderEntity();
		tBxwxMenderEntity.setRealname(user.getRealname());
		tBxwxMenderEntity.setMobile(user.getMobile());
		tBxwxMenderEntity.setPassword(user.getPassword());
		tBxwxMenderEntity.setId(user.getId());
		tBxwxMenderEntity.setPhoto(user.getPhoto());
		tBxwxMenderDao.update(tBxwxMenderEntity);
	}

	@Override
	public List<?> CommentList(Query q) {
		return bxwxCommentDao.queryListByScoretype(q);
	}

	@Override
	public List<TBxwxCommentEntity> CommentDetail(JSONObject params) {
		return bxwxCommentDao.queryList(params);
	}

	@Override
	public R forgot(ServiceUserDTO user) {
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", user.getUsername());
		TBxwxMenderEntity dman = tBxwxMenderDao.queryByCondition(params);
		dman.setPassword(user.getPassword());
		update(user);
		return R.ok();
	}

	@Override
	public Integer queryTotal(JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryCommentTotal(Query query) {
		return bxwxCommentDao.queryCommentTotal(query);
	}

	@Override
	public int quer(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
