package com.poscodx.mysite.controller;

import javax.servlet.ServletContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.service.FileUploadService;
import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;


@Auth(role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private ServletContext servletContext;
  @Autowired
  private ApplicationContext applicationContext;
  @Autowired
  private SiteService siteService;
  @Autowired
  private FileUploadService fileUploadService;


  @RequestMapping("") // 아무것도 안쳐서 들어올 경우
  public String main(Model model) {
    SiteVo vo = siteService.getSite();
    model.addAttribute("siteVo", vo);
    return "admin/main";
  }

  @RequestMapping("/main/update")
  public String update(SiteVo siteVo, MultipartFile file) {
    String profile = fileUploadService.restore(file);
    if (profile != null) {
      siteVo.setProfile(profile);
    }

    siteService.updateSite(siteVo);
    servletContext.setAttribute("siteVo", siteVo);

    SiteVo site = applicationContext.getBean(SiteVo.class);
    // site.setTitile(siteVo.getTitile());
    // site.setWelcome(siteVo.getWelcome());
    // site.setProfile(siteVo.getProfile());
    // site.setDescription(siteVo.getDescription());

    BeanUtils.copyProperties(siteVo, site);

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
