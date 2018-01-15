package com.lcworld.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.FwCommenviewEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.SysUserEntity;
import com.lcworld.service.FwCommenviewService;
import com.lcworld.service.ServiceService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ShiroUtils;
import com.lcworld.utils.ValidateUtil;
import com.qcloud.cos.http.HttpRequest;


/**
 * 服务统计
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:00:55
 */
@RestController
@RequestMapping("statistics")
public class StatisticsController {
	@Autowired
	private FwCommenviewService fwCommenviewService;
	@Autowired
	private ServiceService serviceService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("statistics:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		
		/*Long userId = ShiroUtils.getUserId();
		params.put("uid", userId);*/
        Query query = new Query(params);

		List<FwCommenviewEntity> fwCommenviewList = fwCommenviewService.queryList(query);
		int total = fwCommenviewService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(fwCommenviewList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 获取权限类型
	 */
	@RequestMapping("/roletypeList")
	public R roletypelist(){
		Long userId = ShiroUtils.getUserId();
		List<Map<String,Object>> typelist = new ArrayList<Map<String,Object>>();
		List<ServiceEntity> list = serviceService.getServiceList(userId);
		if(ValidateUtil.isValid(list)){
			for(ServiceEntity s: list){
				if(ValidateUtil.isValid(s.getSign())){
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("type", s.getSign());
					map.put("name", s.getName());
					typelist.add(map);
				}
			}
		}
		return R.ok().put("data", typelist);
	}

}
