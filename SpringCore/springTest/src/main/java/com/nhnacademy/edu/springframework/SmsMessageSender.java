package com.nhnacademy.edu.springframework;


import org.springframework.stereotype.Component;

@Component
public class SmsMessageSender implements MessageSender {

    public SmsMessageSender() {
        System.out.println("SmsMessageSender.SmsMessageSender");
    }

    public void init() {
        System.out.println("SmsMessageSender.init");
    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + message);
    }

}
