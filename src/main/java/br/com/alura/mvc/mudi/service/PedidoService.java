package br.com.alura.mvc.mudi.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UserRepository userRepository;

	public List<Pedido> getAll() {

		return pedidoRepository.findAll();

	}

	public List<Pedido> getByUser(String userName) {
		

		return pedidoRepository.findByUser(getUserByUserName(userName));

	}

	public Pedido salvarPedido(Pedido pedido) {
		pedido.setUser(getUserLogado());
		return pedidoRepository.save(pedido);
	}

	public List<Pedido> getPorStatus(String status) {

		return pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));

	}
	public List<Pedido> getPorStatusPaginadoEOrgdenado(String status) {
		Sort sort = Sort.by("dataEntrega").ascending();
		PageRequest paginacao = PageRequest.of(0, 10,sort);

		return pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()),paginacao);

	}

	public List<Pedido> getPorUserAndStatus(String userName , String status) {

		return pedidoRepository.findByUserAndStatus(getUserByUserName(userName) , StatusPedido.valueOf(status.toUpperCase()));

	}
	
	private User getUserByUserName(String userName) {
		Optional<User> user = userRepository.findById(userName);
		return user.get();
		
	}

	private User getUserLogado() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRepository.findById(userName);
		return user.get();
		
	}
//	public List<Pedido> getAgurdando() {
//		
//		return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO);
//
//	}
//
//	public List<Pedido> getAprovado() {
//
//		return pedidoRepository.findByStatus(StatusPedido.APROVADO);
//
//	}
//
//	public List<Pedido> getEntregue() {
//		return pedidoRepository.findByStatus(StatusPedido.ENTREGUE);
//
//	}

}
