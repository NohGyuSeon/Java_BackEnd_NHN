package com.nhnacademy.edu.springframework;

import org.springframework.stereotype.Component;

@Component
public class EmailMessageSender implements MessageSender {

    public EmailMessageSender() {
        System.out.println("EmailMessageSender.EmailMessageSender");
    }

    public void init() {
        System.out.println("EmailMessageSender.init");
    }

    public void close() {
        System.out.println("EmailMessageSender.cleanUp");
    }

    public void destroy() {
        System.out.println("EmailMessageSender.destroy");
    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getEmail() + " : " + message);
    }

}
