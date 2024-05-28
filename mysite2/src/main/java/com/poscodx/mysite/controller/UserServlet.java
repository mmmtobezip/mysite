package com.poscodx.mysite.controller;

import java.util.Map;
import com.poscodx.mysite.controller.action.main.MainAction;
import com.poscodx.mysite.controller.action.user.JoinAction;
import com.poscodx.mysite.controller.action.user.JoinFormAction;
import com.poscodx.mysite.controller.action.user.JoinSuccess;
import com.poscodx.mysite.controller.action.user.LogoutAction;
import com.poscodx.mysite.controller.action.user.UpdateFormAction;
import com.poscodx.mysite.controller.action.user.LoginAction;
import com.poscodx.mysite.controller.action.user.LoginFormAction;

public class UserServlet extends ActionServlet {
  private static final long serialVersionUID = 1L;
  
  private Map<String, Action> mapAction = Map.of(
    "joinform", new JoinFormAction(),
    "join", new JoinAction(),
    "joinsuccess", new JoinSuccess(),
    "loginform", new LoginFormAction(),
    "login", new LoginAction(),
    "logout", new LogoutAction(),
    "updateform", new UpdateFormAction()
  );
      

  @Override
  protected Action getAction(String actionName) {
    //return mapAction.get(actionName);
    //else문 처리, key 없는 경우 default 처리 
    return mapAction.getOrDefault(actionName, new MainAction());
  }

  // protected void doGet(HttpServletRequest request, HttpServletResponse response)
  // throws ServletException, IOException {
  // request.setCharacterEncoding("UTF-8");
  // String action = request.getParameter("a");
  //
  // if ("joinform".equals(action)) {
  // request.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp").forward(request, response);
  // } else if ("join".equals(action)) {
  // String name = request.getParameter("name");
  // String email = request.getParameter("email");
  // String password = request.getParameter("password");
  // String gender = request.getParameter("gender");
  //
  // UserVo vo = new UserVo();
  // vo.setName(name);
  // vo.setEmail(email);
  // vo.setPassword(password);
  // vo.setGender(gender);
  //
  // System.out.println(vo);
  // new UserDao().insert(vo);
  //
  // // 브라우저에게 아래 joinsuccess 절대 경로로 리다이렉트
  // // response.sendRedirect(request.getContextPath()+"/user?a=joinsuccess");
  // response.sendRedirect(request.getContextPath() + "/user?a=joinsuccess");
  //
  // } else if ("joinsuccess".equals(action)) {
  // request.getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp").forward(request,
  // response);
  // } else {
  // response.sendRedirect(request.getContextPath());
  //
  // }
  // }
  //
  // protected void doPost(HttpServletRequest request, HttpServletResponse response)
  // throws ServletException, IOException {
  // // TODO Auto-generated method stub
  // doGet(request, response);
  // }

}
