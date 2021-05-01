package br.com.alura.mvc.mudi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.model.Acesso;
import br.com.alura.mvc.mudi.repository.AcessoRepository;
@Service
public class AcessoService {
	@Autowired
	AcessoRepository acessoRepository;

	public Page<Acesso> obterTodosAcessos(int currentPage, int pageSize) {
		Sort sort = Sort.by("data").ascending();
		PageRequest paginacao = PageRequest.of(currentPage - 1, pageSize, sort);
		return acessoRepository.findAll(paginacao);

	}

}
