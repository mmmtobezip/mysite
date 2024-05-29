package com.poscodx.mysite.controller;

import javax.servlet.ServletException;
import com.poscodx.mysite.controller.action.main.MainAction;

public class MainServlet extends ActionServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected Action getAction(String actionName) {
    return new MainAction(); // 이때 실행되는 내용이 아래 주석친 doGet() 실행과 동일
  }

  @Override
  public void init() throws ServletException {
    String config = getServletConfig().getInitParameter("config");
    System.out.println("MainController.init() called: " + config);

    super.init(); // 호출해야 다음 호출인 service()호출됨.
  }

}
