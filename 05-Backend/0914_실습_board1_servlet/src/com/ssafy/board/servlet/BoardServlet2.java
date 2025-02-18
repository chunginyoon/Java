package com.ssafy.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.ssafy.board.BoardDto;
import com.ssafy.board.model.dao.BoardDao;

@WebServlet("/write")
public class BoardServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardDao boardDao = new BoardDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 1. 요청 파라미터 추출
		String userId = request.getParameter("userid");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		BoardDto boardDto = new BoardDto();
		boardDto.setUserId(userId);
		boardDto.setSubject(subject);
		boardDto.setContent(content);
		System.out.println("요청 데이터: " + boardDto);
		
		// 2. DB에 글 등록
		try {
			boardDao.registerArticle(boardDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 3. 글 작성 성공 페이지 반환
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ko\">");
		out.println("  <head>");
		out.println("    <meta charset=\"UTF-8\" />");
		out.println("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />");
		out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
		out.println("    <link");
		out.println("      href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\"");
		out.println("      rel=\"stylesheet\"");
		out.println("      integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\"");
		out.println("      crossorigin=\"anonymous\"");
		out.println("    />");
		out.println("    <link href=\"/board/assets/css/app.css\" rel=\"stylesheet\" />");
		out.println("    <title>SSAFY</title>");
		out.println("  </head>");
		out.println("  <body>");
		out.println("    <div class=\"container\">");
		out.println("      <div class=\"row justify-content-center\">");
		out.println("        <div class=\"col-lg-8 col-md-10 col-sm-12\">");
		out.println("          <h2 class=\"my-3 py-3 shadow-sm bg-light text-center\">");
		out.println("            <mark class=\"sky\">글쓰기 결과</mark>");
		out.println("          </h2>");
		out.println("        </div>");
		out.println("        <div class=\"col-lg-8 col-md-10 col-sm-12\">");
		out.println("          <div class=\"card text-center bg-light\">");
		out.println("            <h2 class=\"fw-bold text-primary pt-3\">등록 완료!!!</h2>");
		out.println("            <div class=\"card-body\">");
		out.println("              <p class=\"card-text\">글작성이 완료되었습니다.</p>");
		out.println("              <button type=\"button\" id=\"btn-list\" class=\"btn btn-outline-primary\">");
		out.println("                글목록 페이지 이동...");
		out.println("              </button>");
		out.println("            </div>");
		out.println("          </div>");
		out.println("        </div>");
		out.println("      </div>");
		out.println("    </div>");
		out.println("    <script");
		out.println("      src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\"");
		out.println("      integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\"");
		out.println("      crossorigin=\"anonymous\"");
		out.println("    ></script>");
		out.println("    <script>");
		out.println("      document.querySelector(\"#btn-list\").addEventListener(\"click\", function () {");
		out.println("        location.href = \"/board1_servlet/board\" ");
		out.println("      });");
		out.println("    </script>");
		out.println("  </body>");
		out.println("</html>");
	}
}
