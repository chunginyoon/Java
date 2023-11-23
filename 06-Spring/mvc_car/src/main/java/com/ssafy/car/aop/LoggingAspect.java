package com.ssafy.car.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
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
	
	@Pointcut("execution(* com.ssafy.car.controller.CarController.*(..)) || execution(* com.ssafy.car.model..*.*(..))")
	public void carMethod() {}
	
	@Before("carMethod()")
	public void beforeCall(JoinPoint jp) {
		logger.debug("{} 호출, 파라미터:{}", 
				jp.getSignature(), 
				Arrays.toString(jp.getArgs())
				);
	}
	
}
