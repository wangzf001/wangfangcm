package com.lcworld.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lcworld.annotation.IgnoreSign;
import com.lcworld.utils.RRException;

/**
 * sign验证
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017年4月19日 上午11:30:58
 */
@Component
public class SignCheckInterceptor extends HandlerInterceptorAdapter {
	
	private Logger log = LoggerFactory.getLogger(LogShowParamInterceptor.class);

	public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

	public static final String SECRETKEY = "zxcadsadwa@2321$";
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
		log.info("\n=============================================================================================================================================================================\n\n\n\n\n\n\n\n");
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception {

		{
			String requestUri = request.getRequestURI();
			String contextPath = request.getContextPath();
			String url = requestUri.substring(contextPath.length());

			log.info("\n=============================================================================================================================================================================");
			log.info("Sign Check PreHandle >>>>");

			log.info("requestUri:" + requestUri);
			log.info("contextPath:" + contextPath);
			log.info("url:" + url);

			// 替换.replace("\r", "").replace("\n", "").replace(" ", "")
			String device = request.getParameter("device");
			String timestamp = request.getParameter("timestamp");
			String biz = request.getParameter("biz");
			String sign = request.getParameter("sign");
			String token = request.getParameter("token");

			log.info("device的值是:" + device);
			log.info("timestamp的值是:" + timestamp);
			log.info("biz的值是:" + biz);
			log.info("sign的值是:" + sign);
			log.info("token的值是:" + token);
			log.info("\n");
		}
		
		IgnoreSign annotation;
		if (handler instanceof HandlerMethod) {
			annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreSign.class);
		} else {
			return true;
		}
		String biz = request.getParameter("biz");
//		XssHttpServletRequestWrapper request = (XssHttpServletRequestWrapper) request1;

		// 如果有@IgnoreSign注解，则不验证sign
		if (annotation != null) {
			return true;
		}

		// 从header中获取sign
		String sign = request.getHeader("sign");
		// 如果header中不存在sign，则从参数中获取sign
		if (StringUtils.isBlank(sign)) {
			sign = request.getParameter("sign");
		}

		// sign为空
		if (StringUtils.isBlank(sign)) {
			throw new RRException("sign不能为空", 1);
		}
		String timestamp = request.getParameter("timestamp");

		String signString = URLEncoder.encode(biz, "UTF-8") + timestamp + SECRETKEY;

		String serverSign = DigestUtils.md5Hex(signString);
		
		if (!sign.equals(serverSign)) {
			throw new RRException("sign错误!", 1);
		}
		return true;

	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String signString = URLEncoder.encode("{}", "UTF-8") + "1503642755" + SECRETKEY;
		String serverSign = DigestUtils.md5Hex(signString);
		System.out.println(serverSign);
	}
}
