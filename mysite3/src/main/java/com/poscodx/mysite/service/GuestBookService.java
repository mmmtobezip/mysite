package com.poscodx.mysite.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.poscodx.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

  // 전체 게시글 가져오기
  public List<GuestBookVo> getContentsList() {
    return null;
  }

  // 게시글 지우기 - no & password 이용
  public void deleteContents(Long no, String password) {}

  // 게시글 추가
  public void addContents(GuestBookVo vo) {

  }

}
