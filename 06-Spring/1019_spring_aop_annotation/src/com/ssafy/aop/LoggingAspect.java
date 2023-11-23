package com.ssafy.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	@Pointcut("execution(* com.*.board.model..listArticle(..))")
	public void serviceMethod() {
	}

	@Before("serviceMethod()")
	public void beforeLogging(JoinPoint jp) {
		System.out.println("메소드 호출");
		System.out.println(jp.getSignature());
		System.out.println(Arrays.toString(jp.getArgs()));
	}

	@AfterReturning(pointcut = "serviceMethod()", returning = "result")
	public void afterLogging(JoinPoint jp, Object result) {
		System.out.printf("메소드 반환 메서드명: %s, 반환값: %s\n", jp.getSignature(), result);
	}
}
