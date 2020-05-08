/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.telegram.client.impl.javabot;

import com.blazartech.products.telegram.client.BTTelegramClient;
import com.blazartech.products.telegram.client.Message;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement the telegram client using the java telegram bot package
 * (https://github.com/pengrad/java-telegram-bot-api).
 * 
 * @author scott
 */
@Service
public class BTTelegramClientJavaBotImpl implements BTTelegramClient {

    private static final Logger logger = LoggerFactory.getLogger(BTTelegramClientJavaBotImpl.class);
    
    @Autowired
    private TelegramBot bot;
    
    @Override
    public void sendMessage(Message message) {
        logger.info("sending message to telegram");
        
        SendMessage request = new SendMessage(message.getChatID(), message.getText());
        if (message.isHtml()) {
            request = request.parseMode(ParseMode.HTML);
        }
        
        SendResponse sendResponse = bot.execute(request);
        boolean ok = sendResponse.isOk();
        if (!ok) {
            logger.error("error sending message: " + sendResponse.description());
            throw new RuntimeException("error sending message: " + sendResponse.description());
        }
        logger.info("response: " + sendResponse.message().toString());
    }
    
}
