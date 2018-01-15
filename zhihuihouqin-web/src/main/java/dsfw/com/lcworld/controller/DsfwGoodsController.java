package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lcworld.entity.DsfwGoodsEntity;
import com.lcworld.entity.TYytcMealEntity;
import com.lcworld.service.DsfwGoodsService;
import com.lcworld.util.POIUtil;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订水服务-商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:21
 */
@RestController
@RequestMapping("dsfwgoods")
public class DsfwGoodsController {
	@Autowired
	private DsfwGoodsService dsfwGoodsService;
	//poi导入导出映射关系
	private DualHashBidiMap titleMapping = new DualHashBidiMap();
	{
		titleMapping.put("id","id");
		titleMapping.put("name","商品名");
		titleMapping.put("price","价格");
		titleMapping.put("img","图片");
		titleMapping.put("createtime","创建时间");
		titleMapping.put("cname","分类名");
		titleMapping.put("cid","分类id");
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DsfwGoodsEntity> dsfwGoodsList = dsfwGoodsService.queryList(query);
		int total = dsfwGoodsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dsfwGoodsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DsfwGoodsEntity dsfwGoods = dsfwGoodsService.queryObject(id);
		
		return R.ok().put("dsfwGoods", dsfwGoods);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DsfwGoodsEntity dsfwGoods){
		dsfwGoodsService.save(dsfwGoods);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DsfwGoodsEntity dsfwGoods){
		dsfwGoodsService.update(dsfwGoods);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dsfwGoodsService.deleteBatch(ids);
		
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
	public R exportExcel(Integer[] mIds,String[] mapkeys, HttpServletResponse response) throws Exception {
		DualHashBidiMap mappingExcel = new DualHashBidiMap();
		for (int i = 0; i < mapkeys.length; i++) {
			mappingExcel.put(mapkeys[i], titleMapping.get(mapkeys[i]));
		}
		HashMap<String, Object> params = new HashMap<>();
		params.put("ids", mIds);
		List<DsfwGoodsEntity> queryList = dsfwGoodsService.queryList(params);
		dsfwGoodsService.exportExcel(mappingExcel, queryList, response);
		//POIUtil.generateExcel(mappingExcel, queryList, response);
		return R.ok();
	}

	/**
	 * 导入excel
	 * @param mIds
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/inportExcel")
	public R inportExcel(@RequestParam("file") MultipartFile file) throws Exception{
		//List<DsfwGoodsEntity> list = POIUtil.readExcel(DsfwGoodsEntity.class,titleMapping,file);
		List<DsfwGoodsEntity> list = dsfwGoodsService.importExcel(DsfwGoodsEntity.class, titleMapping, file);
		System.err.println("个数"+list);
		dsfwGoodsService.saveBatch(list);
		return R.ok();
	}
}
