package com.poscodx.mysite.controller.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

public class UpdateAction implements Action {
  // 수정이 잘되면 updateform.jsp로 리다이렉트
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();

    if (session == null) {
      response.sendRedirect(request.getContextPath());
      return;
    }

    UserVo authUser = (UserVo) session.getAttribute("authUser");
    if (authUser == null) {
      response.sendRedirect(request.getContextPath());
      return;
    }

    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String gender = request.getParameter("gender");

    UserVo userVo = new UserVo();
    userVo.setNo(authUser.getNo());
    userVo.setName(name);
    userVo.setPassword(password);
    userVo.setGender(gender);

    new UserDao().update(userVo);
    authUser.setName(name);
    response.sendRedirect(request.getContextPath() + "/user?a=updateform&result=success");
  }
}
