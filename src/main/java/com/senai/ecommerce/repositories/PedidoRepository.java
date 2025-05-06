package com.senai.ecommerce.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.enums.StatusDoPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query(value = "SELECT * FROM tb_pedido p WHERE p.status = :status AND p.momento < :data", nativeQuery = true)
    List<Pedido> findOrderByStatusAndDate(@Param("status") int status, @Param("data") Instant data);
}
