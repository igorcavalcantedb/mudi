package br.com.alura.mvc.mudi.controller.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.dto.RequisicaoNovaOfertas;
import br.com.alura.mvc.mudi.service.PedidoService;

@RestController
@RequestMapping(path = "/api/oferta")
public class OfertaRestController {
	@Autowired
	PedidoService pedidoService;
	
	@PostMapping
	public ResponseEntity<Object> publicarNovaOferta(@Valid @RequestBody RequisicaoNovaOfertas request) throws Exception {
		
		Optional<Pedido> pedidoOptional = pedidoService.obterPorID(Long.valueOf(request.getPedidoId()));
		if(!pedidoOptional.isPresent()) {
			throw new Exception("Pedido não encontrado");
		}
		
			Pedido pedido =  pedidoOptional.get();
			Oferta oferta = request.toOferta();
			oferta.setPedido(pedido);
			pedido.getOfertas().add(oferta);
			pedidoService.salvarPedido(pedido);
		
		
		return ResponseEntity.ok().build();
		
	}
	
	

}
