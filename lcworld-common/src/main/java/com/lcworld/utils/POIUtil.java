package com.lcworld.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.lcworld.utils.util.ValidateUtil;

public class POIUtil {
	private static Logger log = LoggerFactory.getLogger(POIUtil.class);
	/**
	 * 生成Excel
	 * @param titleMapping:需要在Excel中导入的列与实体中域的映射，key为域全名，value为Excel列名
	 * @param list
	 * @throws Exception
	 */
	public static void generateExcel(DualHashBidiMap titleMapping,List list,HttpServletResponse response) throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("new sheet");
		if (ValidateUtil.isValid(list)) {
			HSSFRow row0 = sheet.createRow((short) 0);
			Class clazz = list.get(0).getClass();
			Set<Entry<String, String>> entrySet = titleMapping.entrySet();
			//写标题
			int i = 0;
			for (Entry<String, String> entry : entrySet) {
				row0.createCell(i++).setCellValue(entry.getValue());
			}
			for (int j = 0; j < list.size(); j++) {
				Object obj = list.get(j);
				HSSFRow row1 = sheet.createRow(j+1);
				int k = 0;
				for (Entry<String, String> entry : entrySet) {
					Field field = clazz.getDeclaredField(entry.getKey());
					field.setAccessible(true);
					Object object = field.get(obj);
					if (ValidateUtil.isValid(object)) {
						row1.createCell(k++).setCellValue(object.toString());
					}else{
						row1.createCell(k++).setCellValue("");
					}
				}
			}
		}
		String filename = UUID.randomUUID().toString().replace("-", "")+".xls";
		// 清空response  
        response.reset();  
        response.setContentType("application/octet-stream");  
        response.setHeader("name", filename);  
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");  
        response.setHeader("Pragma", "public");  
        response.setDateHeader("Expires", 0);  
        response.setHeader("Content-disposition","attachment; filename=\""+URLEncoder.encode(filename, "UTF-8")+ "\"");  
        wb.write(response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
        
	}
	/**
	 * 导入Excel
	 * @param clazz
	 * @param file
	 * @param titleMapping:需要在Excel中导入的列与实体中域的映射，key为Excel列名，value为域全名
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> readExcel(Class<T> clazz,DualHashBidiMap titleMapping,MultipartFile file) throws Exception {
		HashMap<Integer,String> map = new HashMap<>();
		HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row2 = sheet.getRow(0);
		short num = row2.getLastCellNum();
		for (int i = row2.getFirstCellNum(); i < num; i++) {
			HSSFCell cell = row2.getCell(i);
			String value = cell.getStringCellValue();
			String fieldname = (String)titleMapping.getKey(value);
			map.put(i, fieldname);
		}
		List<T> list = new ArrayList<>();
		for(int i = sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
		    HSSFRow row = sheet.getRow(i);
		    T entity = clazz.newInstance();
		    for (int j = row.getFirstCellNum(); j < num; j++) {
				String value = row.getCell(j).toString();
				String fieldname = map.get(j);
				Field field = clazz.getDeclaredField(fieldname);
				field.setAccessible(true);
				Object realValue = getRealValue(field.getType(),value);
				if (realValue!=null) {
					field.set(entity, realValue);
				}
			}
		    list.add(entity);
		}
		return list;
	}
	
	private static <T> Object getRealValue(Class<T> clazz,String value){
		if (value==null||"".equals(value)) {
			return null;
		}
		String type = clazz.getName();
		try {
			if (type.equals("java.lang.String"))  
	        {   
	            return value;
	        }  
	        if (type.equals("java.lang.Integer"))  
	        {
	        	if (value.contains(".")) {
					value = value.substring(0,value.indexOf("."));
				}
	        	return Integer.parseInt(value);
	        }  
	        if (type.equals("java.lang.Short"))  
	        {
	        	if (value.contains(".")) {
					value = value.substring(0,value.indexOf("."));
				}
	        	return Short.parseShort(value);
	        }  
	        if (type.equals("java.lang.Double"))  
	        {
	        	return Double.parseDouble(value);
	        }  
	        if (type.equals("java.lang.Boolean"))  
	        {
	        	return Boolean.parseBoolean(value); 
	        }  
	        if (type.equals("java.math.BigDecimal"))  
	        {
	        	return new BigDecimal(value); 
	        }  
	        if (type.equals("java.util.Date"))  
	        {
	        	if (value.contains(".")) {
					value = value.substring(0,value.indexOf("."));
				}
	        	Date parse = DateUtil.parse(value, "yyyy-MM-dd hh:mm");
	        	return parse;
	        }
		} catch (Exception e) {
			log.error("class cast err");
		}
        
        return null;
	}
}
