package com.poscodx.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.poscodx.mysite.service.UserService;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/join", method = RequestMethod.GET)
  // joinform.jsp
  public String join() {
    return "user/join"; // jsp 안써도 되는 이유: spring-servlet.xml에서 ViewResolver가 처리해줌.


  }
}
