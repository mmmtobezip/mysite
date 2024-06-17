package com.poscodx.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.poscodx.mysite.dto.JsonResult;
import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;

@RestController("userApiController") // UserController가 컨테이너 내 두 개일 경우 나누는 방법
@RequestMapping("/user/api")
public class UserController {


  @Autowired
  private UserService userService;

  @ResponseBody
  @GetMapping("/checkemail")
  public JsonResult checkEmail(
      @RequestParam(value = "email", required = true, defaultValue = "") String email) {
    UserVo vo = userService.getUser(email);

    JsonResult jsonResult = new JsonResult();

    jsonResult.setResult("success");
    return jsonResult;

  }
}
