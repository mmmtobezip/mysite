package com.poscodx.mysite.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

public class EncodingFilter extends HttpFilter implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {

    /* request 처리 */
    req.setCharacterEncoding("utf-8");  // 모든 web.xml에 명시된 url에 encoding 설정 해줌. -> 즉, ActionServlet 내 req.setCharacterEncoding("utf-8") 생략 가능 -> 횡단 관심을 공통화 
  
    chain.doFilter(req, res);
    // chain안에 있는 다음 filter 실행 -> 연쇄 호출 -> 마지막 Servlet의 doGet()이 받음


    /* response 처리 */
    // response는 request보다 뒤에서 처리해야함.


  }

}
