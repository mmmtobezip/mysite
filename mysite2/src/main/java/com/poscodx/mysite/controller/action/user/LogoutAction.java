package com.poscodx.mysite.controller.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.poscodx.mysite.controller.ActionServlet.Action;

public class LogoutAction implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 로그인 안하고 /user?a=logout으로 들어오면, session = null이 되어 문제가 생길 수도 있지만,
    // jsp는 session을 모두 가지고 있으므로 크게 문제가 되진 않을 듯.
    HttpSession session = request.getSession();
    if (session != null) { // session이 null이 아니면 -> session이 없으면 ? -> 로그인한 유저가 없는 것?
      // session...
      /* 로그아웃 처리 */
      session.removeAttribute("authUser");
      session.invalidate(); // 세션 id 변경되고, 기존에 있는 HttpSession 삭제
    }

    // redirect to main
    response.sendRedirect(request.getContextPath());
  }
}
