package com.lcworld.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.BgypfwCommentDao;
import com.lcworld.dao.BgypfwMenderDao;
import com.lcworld.dto.ServiceUserDTO;
import com.lcworld.entity.BgypfwMenderEntity;
import com.lcworld.service.BgypfwMenderService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;


@Service("bgypfwMenderService")
public class BgypfwMenderServiceImpl implements BgypfwMenderService {
	@Autowired
	private BgypfwMenderDao bgypfwMenderDao;
	@Autowired
	private BgypfwCommentDao bgypfwCommentDao;
	
	
	@Override
	public BgypfwMenderEntity queryObject(Integer id){
		return bgypfwMenderDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwMenderEntity> queryList(Map<String, Object> map){
		return bgypfwMenderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwMenderDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwMenderEntity bgypfwMender){
		bgypfwMenderDao.save(bgypfwMender);
	}
	
	@Override
	public void update(BgypfwMenderEntity bgypfwMender){
		bgypfwMenderDao.update(bgypfwMender);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwMenderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwMenderDao.deleteBatch(ids);
	}

	@Override
	public R getUserinfo(Integer uid) {
		BgypfwMenderEntity bman = queryObject(uid);
		ServiceUserDTO dto = changeToUserDTO(bman);
		return R.ok().put("userInfo", dto);
	}

	@Override
	public ServiceUserDTO queryByUsername(String uname, Integer servicetypeid) {
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", uname);
		BgypfwMenderEntity dman = bgypfwMenderDao.queryByCondition(params);
		ServiceUserDTO user = changeToUserDTO(dman);
		return user;
	}

	@Override
	public ServiceUserDTO saveUser(ServiceUserDTO tuser) {
		BgypfwMenderEntity bman = new BgypfwMenderEntity();
		bman.setCount(0);
		bman.setScore(5.0);
		bman.setCreatetime(tuser.getCreatetime());
		bman.setMobile(tuser.getUsername());
		bman.setPassword(tuser.getPassword());
		bman.setPhoto("http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png");
		bman.setUsername(tuser.getUsername());
		bman.setValid(1);
		save(bman);
		return changeToUserDTO(bman);
	}

	@Override
	public ServiceUserDTO changeToUserDTO(BgypfwMenderEntity t) {
		ServiceUserDTO userDTO = null;
		if (t != null) {
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
		String realname = user.getRealname();
		String mobile = user.getMobile();
		String password = user.getPassword();
		String photo = user.getPhoto();
		Integer id = user.getId();
		BgypfwMenderEntity bgypfwMenderEntity = new BgypfwMenderEntity();
		bgypfwMenderEntity.setRealname(realname);
		bgypfwMenderEntity.setMobile(mobile);
		bgypfwMenderEntity.setPassword(password);
		bgypfwMenderEntity.setId(id);
		bgypfwMenderEntity.setPhoto(photo);
		bgypfwMenderDao.update(bgypfwMenderEntity);
		
	}

	@Override
	public List<?> CommentList(Query q) {
		return bgypfwCommentDao.queryListByScoretype(q);
	}

	@Override
	public <T> T CommentDetail(JSONObject params) {
		return (T) bgypfwCommentDao.queryObject(params);
	}

	@Override
	public List<BgypfwMenderEntity> queryMenderlist(Map<String, Object> query) {
		 return bgypfwMenderDao.queryMenderlist(query);
	}

	@Override
	public R forgot(ServiceUserDTO user) {
		return null;
	}

	@Override
	public Integer queryTotal(JSONObject params) {
		return bgypfwMenderDao.queryTotal(params);
	}

	@Override
	public int queryCommentTotal(Query query) {
		return  bgypfwCommentDao.queryCommentTotal(query);
	}

}
