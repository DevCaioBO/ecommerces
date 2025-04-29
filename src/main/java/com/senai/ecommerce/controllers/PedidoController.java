package com.senai.ecommerce.controllers;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.dto.ItemDoPedidoNormalDTO;
import com.senai.ecommerce.dto.PedidoDTO;
import com.senai.ecommerce.dto.PedidoxItemsDTO;
import com.senai.ecommerce.dto.ProdutoDTO;
import com.senai.ecommerce.entities.ItemDoPedido;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.enums.StatusDoPedido;
import com.senai.ecommerce.services.PedidoService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("pedidos")
public class PedidoController {
	
	
	
	@Autowired
	private PedidoService pedidoService;
	
//	@PostMapping("/creating")
//	public ResponseEntity<PedidoDTO> insert(@RequestBody PedidoDTO pedidoDTO){
//		
//		pedidoDTO = pedidoService.insert(pedidoDTO);
//		return ResponseEntity.ok(pedidoDTO);
//		
//	}
	

	@PostMapping("create")
	public ResponseEntity<PedidoxItemsDTO> creating(@RequestBody PedidoxItemsDTO dto){
		dto = pedidoService.creatingPedidoWithItems(dto);
		return ResponseEntity.ok(dto);
	}
}
