package com.poscodx.mysite.controller;

public class BoardServlet extends ActionServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected Action getAction(String actionName) {
    return null;
  }
  // 현재코드에선 액션이 없으므로 무조건 ActionServlet에 action = null이 들어가고 400 Error 할당
  // localhost:8080/mysite2/board

  // list, wrtie, modify 등 액션 로직 추가

}
