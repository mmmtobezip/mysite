package com.poscodx.mysite.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscodx.mysite.dto.JsonResult;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public void handler(HttpServletRequest request, HttpServletResponse response, Exception e)
      throws ServletException, IOException {
    // 1. 로깅(logging)
    StringWriter errors = new StringWriter(); // StringWrite안에 buffer 메모리에 wirte
    e.printStackTrace(new PrintWriter(errors));
    logger.error(errors.toString());

    // 2. 요청 구분
    // 2-1. json 요청: request header에 application/json (o)
    // 2-2. html 요청: request header에 application/json (x)
    String accept = request.getHeader("accept");

    if (accept.matches(".*application/json.*")) {
      // 모든 문자와 비교(정규표현식)해서 해당 형식이라면 json으로 요청이 들어온 것
      // 3. json 응답
      JsonResult jsonResult = JsonResult.fail(errors.toString());
      String jsonString = new ObjectMapper().writeValueAsString(jsonResult);

      response.setStatus(HttpServletResponse.SC_OK);
      response.setContentType("application/json; charset=utf-8");
      OutputStream os = response.getOutputStream();
      os.write(jsonString.getBytes("utf-8"));
      os.close();
    } else {
      // 4. 사과 페이지(정상 종료)
      if (e instanceof NoHandlerFoundException) {
        request.getRequestDispatcher("/error/404").forward(request, response);
      } else {
        request.setAttribute("error", errors.toString());
        request.getRequestDispatcher("/error/500").forward(request, response);
      }
    }
  }
}
