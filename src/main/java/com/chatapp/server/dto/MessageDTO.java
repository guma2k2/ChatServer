package com.chatapp.server.dto;

import com.chatapp.server.enums.MessageType;




public record MessageDTO(String sender, String message, MessageType type) {
}
