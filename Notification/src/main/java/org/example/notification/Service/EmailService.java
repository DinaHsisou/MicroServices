package org.example.notification.Service;

import org.example.notification.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class EmailService {
    public void sendNotificationEmail(String userId, String message) {
        // Implement email sending logic here
        // This could use JavaMailSender or an email service provider
        System.out.println("Sending email to user " + userId + ": " + message);
    }
}

