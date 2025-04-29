package com.senai.ecommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.senai.ecommerce.entities.ItemDoPedido;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.enums.StatusDoPedido;

public class PedidoxItemsDTO {
	private Long id;
	private Instant momento;
	private StatusDoPedido status;
	private Long clientId;
	
	private List<ItemDoPedidoNormalDTO> items =  new ArrayList<>();;
	
	public PedidoxItemsDTO() {
		super();
	}
	public PedidoxItemsDTO(Long id, Instant momento, StatusDoPedido status, Long clientId) {
		super();
		this.id = id;
		this.momento = momento;
		this.status = status;
		this.clientId = clientId;
	}
	
	public PedidoxItemsDTO(Pedido entity) {
		super();
		id =entity.getId();
		momento = entity.getMomento();
		status = entity.getStatus();
		clientId =entity.getCliente().getId();
		for (ItemDoPedido cat : entity.getItems()) {
			items.add(new ItemDoPedidoNormalDTO(cat));
		}
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getMomento() {
		return momento;
	}
	public void setMomento(Instant momento) {
		this.momento = momento;
	}
	public StatusDoPedido getStatus() {
		return status;
	}
	public void setStatus(StatusDoPedido status) {
		this.status = status;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public List<ItemDoPedidoNormalDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemDoPedidoNormalDTO> items) {
		this.items = items;
	}

	
	
	
}
