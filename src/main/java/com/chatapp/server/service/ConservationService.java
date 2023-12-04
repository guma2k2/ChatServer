package com.chatapp.server.service;

import com.chatapp.server.entity.Conservation;
import com.chatapp.server.entity.ConservationUser;
import com.chatapp.server.entity.Message;
import com.chatapp.server.entity.User;
import com.chatapp.server.enums.ConservationType;
import com.chatapp.server.repository.ConservationRepository;
import com.chatapp.server.repository.ConservationUserRepository;
import com.chatapp.server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ConservationService {


    private final ConservationRepository conservationRepository;
    private final UserRepository userRepository;
    private final ConservationUserRepository conservationUserRepository;

    public void createGroupConservation (Long userId, String conservationName) {
        User user = userRepository.findById(userId).orElseThrow();

        Conservation conservation = Conservation
                .builder()
                .name(conservationName)
                .type(ConservationType.GROUP)
                .createdTime(LocalDateTime.now())
                .build();

        conservationRepository.saveAndFlush(conservation);

        ConservationUser conservationUser = ConservationUser.builder()
                .conservation(conservation)
                .user(user)
                .isLeader(true)
                .joinTime(LocalDateTime.now())
                .build();

        conservationUserRepository.save(conservationUser) ;
    }


    public void createPrivateConservation (Long senderId, Long receiverId, String messageText) {
        User sender = userRepository.findById(senderId).orElseThrow();
        User receiver = userRepository.findById(receiverId).orElseThrow();

        Conservation conservation = Conservation
                .builder()
                .name(receiver.getUserName())
                .type(ConservationType.PRIVATE)
                .image(receiver.getImagePath())
                .createdTime(LocalDateTime.now())
                .build();
        conservationRepository.saveAndFlush(conservation);

        List<ConservationUser> conservationUserList = List.of(
                ConservationUser.builder()
                        .conservation(conservation)
                        .user(sender)
                        .build(),
                ConservationUser.builder()
                        .conservation(conservation)
                        .user(receiver)
                        .build()
        );
        conservationUserRepository.saveAll(conservationUserList);

        Message message = Message.builder()
                .conservation(conservation)
                .createdAt(LocalDateTime.now())
                .messageText(messageText)
                .user(sender)
                .build();
        conservation.addMessage(message);
        conservationRepository.save(conservation);
    }

    public void addUserToGroupConservation (Long leaderId, Long userId, Long conservationId) {
        conservationUserRepository.findByUserConservation(leaderId, conservationId).ifPresent(conservationUser -> {
            if (!conservationUser.isLeader()) {
                throw new RuntimeException("you dont have permission");
            }
        });
        User addingUser = userRepository.findById(userId).orElseThrow();
        Conservation conservation = conservationRepository.findById(conservationId).orElseThrow();
        ConservationUser conservationUser = ConservationUser.builder()
                .user(addingUser)
                .conservation(conservation)
                .joinTime(LocalDateTime.now())
                .build();

        conservationUserRepository.save(conservationUser);
    }

    public void uploadConservationImage() {}

}
