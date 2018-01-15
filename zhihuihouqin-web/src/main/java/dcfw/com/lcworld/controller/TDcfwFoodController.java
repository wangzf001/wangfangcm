package com.lcworld.controller;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.lcworld.entity.BgypfwProductEntity;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lcworld.entity.TDcfwFoodEntity;
import com.lcworld.service.TDcfwFoodService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订餐系统-食物
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
@RestController
@RequestMapping("dcfwfood")
public class TDcfwFoodController {
	@Autowired
	private TDcfwFoodService tDcfwFoodService;
	//poi导入导出映射关系
	private DualHashBidiMap titleMapping = new DualHashBidiMap();
	{
		titleMapping.put("id","序号");
		titleMapping.put("name","菜名");
		titleMapping.put("original","食材");
		titleMapping.put("price","价格");
		titleMapping.put("img","图片");
		titleMapping.put("totalcount","总数量");
		titleMapping.put("remain","剩余数量");
		titleMapping.put("uploadname","上传人");
		titleMapping.put("createtime","创建时间");
	}
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TDcfwFoodEntity> tDcfwFoodList = tDcfwFoodService.queryList(query);
		int total = tDcfwFoodService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDcfwFoodList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TDcfwFoodEntity tDcfwFood = tDcfwFoodService.queryObject(id);
		return R.ok().put("dcfwFood", tDcfwFood);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TDcfwFoodEntity tDcfwFood){
		tDcfwFood.setRemain(tDcfwFood.getTotalcount());
		tDcfwFood.setCreatetime(new Date());
		tDcfwFoodService.save(tDcfwFood);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TDcfwFoodEntity tDcfwFood){
		tDcfwFoodService.update(tDcfwFood);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tDcfwFoodService.deleteBatch(ids);
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
	public R exportExcel(Integer[] mIds, HttpServletResponse response){
		HashMap<String, Object> params = new HashMap<>();
		params.put("ids", mIds);
		List<TDcfwFoodEntity> queryList = tDcfwFoodService.queryList(params);
		tDcfwFoodService.exportExcel(titleMapping, queryList, response);
		//POIUtil.generateExcel(titleMapping, queryList, response);
		return R.ok();
	}

	/**
	 * 生成excel模板
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/downloadExcel")
	public R downloadExcel(HttpServletResponse response){
		DualHashBidiMap titles = titleMapping;
		titles.remove("id");
		List<TDcfwFoodEntity> queryList = new ArrayList<>();
		queryList.add(new TDcfwFoodEntity());
		tDcfwFoodService.exportExcel(titles, queryList, response);
		return R.ok();
	}

	/**
	 * 导入excel
	 * @param mIds
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/inportExcel")
	public R inportExcel(@RequestParam("file") MultipartFile file){
		//List<TDcfwFoodEntity> list = POIUtil.readExcel(TDcfwFoodEntity.class,titleMapping,file);
		List<TDcfwFoodEntity> list = tDcfwFoodService.importExcel(TDcfwFoodEntity.class, titleMapping, file);
		System.err.println("个数"+list);
		tDcfwFoodService.saveBatch(list);
		return R.ok();
	}
}
