package com.senai.ecommerce.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.ecommerce.dto.RelatorioPedidoDTO;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.repositories.PedidoRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void gerarRelatorio(String caminho) throws JRException {
		// TODO Auto-generated method stub
		
		List<Pedido> pedidos = pedidoRepository.findAll();  // busca todos os pedidos no banco de dados
		
		
			 		 			 			  								
		
		
		 List<RelatorioPedidoDTO> escanor = pedidos.stream() // converte a lista de pedidos em uma lista de DTOs
		 .map(RelatorioPedidoDTO::new) // converte cada pedido em um DTO
		 .collect(Collectors.toList()); // converte a lista de pedidos em uma lista de DTOs
		 
		 JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(escanor); // cria a fonte de dados para o relatório
		 Map<String, Object> parameters = new HashMap<>();
		 parameters.put("titulo", "Relatório de Pedidos"); // adiciona o título do relatório aos parâmetros
		 
		 JasperReport javarReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/relatorio_pedidos.jrxml")); // compila o relatório
		 
		 JasperPrint jasonPrint = JasperFillManager.fillReport(javarReport, parameters, dataSource); // preenche o relatório com os dados
		 
		 JasperExportManager.exportReportToPdfFile(jasonPrint, caminho); // exporta o relatório para um arquivo PDF
		 
	}
}
