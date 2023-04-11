/*
package com.nhnacademy.edu.springframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicatioinConfig {

    private MainConfig mainConfig;

    @Autowired
    public ApplicatioinConfig(MainConfig mainConfig) {
        this.mainConfig = mainConfig;
    }

    @Bean
    public MessageSenderService messageSenderService() {
        return new MessageSenderService(mainConfig.emailMessageSender());
    }

}
*/
