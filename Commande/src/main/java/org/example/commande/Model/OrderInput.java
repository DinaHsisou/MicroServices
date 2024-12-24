package org.example.commande.Model;

import lombok.Data;

import java.util.List;

@Data
public class OrderInput {
    private String customerName;
    private List<OrderItemInput> items;
}
