package com.lcworld.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.VisiuserDao;
import com.lcworld.entity.*;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.*;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.SmsUtils;
import com.lcworld.utils.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service("visiuserService")
public class VisiuserServiceImpl implements VisiuserService {
	@Autowired
	private VisiuserDao visiuserDao;
	@Autowired
	private UserVisibyService userVisibyService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private VisiuserLogService visiuserLogService;
	@Autowired
	private TUserService userservice;
	@Autowired
	private MessageService messageService;
	@Autowired
	private VisiuserNoticeService visiuserNoticeService;
	
	@Override
	public VisiuserEntity queryObject(Integer id){
		return visiuserDao.queryObject(id);
	}
	
	@Override
	public VisiuserEntity queryObjectByOid(Integer id) {
		return visiuserDao.queryObjectByOid(id);
	}

	@Override
	public VisiuserEntity queryVisitLogById(Integer id) {
		return visiuserDao.queryVisitLogById(id);
	}

	@Override
	public List<VisiuserEntity> queryList(Map<String, Object> map){
		return visiuserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return visiuserDao.queryTotal(map);
	}
	
	@Override
	public void save(VisiuserEntity visiuser){
		visiuserDao.save(visiuser);
	}
	
	@Override
	public void update(VisiuserEntity visiuser){
		visiuserDao.update(visiuser);
	}
	
	@Override
	public void delete(Integer id){
		visiuserDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		visiuserDao.deleteBatch(ids);
	}

    @Override
    public void checkBatch(Integer[] ids) {
        visiuserDao.checkBatch(ids);
        
    }

    @Override
    public void uncheckBatch(JSONObject obj) {
        visiuserDao.uncheckBatch(obj);
        
    }

	@Override
	public List<Map<String,Object>> queryOrderList(Query q) {
		return visiuserDao.queryOrderList(q);
	}

	private void savevuser(VisiuserEntity visitUser) {
		VisiuserLogEntity log = visiuserLogService.queryObject(visitUser.getVlid());
		if(ValidateUtil.isValid(log)){
			
			log.setVisitchecked(1);
			log.setChecked(0);
			log.setVisitreason("");
			log.setAdminreason("");
			visitUser.setStatus(1);
			visitUser.setIsdel(0);
			visitUser.setChecked(0);
			visitUser.setVisitchecked(1);
			visitUser.setNotify(0);
			
			if(null == log.getVuid() || -1 == log.getVuid()){
				//add
				visitUser.setCreatetime(new Date());
				visitUser.setChecked(0);
				visitUser.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_WLRY));
				save(visitUser);
			}else{
				//update
				if(1 == visitUser.getVisitchecked()){
					throw new ZHHQException(1800, "被访人已审核通过,不可进行修改");
				}
				update(visitUser);
			}
			
