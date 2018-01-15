package com.lcworld.controller;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.entity.EnergyCostEntity;
import com.lcworld.entity.EnergyImgsEntity;
import com.lcworld.entity.EnergyInformationEntity;
import com.lcworld.service.EnergyCostService;
import com.lcworld.service.EnergyImgsService;
import com.lcworld.service.EnergyInformationService;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("appuser/energy")
public class EnergyController {
    private Logger log = LoggerFactory.getLogger(EnergyController.class);

    @Autowired
    private EnergyCostService energyCostService;
    @Autowired
    private EnergyImgsService energyImgsService;

    @Autowired
    private EnergyInformationService energyInformationService;

    /**
     * 节能减排首页
     */
    @IgnoreToken
    @IgnoreSign
    @PostMapping("/index")
    public R index(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.info("资讯信息" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        //查询列表数据
        Map params = JSONObject.parseObject(biz,Map.class);
        Query query = new Query(params);
        List<EnergyInformationEntity> energyInformationList = energyInformationService.queryList(query);
        query.put("page", 1);
        query.put("limit", 5);
        List<EnergyImgsEntity> imgsEntities = energyImgsService.queryList(query);
        return R.ok().put("news", energyInformationList).put("rollingPic",imgsEntities);
    }

    /**
     * 信息
     */
    @PostMapping("/information")
    public R information(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.info("资讯信息" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        JSONObject jsonObject = JSONObject.parseObject(biz);
        EnergyInformationEntity energyInformation = energyInformationService.queryObject(jsonObject.getInteger("id"));
        return R.ok().put("data", energyInformation);
    }

    //获取前一天的电耗和水耗
    @RequestMapping("/bothCostQuery")
    @ResponseBody
    public R bothCostQuery(HttpServletRequest req){
    	String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        //查询列表数据
        HashMap<String,Object> result = new HashMap<>();

        Integer eleCost = 0;
        Integer waterCost = 0;
	    eleCost = energyCostService.bothCostQuery(1);//查询电耗
	    waterCost = energyCostService.bothCostQuery(2);//查询水耗
	    // 1,电   2，水 
	    if(eleCost>0 && eleCost<=10000){
	    	eleCost = 1;
	    }else if(eleCost>10000 && eleCost<=30000){
	    	eleCost = (int) (1+(Math.ceil((eleCost-10000)/4000)));
	    }else if(eleCost>30000){
	    	eleCost = 7;
	    }else{
	    	eleCost = 0;
	    }
	    
	    if(waterCost != 0 && waterCost<=300){
	     waterCost = (int) Math.ceil(waterCost/50);
	    }else if (waterCost>300){
	    	waterCost = 7;
	    }else if (waterCost == 0) {
	    	waterCost = 0;
	    }
	    
        result.put("eleCost", eleCost);
        result.put("waterCost", waterCost);
        return R.ok(result);
    }
    
    
    @RequestMapping("/energyCostQuery")
    @ResponseBody
    public R energyCostQuery(HttpServletRequest req){
    	String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        params.put("", 1);
        
		Map<String, Object> query = new HashMap<String, Object>(params);
	    List<EnergyCostEntity> energylist = energyCostService.energyCostQuery(query);
	    EnergyCostEntity energyCostEntity = energyCostService.energyCostTotalQuery(query);

	    HashMap<String,Object> resultMap = new HashMap<>();
	    resultMap.put("energyList", energylist);
        resultMap.put("energyTotal", energyCostEntity);

		return R.ok(resultMap);
    }
    //两栋楼消耗对比

    @RequestMapping("/energyCostCompare")
    @ResponseBody
    public R energyCostCompare(HttpServletRequest req){
    	String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        @SuppressWarnings("unchecked")
		List<Integer> intList = (List<Integer>) params.get("bid");
        Map<String,Object> map = new HashMap<String, Object>();
		@SuppressWarnings("serial")
		List<Object> pageList = new ArrayList<Object>(){};
        for (Integer integer : intList) {
			map.put("dateType", params.getInteger("dateType"));
			map.put("energyType", params.getInteger("energyType"));
			map.put("date", params.getString("date"));
			map.put("bid",integer);
			Query query = new Query(map);

		    List<EnergyCostEntity> energylist = energyCostService.energyCostQuery(query);
		    int total = energyCostService.queryTotal(query);
			PageUtils pageUtil= new PageUtils(energylist, total, query.getLimit(), query.getPage());

			pageList.add(pageUtil);
		}
		return R.ok().put("page", pageList);
    }
}
