package br.com.alura.mvc.mudi.controller.old;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alura.mvc.mudi.model.Pedido;

@Controller
public class PedidoControllerAntigo {
	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping(path = "/pedidosAntigo")
	public String obterPedidosAntigos(Model model) {
		TypedQuery<Pedido> query = entityManager.createQuery("select p from Pedido p", Pedido.class);
		List<Pedido> pedidos = query.getResultList();
		model.addAttribute("pedidos", pedidos );
		return "pedidos";
	}

}
