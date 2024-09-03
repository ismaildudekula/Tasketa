package com.tasketa.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmailWithToken(String email, String link) throws MessagingException;
}
