package com.poscodx.mysite.intializer;

import javax.servlet.Filter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.poscodx.mysite.config.AppConfig;
import com.poscodx.mysite.config.WebConfig;

public class MysiteWebApplicationInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer {


  @Override
  // Root Application Context 초기화하는 설정 클래스
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {AppConfig.class};
  }

  @Override
  // Web Application Context 초기화하는 설정 클래스
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {WebConfig.class};
  }

  @Override
  // Encoding Filter 설정하는 클래스
  protected Filter[] getServletFilters() {
    return new Filter[] {new CharacterEncodingFilter("UTF-8"),
        new DelegatingFilterProxy("springSecurityFilterChain")};
  }

  @Override
  // DispatcherServlet 만들 때 매핑시킬 URL
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }

  @Override
  protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
    DispatcherServlet servlet =
        (DispatcherServlet) super.createDispatcherServlet(servletAppContext);

    servlet.setThrowExceptionIfNoHandlerFound(true);
    return servlet;
  }
}
