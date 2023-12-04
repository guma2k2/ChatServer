package com.chatapp.server.controller;

import com.chatapp.server.service.ConservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/conservation")
@AllArgsConstructor
@Slf4j
public class ConservationController {

    private final ConservationService conservationService;

    @GetMapping("/user/{userId}/create/group")
    public ResponseEntity<?> createGroupConservation (
            @PathVariable("userId") Long userId,
            @RequestParam("conservationName") String conservationName
    ) {
        conservationService.createGroupConservation(userId, conservationName);
        return ResponseEntity.ok().body("");
    }


    @GetMapping("/sender/{senderId}/send/receiver/{receiverId}")
    public ResponseEntity<?> createPrivateConservation (
            @PathVariable("senderId") Long senderId,
            @PathVariable("receiverId") Long receiverId,
            @RequestParam("messageText") String messageText
    ) {
        conservationService.createPrivateConservation(senderId, receiverId, messageText);
        return ResponseEntity.ok().body("");
    }


//    @GetMapping("/{conservationId}/leader/{leaderId}/add/user/{userId}")
//    public ResponseEntity<?> addUserToGroupConservation (
//            @PathVariable("leaderId") Long leaderId,
//            @PathVariable("conservationId") Long conservationId,
//            @PathVariable("userId") Long userId
//    ) {
//        conservationService.addUserToGroupConservation(leaderId, userId, conservationId );
//        return ResponseEntity.ok().body("");
//    }

}
