package com.nhnacademy.edu.springframework;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework")
@PropertySource("classpath:name.properties")
public class MainConfig {

//    @Bean
//    public MessageSender smsMessageSender() {
//        return new SmsMessageSender();
//    }

//    @Bean(initMethod = "init")
//    public MessageSender emailMessageSender() {
//        return new EmailMessageSender();
//    }

//    @Bean
//    public MessageSenderService messageSenderService(@Qualifier("emailMessageSender") MessageSender messageSender) {
//        return new MessageSenderService(messageSender);
//    }

//    @Bean
//    public MessageSenderService messageSenderService() {
//        return new MessageSenderService(emailMessageSender());
//    }

}
