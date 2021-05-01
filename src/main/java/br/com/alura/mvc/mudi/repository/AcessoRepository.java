package br.com.alura.mvc.mudi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Acesso;
@Repository
public interface AcessoRepository extends PagingAndSortingRepository<Acesso, Long>{

	


}
