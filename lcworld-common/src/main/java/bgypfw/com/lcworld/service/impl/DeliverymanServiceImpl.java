package com.lcworld.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.DeliverymanDao;
import com.lcworld.dto.ServiceUserDTO;
import com.lcworld.entity.DeliverymanEntity;
import com.lcworld.service.DeliverymanService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.RRException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service("deliverymanService")
public class DeliverymanServiceImpl implements DeliverymanService {
	@Autowired
	private DeliverymanDao deliverymanDao;
	
	@Override
	public DeliverymanEntity queryObject(Integer id){
		return deliverymanDao.queryObject(id);
	}
	
	@Override
	public List<DeliverymanEntity> queryList(Map<String, Object> map){
		return deliverymanDao.queryList(map);
	}

	@Override
	public List<DeliverymanEntity> queryDeliveryList(Map<String, Object> map) {
		return null;
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return deliverymanDao.queryTotal(map);
	}
	
	@Override
	public void save(DeliverymanEntity deliveryman){
		deliverymanDao.save(deliveryman);
	}
	
	@Override
	public void update(DeliverymanEntity deliveryman){
		deliverymanDao.update(deliveryman);
	}
	
	@Override
	public void delete(Integer id){
		deliverymanDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		deliverymanDao.deleteBatch(ids);
	}

	@Override
	public ServiceUserDTO queryByUsername(String uname,Integer servicetypeid) {
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", uname);
		params.put("servicetypeid", servicetypeid);
		
		DeliverymanEntity dman = deliverymanDao.wzbqueryByCondition(params);
		ServiceUserDTO user = changeToUserDTO(dman);
		return user;
	}

	@Override
	public ServiceUserDTO saveUser(ServiceUserDTO tuser) {
		DeliverymanEntity dman = new DeliverymanEntity();
		dman.setCreatetime(tuser.getCreatetime());
		dman.setMobile(tuser.getUsername());
		dman.setOrdernum(0);
		dman.setPassword(tuser.getPassword());
		dman.setPhoto("http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:test:img201711131510587842176.png");
		dman.setServicetypeid(tuser.getServicetypeid());
		dman.setUsername(tuser.getUsername());
		dman.setValid(1);
		save(dman);
		return changeToUserDTO(dman);
	}

	@Override
	public R getUserinfo(Integer uid) {
		DeliverymanEntity dman = queryObject(uid);
		ServiceUserDTO dto = changeToUserDTO(dman);
		return R.ok().put("userInfo", dto);
	}

	@Override
	public ServiceUserDTO changeToUserDTO(DeliverymanEntity t) {
		ServiceUserDTO userDTO = null;
		if (t != null) {
			userDTO = new ServiceUserDTO();
			userDTO.setId(t.getId());
			userDTO.setPassword(t.getPassword());
			userDTO.setServicetypeid(t.getServicetypeid());
			userDTO.setUsername(t.getUsername());
			userDTO.setValid(t.getValid());
			userDTO.setPhoto(t.getPhoto());
			userDTO.setRealname(t.getUsername());
			userDTO.setMobile(t.getMobile());
		}
		return userDTO;
	}

	@Override
	public void update(ServiceUserDTO user) {
		if(!StringUtils.isBlank(user.getMobile())){
			// 校验唯一性
			if(deliverymanDao.queryByPhoneAndServiceType(user)>0){
				throw new RRException("手机号重复");
			}
		}
		
		DeliverymanEntity t = new DeliverymanEntity();
		t.setId(user.getId());
		t.setPhoto(user.getPhoto());
		t.setUsername(user.getRealname());
		t.setPassword(user.getPassword());
		t.setMobile(user.getMobile());
		
		deliverymanDao.update(t);
	}

	@Override
	public List<?> CommentList(Query q) {
		return deliverymanDao.queryListByScoretype(q);
	}

	@Override
	public <T> T CommentDetail(JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R forgot(ServiceUserDTO user) {
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", user.getUsername());
		params.put("servicetypeid", user.getServicetypeid());
		params.put("password", user.getPassword());
		
		deliverymanDao.updatePasswordByMobileAndServiceTypeId(params);
		return R.ok();
	}

	@Override
	public int queryCommentTotal(Query query) {
		return deliverymanDao.queryCommentTotal(query);
	}

	@Override
	public Integer queryTotal(JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

}
