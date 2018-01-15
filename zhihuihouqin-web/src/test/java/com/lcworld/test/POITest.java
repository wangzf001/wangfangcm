package com.lcworld.test;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lcworld.utils.util.ValidateUtil;
import com.mchange.v2.codegen.bean.GeneratorExtension;
import com.mysql.fabric.xmlrpc.base.Array;

public class POITest {
	/**
	 * 生成Excel
	 * @param titleMapping:需要在Excel中导入的列与实体中域的映射，key为域全名，value为Excel列名
	 * @param list
	 * @throws Exception
	 */
	public static void generateExcel(Map<String,String> titleMapping,List list) throws Exception{
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
					row1.createCell(k++).setCellValue(field.get(obj).toString());
				}
			}
		}
		FileOutputStream fileOut = new FileOutputStream("workbook.xls");
		wb.write(fileOut);
		fileOut.close();
	}
	public static void main(String[] args) throws Exception {
		POITestEntity e1 = new POITestEntity();
		POITestEntity e2 = new POITestEntity();
		POITestEntity e3 = new POITestEntity();
		e1.setId(1);
		e2.setId(2);
		e3.setId(3);
		e1.setAge(1L);
		e2.setAge(2L);
		e3.setAge(3L);
		e1.setName("啊哈哈");
		e2.setName("啊啦啦");
		e3.setName("啊丽丽");
		List<POITestEntity> list = new ArrayList<>();
		list.add(e1);
		list.add(e2);
		list.add(e3);
		Map<String,String> titleMapping = new HashMap<>();
		titleMapping.put("id", "id");
		titleMapping.put("name", "名称");
		titleMapping.put("age", "年龄");
		generateExcel(titleMapping, list);
		generateExcel(titleMapping, list);
		generateExcel(titleMapping, list);
	}
	
}
