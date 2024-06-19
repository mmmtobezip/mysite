package com.poscodx.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MeasureExecutionTimeAspect {

  @Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))")
  public Object adviceAround(ProceedingJoinPoint pjp) throws Throwable {
    // @Before
    StopWatch sw = new StopWatch();
    sw.start();

    // 기존 메서드 실행
    Object result = pjp.proceed();

    // @After
    sw.stop();

    long totalTime = sw.getTotalTimeMillis();
    String className = pjp.getTarget().getClass().getName(); // 실행되는 Bean의 class name 도출 가능
    String methodName = pjp.getSignature().getName(); // 실행되는 method name 도출 가능
    String taskName = className + "." + methodName;

    System.out.println("[Execution Time][" + taskName + "] " + totalTime + "millis");
    // [Execution Time][com.poscodx.mysite.repository.GuestBookRepository.findAll] 308millis
    // [Execution Time][com.poscodx.mysite.repository.UserRepository.findByEmailAndPassword] 8millis
    // [Execution Time][com.poscodx.mysite.controller.MainController.index] 17millis

    return result;
  }
}
