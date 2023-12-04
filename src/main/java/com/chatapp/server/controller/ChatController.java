package com.chatapp.server.controller;


import com.chatapp.server.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@Slf4j
public class ChatController {

    @MessageMapping("/chat/{conservationId}/sendMessage")
    @SendTo("/topic/conservation/{conservationId}")
    public MessageDTO sendMessage(@Payload MessageDTO chatMessage) {
        log.info(chatMessage.sender());
        log.info(chatMessage.message());
        return chatMessage ;
    }


    @MessageMapping("/chat/{conservationId}/add")
    @SendTo("/topic/conservation/{conservationId}")
    public MessageDTO addUser(
            @Payload MessageDTO chatMessage,
            SimpMessageHeaderAccessor headerAccessor,
            @DestinationVariable("conservationId") Long conservationId
    ) {
        // Add username in web socket session
        log.info(chatMessage.sender() + " joined");
        log.info(String.valueOf(conservationId));
        headerAccessor.getSessionAttributes().put("username", chatMessage.sender());
        headerAccessor.getSessionAttributes().put("conservationId", conservationId);
        return chatMessage;
    }
}
