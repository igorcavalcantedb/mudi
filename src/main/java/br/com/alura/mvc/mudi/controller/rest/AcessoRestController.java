package br.com.alura.mvc.mudi.controller.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.model.Acesso;
import br.com.alura.mvc.mudi.service.AcessoService;

@RestController
@RequestMapping(path = "/api/acessos")
public class AcessoRestController {
	@Autowired
	AcessoService acessoService;
	
	@GetMapping("todos")
	public Page<Acesso> obterTodosAcessos(@RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
		return acessoService.obterTodosAcessos(currentPage, pageSize);
		
	}

}
