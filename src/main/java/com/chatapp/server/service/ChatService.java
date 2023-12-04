package com.chatapp.server.service;

import com.chatapp.server.ChatGrpc;
import com.chatapp.server.Message;
import com.chatapp.server.dto.MessageDTO;
import com.chatapp.server.enums.MessageType;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.util.HashMap;

@GRpcService
@Slf4j
public class ChatService extends ChatGrpc.ChatImplBase {


    private HashMap<String, StreamObserver<Message>> connectedConservations = new HashMap<>();

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    @Override
    public void subscribe(Message request, StreamObserver<Message> responseObserver) {
        log.info(String.valueOf(request));
        String conservationId= request.getConservationId();
        Message message = Message.newBuilder()
                .setMessage(request.getMessage())
                .setSender(request.getSender())
                .setConservationId(conservationId)
                .setType(String.valueOf(MessageType.JOIN))
                .build();


        if (connectedConservations.containsKey(conservationId)) {
            return;
        } else {
            var messageDTO = new MessageDTO(request.getSender(), request.getMessage(), MessageType.JOIN);
            String destination = "/topic/conservation/" + conservationId;
            messagingTemplate.convertAndSend(destination, messageDTO);
            responseObserver.onNext(message);
            connectedConservations.put(conservationId, responseObserver);
        }
    }

    @Override
    public void sendMessage(Message request, StreamObserver<Message> responseObserver) {
        Message message = Message.newBuilder()
                .setMessage(request.getMessage())
                .setSender(request.getSender())
                .setConservationId(request.getConservationId())
                .setType(String.valueOf(MessageType.SENDING))
                .build();
        responseObserver.onNext(message);
        connectedConservations.forEach((conservation, subscribedObserver) -> {
           if (conservation.equals(message.getConservationId())) {
               var messageDTO = new MessageDTO(request.getSender(), request.getMessage(), MessageType.SENDING);
               String destination = "/topic/conservation/" + request.getConservationId();
               messagingTemplate.convertAndSend(destination, messageDTO);
               try {
                   subscribedObserver.onNext(message);
               } catch (Exception e) {
               }
           }
        });
        responseObserver.onCompleted();
    }
}
