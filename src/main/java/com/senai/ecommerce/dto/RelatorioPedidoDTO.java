package com.senai.ecommerce.dto;

import java.time.format.DateTimeFormatter;

import com.senai.ecommerce.entities.Pedido;

public class RelatorioPedidoDTO {
	
	private Long pedido;
	private String cliente;
	private String status;
	private String momento;
	/**
	 * @param pedido
	 * @param cliente
	 * @param status
	 * @param momento
	 */
	public RelatorioPedidoDTO(Long pedido, String cliente, String status, String momento) {
		super();
		this.pedido = pedido;
		this.cliente = cliente;
		this.status = status;
		this.momento = momento;
	}
	
	public RelatorioPedidoDTO(Pedido entity) {// construtor para converter o pedido em DTO
		this.pedido = entity.getId();// para pegar o id do pedido
		this.cliente = entity.getCliente().getNome();// para pegar o nome do cliente
		this.status = entity.getStatus().toString();// para converter o enum para string
		this.momento = entity.getMomento().atZone(java.time.ZoneId.systemDefault()) // para converter para o fuso horário do sistema
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));// para formatar a data
		// TODO Auto-generated constructor stub
	}

	public Long getPedido() {
		return pedido;
	}

	public String getCliente() {
		return cliente;
	}

	public String getStatus() {
		return status;
	}

	public String getMomento() {
		return momento;
	}

	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMomento(String momento) {
		this.momento = momento;
	}
	
	
	
	
}
