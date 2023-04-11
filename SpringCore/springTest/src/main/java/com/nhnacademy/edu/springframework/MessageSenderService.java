package com.nhnacademy.edu.springframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("messageSenderService")
public class MessageSenderService {

//    private final MessageSender messageSender;
    @Value("${name}")
    private String name;

    private final MessageSender messageSender;

    @Autowired
    public MessageSenderService(SmsMessageSender smsMessageSender) {
        this.messageSender = smsMessageSender;
    }

//    @Autowired
//    public MessageSenderService(@SMS("smsMessageSender") MessageSender messageSender) {
//        this.messageSender = messageSender;
//    }

//    public MessageSenderService(MessageSender messageSender) {
//        this.messageSender = messageSender;
//    }

//    public void setEmailMessageSender(MessageSender messageSender) {
//        System.out.println("setEmailMessageSender invoked!");
//        this.messageSender = messageSender;
//    }
//
//    public void setSmsMessageSender(MessageSender messageSender) {
//        System.out.println("setSmsMessageSender invoked!");
//        this.messageSender = messageSender;
//    }

//    public void setMessageSender(MessageSender messageSender) {
//        System.out.println("setMessageSender invoked!");
//        this.messageSender = messageSender;
//    }

    public void doSendMessage() {
        User user = new User("kusun1020@gmail.com", "010-2310-2927");
        messageSender.sendMessage(user, "This is SMS Message from : " + name);
    }

}
