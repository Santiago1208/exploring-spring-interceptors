package com.example.springboot.app.springbootschedule.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@Value("${config.schedule.openAt}")
	private Integer opensAt;

	@Value("${config.schedule.closeAt}")
	private Integer closesAt;
	
	@GetMapping({"/", "/index", ""})
	public String index(Model model) {
		model.addAttribute("title", "Welcome to the client attention schedule!");
		return "index";
	}

	@GetMapping("/closed")
	public String closed(Model model) {
		model.addAttribute("title", "You are out of the attention schedule");
		StringBuilder message = new StringBuilder("Attention closed. Plase visit us from ");
		message.append(opensAt).append(" hrs. to ").append(closesAt).append(" hrs.");
		model.addAttribute("message", message.toString());
		return "closed";
	}

}
