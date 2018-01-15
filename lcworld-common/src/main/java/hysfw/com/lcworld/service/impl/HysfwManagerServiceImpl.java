package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.HysfwCommentDao;
import com.lcworld.dao.HysfwManagerDao;
import com.lcworld.dto.ServiceUserDTO;
import com.lcworld.entity.BgypfwMenderEntity;
import com.lcworld.entity.HysfwManagerEntity;
import com.lcworld.service.HysfwManagerService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;



@Service("hysfwManagerService")
public class HysfwManagerServiceImpl implements HysfwManagerService {
	@Autowired
	private HysfwManagerDao hysfwManagerDao;
	@Autowired
	private HysfwCommentDao hysfwCommentDao;
	
	@Override
	public HysfwManagerEntity queryObject(Integer id){
		return hysfwManagerDao.queryObject(id);
	}
	
	@Override
	public List<HysfwManagerEntity> queryList(Map<String, Object> map){
		return hysfwManagerDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hysfwManagerDao.queryTotal(map);
	}
	
	@Override
	public void save(HysfwManagerEntity hysfwManager){
		hysfwManagerDao.save(hysfwManager);
	}
	
	@Override
	public void update(HysfwManagerEntity hysfwManager){
		hysfwManagerDao.update(hysfwManager);
	}
	
	@Override
	public void delete(Integer id){
		hysfwManagerDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hysfwManagerDao.deleteBatch(ids);
	}

	@Override
	public R getUserinfo(Integer uid) {
		HysfwManagerEntity bman = queryObject(uid);
		ServiceUserDTO dto = changeToUserDTO(bman);
		return R.ok().put("userInfo", dto);
	}

	@Override
	public ServiceUserDTO queryByUsername(String uname, Integer servicetypeid) {
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", uname);
		HysfwManagerEntity dman = hysfwManagerDao.queryByCondition(params);
		ServiceUserDTO user = changeToUserDTO(dman);
		return user;
	}

	@Override
	public ServiceUserDTO saveUser(ServiceUserDTO tuser) {
		HysfwManagerEntity bman = new HysfwManagerEntity();
		bman.setScore(5.0);
		bman.setMobile(tuser.getUsername());
		bman.setPassword(tuser.getPassword());
		bman.setPhoto("http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png");
		bman.setUsername(tuser.getUsername());
		bman.setValid(1);
		save(bman);
		return changeToUserDTO(bman);
	}

	@Override
	public ServiceUserDTO changeToUserDTO(HysfwManagerEntity t) {
		ServiceUserDTO userDTO = null;
		if (ValidateUtil.isValid(t)) {
			userDTO = new ServiceUserDTO();
			userDTO.setId(t.getId());
			userDTO.setPassword(t.getPassword());
			userDTO.setServicetypeid(APPConstant.TYPE_BXFW);
			userDTO.setUsername(t.getMobile());
			userDTO.setValid(t.getValid());
			userDTO.setPhoto(t.getPhoto());
			userDTO.setRealname(t.getName());
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
		HysfwManagerEntity hysfwManagerEntity = new HysfwManagerEntity();
		hysfwManagerEntity.setName(realname);
		hysfwManagerEntity.setMobile(mobile);
		hysfwManagerEntity.setPassword(password);
		hysfwManagerEntity.setId(id);
		hysfwManagerEntity.setPhoto(photo);
		hysfwManagerDao.update(hysfwManagerEntity);
		
	}

	@Override
	public List<?> CommentList(Query q) {
		return hysfwCommentDao.queryListByScoretype(q);
	}

	@Override
	public <T> T CommentDetail(JSONObject params) {
		return null;//hysfwCommentDao.queryList(params);
	}
	@Override
	public List<HysfwManagerEntity> queryMenderlist(Map<String, Object> query) {
		 return hysfwManagerDao.queryMenderlist(query);
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
		return hysfwCommentDao.queryCommentTotal(query);
	}

	
}
