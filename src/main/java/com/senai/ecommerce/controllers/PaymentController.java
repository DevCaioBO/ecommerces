package com.senai.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.dto.PedidoxItemsDTO;
import com.senai.ecommerce.entities.Pagamento;
import com.senai.ecommerce.services.PagamentoService;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
		
	@Autowired
	private PagamentoService pagamentoService;
	 
		@PostMapping("/{id}")
		public ResponseEntity<Pagamento> pagar(@PathVariable Long id) {
		    Pagamento dto = pagamentoService.MakePayment(id);
		    return ResponseEntity.ok(dto);
		}
}
