package com.nhnacademy.edu.springframework;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework");

        MessageSenderService messageSenderService = context.getBean("messageSenderService", MessageSenderService.class);
        messageSenderService.doSendMessage();

        context.close();

//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            BeanDefinition beanDefinition = context.getBeanDefinition(beanDefinitionName);
//
//            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
//                System.out.println("beanDefinitionName = " + beanDefinitionName +
//                    " beanDefinition = " + beanDefinition);
//            }
//        }

//        SmsMessageSender smsMessageSender = new SmsMessageSender();
//        MessageSenderService messageSenderService = new MessageSenderService(smsMessageSender);
//        messageSenderService.doSendMessage();

/*        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            MessageSenderService messageSenderService = context.getBean("messageSenderService", MessageSenderService.class);

            messageSenderService.doSendMessage();*/

/*            User user = new User("kusun1020@gmail.com", "010-2310-2927");

            MessageSender smsMessageSender = context.getBean("smsMessageSender", MessageSender.class);
            System.out.println("---------");
            MessageSender smsMessageSender1 = context.getBean("smsMessageSender", MessageSender.class);
            System.out.println("---------");
            MessageSender emailMessageSender = context.getBean("emailMessageSender", MessageSender.class);
            System.out.println("---------");
            MessageSender emailMessageSender2 = context.getBean("emailMessageSender", MessageSender.class);
            System.out.println("---------");*/

//            smsMessageSender.sendMessage(user, "안뇽!");
//            emailMessageSender.sendMessage(user, "안뇽?");

//        }
    }
}
