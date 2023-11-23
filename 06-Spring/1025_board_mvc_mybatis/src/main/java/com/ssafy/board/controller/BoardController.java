package com.ssafy.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.board.dto.Product;
import com.ssafy.board.model.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService boardService;

	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<Product> list = boardService.selectAll();
		model.addAttribute("list", list);
		return "list";
	}

	@GetMapping("/detail")
	public String detail(@RequestParam(name = "code") String code, Model model) {
		Product product = boardService.selectByCode(code);
		model.addAttribute("product", product);
		return "detail";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "code") String code) {
		boardService.deleteByCode(code);
		return "redirect:/board/list";
	}

	@GetMapping("/regist")
	public String mvRegist() {
		return "regist";
	}

	@PostMapping("regist")
	public String regist(@ModelAttribute Product product) {
		boardService.regist(product);
		return "redirect:/board/list";
	}
}