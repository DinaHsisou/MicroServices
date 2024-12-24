package org.example.notification.Service;

import org.example.commande.Model.OrderEvent;
import org.example.notification.Model.NotificationType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class KafkaNotificationListener {
    private final NotificationService notificationService;

    public KafkaNotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void handleOrderEvent(OrderEvent event) {
        switch(event.getEventType()) {
            case "ORDER_CREATED":
                notificationService.createNotification(
                        event.getOrder().getCustomerId(),
                        "Votre commande #" + event.getOrder().getId() + " a été créée",
                        NotificationType.ORDER_CREATED
                );
                break;
            case "ORDER_UPDATED":
                notificationService.createNotification(
                        event.getOrder().getCustomerId(),
                        "Votre commande #" + event.getOrder().getId() + " a été mise à jour",
                        NotificationType.ORDER_UPDATED
                );
                break;
        }
    }

    @KafkaListener(topics = "product-events", groupId = "notification-group")
    public <ProductEvent> void handleProductEvent(ProductEvent event) {
        // Handle product related notifications
        if ("PRODUCT_UPDATED".equals(event.getClass())) {
            // Notify interested users about product updates
            // This could be based on user preferences or watch lists
        }
    }
}
