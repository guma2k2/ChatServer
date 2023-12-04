package com.chatapp.server.mapper;

import com.chatapp.server.dto.response.ConservationResponse;
import com.chatapp.server.dto.response.MessageResponse;
import com.chatapp.server.entity.Conservation;
import org.springframework.stereotype.Component;

@Component
public class ConservationMapper {
    public ConservationResponse convertEntityToResponse(Conservation conservation, MessageResponse messageResponse) {
        return new ConservationResponse(conservation.getId(), conservation.getName(), conservation.getImagePath(), messageResponse);
    }
}
