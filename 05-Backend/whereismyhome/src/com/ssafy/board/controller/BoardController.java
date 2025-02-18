package com.ssafy.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.ParameterCheck;

@WebServlet("/article")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = BoardServiceImpl.getBoardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		String path = "";

		if ("list".equals(action)) {
			path = list(request, response);
			forward(request, response, path);
		} else if ("view".equals(action)) {
			path = view(request, response);
			forward(request, response, path);
		} else if ("mvwrite".equals(action)) {
			path = "/board/write.jsp";
			redirect(request, response, path);
		} else if ("write".equals(action)) {
			path = write(request, response);
			redirect(request, response, path);
		} else if ("mvmodify".equals(action)) {
			path = mvModify(request, response);
			forward(request, response, path);
		} else if ("modify".equals(action)) {
			path = modify(request, response);
			forward(request, response, path);
		} else if ("delete".equals(action)) {
			path = delete(request, response);
			redirect(request, response, path);
		} else {
			redirect(request, response, path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {

		try {

			List<BoardDto> list = boardService.listArticle();
			request.setAttribute("articles", list);

			return "/board/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글목록 출력 중 문제 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String view(HttpServletRequest request, HttpServletResponse response) {
		int articleNo = Integer.parseInt(request.getParameter("articleno"));
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			boardService.updateHit(articleNo);
			request.setAttribute("article", boardDto);

			return "/board/view.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글내용 출력 중 문제 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String write(HttpServletRequest request, HttpServletResponse response) {
		BoardDto boardDto = new BoardDto();
		boardDto.setUserId(request.getParameter("user_id"));
		boardDto.setSubject(request.getParameter("subject"));
		boardDto.setContent(request.getParameter("content"));
		try {
			boardService.writeArticle(boardDto);
			return "/article?action=list";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글작성 중 문제 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
		// TODO : 수정하고자하는 글의 글번호를 얻는다.
		// TODO : 글번호에 해당하는 글정보를 얻고 글정보를 가지고 modify.jsp로 이동.
		try {
			int articleNo = Integer.parseInt(request.getParameter("article_no"));
			BoardDto boardDto = boardService.getArticle(articleNo);
			request.setAttribute("article", boardDto);

			return "/board/modify.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글내용 얻는 중 문제 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) {
		// TODO : 수정 할 글정보를 얻고 BoardDto에 set.
		// TODO : boardDto를 파라미터로 service의 modifyArticle() 호출.
		// TODO : 글수정 완료 후 view.jsp로 이동.(이후의 프로세스를 생각해 보세요.)

		BoardDto boardDto = new BoardDto();

		try {
			boardDto.setArticleNo(Integer.parseInt(request.getParameter("articleno")));
			boardDto.setSubject(request.getParameter("subject"));
			boardDto.setContent(request.getParameter("content"));
			boardService.modifyArticle(boardDto);
			return "/article?action=list";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글수정 중 문제 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO : 삭제할 글 번호를 얻는다.
		// TODO : 글번호를 파라미터로 service의 deleteArticle()을 호출.
		// TODO : 글삭제 완료 후 list.jsp로 이동.(이후의 프로세스를 생각해 보세요.)

		int articleNo = Integer.parseInt(request.getParameter("articleno"));

		try {
			boardService.deleteArticle(articleNo);
			return "/article?action=list";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글삭제 중 문제 발생!!!");
			return "/error/error.jsp";
		}
	}
}