package com.chatapp.server.dto.response;

public record ConservationResponse(
        Long id,
        String name,
        String imagePath,
        MessageResponse message
) {
}
