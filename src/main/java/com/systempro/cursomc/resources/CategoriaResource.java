package com.systempro.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systempro.cursomc.domain.Categoria;
import com.systempro.cursomc.services.CategoriaService;

// class de controle REST
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource { 
	
	@Autowired
	private CategoriaService service;
 
	@RequestMapping(value= "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Optional<Categoria>> finid(@PathVariable Integer id) {
		Optional<Categoria> obj = service.find(id);		
		return ResponseEntity.ok().body(obj);
		
	}

}
