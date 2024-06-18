package com.poscodx.mysite.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.poscodx.mysite.repository.GuestBookLogRepository;
import com.poscodx.mysite.repository.GuestBookRepository;
import com.poscodx.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
  @Autowired
  private GuestBookRepository guestBookRepository;
  @Autowired
  private GuestBookLogRepository guestBookLogRepository;

  // 전체 게시글 가져오기
  public List<GuestBookVo> getContentsList() {
    return guestBookRepository.findAll();
  }

  // 게시글 지우기 - no & password 이용
  @Transactional
  public void deleteContents(Long no, String password) {
    guestBookLogRepository.update(no);
    guestBookRepository.deleteByNoAndPassword(no, password);
  }

  // 게시글 추가
  @Transactional
  public void addContents(GuestBookVo vo) {
    int count = guestBookLogRepository.update();

    if (count == 0) {
      guestBookLogRepository.insert();
    }
    guestBookRepository.insert(vo);
  }
}

