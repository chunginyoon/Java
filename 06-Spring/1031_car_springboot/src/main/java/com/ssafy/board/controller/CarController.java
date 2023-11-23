package com.ssafy.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.dto.Car;
import com.ssafy.board.model.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
	
	private final Logger logger = LoggerFactory.getLogger(CarController.class);
	private CarService carService;
	
	public CarController(CarService carService) {
		super();
		this.carService = carService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Car>> selectAll(){
		List<Car> list = carService.selectAll();
		logger.debug("selectAll {}", list);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{carNo}")
	@ResponseBody
	public ResponseEntity<Car> selectByCarNo(@PathVariable("carNo") int carNo){
		Car car = carService.selectByCarNo(carNo);
		logger.debug("selectByCarNo {}", car);
		return ResponseEntity.ok(car);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Void> regist(@RequestBody Car car){
		carService.regist(car);
		logger.debug("regist {}", car);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Void> modify(@RequestBody Car car){
		carService.modify(car);
		logger.debug("modify {}", car);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{carNo}")
	@ResponseBody
	public ResponseEntity<Void> delete(@PathVariable("carNo") int carNo){
		carService.delete(carNo);
		logger.debug("delete {}");
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}