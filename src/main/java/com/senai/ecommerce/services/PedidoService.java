package com.senai.ecommerce.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.ecommerce.dto.ItemDoPedidoDTO;
import com.senai.ecommerce.dto.ItemDoPedidoNormalDTO;
import com.senai.ecommerce.dto.PedidoDTO;
import com.senai.ecommerce.dto.PedidoxItemsDTO;
import com.senai.ecommerce.entities.ItemDoPedido;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.entities.Produto;
import com.senai.ecommerce.entities.Usuario;
import com.senai.ecommerce.enums.StatusDoPedido;
import com.senai.ecommerce.repositories.ItemDoPedidoRepository;
import com.senai.ecommerce.repositories.PedidoRepository;
import com.senai.ecommerce.repositories.ProdutoRepository;
import com.senai.ecommerce.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ItemDoPedidoRepository itemDoPedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	
	@Transactional
	public PedidoxItemsDTO creatingPedidoWithItems(PedidoxItemsDTO dto) {
		Pedido pedido = new Pedido();
		pedido.setMomento(Instant.now());
		pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);

		Usuario usuario = usuarioRepository.getReferenceById(dto.getClientId());
		pedido.setCliente(usuario);

		for (ItemDoPedidoNormalDTO itemDto : dto.getItems()) {
			Produto produto = produtoRepository.getReferenceById(itemDto.getId_produto());
			ItemDoPedido item = new ItemDoPedido(pedido, produto, itemDto.getQuantidade(), produto.getPreco());
			pedido.getItems().add(item);
		}

		pedido = pedidoRepository.save(pedido);
		itemDoPedidoRepository.saveAll(pedido.getItems());
		return new PedidoxItemsDTO(pedido);
	}

	
//	
//	public PedidoDTO insert(PedidoDTO pedidoDTO) {
//		Pedido pedido = new Pedido();
//		pedido.setMomento(Instant.now());
//		pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);
//		Usuario usuario = usuarioRepository.getReferenceById(pedidoDTO.getClientId());
//		
//		pedido.setCliente(usuario);
//		pedido = pedidoRepository.save(pedido);
//		
//		return new PedidoDTO(pedido);
//	}
//	
//	@Transactional
//	public PedidoxItemsDTO creatingPedidoWithItems(PedidoxItemsDTO dto) {
//	    if (dto.getClientId() == null || !usuarioRepository.existsById(dto.getClientId())) {
//	        throw new IllegalArgumentException("Cliente inválido: " + dto.getClientId());
//	    }
//	    if (dto.getItems() == null || dto.getItems().isEmpty()) {
//	        throw new IllegalArgumentException("A lista de itens não pode estar vazia");
//	    }
//
//	    Pedido prod = new Pedido();
//	    prod.setMomento(Instant.now());
//	    prod.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);
//	    prod.setCliente(usuarioRepository.getReferenceById(dto.getClientId()));
//	    
//	 
//	    
//	    // Criar e salvar ItemDoPedido
//	    for (ItemDoPedidoNormalDTO cat : dto.getItems()) {
//	        if (!produtoRepository.existsById(cat.getId_produto())) {
//	            throw new IllegalArgumentException("Produto não encontrado: " + cat.getId_produto());
//	        }
//	        Produto produto = produtoRepository.getReferenceById(cat.getId_produto());
//	        ItemDoPedido entity = new ItemDoPedido(prod, produto, cat.getQuantidade(), cat.getPreco());
//	        prod.getItems().add(entity);
//	        // Salvar ItemDoPedido explicitamente
//	      
//	    }
//	    
//	    // Log para depuração
//	    System.out.println("Pedido salvo com ID: " + prod.getId());
//	    System.out.println("Número de itens: " + prod.getItems().size());
//	    
//	    itemDoPedidoRepository.saveAll(prod.getItems());
//	    
//	    // Salvar o Pedido primeiro para gerar o ID
//	    prod = pedidoRepository.save(prod);
//	    
//	    return new PedidoxItemsDTO(prod);
//	}
	
	public PedidoxItemsDTO findById(Long id) {
	    Pedido pedido = pedidoRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
	    return new PedidoxItemsDTO(pedido);
	}

	public List<PedidoxItemsDTO> findAll() {
	    return pedidoRepository.findAll().stream()
	        .map(PedidoxItemsDTO::new)
	        .collect(Collectors.toList());
	}
	
	@Transactional
	public PedidoxItemsDTO update(Long id, PedidoxItemsDTO dto) {
	    Pedido pedido = pedidoRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));

	    // Atualizar status
	    if (dto.getStatus() != null) {
	        pedido.setStatus(dto.getStatus());
	    }

	    // Atualizar cliente
	    if (dto.getClientId() != null) {
	        if (!usuarioRepository.existsById(dto.getClientId())) {
	            throw new IllegalArgumentException("Cliente inválido: " + dto.getClientId());
	        }
	        pedido.setCliente(usuarioRepository.getReferenceById(dto.getClientId()));
	    }

	    // Atualizar itens
	    pedido.getItems().clear();
	    for (ItemDoPedidoNormalDTO itemDto : dto.getItems()) {
	        if (!produtoRepository.existsById(itemDto.getId_produto())) {
	            throw new IllegalArgumentException("Produto não encontrado: " + itemDto.getId_produto());
	        }
	        Produto produto = produtoRepository.getReferenceById(itemDto.getId_produto());
	        ItemDoPedido item = new ItemDoPedido(pedido, produto, itemDto.getQuantidade(), produto.getPreco());
	        pedido.getItems().add(item);
	    }

	    pedido = pedidoRepository.save(pedido);

	
	    System.out.println("Pedido atualizado com ID: " + pedido.getId() + ", Itens: " + pedido.getItems().size());

	    return new PedidoxItemsDTO(pedido);
	}
	
	@Transactional
	public void delete(Long id) {
	    if (!pedidoRepository.existsById(id)) {
	        throw new IllegalArgumentException("Pedido não encontrado: " + id);
	    }
	    pedidoRepository.deleteById(id);
	}
	
}
