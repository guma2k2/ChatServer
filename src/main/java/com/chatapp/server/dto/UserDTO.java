package com.chatapp.server.dto;

import com.chatapp.server.dto.response.ConservationResponse;

import java.util.List;

public record UserDTO (
        Long id,
        String userName,
        String email,
        String password,
        String imagePath,
        List<ConservationResponse> conservations
) {
}
