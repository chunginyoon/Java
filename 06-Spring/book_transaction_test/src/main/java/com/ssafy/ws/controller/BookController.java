package com.ssafy.ws.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.model.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	private BookService bookService;

	//	@Autowired	생략
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	//전체 조회 요청 처리
	@GetMapping("/list")
	public String selectAll(Model model) throws SQLException {
		List<Book> list = bookService.selectAll();

		model.addAttribute("list", list);

		return "book/list";
	}

	//상세 조회 요청 처리
	@GetMapping("/detail")
	public ModelAndView selectByCarNo(@RequestParam(name = "no") int no, ModelAndView mv) throws SQLException {
		Book book = bookService.selectByNo(no);

		mv.addObject("book", book);
		mv.setViewName("book/detail");

		return mv;
	}

	@GetMapping("/regist")
	public String mvRegist() {
		return "/book/regist";
	}
	
	//등록 처리 완료 후엔 목록화면 볼 수 있도록
	@PostMapping("/regist")
	public String registCar(@ModelAttribute Book book) throws SQLException{
		bookService.registBook(book);

		return "redirect:/book/list";
	}
	
	//삭제 처리 완료 후 목록 화면
	@GetMapping("/delete")
	public String deleteCar(int no) throws SQLException{
		bookService.deleteByNo(no);

		return "redirect:/book/list";
	}
}
