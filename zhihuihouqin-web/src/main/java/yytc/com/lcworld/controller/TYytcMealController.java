package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lcworld.entity.TYytcMealEntity;
import com.lcworld.service.TYytcMealService;
import com.lcworld.util.POIUtil;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.freemarker.FreeMarkerUtils;
import com.lcworld.utils.freemarker.FreemarkerWriterFileNames;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
@RestController
@RequestMapping("tyytcmeal")
public class TYytcMealController {
	private Logger log = LoggerFactory.getLogger(TYytcMealController.class);
	@Autowired
	private TYytcMealService tYytcMealService;
	//poi导入导出映射关系
	private DualHashBidiMap titleMapping = new DualHashBidiMap();
	{
		titleMapping.put("mId", "id");
		titleMapping.put("mTitle", "标题");
		titleMapping.put("mCreateTime", "创建时间");
		titleMapping.put("mSource", "来源");
		titleMapping.put("mType", "菜单类型(1今日菜谱2营养套餐)");
		titleMapping.put("mTimeType", "时间类型(1早2中3晚)");
		titleMapping.put("mImg", "菜单配图");
		titleMapping.put("mContent", "菜单简介");
		titleMapping.put("mScanNum", "浏览人数");
		titleMapping.put("mPraiseNum", "点赞人数");
		titleMapping.put("mFavorNum", "收藏人数");
		titleMapping.put("mCalories", "摄入热量(卡路里)");
	}
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		if ("".equals((String)params.get("type"))) {
			params.remove("type");
		}
		if ("".equals((String)params.get("timeType"))) {
			params.remove("timeType");
		}
		Query query = new Query(params);
		List<TYytcMealEntity> tYytcMealList = tYytcMealService.queryList(query);
		int total = tYytcMealService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(tYytcMealList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{mId}")
	public R info(@PathVariable("mId") Integer mId) {
		TYytcMealEntity tYytcMeal = tYytcMealService.queryObject(mId);

		return R.ok().put("tYytcMeal", tYytcMeal);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TYytcMealEntity tYytcMeal,HttpServletRequest request) {
		log.debug("model=" + tYytcMeal);
		tYytcMealService.save(tYytcMeal);
		String generateHTML = FreeMarkerUtils.generateHTML(FreemarkerWriterFileNames.TEMPLATE_YYTC, FreemarkerWriterFileNames.FREEMARKER_YYTC_FOLDER+tYytcMeal.getMId()+".html", tYytcMeal,request);
		tYytcMeal.setmUrl(generateHTML);
		tYytcMealService.update(tYytcMeal);
		log.debug(generateHTML);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TYytcMealEntity tYytcMeal,HttpServletRequest request) {
		String generateHTML = FreeMarkerUtils.generateHTML(FreemarkerWriterFileNames.TEMPLATE_YYTC, FreemarkerWriterFileNames.FREEMARKER_YYTC_FOLDER+tYytcMeal.getMId()+".html", tYytcMeal,request);
		tYytcMeal.setmUrl(generateHTML);
		tYytcMealService.update(tYytcMeal);
		return R.ok();
	}
	/**
	 * 修改推荐
	 */
	@RequestMapping("/recommend")
	public R recommend(@RequestBody Integer[] mIds) {
		tYytcMealService.recommend(mIds);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] mIds) {
		tYytcMealService.deleteBatch(mIds);

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
		List<TYytcMealEntity> queryList = tYytcMealService.queryList(params);
		POIUtil.generateExcel(titleMapping, queryList, response);
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
//		titleMapping.put("id","mId");
//		titleMapping.put("标题","mTitle");
//		titleMapping.put("创建时间","mCreateTime");
//		titleMapping.put("来源","mSource");
//		titleMapping.put("菜单类型(1今日菜谱2营养套餐)","mType");
//		titleMapping.put("时间类型(1早2中3晚)","mTimeType");
//		titleMapping.put("菜单配图","mImg");
//		titleMapping.put("菜单简介","mContent");
//		titleMapping.put("浏览人数","mScanNum");
//		titleMapping.put("点赞人数","mPraiseNum");
//		titleMapping.put("收藏人数","mFavorNum");
//		titleMapping.put("摄入热量(卡路里)","mCalories");
		List<TYytcMealEntity> list = POIUtil.readExcel(TYytcMealEntity.class,titleMapping,file);
		System.err.println("个数"+list);
		tYytcMealService.saveBatch(list);
		return R.ok();
	}

	
	
}
