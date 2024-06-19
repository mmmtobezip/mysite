


package com.poscodx.mysite.controller;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.security.AuthUser;
import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/join", method = RequestMethod.GET)
  // joinform.jsp
  public String join(@ModelAttribute UserVo vo) {
    return "user/join";
  }

  @RequestMapping(value = "/join", method = RequestMethod.POST)
  public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
    if (result.hasErrors()) {
      Map<String, Object> map = result.getModel();

      model.addAllAttributes(map);

      // Error 출력
      // List<ObjectError> list = result.getAllErrors();
      // for (ObjectError error : list) {
      // System.out.println(error);
      // }

      return "user/join";
    }
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

  @Auth
  @RequestMapping(value = "/update", method = RequestMethod.GET)
  public String update(@AuthUser UserVo authUser, Model model) {

    UserVo vo = userService.getUser(authUser.getNo());
    // update.jsp에서 받고 있는 값으로 변수를 지정해주기
    model.addAttribute("userVo", vo);
    return "user/update";
  }

  @Auth
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(@AuthUser UserVo authUser, UserVo vo) {
    vo.setNo(authUser.getNo()); // session에 있는 유저의 no를 가져오기
    userService.update(vo);

    authUser.setName(vo.getName());
    return "redirect:/user/update";
  }

  @RequestMapping("/auth")
  public void auth() {}

  @RequestMapping("/logout")
  public void logout() {}
}
