
package com.lcworld.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {
	
	@RequestMapping("sys/{url}.html")
	public String page(@PathVariable("url") String url){
		String[] split = url.split("/");
		System.err.println(Arrays.toString(split));
		return "sys/" + url + ".html";
	}

	@RequestMapping("generator/{dir}/{url}.html")
	public String generator(@PathVariable("dir") String dir,@PathVariable("url") String url){
		String[] split = url.split("/");
		System.err.println(Arrays.toString(split));
		return "generator/"+dir+"/" + url + ".html";
	}
	
	@RequestMapping("generator/{parentdir}/{dir}/{url}.html")
	public String generator(@PathVariable("parentdir") String parentdir,@PathVariable("dir") String dir,@PathVariable("url") String url){
	    String[] split = url.split("/");
	    System.err.println(Arrays.toString(split));
	    return "generator/"+parentdir+"/"+dir+"/" + url + ".html";
	}
	@RequestMapping("generator/{url}.html")
	public String generator(@PathVariable("url") String url){
		String[] split = url.split("/");
		System.err.println(Arrays.toString(split));
		return "generator/" + url + ".html";
	}
}
