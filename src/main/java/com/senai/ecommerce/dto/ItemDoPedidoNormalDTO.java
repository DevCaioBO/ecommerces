package com.senai.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.senai.ecommerce.entities.ItemDoPedido;

public class ItemDoPedidoNormalDTO {
	private Long id_produto;
	private String nome;
	private String imgUrl;
	private Integer quantidade;
	private Double preco;
	
	public Long getId_produto() {
		return id_produto;
	}

	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ItemDoPedidoNormalDTO() {
		
	}
	

	public ItemDoPedidoNormalDTO(Long id_produto, String nome, String imgUrl, Integer quantidade, Double preco) {
		this.id_produto = id_produto;
		this.nome = nome;
		this.imgUrl = imgUrl;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public ItemDoPedidoNormalDTO(ItemDoPedido entity) {

		quantidade = entity.getQuantidade();
		preco = entity.getPreco();		
		id_produto = entity.getProduto().getId();
		nome = entity.getProduto().getNome();
		imgUrl = entity.getProduto().getImgUrl();
	
	}
	
}
