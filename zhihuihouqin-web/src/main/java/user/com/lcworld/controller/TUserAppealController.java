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

import com.lcworld.entity.TUserAppealEntity;
import com.lcworld.service.TUserAppealService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.SmsUtils;


/**
 * 账号申诉
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:42
 */
@RestController
@RequestMapping("tuserappeal")
public class TUserAppealController {
	@Autowired
	private TUserAppealService tUserAppealService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tuserappeal:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TUserAppealEntity> tUserAppealList = tUserAppealService.queryList(query);
		int total = tUserAppealService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tUserAppealList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tuserappeal:info")
	public R info(@PathVariable("id") Integer id){
		TUserAppealEntity tUserAppeal = tUserAppealService.queryObject(id);
		
		return R.ok().put("tUserAppeal", tUserAppeal);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tuserappeal:save")
	public R save(@RequestBody TUserAppealEntity tUserAppeal){
		tUserAppealService.save(tUserAppeal);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tuserappeal:update")
	public R update(@RequestBody TUserAppealEntity tUserAppeal){
		tUserAppealService.update(tUserAppeal);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/updateStatus")
	public R updateStatus(@RequestBody TUserAppealEntity tUserAppeal){
	    tUserAppealService.update(tUserAppeal);
	    if(tUserAppeal.getStatus() == 1){
	        SmsUtils.getInstance().sendinfo(tUserAppeal.getMobile(), "您的账号申诉"+getStr(tUserAppeal)+",请及时查看");
	    }
	    return R.ok().put("tUserAppeal", tUserAppeal);
	}
	
	private String getStr(TUserAppealEntity tUserAppeal) {
        if(tUserAppeal.getStatus() == 1){
            return "成功";
        }else if(tUserAppeal.getStatus() == 2){
            return "失败,原因："+tUserAppeal.getFailurereason();
        }
        return "";
    }


    /**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tuserappeal:delete")
	public R delete(@RequestBody Integer[] ids){
		tUserAppealService.deleteBatch(ids);
		return R.ok();
	}
	
}
