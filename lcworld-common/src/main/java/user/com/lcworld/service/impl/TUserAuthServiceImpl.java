package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TCarUsercarinfoDao;
import com.lcworld.dao.TUserAuthDao;
import com.lcworld.dao.TUserDao;
import com.lcworld.entity.TCarUsercarinfoEntity;
import com.lcworld.entity.TUserAuthEntity;
import com.lcworld.entity.TUserEntity;
import com.lcworld.service.TUserAuthService;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.vo.TUserAuthVO;



@Service("tUserAuthService")
public class TUserAuthServiceImpl implements TUserAuthService {
	@Autowired
	private TUserAuthDao tUserAuthDao;
	@Autowired
	private TCarUsercarinfoDao tCarUsercarinfoDao;
	@Autowired
	private TUserDao tUserDao;
	
	@Override
	public TUserAuthEntity queryObject(Integer id){
		return tUserAuthDao.queryObject(id);
	}
	
	@Override
	public List<TUserAuthEntity> queryList(Map<String, Object> map){
		return tUserAuthDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tUserAuthDao.queryTotal(map);
	}
	
	@Override
	public void save(TUserAuthEntity tUserAuth){
		tUserAuthDao.save(tUserAuth);
	}
	
	@Override
	public void update(TUserAuthEntity tUserAuth){
		tUserAuthDao.update(tUserAuth);
	}
	
	@Override
	public void delete(Integer id){
		tUserAuthDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tUserAuthDao.deleteBatch(ids);
	}

    @Override
    public void saveauth(TUserAuthVO authVo) {
        TUserAuthEntity auth = authVo.getAuth();
        auth.setStatus(1);
        save(auth);
        //保存用户认证状态
        TUserEntity tUserEntity = new TUserEntity();
        tUserEntity.setId(auth.getUid());
        tUserEntity.setAuthStatus(1);
        tUserDao.update(tUserEntity);
        List<TCarUsercarinfoEntity> carlist = authVo.getCarList();
        if(ValidateUtil.isValid(carlist)){
            for(TCarUsercarinfoEntity car : carlist){
                car.setUid(auth.getUid());
                car.setAuthid(auth.getId());
                tCarUsercarinfoDao.save(car);
            }
        }
    }

    @Override
    public void saveauthinfo(TUserAuthEntity tUserAuth) {
        JSONObject params  = new JSONObject();
        params.put("idcard", tUserAuth.getIdcard());
        params.put("status", 3);
        List<TUserAuthEntity> nlist = queryList(params);
        if(ValidateUtil.isValid(nlist)){
            for(TUserAuthEntity en : nlist){
                delete(en.getId());
            }
        }
        save(tUserAuth);
    }

    @Override
    public void setauthscuess(TUserAuthEntity tUserAuth) {
        update(tUserAuth);
        TUserEntity user = tUserDao.queryObject(tUserAuth.getUid());
        user.setMobile(tUserAuth.getMobile());
        user.setNation(tUserAuth.getNation());
        user.setPositionid(tUserAuth.getPositionid());
        user.setRealname(tUserAuth.getName());
        user.setRoomnum(tUserAuth.getRoomnum());
        user.setTel(tUserAuth.getOfficetel());
        user.setBuildnum(tUserAuth.getBuildnum());
        user.setAuthStatus(tUserAuth.getStatus());
        user.setDepartid(tUserAuth.getDepartid());
        user.setOfficeid(tUserAuth.getOfficeid());
        tUserDao.update(user);
    }

    @Override
    public void updateAuth(TUserAuthEntity auth) {
            update(auth);
            TUserEntity user = new TUserEntity();
            user.setAuthStatus(auth.getStatus());
            user.setId(auth.getUid());
            tUserDao.update(user);
    }
	
}
