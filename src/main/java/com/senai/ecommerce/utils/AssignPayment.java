package com.senai.ecommerce.utils;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senai.ecommerce.dto.PedidoxItemsDTO;
import com.senai.ecommerce.entities.Pagamento;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.enums.StatusDoPedido;
import com.senai.ecommerce.repositories.PedidoRepository;

import jakarta.transaction.Transactional;

@Component
public class AssignPayment {
	
	 @Autowired
	   private PedidoRepository pedidoRepository;
	 
	
	public Boolean changeOrderStatusPayment(Long id) {
		Boolean retorno = false;
	    Pedido pedido = pedidoRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
	    
	   

	    if (pedido.getStatus() != StatusDoPedido.AGUARDANDO_PAGAMENTO) {
	    	System.out.print(pedido.getStatus().toString());
	        // Se o pedido já estiver pago ou cancelado, não é possível marcar como pago novamente
	        // Retorna false ou lança uma exceção, dependendo da lógica desejada
	        // Aqui, lançamos uma exceção
	    	retorno = false;
	        throw new IllegalStateException("Não é possível marcar como pago: o pedido está pago ou cancelado.");
	    }

	    pedido.setStatus(StatusDoPedido.PAGO);
	    pedido.setMomento(Instant.now());
	    pedido = pedidoRepository.save(pedido);
	    
	    retorno = true;
	    

	    return  retorno ;
	}
}
