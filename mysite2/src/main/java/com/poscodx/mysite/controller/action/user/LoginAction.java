package com.poscodx.mysite.controller.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

public class LoginAction implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    UserVo authUser = new UserDao().findByNoAndPassword(email, password);

    // login 실패
    if (authUser == null) {
      // email, password가 틀린 경우 -> 무엇이 틀렸는지 알려주진 않음 왜냐? 보안상 문제
      // forwarding or redirect로 다시 loginform으로 보내주기
      request.setAttribute("email", email);
      request.setAttribute("result", "fail");
      // response.sendRedirect(request.getContextPath());
      request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp").forward(request, response);
      return;
    }

    // login 처리 - session manager 사용되는 곳
    HttpSession session = request.getSession(true);
    session.setAttribute("authUser", authUser);

    // redirect to main
    response.sendRedirect(request.getContextPath());
  }

}