			log.setStatus(1);
			log.setVuid(visitUser.getId());
			visiuserLogService.update(log);
		}else{
			throw new ZHHQException(500, "您无权进行此操作");
		}
		
		userVisibyService.deleteByvlid(log.getId());
	     if(ValidateUtil.isValid(visitUser.getByuserlist())){
			for(UserVisibyEntity by : visitUser.getByuserlist()){
				by.setVisituid(visitUser.getId());
				by.setVlid(log.getId());
			}
		}
	     if(ValidateUtil.isValid(visitUser.getByuserlist())){
	    	 userVisibyService.savebench(visitUser.getByuserlist());
	     }
	     
	     
	     
		
	}

	@Override
	public void updateVisitUserlog(VisiuserEntity visitUser) {
		JSONObject params = new JSONObject();
		params.put("uid", visitUser.getUid());
		params.put("stamp", visitUser.getStamp());
		VisiuserEntity visiuserEntity = null;
		VisiuserLogEntity log = null;
		List<VisiuserLogEntity> visiuserLoglist = visiuserLogService.queryList(params);
		if(ValidateUtil.isValid(visiuserLoglist)){
			log = visiuserLoglist.get(0);
			if(null != log.getVuid() && -1 != log.getVuid()){
				//已下单\
				visiuserEntity = queryObject(visitUser.getId());
				deaorder(1,log);
			}else{
				//未下单
				deaorder(2,log);
			}
			
			log.setUnit(visitUser.getUnit());
			log.setVidnum(visitUser.getVidnum());
			log.setVisittime(visitUser.getVisittime());
			log.setVmobile(visitUser.getVmobile());
			log.setVname(visitUser.getVname());
			log.setVpnum(Integer.parseInt(visitUser.getVpnum()));
			log.setNotify(visitUser.getNotify());
			log.setBuildnum(visitUser.getBuildnum());
			visiuserLogService.update(log);
			
			//处理byuser
			userVisibyService.deleteByvlid(log.getId());
		     if(ValidateUtil.isValid(visitUser.getByuserlist())){
				for(UserVisibyEntity by : visitUser.getByuserlist()){
					by.setVlid(log.getId());
				}
			}
		     if(ValidateUtil.isValid(visitUser.getByuserlist())){
		    	 userVisibyService.savebench(visitUser.getByuserlist());
		     }
		     if(1 == log.getNotify()){ 
					TUserEntity user = userservice.queryObject(visitUser.getUid());
					String addr = "您有待审核的来访人员订单,请及时查看并审核";
					SmsUtils.getInstance().sendinfo(user.getUserName(), addr);
				}
		     
		     //add notify
		     VisiuserNoticeEntity  visiuserNotice = new VisiuserNoticeEntity();
		     visiuserNotice.setContent("您有待审核的来访人员订单,请及时查看并审核");
		     visiuserNotice.setCreatetime(new Date());
		     visiuserNotice.setGetid(log.getUid());
		     visiuserNotice.setGetreadstatus(0);
		     visiuserNoticeService.save(visiuserNotice);
			
		}else{
			throw new ZHHQException(500, "没有之前的发送信息记录");
		}
		
	}

	/**处理订单信息
	 * @param i
	 * @param log
	 */
	private void deaorder(int i,  VisiuserLogEntity log) {
		if(1 == log.getVisitchecked() ){
			//被访人通过
			if(1 ==log.getChecked() ){
				throw new ZHHQException(500, "信息已被审核通过，不可修改");
			}else if( 0 == log.getChecked()){
				throw new ZHHQException(500, "信息已被被访人审核通过，正在等待管理员审核");
			}else if(2 == log.getChecked()){
				//回到待审核 并删除 订单
				log.setVisitchecked(0);
				log.setChecked(0);
				log.setVisitreason("");
				log.setAdminreason("");
				if(1 == i ){
					//删除已审核订单
					delete(log.getVuid());
					log.setVuid(-1);
				}
			}
		}else if (2 == log.getVisitchecked() || 0 == log.getVisitchecked()){
			//被访人不通过
			log.setVisitchecked(0);
			log.setChecked(0);
			log.setVisitreason("");
			log.setAdminreason("");
			
		}
	}

	@Override
	public List<Map<String,Object>> queryvuserList(Query query) {
		return visiuserDao.queryvuserList(query);
	}

	private void savevuserself(VisiuserEntity visitUser) {
		visitUser.setCreatetime(new Date());
		visitUser.setChecked(0);
		visitUser.setNotify(0);
		visitUser.setStatus(1);
		visitUser.setIsdel(0);
		visitUser.setVisitchecked(1);
		visitUser.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_WLRY));
		save(visitUser);
		
		userVisibyService.deleteBypid(visitUser.getId());
	     if(ValidateUtil.isValid(visitUser.getByuserlist())){
			for(UserVisibyEntity by : visitUser.getByuserlist()){
				by.setVisituid(visitUser.getId());
			}
		}
	     if(ValidateUtil.isValid(visitUser.getByuserlist())){
	    	 userVisibyService.savebench(visitUser.getByuserlist());
	     }
	}

	@Override
	public void savevisituser(VisiuserEntity visitUser) {
		if(1 == visitUser.getFrom()){
			//来自待提交订单
			savevuser(visitUser);
		}else if(2 == visitUser.getFrom()){
			savevuserself(visitUser);
		}
		
	}
	
	
}
