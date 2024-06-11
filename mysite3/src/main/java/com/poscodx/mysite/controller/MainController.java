package com.poscodx.mysite.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.poscodx.mysite.service.SiteService;

@Controller
public class MainController {
  private final SiteService siteService;

  public MainController(SiteService siteService) {
    this.siteService = siteService;
  }

  @RequestMapping({"/", "main"})
  public String index(HttpServletRequest request) {
    ServletContext sc = request.getServletContext();
    return "main/index";
  }
}
