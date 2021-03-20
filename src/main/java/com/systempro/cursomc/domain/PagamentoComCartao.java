package com.systempro.cursomc.domain;

import javax.persistence.Entity;

import com.systempro.cursomc.domain.enums.EstadoPagamento;


@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao () {
	}

	public PagamentoComCartao(Integer id, EstadoPagamento pagamento, Pedido pedido,
			Integer numeroDeParcelas) {
		super(id, pagamento, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	
	
}
