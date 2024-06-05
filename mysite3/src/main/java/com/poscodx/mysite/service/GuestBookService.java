package com.poscodx.mysite.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poscodx.mysite.repository.GuestBookRepository;
import com.poscodx.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

  @Autowired
  private GuestBookRepository guestbookRepository;

  // 전체 게시글 가져오기
  public List<GuestBookVo> getContentsList() {
    return guestbookRepository.findAll();
  }

  // 게시글 지우기 - no & password 이용
  public void deleteContents(Long no, String password) {
    guestbookRepository.deleteByNoAndPassword(no, password);
  }

  // 게시글 추가
  public void addContents(GuestBookVo vo) {
    System.out.println(vo);
    guestbookRepository.insert(vo);
    System.out.println(vo);
  }
}

