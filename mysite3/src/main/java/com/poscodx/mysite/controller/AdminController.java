package com.poscodx.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// @Auth(role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

  @RequestMapping("") // 아무것도 안쳐서 들어올 경우
  public String main() {
    return "admin/main";
  }

  // 방명록 관리 페이지
  @RequestMapping("/guestbook") // 아무것도 안쳐서 들어올 경우
  public String guestbook() {
    return "admin/guestbook";
  }

  // 게시판 관리 페이지
  @RequestMapping("/board")
  public String board() {
    return "admin/board";
  }

  // 사용자 관리 페이지
  @RequestMapping("/user")
  public String user() {
    return "admin/user";
  }
}
