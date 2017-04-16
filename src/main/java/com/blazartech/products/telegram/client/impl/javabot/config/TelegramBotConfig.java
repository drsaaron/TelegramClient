/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.telegram.client.impl.javabot.config;

import com.blazartech.products.crypto.BlazarCryptoFile;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author scott
 */
@Configuration
public class TelegramBotConfig {
    
    private static final Logger logger = Logger.getLogger(TelegramBotConfig.class);
    
    @Value("${blazartech.telegram.client.userID}")
    private String userID; 
    
    @Value("${blazartech.telegram.client.resourceID}")
    private String resourceID; 
    
    @Autowired
    private BlazarCryptoFile cryptoFile;
     
    @Bean
    public TelegramBot getTelegramBot() {
        logger.info("configuring telegram client");
        
        String token = cryptoFile.getPassword(userID, resourceID);
        return TelegramBotAdapter.build(token);
    }
}
