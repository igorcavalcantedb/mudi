package br.com.alura.mvc.mudi.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String home(Model model, @RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
		
		String nome = SecurityContextHolder.getContext().getAuthentication().getName();    
		Page<Pedido> pedidos = pedidoService.getPorStatusPaginadoEOrgdenado(StatusPedido.ENTREGUE.toString(), currentPage, pageSize);
		
		model.addAttribute("username", nome);
		model.addAttribute("pedidos", pedidos);
		
		 int totalPages = pedidos.getTotalPages();
		 if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
		
		return "home";
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "oi";
	}

}
