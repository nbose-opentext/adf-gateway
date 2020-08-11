package com.opentext.adf.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
	@RequestMapping("/")
	public String index() {
		return "Greetings from Gateway Service!";
	}
}
