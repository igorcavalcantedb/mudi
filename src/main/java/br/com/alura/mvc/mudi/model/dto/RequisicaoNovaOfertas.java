package br.com.alura.mvc.mudi.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.alura.mvc.mudi.model.Oferta;

public class RequisicaoNovaOfertas {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
	private String id;
	private String pedidoId;
	@NotBlank
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
	private String valor;
	@NotBlank
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	private String dataEntrega;
	private String observacao;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(String pedidoId) {
		this.pedidoId = pedidoId;
	}
	
	public Oferta toOferta() {
		Oferta oferta = new Oferta();
		oferta.setComentario((observacao == null)? "sem comentário" : observacao);
		oferta.setDataEntrega( dataEntrega == null?LocalDate.parse("01/01/2000", formatter) : LocalDate.parse(dataEntrega, formatter) );
		oferta.setValor(valor == null ? BigDecimal.ZERO : new BigDecimal(valor));
		return oferta;
		
	}

}
