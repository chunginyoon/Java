package com.ssafy.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;

@WebServlet("/article")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		boardService = BoardServiceImpl.getBoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action"); // 사용자 요구

		String path = "";
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
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
			return "/index.jsp";
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
			return "/index.jsp";
		}
	}

	private String write(HttpServletRequest request, HttpServletResponse response) {
		BoardDto boardDto = new BoardDto();
		boardDto.setUserId(request.getParameter("userid"));
		boardDto.setSubject(request.getParameter("subject"));
		boardDto.setContent(request.getParameter("content"));
		try {
			boardService.writeArticle(boardDto);
			return "/article?action=list";
		} catch (Exception e) {
			e.printStackTrace();
			return "/index.jsp";
		}
	}

	private String mvModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO : 수정하고자하는 글의 글번호를 얻는다.
		// TODO : 글번호에 해당하는 글정보를 얻고 글정보를 가지고 modify.jsp로 이동.

		int articleNo = Integer.parseInt(request.getParameter("articleno"));
		BoardDto boardDto = boardService.getArticle(articleNo);

		request.setAttribute("boardDto", boardDto);
		return "/modify.jsp";
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO : 수정 할 글정보를 얻고 BoardDto에 set.
		// TODO : boardDto를 파라미터로 service의 modifyArticle() 호출.
		// TODO : 글수정 완료 후 view.jsp로 이동.(이후의 프로세스를 생각해 보세요.)

		int articleNo = Integer.parseInt(request.getParameter("articleno"));
		String userId = request.getParameter("userid");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int hit = Integer.parseInt(request.getParameter("hit"));
		String registerTime = request.getParameter("registertime");

		BoardDto boardDto = new BoardDto();
		boardDto.setArticleNo(articleNo);
		boardDto.setContent(content);
		boardDto.setHit(hit);
		boardDto.setRegisterTime(registerTime);
		boardDto.setSubject(subject);
		boardDto.setUserId(userId);

		boardService.modifyArticle(boardDto);
		request.setAttribute("boardDto", boardDto);

		return "/view.jsp";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO : 삭제할 글 번호를 얻는다.
		// TODO : 글번호를 파라미터로 service의 deleteArticle()을 호출.
		// TODO : 글삭제 완료 후 list.jsp로 이동.(이후의 프로세스를 생각해 보세요.)
		int articleNo = Integer.parseInt(request.getParameter("articleno"));
		boardService.deleteArticle(articleNo);
		return "/list.jsp";
	}
}