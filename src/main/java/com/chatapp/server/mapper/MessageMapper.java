package com.chatapp.server.mapper;

import com.chatapp.server.Utility.DateUtility;
import com.chatapp.server.dto.response.MessageResponse;
import com.chatapp.server.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageResponse convertEntityToResponse (Message message) {
        String createdAt = DateUtility.convertDateToString(message.getCreatedAt());
        String sender = message.getUser().getUserName();
        return new MessageResponse(message.getId(), message.getMessageText(), createdAt, sender);
    }
}
