package com.poscodx.mysite.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.poscodx.mysite.service.BoardService;
import com.poscodx.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private BoardService boardService;

  // @RequestMapping("")
  // public String index(Model model) {
  // Map map = boardService.getContentsList();
  // // 여러개 데이터를 서비스에 보낼 때 map 쓰기 (list, paging처리는)
  // model.addAllAttributes(map); // 방법1
  //
  // // model.addAttribute("map", map); 방법2
  // return;
  // }
  @RequestMapping("")
  public String list(Model model) {
    List<BoardVo> list = boardService.getContentsList();
    model.addAttribute("list", list);
    return "board/list";
  }

  // WirteFormAction to write.jsp
  @RequestMapping(value = "/write", method = RequestMethod.GET)
  public String write() {
    return "board/write";
  }

  @RequestMapping(value = "/write", method = RequestMethod.POST)
  public String write(BoardVo vo, HttpSession session) {
    System.out.println(vo);
    boardService.write(vo);
    return "redirect:/board";
  }



  // @RequestMapping("/view/{no}")
  // public String view(@PathVariable("no") Long no) {
  //
  // }
  //
  // @RequestMapping("/delete/{no}")
  // public String delete(HttpSession session, @PathVariable("no") Long no) {
  // UserVo authUser = (UserVo) session.getAttribute("authUser");
  //
  // if (authUser == null) {
  // return "redirect:/";
  // }
  // }
}
