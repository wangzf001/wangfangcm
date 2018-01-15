package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.DsfwCommentDao;
import com.lcworld.dao.DsfwMenderDao;
import com.lcworld.dto.ServiceUserDTO;
import com.lcworld.entity.DsfwMenderEntity;
import com.lcworld.entity.TBxwxMenderEntity;
import com.lcworld.service.DsfwCommentService;
import com.lcworld.service.DsfwMenderService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;



@Service("dsfwMenderService")
public class DsfwMenderServiceImpl implements DsfwMenderService {
	@Autowired
	private DsfwMenderDao dsfwMenderDao;
	@Autowired
	private DsfwCommentDao dsfwCommentDao;
	
	@Override
	public DsfwMenderEntity queryObject(Integer id){
		return dsfwMenderDao.queryObject(id);
	}
	
	@Override
	public List<DsfwMenderEntity> queryList(Map<String, Object> map){
		return dsfwMenderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dsfwMenderDao.queryTotal(map);
	}
	
	@Override
	public void save(DsfwMenderEntity dsfwMender){
		dsfwMenderDao.save(dsfwMender);
	}
	
	@Override
	public void update(DsfwMenderEntity dsfwMender){
		dsfwMenderDao.update(dsfwMender);
	}
	
	@Override
	public void delete(Integer id){
		dsfwMenderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dsfwMenderDao.deleteBatch(ids);
	}

	@Override
	public R getUserinfo(Integer uid) {
		DsfwMenderEntity bman = queryObject(uid);
		ServiceUserDTO dto = changeToUserDTO(bman);
		return R.ok().put("userInfo", dto);
	}

	@Override
	public ServiceUserDTO queryByUsername(String uname, Integer servicetypeid) {
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", uname);
		DsfwMenderEntity dman = dsfwMenderDao.queryByCondition(params);
		ServiceUserDTO user = changeToUserDTO(dman);
		return user;
	}

	@Override
	public ServiceUserDTO saveUser(ServiceUserDTO tuser) {
		DsfwMenderEntity bman = new DsfwMenderEntity();
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
	public ServiceUserDTO changeToUserDTO(DsfwMenderEntity t) {
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
		String realname = user.getRealname();
		String mobile = user.getMobile();
		String password = user.getPassword();
		String photo = user.getPhoto();
		Integer id = user.getId();
		DsfwMenderEntity dsfwMenderEntity = new DsfwMenderEntity();
		dsfwMenderEntity.setRealname(realname);
		dsfwMenderEntity.setMobile(mobile);
		dsfwMenderEntity.setPassword(password);
		dsfwMenderEntity.setId(id);
		dsfwMenderEntity.setPhoto(photo);
		dsfwMenderDao.update(dsfwMenderEntity);
	}

	@Override
	public List<?> CommentList(Query q) {
		return dsfwCommentDao.queryListByScoretype(q);
	}

	@Override
	public <T> T CommentDetail(JSONObject params) {
		return (T) dsfwCommentDao.queryObject(params);
	}

	@Override
	public R forgot(ServiceUserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer queryTotal(JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryCommentTotal(Query query) {
		return dsfwCommentDao.queryCommentTotal(query);
	}

}
