package com.senai.ecommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.ecommerce.entities.Pagamento;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.repositories.PagamentoRepository;
import com.senai.ecommerce.repositories.PedidoRepository;
import com.senai.ecommerce.utils.AssignPayment;

import jakarta.transaction.Transactional;

@Service
public class PagamentoService {
   @Autowired
   private PedidoRepository pedidoRepository;
   
   @Autowired
   private PagamentoRepository pagamentoRepository;
   
   @Autowired 
   private AssignPayment assignPayment;
	
   @Transactional
	public Pagamento MakePayment(Long id) {
		
		 Pagamento payment = new Pagamento();
		 Boolean pedidoOK =  assignPayment.changeOrderStatusPayment(id);
		 Pedido pedido = pedidoRepository.findById(id)
				    .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
		 
		
		 if (pedidoOK) {
			 payment.setMomento(Instant.now());
			 payment.setPedido(pedido);
			 pagamentoRepository.save(payment);
			 return payment;
		 }
		 else {
			 throw new IllegalStateException("Não é possível marcar como pago: o pedido está pago ou cancelado.");
		 }
	
	}

}
