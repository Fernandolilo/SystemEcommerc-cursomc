package com.systempro.cursomc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempro.cursomc.domain.Categoria;



//interfaca de acesso a dados
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
