package com.lcworld.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.PurchaseAccounlogDao;
import com.lcworld.dao.PurchaseAccountDao;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.entity.BaseUserRoleEntity;
import com.lcworld.entity.DepartEntity;
import com.lcworld.entity.OfficeEntity;
import com.lcworld.entity.PayinfoEntity;
import com.lcworld.entity.PurchaseAccounlogEntity;
import com.lcworld.entity.PurchaseAccountEntity;
import com.lcworld.entity.PurchaseTypeEntity;
import com.lcworld.entity.TUserEntity;
import com.lcworld.factory.PurchaseCountServiceFactory;
import com.lcworld.service.BaseUserRoleService;
import com.lcworld.service.DepartService;
import com.lcworld.service.IPurchaseCountService;
import com.lcworld.service.OfficeService;
import com.lcworld.service.PurchaseAccountService;
import com.lcworld.service.PurchaseTypeService;
import com.lcworld.service.TUserService;
import com.lcworld.utils.ValidateUtil;



@Service("purchaseAccountService")
public class PurchaseAccountServiceImpl implements PurchaseAccountService {
    private Logger log = LoggerFactory.getLogger(PurchaseAccountServiceImpl.class);
    @Autowired
	private PurchaseAccountDao purchaseAccountDao;
	@Autowired
	private PurchaseAccounlogDao purchaseAccounlogDao;
	@Autowired
	private PurchaseCountServiceFactory purchaseCountServiceFactory;
	@Autowired
	private PurchaseTypeService purchaseTypeService;
	@Autowired
	private TUserService tUserService;
	@Autowired
	private DepartService departService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private BaseUserRoleService baseUserRoleService;
	
	@Override
	public PurchaseAccountEntity queryObject(Integer id){
		return purchaseAccountDao.queryObject(id);
	}
	
	@Override
	public List<PurchaseAccountEntity> queryList(Map<String, Object> map){
		return purchaseAccountDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return purchaseAccountDao.queryTotal(map);
	}
	
	@Override
	public void save(PurchaseAccountEntity purchaseAccount){
		purchaseAccountDao.save(purchaseAccount);
	}
	
	@Override
	public void update(PurchaseAccountEntity purchaseAccount){
		purchaseAccountDao.update(purchaseAccount);
	}
	
	@Override
	public void delete(Integer id){
		purchaseAccountDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		purchaseAccountDao.deleteBatch(ids);
	}

    @Override
    public PurchaseAccountEntity queryGroupAcount(JSONObject param) {
        return purchaseAccountDao.queryGroupAcount(param);
    }

    @Override
    public void savePurchasePayed(PurchaseAccountEntity account, PayinfoEntity pay) {
        purchaseAccountDao.update(account);
        PurchaseAccounlogEntity log = new PurchaseAccounlogEntity();
        log.setCreatetime(new Date());
        log.setAccountid(account.getId());
        log.setMoney(pay.getPaymoney());
        log.setOrderid(pay.getOrderid());
        log.setOrdertype(pay.getOrdertype());
        log.setOrdertypename(APPConstant.gettypeMap().get(pay.getOrdertype()));
        log.setUid(pay.getUid());
        log.setUsetype(2);//使用类型 1: 收入，2： 支出
        purchaseAccounlogDao.save(log);
        
    }

    /*分割servicecode，获取list
     *  (non-Javadoc)
     * @see com.lcworld.service.PurchaseAccountService#getPubPurchaseList(java.lang.Integer, java.lang.String)
     */
    @Override
    public List<PurchaseDTO> getPubPurchaseList(Integer uid, String servicecode) {
        List<PurchaseDTO> purlist = new ArrayList<PurchaseDTO>();
        TUserEntity user = tUserService.queryObject(uid);
        if(ValidateUtil.isValid(servicecode)){
            addList(purlist,user,servicecode);
        }else{
            List<PurchaseTypeEntity> typelist = purchaseTypeService.queryList(new HashMap<String,Object>());
           if(ValidateUtil.isValid(typelist)){
               for(PurchaseTypeEntity type : typelist){
                   addList(purlist,user,type.getServicecode());
               }
           }
        }
        return purlist;
    }

    /**
     * 添加list
     * @param purlist
     * @param user
     * @param servicecode
     */
    private void addList(List<PurchaseDTO> purlist, TUserEntity user, String servicecode) {
        Integer type = Integer.parseInt(servicecode);
        IPurchaseCountService service = purchaseCountServiceFactory.getPurchaseCountService(Integer.parseInt(servicecode));
        //if(hasPayRight(user,type)){
            List<PurchaseDTO> list = service.getPubPurchaseList(user.getId(),user.getDepartid(),user.getOfficeid(),servicecode);
            if(ValidateUtil.isValid(list)){
                purlist.addAll(list);
            //}
        }
    }
    
    /**是否显示办公用品其他成员订单
     * @param user
     * @return
     */
    @Override
    public boolean showOtherMemberList( TUserEntity user){
        boolean flag = false;
        DepartEntity depart = departService.queryObject(user.getDepartid());
        if("1".equalsIgnoreCase(depart.getBgyprighttype())){
            //union  是三级管理并且拥有支付权限
             flag =!(isthirdadmin(user) && hasPayRight(user,APPConstant.TYPE_BGYPFW));
        }else if("2".equals(depart.getBgyprighttype())){
            //depart
            flag = true;
        }else{
            throw new RuntimeException("请联系管理员,设置部门对公办公用品使用类型");
        }
        return flag;
    }

    /**是否含有支付权限
     * @param user
     * @param typeBgypfw 
     * @return
     */
    @Override
    public boolean hasPayRight(TUserEntity user, int type) {
        if(!ValidateUtil.isValid(type)){
          return false;
        }
        JSONObject obj = new JSONObject();
        obj.put("uid", user.getId());
        obj.put("servicecode", type);
        List<BaseUserRoleEntity> list = baseUserRoleService.queryRoleList(obj);
        return  ValidateUtil.isValid(list);
       
    }

    /**是三级管理
     * @param userName
     * @return
     */
    @Override
    public boolean isthirdadmin(TUserEntity user) {
        boolean flag = false;
        OfficeEntity office = officeService.queryObject(user.getOfficeid());
        if(ValidateUtil.isValid(office)){
            flag = office.getUid()== user.getId();
        }else{
            log.warn("uid:"+user.getId()+",手机号："+user.getUserName()+"没有指定处室");
        }
        return flag;
    }
	
}
