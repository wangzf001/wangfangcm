package com.lcworld.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.service.NationService;
import com.lcworld.utils.R;

@RestController
@RequestMapping("appuser/common")
public class CommonController {
    private Logger log = LoggerFactory.getLogger(CommonController.class);
    
    @Autowired
    private NationService nationService;

    @RequestMapping("nationlist")
    @ResponseBody
    public R nationlist(HttpServletRequest req){
      R result = new R();
      result.put("nationlist", nationService.queryList(new HashMap<String,Object>()));
      return result;
    }
}
