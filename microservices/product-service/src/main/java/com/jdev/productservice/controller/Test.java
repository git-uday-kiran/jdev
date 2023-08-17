package com.jdev.productservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class Test {
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public String test() {
		return "Hey, It's Working!";
	}
}
