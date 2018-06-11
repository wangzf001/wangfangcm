package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.entity.WfAdInfoEntity;
import com.lcworld.service.WfAdInfoService;
import com.lcworld.utils.R;


@RestController
@RequestMapping("/ad")
public class AdInfoController {
    private Logger log = LoggerFactory.getLogger(AdInfoController.class);
	
    @Autowired
    private WfAdInfoService wfAdInfoService;
    
    @IgnoreSign
    @IgnoreToken
    @RequestMapping("/findAdInfo")
	public R findAdInfo(String biz) {
		
		log.debug(biz);
		
		JSONObject params = JSONObject.parseObject(biz);
		HashMap<String , Object>  map = new HashMap<String, Object>(params);
		
		List<WfAdInfoEntity> adList = wfAdInfoService.queryList(map);
		return R.ok().put("adList", adList);
		
	}
    
    
	
}
