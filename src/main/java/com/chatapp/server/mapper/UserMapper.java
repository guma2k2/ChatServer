package com.chatapp.server.mapper;

import com.chatapp.server.dto.UserDTO;
import com.chatapp.server.dto.response.ConservationResponse;
import com.chatapp.server.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserDTO convertEntityToDto(User user, List<ConservationResponse> conservations) {
        return new UserDTO(user.getId(), user.getUserName(), user.getEmail(), user.getPassword(), user.getImagePath(),conservations);
    }
}
