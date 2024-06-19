package com.poscodx.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class GuestBookLogRepository {
  private SqlSession sqlSession;

  public GuestBookLogRepository(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  public int insert() {
    return sqlSession.update("guestbooklog.insert");
  }

  // 카운트 증가
  public int update() {
    return sqlSession.update("guestbooklog.update-increase");
  }

  // 카운트 감소
  public int update(Long no) {
    return sqlSession.update("guestbooklog.update-decrease", no);
  }
}
