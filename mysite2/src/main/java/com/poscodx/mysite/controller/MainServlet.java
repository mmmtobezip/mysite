package com.poscodx.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      request
            .getRequestDispatcher("/WEB-INF/views/main/index.jsp")  //view(Servlet -> jsp)로 코드의 흐름이 옮겨갈 수 있도록 객체 생성 
            .forward(request, response);  
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    doGet(request, response);
  }

}
