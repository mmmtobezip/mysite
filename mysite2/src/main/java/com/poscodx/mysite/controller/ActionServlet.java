package com.poscodx.mysite.controller;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ActionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected abstract Action getAction(String actionName);


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String actionName = Optional.ofNullable(req.getParameter("a")).orElse(""); // 파라미터 a가 null이라면
                                                                               // "", 아니라면
                                                                               // actionName을

    Action action = getAction(actionName);

    // action이 null이라면 Servlet에서 actionName에 해당하는 처리가 없다는거고 = 잘못된 요청을 의미 = nullpointexception 처리 필요
    if (action == null) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "...");
      return; // return안해주면 다음 라인이 실행됨.
    }
    action.execute(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }

  public static interface Action {
    // GuestbookServlet의 if-else문 구현
    // getParameter, getContextPath() 들이 대부분 ServletExcpetion , IOException을 던짐
    void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;

  }
}
