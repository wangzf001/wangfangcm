package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TUserAuthEntity;
import com.lcworld.entity.TUserEntity;
import com.lcworld.service.DepartService;
import com.lcworld.service.NationService;
import com.lcworld.service.OfficeService;
import com.lcworld.service.TUserAuthService;
import com.lcworld.service.TUserPositionService;
import com.lcworld.service.TUserService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;


/**
 * 认证表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-28 17:21:21
 */
@RestController
@RequestMapping("tuserauth")
public class TUserAuthController {
	@Autowired
	private TUserAuthService tUserAuthService;
	@Autowired
	private NationService nationService;
	@Autowired
	private TUserPositionService tUserPositionService;
	  @Autowired
      private DepartService departService;
      @Autowired
      private OfficeService officeService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        
		List<TUserAuthEntity> tUserAuthList = tUserAuthService.queryList(query);
		int total = tUserAuthService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tUserAuthList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TUserAuthEntity tUserAuth = tUserAuthService.queryObject(id);
		
		return R.ok().put("tUserAuth", tUserAuth);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TUserAuthEntity tUserAuth){
	    TUserAuthEntity auth = tUserAuth;
        JSONObject params  = new JSONObject();
        params.put("idcard", auth.getIdcard());
        params.put("status", 3);
        List<TUserAuthEntity> nlist = tUserAuthService.queryList(params);
        if(ValidateUtil.isValid(nlist)){
            for(TUserAuthEntity en : nlist){
                tUserAuthService.delete(en.getId());
            }
        }
        params.put("status", 2);
        List<TUserAuthEntity> list = tUserAuthService.queryList(params);
        if(ValidateUtil.isValid(list)){
            return R.error(1015, "该身份证号已进行过实名认证");
        }
        params.put("status", 1);
        List<TUserAuthEntity> notauthlist = tUserAuthService.queryList(params);
        if(ValidateUtil.isValid(notauthlist)){
            return R.error(1016, "该身份证号正在审核中，不可重复提交");
        }
		tUserAuthService.saveauthinfo(tUserAuth);
		return R.ok().put("tUserAuth", tUserAuth);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TUserAuthEntity tUserAuth){
		tUserAuthService.update(tUserAuth);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/setauthscuess")
	public R setauthscuess(@RequestBody TUserAuthEntity tUserAuth){
	    tUserAuthService.setauthscuess(tUserAuth);
	    return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tUserAuthService.deleteBatch(ids);
		return R.ok();
	}
	
	/**
     * 获取认证详情
     */
    @RequestMapping("/userauthinfo")
    public R userauthinfo(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<TUserAuthEntity> tUserAuthList = tUserAuthService.queryList(query);
        R result = R.ok().put("tUserAuth", ValidateUtil.isValid(tUserAuthList)?tUserAuthList.get(0):new TUserAuthEntity());
        result.put("nationlist", nationService.queryList(new HashMap<String,Object>()));
        result.put("positionlist", tUserPositionService.queryList(new HashMap<String,Object>()));
        result.put("departlist", departService.queryList(new HashMap<String,Object>()));
        result.put("officelist", officeService.queryList(new HashMap<String,Object>()));
        return result;
    }
    /**
     * 获取认证详情
     */
    @RequestMapping("/updateAuthStatus")
    public R updateAuthStatus(@RequestBody TUserAuthEntity tUserAuth){
        
        TUserAuthEntity auth = tUserAuthService.queryObject(tUserAuth.getId());
        auth.setStatus(tUserAuth.getStatus());
        auth.setReason(tUserAuth.getReason());
        tUserAuthService.updateAuth(auth);
        return R.ok();
    }
	
}
