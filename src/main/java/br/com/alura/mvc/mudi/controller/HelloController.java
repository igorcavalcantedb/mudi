package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/hello") 
	public String oi(Model model) {
		model.addAttribute("nome", "Maria DB");
		return "oi";
		
	}

}
