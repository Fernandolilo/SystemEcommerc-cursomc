package com.systempro.cursomc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempro.cursomc.domain.Produto;

//interfaca de acesso a dados
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
