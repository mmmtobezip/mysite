package com.poscodx.mysite.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ContextLoadListener
 *
 */
public class ContextLoadListener implements ServletContextListener {


  // when: application load
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext sc = sce.getServletContext();
    String contextConfigLocation = sc.getInitParameter("contextConfigLocation");

    System.out.println("Application[MySite2] starts..." + contextConfigLocation);
  }


  // when: application down
  public void contextDestroyed(ServletContextEvent sce) {

  }


}
