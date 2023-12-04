package com.chatapp.server.dto.request;

public record ConservationCreateRequest (
        String name,
        String type
) {
}
