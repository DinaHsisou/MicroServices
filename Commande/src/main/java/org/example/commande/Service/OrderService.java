package org.example.commande.Service;

import org.example.commande.Model.Order;
import org.example.commande.Model.OrderInput;
import org.example.commande.Model.OrderStatus;
import org.example.commande.Repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(OrderInput input) {
        Order order = new Order();
        order.setCustomerName(input.getCustomerName());
        order.setStatus(OrderStatus.CREATED);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

