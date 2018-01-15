package com.lcworld.interceptor;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lcworld.utils.util.ValidateUtil;
@Component
public class LogShowParamInterceptor extends HandlerInterceptorAdapter{
	private Logger log = LoggerFactory.getLogger(LogShowParamInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		HandlerMethod method = null;
//		if (handler instanceof HandlerMethod) {
//			method = (HandlerMethod)handler;
//		}
//		Object bean = method.getBean();
		log.debug("requestUrl:"+request.getRequestURI());
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (ValidateUtil.isValid(parameterMap)) {
			Set<Entry<String,String[]>> entrySet = parameterMap.entrySet();
			for (Entry<String, String[]> entry : entrySet) {
				String[] value = entry.getValue();
				if (ValidateUtil.isValid(value)) {
					if (value.length==1) {
						log.debug("params:"+entry.getKey()+"="+value[0]);
					}else{
						log.debug("params:"+entry.getKey()+"="+Arrays.toString(value));
					}
				}
			}
		}
		
		return true;
	}
	public static void main(String[] args) {
		Integer[] s = {1,2,3};
		System.out.println(Arrays.toString(s));
	}
}
