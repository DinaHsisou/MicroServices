package org.example.commande.Model;

import lombok.Data;

import java.math.BigDecimal;

@Data
class OrderItemInput {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
}
