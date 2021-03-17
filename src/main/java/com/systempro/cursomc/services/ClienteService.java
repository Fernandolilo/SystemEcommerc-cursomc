package com.systempro.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systempro.cursomc.domain.Cliente;
import com.systempro.cursomc.domain.repositories.ClienteRepository;
import com.systempro.cursomc.services.excepitions.ObjectNotFoundException;



//implementa o tratamento do serviço
@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) { 
		 Optional<Cliente> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
		} 


}
