package com.systempro.cursomc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempro.cursomc.domain.Cliente;



//interfaca de acesso a dados
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
