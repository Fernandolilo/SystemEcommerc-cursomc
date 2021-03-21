package com.systempro.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.systempro.cursomc.domain.Categoria;
import com.systempro.cursomc.domain.dto.CategoriaDTO;
import com.systempro.cursomc.services.CategoriaService;

// class de controle REST
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Categoria>> find(@PathVariable Integer id) {
		Optional<Categoria> obj = Optional.of(service.find(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
		Categoria obj = service.fromDTO(objDto);		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updata(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
		Categoria obj =  service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Optional<Void>> delete(@PathVariable Integer id) {	
		service.delete(id);
		return ResponseEntity.noContent().build();		
	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> lista =service.findAll();
		List<CategoriaDTO> listaDTO =lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue ="0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue ="24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue ="nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue ="ASC") String direction) {
		Page<Categoria> lista =service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listaDTO =lista.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listaDTO);
	}

}

















