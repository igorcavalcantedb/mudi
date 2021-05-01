package br.com.alura.mvc.mudi.controller.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.model.dto.RequisicaoNovaOfertas;
import br.com.alura.mvc.mudi.service.PedidoService;

@RestController
@RequestMapping(path = "/api/pedidos")
public class PedidosRestController {
	
	@Autowired
	PedidoService pedidoService;
	@GetMapping("aguardando")
	public Page<Pedido> obterListaPedidosAguardando(@RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
		return pedidoService.getPorStatusPaginadoEOrgdenado(StatusPedido.AGUARDANDO.toString(), currentPage, pageSize);
		
	}
	
	

}
