package br.com.alura.mvc.mudi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.service.PedidoService;

@Controller
@RequestMapping("/oferta")
public class OfertaController {
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public String obterOfertas(Model model) {
		
		return "ofertas";
		
	}
	
	

}
