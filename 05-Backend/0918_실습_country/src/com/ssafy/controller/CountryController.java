package com.ssafy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.Country;
import com.ssafy.model.service.CountryService;
import com.ssafy.model.service.CountryServiceImpl;

/**
 * Servlet implementation class CountryServlet
 */
@WebServlet("/country")
public class CountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CountryService countryService = new CountryServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null) {
			response.setContentType("text/plain;charset=utf-8");
			response.getWriter().write("잘못된 요청");
			return;
		}
		System.out.println("요청 수신 action:" + action);

		try {
			if (action.equals("list")) {
				selectAll(request, response);
			} else if (action.equals("detail")) {
				selectBycode(request, response);
			}
			else if (action.equals("register")) {
				selectBycode(request, response);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void selectAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		System.out.println("전체 조회 요청 수신");

		// DB에서 나라목록 조회
		List<Country> list = countryService.selectAll();

		// JSP로 요청 처리 넘김
		request.setAttribute("list", list);
		// fowarding 코드
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
		dispatcher.forward(request, response);

		// redirect 코드
//		response.sendRedirect("/country/list.jsp");
	}

	private void selectBycode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		System.out.println("상세 조회 요청 수신");

		// DB에서 나라목록 조회
		Country country = countryService.selectByCode2(request.getParameter("code"));

		// JSP로 요청 처리 넘김
		request.setAttribute("code", country);
		// fowarding 코드
		RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
		dispatcher.forward(request, response);
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		System.out.println("등록 요청 수신");

		// DB에서 나라목록 조회
		Country country = countryService.selectByCode2(request.getParameter("code"));

		// JSP로 요청 처리 넘김
		request.setAttribute("code", country);
		// fowarding 코드
		RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}
}
