package com.poscodx.mysite.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.poscodx.mysite.dao.GuestBookDao;
import com.poscodx.mysite.vo.GuestBookVo;

public class GuestBookServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("a");

    if ("add".equals(action)) {
      String name = request.getParameter("name");
      String password = request.getParameter("password");
      String contents = request.getParameter("contents");

      GuestBookVo vo = new GuestBookVo();
      vo.setName(name);
      vo.setPassword(password);
      vo.setContents(contents);
      System.out.println(vo);

      new GuestBookDao().insert(vo);

      response.sendRedirect(request.getContextPath() + "/guestbook");
    } else if ("deleteform".equals(action)) {
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
      rd.forward(request, response);

    } else if ("delete".equals(action)) {
      request.setCharacterEncoding("utf-8");
      Long no = Long.parseLong(request.getParameter("no"));
      String password = request.getParameter("password");

      new GuestBookDao().deleteByNoAndPassword(no, password);

      // response.sendRedirect("/guestbook1");
      response.sendRedirect(request.getContextPath() + "/guestbook");
    } else {
      List<GuestBookVo> list = new GuestBookDao().findAll();
      request.setAttribute("list", list);
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
      rd.forward(request, response);
    }

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    doGet(request, response);
  }

}
