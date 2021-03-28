package com.systempro.cursomc.domain.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.systempro.cursomc.domain.Categoria;
import com.systempro.cursomc.domain.Produto;

//interfaca de acesso a dados
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	// duas formas de fazer uma busca relacionando tabelas, uma usando o jpql e outra usando o Spring
	
	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	//Page<Produto>search(@Param("nome") String nome, @Param("categorias")List<Categoria> categorias, Pageable pageRequest);
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto>findDistinctByNomeContainingAndCategoriasIn( String nome, List<Categoria> categorias, Pageable pageRequest);
}
