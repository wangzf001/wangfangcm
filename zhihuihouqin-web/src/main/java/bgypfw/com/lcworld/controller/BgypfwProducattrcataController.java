package com.lcworld.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.BgypfwProducattrcataEntity;
import com.lcworld.entity.BgypfwSkuCataInfoEntity;
import com.lcworld.entity.BgypfwSkufirstcataEntity;
import com.lcworld.service.BgypfwProducattrcataService;
import com.lcworld.service.BgypfwSkufirstcataService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-28 11:43:37
 */
@RestController
@RequestMapping("bgypfwproducattrcata")
public class BgypfwProducattrcataController {
	@Autowired
	private BgypfwProducattrcataService bgypfwProducattrcataService;
	@Autowired
	private BgypfwSkufirstcataService bgypfwSkufirstcataService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwProducattrcataEntity> bgypfwProducattrcataList = bgypfwProducattrcataService.queryList(query);
		int total = bgypfwProducattrcataService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwProducattrcataList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/notattrlist")
	public R attrlist(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<BgypfwSkufirstcataEntity> attrlist = bgypfwSkufirstcataService.queryList(params);
		return R.ok().put("attrlist", attrlist);
	}
	
	@RequestMapping("/catalist")
	public R catalist(@RequestParam Map<String, Object> params){
		//查询列表数据
		 List<BgypfwProducattrcataEntity> catalist = bgypfwProducattrcataService.queryList(params);
		return R.ok().put("catalist", converttoattrinfo(catalist));
	}

	
	private  List<BgypfwSkuCataInfoEntity> converttoattrinfo(List<BgypfwProducattrcataEntity> catalist) {
		List<BgypfwSkuCataInfoEntity> addlist = new ArrayList<BgypfwSkuCataInfoEntity>();
		if(ValidateUtil.isValid(catalist)){
			for(BgypfwProducattrcataEntity en : catalist){
				
				BgypfwSkuCataInfoEntity e = new BgypfwSkuCataInfoEntity();
				e.setCataid(en.getCataid());
				e.setCataname(en.getCataname());
				addlist.add(e);
			}
		}
		return addlist;
		
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		BgypfwProducattrcataEntity bgypfwProducattrcata = bgypfwProducattrcataService.queryObject(id);
		
		return R.ok().put("bgypfwProducattrcata", bgypfwProducattrcata);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody BgypfwProducattrcataEntity bgypfwProducattrcata){
		bgypfwProducattrcataService.save(bgypfwProducattrcata);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody BgypfwProducattrcataEntity bgypfwProducattrcata){
		bgypfwProducattrcataService.update(bgypfwProducattrcata);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		bgypfwProducattrcataService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
