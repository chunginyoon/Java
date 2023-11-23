package com.ssafy.ws.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * @return false인 경우, controller 호출 X
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 세션에 사용자 정보 있는지 체크
		System.out.println("interceptor의 preHandle() 실행");
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo")==null) {
			System.out.println("로그인 권한 필요");
			
			response.setContentType("text/plain;charset=utf-8");
			response.setStatus(401);	//권한 없음 에러 코드
			response.getWriter().append("권한 없음");
			
			return false;
		}
		
		return true;
	}
	
}
