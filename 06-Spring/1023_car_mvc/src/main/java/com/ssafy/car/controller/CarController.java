package com.ssafy.car.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.car.dto.Car;
import com.ssafy.car.model.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController {

	private CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	// 전체 조회 요청 처리
	@GetMapping("/list")
	public String selectAll(Model model) throws SQLException {
		System.out.println("전체 조회 요청 수신");

		List<Car> list = carService.selectAll();

		System.out.println(list);
		model.addAttribute("list", list);

		return "list";
	}

	// 등록 페이지로 이동
	@GetMapping("/mvRegist")
	public String mvRegist() {
		System.out.println("전체 조회 요청 수신");

		return "registCar";
	}

	// 상세 조회 요청 처리
	@GetMapping("/detail")
	public String selectByCarNo(@RequestParam(name = "carNo") int carNo, Model model) throws SQLException {
		System.out.println("상세 조회요청 수신 조회 no : " + carNo);
		Car car = carService.selectByCarNo(carNo);
		System.out.println(car);

		model.addAttribute("car", car);

		return "detail";
	}

	// 등록 처리 완료 후엔 목록화면 볼 수 있도록
	@PostMapping("/regist")
	public String registCar(@ModelAttribute("car") Car car, RedirectAttributes attributes) throws SQLException {
		System.out.println("등록 요청 수신, 차 정보:" + car);

		int cnt = carService.registCar(car);
		System.out.println("등록된 차 정보 개수:" + cnt);

//		attributes.addAttribute("carNo", 3);

		// 한 번 사용하고 사라질 파라미터
		attributes.addFlashAttribute("carNo", car.getCarNo());

		return "redirect:/car/list";
	}

	// 삭제 완료 후엔 목록화면 볼 수 있도록
	@GetMapping("/delete")
	public String registCar(@RequestParam(name = "carNo") int carNo, RedirectAttributes attributes)
			throws SQLException {
		System.out.println("삭제 요청 수신, 차 정보:" + carNo);

		int cnt = carService.deleteCar(carNo);
		System.out.println("삭제된 차 정보 개수:" + cnt);

//		attributes.addAttribute("carNo", 3);

		// 한 번 사용하고 사라질 파라미터
		attributes.addFlashAttribute("carNo", carNo);

		return "redirect:/car/list";
	}
}