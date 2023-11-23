package com.ssafy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieCheckServlet
 */
@WebServlet("/CookieCheckServlet")
public class CookieCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			System.out.println("쿠키명: " + cookie.getName());
			System.out.println("값: " + cookie.getValue());
		}
		request.getRequestDispatcher("/request.jsp").forward(request, response);
	}
}
