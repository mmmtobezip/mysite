package com.poscodx.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class) // 받아야할 exception 종류를 적어줌. controller에서 발생하는 exception의 부모를
                                     // 명시해주면,
  // checkedexception도 throws로 위로 toss할 수 있는데, 모든 excepton을 받기 위해 부모의 exception.class를 명시해줌.
  // 만약 종류별로 처리하고 싶으면 SQL Exception처리 전용 메서드 등을 따로 분리해줘도
  public String handler(Exception e, Model model) {
    // 1. 로깅(logging)
    StringWriter error = new StringWriter(); // StringWrite안에 buffer 메모리에 wirte
    e.printStackTrace(new PrintWriter(error));
    System.out.println(error.toString());

    // 2. 사과(종료)
    model.addAttribute("error", error.toString());
    return "errors/exception";
  }
}
