package com.lcworld.utils.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TYytcIngredientEntity;
import com.lcworld.entity.TYytcMealEntity;
import com.lcworld.utils.util.ValidateUtil;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtils {
	// 生成商品详情静态文件
	private static Logger log = LoggerFactory.getLogger(FreeMarkerUtils.class);
	private static Configuration configuration;
	private static String realPath;
	private static URL resource;
	private static final String generateDir = "/freemaker/html/";
	private static final String saveDir = "/freemaker/template";
	
	private FreeMarkerUtils(){
		
	}
	static {
		configuration = new Configuration(Configuration.VERSION_2_3_23);
	}
	// 获取或创建一个模版。
	public static Template getTemplate(String file) {
		try {
			return configuration.getTemplate(file);
		} catch (Exception e) {
			log.error("获取freemarker模板异常", e);
		}
		return null;
	}
	private static void createFolder() {
		// 商品详情
		new File(realPath + FreemarkerWriterFileNames.FREEMARKER_YYTC_FOLDER).mkdirs();
	}
	// 设置输出文件
	public static Writer getOutputStreamWriter(String outFileName) {
		if (!ValidateUtil.isValid(realPath)) {
			initRealPath();
		}
		try {
			File file = new File(realPath + outFileName);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			return new OutputStreamWriter(new FileOutputStream(realPath + outFileName), "UTF-8");
		} catch (Exception e) {
			log.error("渲染商品数据生成文件异常。", e);
		}
		return null;
	}
	private static void initRealPath() {
		realPath = new File(resource.getFile())+generateDir;
		log.debug("freemakerRealPath="+realPath);
		// 创建相关业务文件夹
		createFolder();
	}
	/**
	 * 
	 * @param templateName
	 * @param outputFileName
	 * @param obj
	 * @return 生成的静态文件的地址
	 */
	public static String generateHTML(String templateName,String outputFileName,Object obj,HttpServletRequest request){
		try {
			resource = request.getServletContext().getResource("");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		log.debug("freemakerResource="+resource.getPath());
		try {
			configuration.setDirectoryForTemplateLoading(new File(resource.getFile()+saveDir));
		} catch (IOException e) {
			log.error("实例化freemarker异常", e);
		}
		configuration.setObjectWrapper(new BeansWrapper(Configuration.VERSION_2_3_23));
		configuration.setDefaultEncoding("UTF-8"); // 这个一定要设置，不然在生成的页面中 会乱码
		Template template = FreeMarkerUtils.getTemplate(templateName);
		Object json = JSONObject.toJSON(obj);
		String url = null;
		try {
			template.process(json, getOutputStreamWriter(outputFileName));
			url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+generateDir + outputFileName;
			log.debug("freemakerUrl:"+url);
		} catch (TemplateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public static void main(String[] args) {
		TYytcMealEntity meal = new TYytcMealEntity();
		meal.setMTitle("咕咾肉");
		List<TYytcIngredientEntity> list = meal.getIngredientList();
		TYytcIngredientEntity i = new TYytcIngredientEntity();
		TYytcIngredientEntity i1 = new TYytcIngredientEntity();
		TYytcIngredientEntity i2 = new TYytcIngredientEntity();
		TYytcIngredientEntity i3 = new TYytcIngredientEntity();
		i.setINameNum("肉100g");
		i1.setINameNum("蒜100g");
		i2.setINameNum("葱100g");
		i3.setINameNum("葱姜100g");
		list.add(i);
		list.add(i2);
		list.add(i3);
		list.add(i1);
//		String generateHTML = generateHTML("test.ftl", "test.html", meal);
//		System.out.println(generateHTML);
	}
}
