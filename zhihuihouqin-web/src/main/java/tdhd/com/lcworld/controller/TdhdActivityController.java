package com.lcworld.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.TdhdActivityEntity;
import com.lcworld.service.CheckfailureReasonService;
import com.lcworld.service.TdhdActivityService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 团队活动系统-活动
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 13:45:52
 */
@RestController
@RequestMapping("tdhdactivity")
public class TdhdActivityController {
	@Autowired
	private TdhdActivityService tdhdActivityService;
	@Autowired
	private CheckfailureReasonService checkfailureReasonService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tdhdactivity:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TdhdActivityEntity> tdhdActivityList = tdhdActivityService.queryList(query);
		int total = tdhdActivityService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tdhdActivityList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{aId}")
	@RequiresPermissions("tdhdactivity:info")
	public R info(@PathVariable("aId") Integer aId){
		TdhdActivityEntity tdhdActivity = tdhdActivityService.queryObject(aId);
		if(ValidateUtil.isValid(tdhdActivity.getaPhoto())){
		    tdhdActivity.getImgEntityList().addAll(Arrays.asList(tdhdActivity.getaPhoto().split(",")));
		}
		return R.ok().put("tdhdActivity", tdhdActivity);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tdhdactivity:save")
	public R save(@RequestBody TdhdActivityEntity tdhdActivity){
	    tdhdActivity.setaCreateTime(new Date());
	    tdhdActivity.setaIsChecked(1);
		tdhdActivityService.save(tdhdActivity);
		
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/updateCheck")
	public R updateCheck(@RequestBody TdhdActivityEntity tdhdActivity){
	    TdhdActivityEntity entry = new TdhdActivityEntity();
	    if(tdhdActivity.getFailurereasonid() != null){
	        entry.setFailurereason(checkfailureReasonService.queryObject(tdhdActivity.getFailurereasonid()).getReason());
	    }else{
	        entry.setFailurereason(tdhdActivity.getFailurereason());
	    }
	    entry.setaIsChecked(tdhdActivity.getaIsChecked());
	    entry.setaId(tdhdActivity.getaId());
	    tdhdActivityService.update(entry);
	    return R.ok();
	}
	

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tdhdactivity:update")
	public R update(@RequestBody TdhdActivityEntity tdhdActivity){
		tdhdActivityService.update(tdhdActivity);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tdhdactivity:delete")
	public R delete(@RequestBody Integer[] aIds){
		tdhdActivityService.deleteBatch(aIds);
		
		return R.ok();
	}
	
	/**
	 * reasonlist
	 */
	@RequestMapping("/reasonlist")
	public R reasonlist(){
	    JSONObject p = new JSONObject();
	    p.put("servicecode", APPConstant.TYPE_TDHD);
	    return R.ok().put("reasonlist", checkfailureReasonService.queryList(p));
	}
	
	
	
}
