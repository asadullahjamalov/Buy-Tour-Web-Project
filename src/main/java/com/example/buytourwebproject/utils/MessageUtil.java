package com.example.buytourwebproject.utils;

import com.example.buytourwebproject.models.Agent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MessageUtil {

    @Value("${mail.registration.success.subject}")
    private String successMessageSubject;

    @Value("${mail.registration.success.message}")
    private String successMessageText;

    @Value("${mail.registration.confirm.subject}")
    private String registrationVerifySubject;

    @Value("${mail.registration.confirm.message}")
    private String registrationVerifyText;


    final
    MailSenderUtil mailSenderUtil;

    public MessageUtil(MailSenderUtil mailSenderUtil) {
        this.mailSenderUtil = mailSenderUtil;
    }


    public void regVerifyNotification(Agent agent, String token) {
        mailSenderUtil.sendEmail(agent.getEmail(), registrationVerifySubject,
                String.format(registrationVerifyText, token));
    }

    public void successRegister(Agent agent) {
        mailSenderUtil.sendEmail(agent.getEmail(), successMessageSubject, successMessageText);
    }
}
