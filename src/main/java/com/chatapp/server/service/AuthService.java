package com.chatapp.server.service;


import com.chatapp.server.dto.UserDTO;
import com.chatapp.server.dto.request.LoginRequest;
import com.chatapp.server.dto.request.RegistrationRequest;
import com.chatapp.server.dto.response.ConservationResponse;
import com.chatapp.server.dto.response.MessageResponse;
import com.chatapp.server.entity.Conservation;
import com.chatapp.server.entity.ConservationUser;
import com.chatapp.server.entity.Message;
import com.chatapp.server.entity.User;
import com.chatapp.server.exception.DuplicationResourceException;
import com.chatapp.server.mapper.ConservationMapper;
import com.chatapp.server.mapper.MessageMapper;
import com.chatapp.server.mapper.UserMapper;
import com.chatapp.server.repository.MessageRepository;
import com.chatapp.server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder ;

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final ConservationMapper conservationMapper;
    private final UserMapper userMapper;

    public UserDTO register (RegistrationRequest request) {
        userRepository.findByEmailOrUserName(request.email(), request.userName()).ifPresent(user -> {
            throw new DuplicationResourceException("email or username was existed") ;
        });

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .userName(request.userName())
                .image(null)
                .build();

        userRepository.saveAndFlush(user);
        return userMapper.convertEntityToDto(user, null);

    }

    public UserDTO login (LoginRequest request) {
        User user = userRepository
                .findByEmail(request.email())
                .filter(entity -> passwordEncoder.matches(request.password(), entity.getPassword()))
                .orElseThrow(() -> new BadCredentialsException("email or password is not corrected"));
        List<Conservation> conservationUserList = user.getConservationUserList().stream().map(ConservationUser::getConservation).toList()  ;

        List<ConservationResponse> conservationResponses = conservationUserList.stream().map(conservation -> {
            Message message = messageRepository.findByConservationIdAndLatestTime(conservation.getId()).orElseThrow();
            MessageResponse messageResponse = messageMapper.convertEntityToResponse(message);
            return conservationMapper.convertEntityToResponse(conservation, messageResponse);
        }).toList();


        return userMapper.convertEntityToDto(user, conservationResponses);
    }
}
