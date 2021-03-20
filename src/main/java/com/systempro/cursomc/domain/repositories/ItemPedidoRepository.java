package com.systempro.cursomc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempro.cursomc.domain.ItemPedido;



//interfaca de acesso a dados
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
