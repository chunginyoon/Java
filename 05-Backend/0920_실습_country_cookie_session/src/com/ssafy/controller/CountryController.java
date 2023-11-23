package com.ssafy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.Country;
import com.ssafy.model.service.CountryService;
import com.ssafy.model.service.CountryServiceImpl;

@WebServlet("/country")
public class CountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CountryService countryService = new CountryServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "mvRegist":
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
				break;
			case "mvUpdate":
				System.out.println("수정페이지 이동 요청");
				mvUpdate(request,response);
				break;
			case "list":
				list(request,response);
				break;
			case "view":
				view(request,response);
				break;
			case "regist":
				regist(request,response);

				break;
			case "update":
				update(request,response);
				break;
			case "delete":
				delete(request,response);
				break;
			default :
				response.getWriter().write("<h1>wrong request</h1>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("===Internal Server Error===");
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("수정 요청 수신");
		//1. 등록할 국가정보  파라미터로부터 추출 후, Country 객체로 가공
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String continent = request.getParameter("continent");
		double gnp = Double.parseDouble(request.getParameter("gnp"));
		Country country = new Country(code, name, continent,gnp);
		System.out.println("수정할 국가 정보 : "+country);		
		
		//2. service의 update() 메소드 호출을 통해 요청 처리
		int cnt= countryService.updateCountry(country);
		//3. redirect를 통해 브라우저가 새로 요청할 상세 조회 페이지 url 반환
		response.sendRedirect(request.getContextPath()+"/country?action=view&code="+code);
	}

	private void mvUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("수정페이지 이동 요청 수신");
		//1. 수정할 국가의 code값을 파라미터로부터 추출
		String code = request.getParameter("code");
		
		//2. code 값을 통해 기존의 국가 데이터를 DB에서 조회
		Country country = countryService.selectByCode2(code);
		
		//3. 수정페이지 반환은 update.jsp로 넘김
		request.setAttribute("country", country);
		request.getRequestDispatcher("/update.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("삭제 요청 수신");
		//1. 삭제할 국가 코드 정보 파라미터에서 추출
		String code = request.getParameter("code");
		System.out.println("삭제할 국가 코드: "+code);
		
		//2. service의 delete 메소드 호출을 통해 요청 처리
		int cnt= countryService.deleteByCode(code);
		
		//3. 삭제 성공시, 목록 조회 페이지 반환
		response.sendRedirect(request.getContextPath()+"/country?action=list");
	}

	private void regist(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		System.out.println("등록 요청 수신");	
		HttpSession session = request.getSession();
		if(session.getAttribute("memberInfo")==null) {
			System.out.println("등록 없는 요청");
			request.getRequestDispatcher("/error/error401.jsp").forward(request, response);
			return;
		}
		
		//1.등록할 국가 정보를 파라미터로부터 추출 및 country로 가공
		//code, name, continent,gnp
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String continent = request.getParameter("continent");
		double gnp = Double.parseDouble(request.getParameter("gnp"));
		Country country = new Country(code, name, continent,gnp);
		System.out.println("등록할 국가 정보 : "+country);		
		
		//2. Service의 등록 메소드 호출해서 요청처리 맡김
		int cnt = countryService.insertCountry(country);
		
		//3. 등록 성공 시, view.jsp로 이동(브라우저에게 새로 요청 주소값 반환)
		response.sendRedirect(request.getContextPath()+"/country?action=view&code="+code);
		
	}

	private void view(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("상세 조회 요청 수신");
		//1.code 정보 추출
		String code = request.getParameter("code");
		System.out.println("조회할 코드명 : "+code);
		
		//2. code 정보를 통해 상세 국가 정보 조회
		Country country = countryService.selectByCode2(code);
		
		//3.Country 정보와 함께 view.jsp로 포워딩
		request.setAttribute("country", country);
		request.getRequestDispatcher("/view.jsp").forward(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("전체 목록 조회 요청 수신");
		List<Country> list = countryService.selectAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("list.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Post요청 시 한글 파라미터에 대한 인코딩 처리
		request.setCharacterEncoding("utf-8");

		doGet(request, response);
	}

}
