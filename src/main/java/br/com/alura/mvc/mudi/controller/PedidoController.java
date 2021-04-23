package br.com.alura.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.dto.CadastroDto;
import br.com.alura.mvc.mudi.service.PedidoService;

@Controller
@RequestMapping(path = "pedido")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	
	private String obterUserNameLogado() {
		
//		*** FORMA MAIS VERBOSA  ***
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		String nome;    
//
//		if (principal instanceof UserDetails) {
//		    nome = ((UserDetails)principal).getUsername();
//		} else {
//		    nome = principal.toString();
//		}
//		return nome;
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
		
	}

	@GetMapping(path = "/pedidos")
	public String listAll(Model model) {
		
		
		List<Pedido> pedidos = pedidoService.getByUser(obterUserNameLogado());
		model.addAttribute("pedidos", pedidos);
		return "pedidos";
	}
	
	@GetMapping(path = "/novo")
	public String novo(CadastroDto cadastroDto) {
		
		return "pedido/novo";
	}
	@PostMapping(path = "/novo")
	public String cadastro(@Validated CadastroDto cadastroDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "pedido/novo";
		}
		Pedido pedido = cadastroDto.toPedido();
		pedidoService.salvarPedido(pedido);
		return "redirect:/pedido/pedidos";
		
	}
	
	@GetMapping(path = "/{status}")
	public String porStatus(@PathVariable("status") String status,Model model) {
		List<Pedido> pedidos = pedidoService.getPorUserAndStatus(obterUserNameLogado(), status);
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "pedidos";
	}
	/*
	 * @GetMapping(path = "/aprovado") public String aprovado(Model model) {
	 * List<Pedido> pedidos = pedidoService.getAprovado();
	 * model.addAttribute("pedidos", pedidos); return "pedidos"; }
	 * 
	 * @GetMapping(path = "/entregue") public String entregue(Model model) {
	 * List<Pedido> pedidos = pedidoService.getEntregue();
	 * model.addAttribute("pedidos", pedidos); return "pedidos"; }
	 */
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/";
	}

}
