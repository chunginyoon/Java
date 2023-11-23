package com.ssafy.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.Member;
import com.ssafy.model.service.MemberService;
import com.ssafy.model.service.MemberServiceImpl;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			switch (action) {
			case "mvLogin":
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				break;
			case "login":
				login(request, response);
				break;
			case "logout":
				logout(request, response);
				break;

			default:
				request.getRequestDispatcher("/error/error404.jsp").forward(request, response);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error/error500.jsp").forward(request, response);
		}

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("로그아웃 요청 수신");
		// 1. 세션 무효화
		HttpSession session = request.getSession();
		session.invalidate();
		// 2. 재요청할 메인페이지 url응답
		response.sendRedirect(request.getContextPath()); //새로 처음부터 다시

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("로그인 요청 수신");
		// 1. 아이디, 비밀번호 값 추출
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");// ""값은 html의 name과 일치
		Member member = new Member();
		member.setMemberId(memberId);
		member.setPassword(password);

		// 2. DB에 해당 멤버 조회
		Member memberInfo = memberService.login(member);

		if (memberInfo != null) {// 로그인 성공
			System.out.println("로그인한 유저의 정보 : " + memberInfo);
			// 3. 해당 멤버가 존재한다면 세션에 유저 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("memberInfo", memberInfo);
			// =============아이디 저장 쿠기 관련================
			String isRemember = request.getParameter("isRemember");
			System.out.println("isRemember 파라미터 : " + isRemember);
			if (isRemember != null) {// 아이디 저장 해야함
				Cookie cookie = new Cookie("rememberId", memberId);
				cookie.setMaxAge(60 * 60);// 1시간
				response.addCookie(cookie);
			} else {// 아이디 저장 x
					// 기존 쿠키를 유효기간 0인 쿠키로 대체(삭제와 비슷한 효과)
				Cookie cookie = new Cookie("rememberId", memberId);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

			// ============================================
			// 4. 클라이언트가 새로 요청할 주소값 넘김(index.jsp로 요청을 넘김)
			response.sendRedirect(request.getContextPath());
		} else {// 로그인 실패
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath() + "/member?action=mvLogin");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
}