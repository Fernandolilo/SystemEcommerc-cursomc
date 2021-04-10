package com.systempro.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systempro.cursomc.domain.Cidade;
import com.systempro.cursomc.domain.repositories.CidadeRepository;

@Service
public class CidadeService {
	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findCidades(Integer estadoId){
		return repo.findCidades(estadoId);
	}

}
