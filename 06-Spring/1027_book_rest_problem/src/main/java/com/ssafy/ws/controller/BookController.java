package com.ssafy.ws.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.model.service.BookService;

@Controller
@RequestMapping("/books")
@CrossOrigin("http://127.0.0.1:5500")
public class BookController {
	private BookService bookService;

	// @Autowired 생략
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * 전체 조회
	 * 
	 * @return
	 * @throws SQLException
	 */
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Book>> getBooks() throws SQLException {

		System.out.println("getBooks() 수신");

		List<Book> list = bookService.selectAll();

		return ResponseEntity.ok(list);
	}

	@GetMapping("/{no}")
	@ResponseBody
	public ResponseEntity<Book> getBook(@PathVariable int no) throws SQLException {

		Book book = bookService.selectByNo(no);
		return ResponseEntity.ok(book);
	}

//	@PostMapping
//	@ResponseBody
//	/* application/x-www-form-urlencoded */
//	public ResponseEntity<Void> registBook(@ModelAttribute Book book){
//		bookService.registBook(book);
//		
//		return ResponseEntity.status(HttpStatus.CREATED).build();
//	}

	@PostMapping
	@ResponseBody
	/* application/json */
	public ResponseEntity<Void> registBook(@RequestBody Book book) {
		bookService.registBook(book);

		return ResponseEntity.status(HttpStatus. ).build();
	}

	@DeleteMapping("/{no}")
	public ResponseEntity<Void> deleteBook(@PathVariable int no) {
		bookService.deleteByNo(no);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
