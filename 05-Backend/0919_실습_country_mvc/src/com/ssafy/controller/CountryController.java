package com.ssafy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.Country;
import com.ssafy.model.service.CountryService;
import com.ssafy.model.service.CountryServiceImpl;

/**
 * Servlet implementation class CountryController
 */

/*
 * Controller의 흐름 제어 1. 요청 파라미터 추출 및 필요하다면 가공 2. 요청에 대한 처리를 model에 맡김 3. 응답 페이지
 * 반환 처리를 뷰에 넘김
 */

/*
 * 메인화면(index.jsp)
    -전체 조회 페이지 이동(a 태그)
    -등록 페이지 이동
    
    전체 조회 화면(list.jsp)
    -전체 조회 화면
    -국가명 클릭시 상세화면 이동
    
    상세 조회 화면(view.jsp)
    -수정 버튼 클릭 시 수정 화면 이동
    -삭제 버튼 클릭 시 해당 국가 정보 삭제
    
    등록 화면(regist.jsp)
    -사용자로부터 등록할 국가 정보 입력 form
    -등록 버튼 클릭 시 국가 정보 등록 요청 => 등록 성공 시 상세 조회 화면으로 이동
    
    수정 화면(update.jsp)
    -사용자로부터 수정할 국가정보 입력 form => 국가 코드(code) 정보는 수정 불가!
    -수정 버튼 클릭 시, 국가 정보 수정 요청 => 수정 성공 시 상세 조회 화면 이동
 */
@WebServlet("/country")
public class CountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CountryService countryService = new CountryServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action"); // action의 파라미터 갓을 꺼내온다

		try {
			switch (action) {
			case "mvRegist":
				request.getRequestDispatcher("/regist.jsp"); // 기본 위치가 webcontent 안이다
				break;
			case "mvUpdate":
				mvUpdate(request, response);
				break;
			case "list":
				list(request, response);
				break;
			case "view":
				view(request, response);
				break;
			case "regist":
				regist(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			default:
				response.getWriter().write("wrong request");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Internal Server Error");
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("수정 요청 수신");
		
		//1. 등록할 국가 정보 파라미터 추출 후, Country 객체로 가공
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String continent = request.getParameter("continent");
		double gnp = Double.parseDouble(request.getParameter("gnp"));
		Country country = new Country(code, name, continent, gnp);
		System.out.println("수정할 국가 정보: " + country);
		
		//2. service의 update() 메소드 호출을 통해 요청 처리
		int cnt = countryService.updateCountry(country);
		
		//3.redirect를 통해 브라우저가 새로 요청할 상세 조회 페이지 url 반환
		response.sendRedirect(request.getContextPath()+"/country?action=view&code="+code);
	}

	private void mvUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("수정 페이지 이동 요청 수신");
		
		//1. 수정할 국가의 code 값을 파라미터로부터 추출
		String code = request.getParameter("code");
		
		//2. code 값을 통해 기존의 국가데이터를 DB에서 조회
		Country country = countryService.selectByCode2(code);
		
		//3. 수정 페이지 반환은 update.jsp로 넘김
		request.setAttribute("country", country);
		request.getRequestDispatcher("/update.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("삭제 요청 수신");
		
		//1. 삭제할 국가 코드 정보 파라미터에서 추출
		String code = request.getParameter("code");
		System.out.println("삭제할 국가 코드: "+code);
		
		//2. service의 delete 메소드 호출을 통해 
		int cnt = countryService.deleteByCode(code);
		
		//3. 삭제 성공 시 목록 조회 화면 반환
		response.sendRedirect(request.getContextPath()+"/country?action=list");
	}

	private void regist(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("등록 요청 수신");

		// 1. 등록할 국가 정보를 파라미터로부터 추출 및 Country로 가공
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String continent = request.getParameter("continent");
		double gnp = Double.parseDouble(request.getParameter("gnp"));
		Country country = new Country(code, name, continent, gnp);
		System.out.println("등록할 국가 정보: " + country);

		// 2. Service의 등록 메소드 호출해서 요청 처리 맡김
		int cnt = countryService.insertCountry(country);

		// 3. 등록 성공 시, view.jsp로 이동
		response.sendRedirect(request.getContextPath() + "/country?action=view&code=" + code);
	}

	private void view(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		System.out.println("상세 조회 요청 수신");

		// 1. code 정보 추출
		String code = request.getParameter("code");
		System.out.println("조회할 코드명: " + code);

		// 2. code 정보를 통해 상세 국가 정보 조회
		Country country = countryService.selectByCode2(code);

		// 3. Country 정보와 함께 view.jsp로 포워딩
		request.setAttribute("country", country);
		request.getRequestDispatcher("/view.jsp").forward(request, response);

	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		System.out.println("전체 목록 조회 요청 수신");
		List<Country> list = countryService.selectAll();

		request.setAttribute("list", list);
		request.getRequestDispatcher("/list.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 요청 시 한글 파라미터에 대한 인코딩 처리
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
}
