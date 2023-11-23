package com.ssafy.book.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* com.ssafy.book.controller.BookController.*(..)) || execution(* com.ssafy.book.model..*.*(..))")
	public void bookMethod() {}
	
	@Before("bookMethod()")
	public void beforeCall(JoinPoint jp) {
		Signature s = jp.getSignature();
		logger.debug("호출 : {}, 파라미터:{}", 
				s.getDeclaringType().getSimpleName()+"."+
						s.getName(),
				Arrays.toString( jp.getArgs() )
		);
	}
	
	@AfterReturning(pointcut = "bookMethod()", returning = "result")
	public void afterCall(JoinPoint jp, Object result) {
		Signature s = jp.getSignature();
		logger.debug("리턴 : {}, 반환값: {}", 
				s.getDeclaringType().getSimpleName()+"."+
				s.getName(), 
				result);
	}
	
}
