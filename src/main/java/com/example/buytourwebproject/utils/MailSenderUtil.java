//package com.example.buytourwebproject.utils;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class MailSenderUtil {
//
//    private final JavaMailSender javaMailSender;
//
//    public void sendEmail(String to, String subject, String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(content);
//        javaMailSender.send(message);
//    }
//
//}
