package com.ssafy.car.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.car.dto.Car;
import com.ssafy.car.model.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController {

	// 로거 변수
	private Logger logger = LoggerFactory.getLogger(CarController.class);

	private CarService carService;

//	@Autowired	생략
	public CarController(CarService carService) {
		this.carService = carService;
	}

	// 전체 조회 요청 처리
	@GetMapping("/list")
	public String selectAll(Model model) throws SQLException {
		logger.debug("전체 조회 요청 수신 debug");
		logger.info("전체 조회 요청 수신 info");
		logger.warn("전체 조회 요청 수신 warn");

		List<Car> list = carService.selectAll();

		System.out.println(list);
		model.addAttribute("list", list);

		return "car/list";
	}

	// 상세 조회 요청 처리
	@GetMapping("/detail")
	public ModelAndView selectByCarNo(@ModelAttribute("carNo") int carNo2,
			@RequestParam(name = "carNo", required = false, defaultValue = "1") int carNo, ModelAndView mv)
			throws SQLException {
		System.out.println("상세 조회요청 수신 조회 no : " + carNo);

		System.out.println("flashAttribute로 전달받은 속성: " + carNo2);
		Car car = carService.selectByCarNo(carNo);
		System.out.println(car);

		mv.addObject("car", car);
		mv.setViewName("car/detail");

		return mv;
	}

	// 등록 처리 완료 후엔 목록화면 볼 수 있도록
	@GetMapping("/regist")
	public String registCar(@ModelAttribute("car") Car car, RedirectAttributes attributes) throws SQLException {
		System.out.println("등록 요청 수신, 차 정보:" + car);

		int cnt = carService.registCar(car);
		System.out.println("등록된 차 정보 개수:" + cnt);

//		attributes.addAttribute("carNo", 3);

		// 한 번 사용하고 사라질 파라미터
		attributes.addFlashAttribute("carNo", 3);

		return "redirect:/car/detail";
	}

	@ExceptionHandler(SQLException.class)
	public String sqlException() {

		logger.debug("carController의 exceptionHandler 호출");
		return "error/500error";
	}
}
