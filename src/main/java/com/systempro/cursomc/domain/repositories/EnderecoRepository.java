package com.systempro.cursomc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempro.cursomc.domain.Endereco;



//interfaca de acesso a dados
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
