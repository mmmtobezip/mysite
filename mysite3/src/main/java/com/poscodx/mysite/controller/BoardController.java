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
import com.poscodx.mysite.vo.UserVo;

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
  // @RequestMapping("")
  // public String list(@RequestParam(value = "p", required = true, defaultValue = "1") Integer
  // page,
  // Model model) {
  // //Map<String, Object> map = boardService.getContentsList();
  //
  //
  // List<BoardVo> list = boardService.getContentsList(page);
  // model.addAttribute("list", list);
  // return "board/list";
  // }

  @RequestMapping("")
  public String list(Model model) {
    // Map<String, Object> map = boardService.getContentsList();


    List<BoardVo> list = boardService.getContentsList();
    model.addAttribute("list", list);
    return "board/list";
  }

  // WirteFormAction to write.jsp
  @RequestMapping(value = "/write", method = RequestMethod.GET)
  public String write(HttpSession session) {

    UserVo authUser = (UserVo) session.getAttribute("authUser");
    if (authUser == null) {
      return "redirect:/board";
    }
    return "board/write";
    // 만약 파라미터로 board?a= + page 이런식으로 보내려면 @RequestParam 가져오기
  }

  @RequestMapping(value = "/write", method = RequestMethod.POST)
  public String write(BoardVo vo, HttpSession session) {

    UserVo authUser = (UserVo) session.getAttribute("authUser");
    if (authUser == null) {
      return "user/join";
    }

    vo.setUserNo(authUser.getNo());
    boardService.write(vo);
    return "redirect:/board"; // 글 쓰고선 메인으로
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
