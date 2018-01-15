package com.lcworld.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.lcworld.annotation.LoginUser;
import com.lcworld.entity.TUserEntity;
import com.lcworld.interceptor.SignCheckInterceptor;
import com.lcworld.service.TUserService;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 22:02
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private  TUserService userService;

    public void setUserService(TUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(TUserEntity.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getAttribute(SignCheckInterceptor.LOGIN_USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if(object == null){
            return null;
        }
        //获取用户信息
        TUserEntity user = userService.queryObject((int)object);
        return user;
    }
}