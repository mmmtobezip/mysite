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

  // ViewResolver
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    viewResolver.setExposeContextBeansAsAttributes(true);
    viewResolver.setExposedContextBeanNames("site"); // 여러개일 경우 가변 파라미터 추가 가능

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
