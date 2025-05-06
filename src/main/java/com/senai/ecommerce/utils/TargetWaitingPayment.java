package com.senai.ecommerce.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.enums.StatusDoPedido;
import com.senai.ecommerce.repositories.PedidoRepository;

@Component
public class TargetWaitingPayment {
//	
//	@Autowired
//    private PedidoRepository pedidoRepository;
//
//
//    @Scheduled(fixedRate = 60000)
//    public void cancelOrderByWaitingFlag() {
//        // Data limite: 3 dias atrás
//        LocalDateTime dataLimiteLocal = LocalDateTime.now().minusDays(3);
//        // Converte LocalDateTime para Instant
//        Instant dataLimite = dataLimiteLocal.atZone(ZoneId.of("UTC")).toInstant();
//
//     
//        List<Pedido> pedidosPendentes = pedidoRepository.findOrderByStatusAndDate(
//                StatusDoPedido.AGUARDANDO_PAGAMENTO.ordinal(), dataLimite);
//
//      
//        for (Pedido pedido : pedidosPendentes) {
//            pedido.setStatus(StatusDoPedido.CANCELADO);
//            pedidoRepository.save(pedido);
//            System.out.println("Pedido ID " + pedido.getId() + " cancelado após 3 dias em AGUARDANDO_PAGAMENTO.");
//        }
//    }
//}
}

