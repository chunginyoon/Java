package com.ssafy.home.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.home.model.service.HouseDealService;
import com.ssafy.home.model.service.HouseDealServiceImpl;
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String path;
	
	private HouseDealService houseDealService = HouseDealServiceImpl.getInstance();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		String action = request.getParameter("action");
		try {
			if(action.equals("mvSearch")) {
				path = mvSearch();
				forward(path, request, response);
			}else if(action.equals("search")) {
				path = search(request, response);
			}else {
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private String search(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("검색 요청 수신");
		String sidoName = request.getParameter("sido");
		String gugunName = request.getParameter("gugun");
		String dongName = request.getParameter("dong");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		
		
		return "";
	}

	private String mvSearch() {
		System.out.println("검색 페이지 요청 수신");
		return "/priceSearch.jsp";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	

	private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	
	private void redirect(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String url = request.getContextPath()+path;
		System.out.println(url);
		response.sendRedirect(url);
	}

}
