package com.poscodx.mysite.event;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;

public class ApplicationContextEventListener {

  @Autowired
  private ApplicationContext applicationContext;

  @EventListener({ContextRefreshedEvent.class}) // 해당하는 이벤트가 일어났을 때 Application Container단에서 실행시킴
  public void handleContextRefreshEvent() {
    System.out.println("--- Context Refreshed Evenet Received --- ");

    SiteService siteService = applicationContext.getBean(SiteService.class);
    SiteVo siteVo = siteService.getSite();

    MutablePropertyValues propertyValues = new MutablePropertyValues();
    propertyValues.add("title", siteVo.getTitle());
    propertyValues.add("profile", siteVo.getProfile());
    propertyValues.add("welcome", siteVo.getWelcome());
    propertyValues.add("description", siteVo.getDescription());

    // 컨테이너에내에 registry가 xml에 정의된 내용을 읽고 beanDefinition에 저장한 후, 자신의 registry에 저장함.
    GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
    beanDefinition.setBeanClass(SiteVo.class); // xml내 class지정과 동일
    beanDefinition.setPropertyValues(propertyValues); // xml내 property로 정의된 것과 비슷

    BeanDefinitionRegistry registry =
        (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
    registry.registerBeanDefinition("site", beanDefinition);

  }
}
