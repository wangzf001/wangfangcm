package com.lcworld.test;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.utils.ConfigConstant;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.HttpUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class TestLogCommon {

	private static Logger log = LoggerFactory.getLogger(TestLogCommon.class);
	public static void main(String[] args) {
		//log.debug("debug.....11");
		String post = HttpUtils.sendPost(ConfigConstant.ENERGY_COST_URL, "day="+DateUtils.format(new Date(), DateUtils.DATE_PATTERN));
		try {
			Document document = DocumentHelper.parseText(post);
			Element rootElement = document.getRootElement();
			String text = rootElement.getText();
			JSONArray jsonArray = JSONObject.parseArray(text);
			JSONObject jsonObject= (JSONObject) jsonArray.get(0);
			System.out.println(jsonObject);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		System.out.println(DateUtils.format(new Date(), DateUtils.DATE_PATTERN));
	}
}
