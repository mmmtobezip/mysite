package com.poscodx.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.vo.SiteVo;

@Auth(role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

  @RequestMapping("") // 아무것도 안쳐서 들어올 경우
  public String main() {
    return "admin/main";
  }

  @RequestMapping("/main/update")
  public String update(SiteVo siteVo) {
    return "redirect:/admin";
  }

  // 방명록 관리 페이지
  @RequestMapping("/guestbook")
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
