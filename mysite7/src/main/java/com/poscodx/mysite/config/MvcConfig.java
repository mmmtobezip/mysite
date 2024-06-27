package com.poscodx.mysite.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import com.poscodx.mysite.event.ApplicationContextEventListener;
import com.poscodx.mysite.interceptor.SiteInterceptor;

@SpringBootConfiguration
public class MvcConfig implements WebMvcConfigurer {

  // Locale Resolver
  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    localeResolver.setCookieName("lang");
    localeResolver.setCookieHttpOnly(false);

    return localeResolver;
  }

  // JSP View Resolver
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".jsp");
    viewResolver.setExposeContextBeansAsAttributes(true);
    viewResolver.setExposedContextBeanNames("site"); // 여러개일 경우 가변 파라미터 추가 가능
    viewResolver.setViewNames("views/*");
    viewResolver.setOrder(0);

    return viewResolver;
  }

  // Thymeleaf View Resolver
  @Bean
  public ViewResolver thymeleafViewResolver(ISpringTemplateEngine templateEngine) {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

    viewResolver.setTemplateEngine(templateEngine);
    viewResolver.setCharacterEncoding("UTF-8");
    viewResolver.setOrder(1);

    return viewResolver;
  }

  // Site Interceptor
  @Bean
  public HandlerInterceptor siteInterceptor() {
    return new SiteInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // registry를 통해 interceptor 등록 작업
    registry.addInterceptor(siteInterceptor()) // siteInterceptor() 생성자 바로 호출
        .addPathPatterns("/**").excludePathPatterns("/assets/**");

  }

  // ApplicationContext Event Listener
  @Bean
  public ApplicationContextEventListener applicationContextEventListener() {
    return new ApplicationContextEventListener();
  }
}
