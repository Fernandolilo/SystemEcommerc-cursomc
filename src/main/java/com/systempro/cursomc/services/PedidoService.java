package com.systempro.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systempro.cursomc.domain.ItemPedido;
import com.systempro.cursomc.domain.PagamentoComBoleto;
import com.systempro.cursomc.domain.Pedido;
import com.systempro.cursomc.domain.enums.EstadoPagamento;
import com.systempro.cursomc.domain.repositories.ItemPedidoRepository;
import com.systempro.cursomc.domain.repositories.PagamentoRepository;
import com.systempro.cursomc.domain.repositories.PedidoRepository;
import com.systempro.cursomc.domain.repositories.ProdutoRepository;
import com.systempro.cursomc.services.excepitions.ObjectNotFoundException;

//implementa o tratamento do serviço
@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setPagamento(EstadoPagamento.PENDETE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoCOmBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findById(ip.getProduto().getId()).get().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

}
