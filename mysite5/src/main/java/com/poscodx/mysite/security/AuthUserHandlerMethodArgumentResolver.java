package com.poscodx.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.poscodx.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);

    // @AuthUser가 붙어있지 않다면,
    if (authUser == null) {
      return false;
    }

    // @AuthUser가 붙어 있어도, 타입 체크 필수
    // 파라미터 타입이 UserVo가 아니면,
    if (!parameter.getParameterType().equals(UserVo.class)) {
      return false;
    }
    return true;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

    if (!supportsParameter(parameter)) {
      return WebArgumentResolver.UNRESOLVED;
    }

    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest(); //tomcat에 종속된 resolver 
    HttpSession session = request.getSession();

    return session.getAttribute("authUser");
  }

}
