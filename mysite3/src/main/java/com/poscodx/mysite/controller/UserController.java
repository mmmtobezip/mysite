


package com.poscodx.mysite.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/join", method = RequestMethod.GET)
  // joinform.jsp
  public String join() {
    return "user/join";
  }

  @RequestMapping(value = "/join", method = RequestMethod.POST)
  public String join(UserVo vo) {
    System.out.println(vo);
    userService.join(vo);
    return "redirect:/user/joinsuccess"; // 아래 joinsuccess 요청으로 들어감
  }

  @RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
  // joinsuccess.jsp
  public String joinsuccess() {
    return "user/joinsuccess";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  // loginform.jsp
  public String login() {
    return "user/login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(HttpSession session, UserVo vo, Model model) {
    UserVo authUser = userService.getUser(vo.getEmail(), vo.getPassword());
    if (authUser == null) {
      model.addAttribute("email", vo.getEmail());
      model.addAttribute("result", "fail");

      return "user/login";
    }
    // login 처리
    session.setAttribute("authUser", authUser);
    return "redirect:/"; // main으로 보내기
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(HttpSession session) {
    session.removeAttribute("authUser");
    session.invalidate();
    return "redirect:/";
  }

  @RequestMapping(value = "/update", method = RequestMethod.GET)
  public String update(HttpSession session, Model model) {
    UserVo authUser = (UserVo) session.getAttribute("authUser");

    if (authUser == null) {
      return "redirect:/";
    }

    // =====횡단 관심 분리=====

    UserVo vo = userService.getUser(authUser.getNo());
    // update.jsp에서 받고 있는 값으로 변수를 지정해주기
    model.addAttribute("userVo", vo);
    return "user/update";
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(HttpSession session, UserVo vo) {
    // Access Control
    UserVo authUser = (UserVo) session.getAttribute("authUser");

    if (authUser == null) {
      return "redirect:/";
    }

    // =====횡단 관심 분리=====

    vo.setNo(authUser.getNo()); // session에 있는 유저의 no를 가져오기
    userService.update(vo);

    authUser.setName(vo.getName());
    return "redirect:/user/update";
  }
}
