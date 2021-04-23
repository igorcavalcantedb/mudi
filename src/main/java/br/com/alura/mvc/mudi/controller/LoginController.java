package br.com.alura.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.service.PedidoService;

@Controller

public class LoginController {
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping(path = "/login")
	public String login() {
		return "login";
	}
	@GetMapping(path = "/")
	public String home(Model model) {
		
		String nome = SecurityContextHolder.getContext().getAuthentication().getName();    
		List<Pedido> pedidos = pedidoService.getPorStatusPaginadoEOrgdenado("ENTREGUE");
		
		model.addAttribute("username", nome);
		model.addAttribute("pedidos", pedidos);
		return "pedidos";
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "oi";
	}

}
