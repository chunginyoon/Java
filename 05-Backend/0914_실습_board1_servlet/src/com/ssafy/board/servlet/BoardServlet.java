package com.ssafy.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.ssafy.board.BoardDto;
import com.ssafy.board.model.dao.BoardDao;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardDao boardDao = new BoardDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("BoardServlet doGet() 메소드 실행");

		try {
			// 1. DB로부터 글목록 정보 조회
			List<BoardDto> list = boardDao.searchListAll(); //데이터베이스를 읽어온다

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
			out.println("            <mark class=\"sky\">글목록</mark>");
			out.println("          </h2>");
			out.println("        </div>");
			out.println("        <div class=\"col-lg-8 col-md-10 col-sm-12\">");
			out.println("          <div class=\"row align-self-center mb-2\">");
			out.println("            <div class=\"col-md-2 text-start\">");
			out.println(
					"              <button type=\"button\" id=\"btn-mv-register\" class=\"btn btn-outline-primary btn-sm\">");
			out.println("                글쓰기");
			out.println("              </button>");
			out.println("            </div>");
			out.println("            <div class=\"col-md-7 offset-3\">");
			out.println("              <form class=\"d-flex\">");
			out.println("                <select");
			out.println("                  id=\"key\"");
			out.println("                  class=\"form-select form-select-sm ms-5 me-1 w-50\"");
			out.println("                  aria-label=\"검색조건\"");
			out.println("                >");
			out.println("                  <option selected>검색조건</option>");
			out.println("                  <option value=\"articleno\">글번호</option>");
			out.println("                  <option value=\"subject\">제목</option>");
			out.println("                  <option value=\"userid\">작성자</option>");
			out.println("                </select>");
			out.println("                <div class=\"input-group input-group-sm\">");
			out.println("                  <input type=\"text\" class=\"form-control\" placeholder=\"검색어...\" />");
			out.println("                  <button class=\"btn btn-dark\" type=\"button\">검색</button>");
			out.println("                </div>");
			out.println("              </form>");
			out.println("            </div>");
			out.println("          </div>");
			out.println("          <table class=\"table table-hover\">");
			out.println("            <thead>");
			out.println("              <tr class=\"text-center\">");
			out.println("                <th scope=\"col\">글번호</th>");
			out.println("                <th scope=\"col\">제목</th>");
			out.println("                <th scope=\"col\">작성자</th>");
			out.println("                <th scope=\"col\">조회수</th>");
			out.println("                <th scope=\"col\">작성일</th>");
			out.println("              </tr>");
			out.println("            </thead>");
			out.println("            <tbody>");

			for(BoardDto board : list) { //읽어온 데이터 개수만큼 출력
                out.println("              <tr class=\"text-center\">");
                out.println("                <th scope=\"row\">"+ board.getArticleNo() +"</th>");
                out.println("                <td class=\"text-start\">");
                out.println("                  <a");
                out.println("                    href=\"http://localhost:8080/board1_servlet/view?article_no="+board.getArticleNo()+"\"");
                out.println("                    class=\"article-title link-dark\"");
                out.println("                    data-no=\""+board.getArticleNo()+"\"");
                out.println("                    style=\"text-decoration: none\"");
                out.println("                  >");
                out.println("                    "+board.getSubject());
                out.println("                  </a>");
                out.println("                </td>");
                out.println("                <td>"+board.getUserId()+"</td>");
                out.println("                <td>"+board.getHit()+"</td>");
                out.println("                <td>"+board.getRegisterTime()+"</td>");
                out.println("              </tr>");
            }

			out.println("            </tbody>");
			out.println("          </table>");
			out.println("        </div>");
			out.println("        <div class=\"row\">");
			out.println("          <ul class=\"pagination justify-content-center\">");
			out.println("            <li class=\"page-item\">");
			out.println("              <a class=\"page-link\" href=\"#\">이전</a>");
			out.println("            </li>");
			out.println("            <li class=\"page-item\"><a class=\"page-link\" href=\"#\">1</a></li>");
			out.println("            <li class=\"page-item active\">");
			out.println("              <a class=\"page-link\" href=\"#\">2</a>");
			out.println("            </li>");
			out.println("            <li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>");
			out.println("            <li class=\"page-item\"><a class=\"page-link\" href=\"#\">4</a></li>");
			out.println("            <li class=\"page-item\"><a class=\"page-link\" href=\"#\">5</a></li>");
			out.println("            <li class=\"page-item\"><a class=\"page-link\" href=\"#\">다음</a></li>");
			out.println("          </ul>");
			out.println("        </div>");
			out.println("      </div>");
			out.println("    </div>");
			out.println("    <script");
			out.println("      src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\"");
			out.println("      integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\"");
			out.println("      crossorigin=\"anonymous\"");
			out.println("    ></script>");
			out.println("    <script>");
			out.println("      let titles = document.querySelectorAll(\".article-title\");");
			out.println("      titles.forEach(function (title) {");
			out.println("        title.addEventListener(\"click\", function () {");
			out.println("          console.log(this.getAttribute(\"data-no\"));");
			out.println("        });");
			out.println("      });");
			out.println("");
			out.println("      document.querySelector(\"#btn-mv-register\").addEventListener(\"click\", function () {");
			out.println("        location.href = \"/board/write.html\";");
			out.println("      });");
			out.println("    </script>");
			out.println("  </body>");
			out.println("</html>");

		} catch (Exception e) {
			// TODO: handle 
			e.printStackTrace();
		}
	}
}
