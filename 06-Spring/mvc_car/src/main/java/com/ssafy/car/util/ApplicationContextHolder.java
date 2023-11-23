package com.ssafy.car.util;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class ApplicationContextHolder implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        
        System.err.printf("클래스명 : %s%n", context.getClass().getName());
		System.err.printf("ApplicationContext 정보: %s%n", context.toString());
        
		String[] beanNames = context.getBeanDefinitionNames();
		for(int i=0; i<beanNames.length; i++) {
			System.err.printf("%d번 빈이름 : %s%n", i+1 , beanNames[i]);
		}
    }
}