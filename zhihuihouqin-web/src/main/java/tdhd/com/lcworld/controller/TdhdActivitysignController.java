package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.lcworld.dto.TdhdActivityEnrollDTO;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.dto.TdhdActivityDTO;
import com.lcworld.dto.TdhdActivityUserDTO;
import com.lcworld.entity.BgypfwCommentEntity;
import com.lcworld.entity.TdhdActivitysignEntity;
import com.lcworld.service.TdhdActivitysignService;
import com.lcworld.util.POIUtil;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 团队活动系统-报名表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 13:45:51
 */
@RestController
@RequestMapping("tdhdactivitysign")
public class TdhdActivitysignController {
	@Autowired
	private TdhdActivitysignService tdhdActivitysignService;
	//poi导入导出映射关系
    private DualHashBidiMap titleMapping = new DualHashBidiMap();
	{
        titleMapping.put("id","编号");
        titleMapping.put("username","用户名");
		titleMapping.put("sex","性别");
		titleMapping.put("age","年龄");
		titleMapping.put("nation","民族");
		titleMapping.put("mobile","联系方式");
		titleMapping.put("depart","司局");
		titleMapping.put("office","处室");
		titleMapping.put("postion","职务");
    }
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TdhdActivityEnrollDTO> tdhdActivitysignList = tdhdActivitysignService.queryEnrollList(query);
		int total = tdhdActivitysignService.queryuserTotal(query);
		
		PageUtils pageUtil = new PageUtils(tdhdActivitysignList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/userlist")
	public R userlist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    if(!ValidateUtil.isValid(params.get("activityid"))){
	        return R.ok();
	    }
	    List<TdhdActivityUserDTO> tdhdActivitysignList = tdhdActivitysignService.queryuserList(query);
	    int total = tdhdActivitysignService.queryuserTotal(query);
	    
	    PageUtils pageUtil = new PageUtils(tdhdActivitysignList, total, query.getLimit(), query.getPage());
	    
	    return R.ok().put("page", pageUtil);
	}
	

	/**
	 * 信息
	 */
	@RequestMapping("/info/{asId}")
	public R info(@PathVariable("asId") Integer asId){
		TdhdActivitysignEntity tdhdActivitysign = tdhdActivitysignService.queryObject(asId);
		
		return R.ok().put("tdhdActivitysign", tdhdActivitysign);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TdhdActivitysignEntity tdhdActivitysign){
		tdhdActivitysignService.save(tdhdActivitysign);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TdhdActivitysignEntity tdhdActivitysign){
		tdhdActivitysignService.update(tdhdActivitysign);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] asIds){
		tdhdActivitysignService.deleteBatch(asIds);
		
		return R.ok();
	}
	
	/**
     * 导出excel
     * 
     * @param mIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportExcel")
    public R exportExcel(Integer[] mIds, HttpServletResponse response) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("ids", mIds);
		List<TdhdActivityEnrollDTO> tdhdActivitysignList = tdhdActivitysignService.queryEnrollList(params);
        POIUtil.generateExcel(titleMapping, tdhdActivitysignList, response);
        return R.ok();
    }
	
}
