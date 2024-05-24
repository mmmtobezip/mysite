package com.poscodx.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

public class UserServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("a");

    if ("joinform".equals(action)) {
      request.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp").forward(request, response);
    } else if ("join".equals(action)) {
      String name = request.getParameter("name");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String gender = request.getParameter("gender");

      UserVo vo = new UserVo();
      vo.setName(name);
      vo.setEmail(email);
      vo.setPassword(password);
      vo.setGender(gender);

      System.out.println(vo);
      new UserDao().insert(vo);

      // 브라우저에게 아래 joinsuccess 절대 경로로 리다이렉트
      // response.sendRedirect(request.getContextPath()+"/user?a=joinsuccess");
      response.sendRedirect(request.getContextPath() + "/user?a=joinsuccess");

    } else if ("joinsuccess".equals(action)) {
      request.getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp").forward(request,
          response);
    } else {
      response.sendRedirect(request.getContextPath());

    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
