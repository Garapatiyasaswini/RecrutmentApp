package com.recruitment.recruitment_app.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendEmail(String to, String subject, String body) {
        // Email sending logic
        System.out.println("Sending Email to " + to + ": " + subject + " - " + body);
    }

    public void sendSMS(String phoneNumber, String message) {
        // SMS sending logic
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }

    public void sendWhatsApp(String phoneNumber, String message) {
        // WhatsApp sending logic
        System.out.println("Sending WhatsApp message to " + phoneNumber + ": " + message);
    }
}
