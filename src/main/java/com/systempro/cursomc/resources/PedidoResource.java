package com.systempro.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systempro.cursomc.domain.Pedido;
import com.systempro.cursomc.services.PedidoService;

// class de controle REST
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Pedido>> find(@PathVariable Integer id) {
		Optional<Pedido> obj = Optional.of(service.find(id));
		return ResponseEntity.ok().body(obj);

	}

}
