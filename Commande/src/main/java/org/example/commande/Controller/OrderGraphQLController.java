package org.example.commande.Controller;


import org.example.commande.Model.Order;
import org.example.commande.Model.OrderEvent;
import org.example.commande.Model.OrderInput;
import org.example.commande.Service.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderGraphQLController {
    private final OrderService orderService;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderGraphQLController(OrderService orderService,
                                  KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.orderService = orderService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @QueryMapping
    public List<Order> orders() {
        return orderService.getAllOrders();
    }

    @MutationMapping
    public Order createOrder(@Argument OrderInput orderInput) {
        Order order = orderService.createOrder(orderInput);
        // Envoyer l'événement à Kafka
        OrderEvent event = new OrderEvent("ORDER_CREATED", order);
        kafkaTemplate.send("order-events", event);
        return order;
    }
}
