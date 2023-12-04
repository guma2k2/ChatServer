package com.chatapp.server.dto.response;

public record MessageResponse(
        Long id,
        String messageText,
        String createdAt,
        String sender
) {
}
