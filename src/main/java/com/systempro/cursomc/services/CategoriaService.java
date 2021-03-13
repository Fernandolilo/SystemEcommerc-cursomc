package com.systempro.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systempro.cursomc.domain.Categoria;
import com.systempro.cursomc.domain.repositories.CategoriaRepository;


//implementa o tratamento do serviço
@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Optional<Categoria> find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj;
	}

}
