package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {



	@GetMapping(value = { "home", "/" })
	public String home() {
		return "home";
	}
}
