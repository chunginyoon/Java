package com.ssafy.board.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableAspectJAutoProxy // AOP auto proxy 설정
@MapperScan(basePackages = {"com.ssafy.board.model.mapper"}) // Mapper 인터페이스 빈 등록
public class WebMVCConfig implements WebMvcConfigurer {
    
    
    // CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
        .addMapping("/cars/**")
        .allowedOrigins("http://127.0.0.1:5500");
    }

}