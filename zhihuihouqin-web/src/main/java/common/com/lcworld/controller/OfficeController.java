package com.lcworld.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.DepartEntity;
import com.lcworld.entity.OfficeEntity;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.service.OfficeService;
import com.lcworld.service.TUserService;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;


/**
 * 处室
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 18:37:02
 */
@RestController
@RequestMapping("office")
public class OfficeController {
	@Autowired
	private OfficeService officeService;
	@Autowired
	private TUserService tUserService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("office:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OfficeEntity> officeList = officeService.queryList(query);
		int total = officeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(officeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/officelist")
	public R officelist(@RequestParam Map<String, Object> params){
	  //查询列表数据
        List<OfficeEntity> officeList =null;
        SysUserEntity user = ShiroUtils.getUserEntity();
        if( Constant.SUPER_ADMIN  == ShiroUtils.getUserId().intValue()){
            officeList = officeService.queryList(params);
        }else{
            params.put("departid", user.getDepartid().intValue());
            params.put("officeid", user.getOfficeid().intValue());
            officeList = officeService.queryList(params);
        }
        return R.ok().put("officeList", officeList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("office:info")
	public R info(@PathVariable("id") Integer id){
		OfficeEntity office = officeService.queryObject(id);
		
		return R.ok().put("office", office);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("office:save")
	public R save(@RequestBody OfficeEntity office){
		officeService.save(office);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("office:update")
	public R update(@RequestBody OfficeEntity office){
		officeService.update(office);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/setfthirdadmin")
	public R setfthirdadmin(Integer uid){
	    Integer officeid = tUserService.queryObject(uid).getOfficeid();
	    OfficeEntity office = new OfficeEntity();
	    office.setId(officeid);
	    office.setUid(uid);
	    officeService.update(office);
	    return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("office:delete")
	public R delete(@RequestBody Integer[] ids){
		officeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
