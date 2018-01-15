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

import com.lcworld.entity.BgypfwProductimgEntity;
import com.lcworld.service.BgypfwProductimgService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 办公用品服务-商品规格图片
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
@RestController
@RequestMapping("bgypfwproductimg")
public class BgypfwProductimgController {
	@Autowired
	private BgypfwProductimgService bgypfwProductimgService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
		public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwProductimgEntity> bgypfwProductimgList = bgypfwProductimgService.queryList(query);
		int total = bgypfwProductimgService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwProductimgList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
		public R info(@PathVariable("id") Integer id){
		BgypfwProductimgEntity bgypfwProductimg = bgypfwProductimgService.queryObject(id);
		
		return R.ok().put("bgypfwProductimg", bgypfwProductimg);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
		public R save(@RequestBody BgypfwProductimgEntity bgypfwProductimg){
		bgypfwProductimgService.save(bgypfwProductimg);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
		public R update(@RequestBody BgypfwProductimgEntity bgypfwProductimg){
		bgypfwProductimgService.update(bgypfwProductimg);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
		public R delete(@RequestBody Integer[] ids){
		bgypfwProductimgService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
