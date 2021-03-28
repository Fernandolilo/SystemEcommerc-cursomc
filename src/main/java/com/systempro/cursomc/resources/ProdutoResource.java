package com.systempro.cursomc.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.systempro.cursomc.domain.Produto;
import com.systempro.cursomc.domain.dto.ProdutoDTO;
import com.systempro.cursomc.resources.utils.URL;
import com.systempro.cursomc.services.ProdutoService;

// class de controle REST
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Produto>> find(@PathVariable Integer id) {
		Optional<Produto> obj = Optional.of(service.find(id));
		return ResponseEntity.ok().body(obj);

	}
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue ="") String nome, 
			@RequestParam(value = "categorias", defaultValue ="") String categorias, 
			@RequestParam(value = "page", defaultValue ="0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue ="24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue ="nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue ="ASC") String direction) {
		String nomeDecode = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeInt(categorias);
		Page<Produto> lista =service.search(nomeDecode,ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listaDTO =lista.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listaDTO);
	} 

}
