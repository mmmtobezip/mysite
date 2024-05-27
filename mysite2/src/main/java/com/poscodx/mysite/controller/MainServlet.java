package com.poscodx.mysite.controller;

import com.poscodx.mysite.controller.action.main.MainAction;

public class MainServlet extends ActionServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected Action getAction(String actionName) {
    return new MainAction(); // 이때 실행되는 내용이 아래 주석친 doGet() 실행과 동일
  }

  // protected void doGet(HttpServletRequest request, HttpServletResponse response)
  // throws ServletException, IOException {
  // request.getRequestDispatcher("/WEB-INF/views/main/index.jsp")
  // .forward(request, response);
  // }
  //
  // protected void doPost(HttpServletRequest request, HttpServletResponse response)
  // throws ServletException, IOException {
  //
  // doGet(request, response);
  // }

}
