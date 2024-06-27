package com.poscodx.mysite.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.poscodx.mysite.repository.BoardRepository;
import com.poscodx.mysite.vo.BoardVo;

@Service
public class BoardService {

  @Autowired
  private BoardRepository boardRepository;

  // 글쓰는거
  // public void addContents(BoardVo vo) {
  // //게시판 글 등록 = transaction
  // if(vo.getGroupNo() != null) {
  // boardRepository.updateOrderNo(..);
  // }
  //
  // //답글 달기 = transaction
  // boardRepository.insert(vo);
  //
  // }
  //
  // // 글 보기 - 로그인안해도 볼 수 있음
  // public BoardVo getcontents(Long no) {
  // // controller에서 넘어온 no값
  // BoardVo vo = boardRepository.findByNo(no);
  //
  // if (vo != null) {
  // boardRepository.updateHit();
  // }
  //
  // return vo;
  //
  // }
  //
  // // 세션에 있는 no가져와서 글 수정할 때 updateform - 로그인 사용자를 위해
  // public BoardVo getContents(Long boardNo, Long userNo) {
  // // update로 보내줘야지
  //
  // }

  // updateAction
  public void updateContents(BoardVo vo) {

  }

  // 글 삭제
  public void deleteContents(Long boardNo, Long userNo) {

  }

  // // 페이징 처리
  // public Map<String, Object> getContentsList(int currentPage) {
  // // 키워드 검색 기능 추가하기
  // List<BoardVo> list = null;
  // Map<String, Object> map = null;
  //
  // map.put("list", list);
  // // map.put("pager", pager);
  // // 키워드 처리 시 추가
  // // map.put("keyword", "");
  //
  // map.put("totalCount", 0);
  // map.put("listSize", 0);
  // map.put("currentPage", 0);
  // map.put("beginPage", 0);
  // map.put("endPage", 0);
  // map.put("prevPage", 0);
  // map.put("nextPage", 0);
  // // map.put("keyword", 0);
  // return map;
  //
  // }
  public List<BoardVo> getContentsList() {
    return boardRepository.findAll();
  }

  @Transactional
  public void write(BoardVo vo) {
    if (vo.getGroupNo() != null) {
      boardRepository.getNextGroupNo();
    }
    boardRepository.insert(vo);
  }
}
