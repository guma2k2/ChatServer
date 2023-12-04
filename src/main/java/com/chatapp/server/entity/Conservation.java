package com.chatapp.server.entity;

import com.chatapp.server.enums.ConservationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conservation")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    @Enumerated(EnumType.STRING)
    private ConservationType type;

    private LocalDateTime createdTime ;

    @Builder.Default
    @OneToMany(mappedBy = "conservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();


    public void addMessage (Message message) {
        messages.add(message);
        message.setConservation(this);
    }

    public void removeMessage (Message message) {
        messages.remove(message);
        message.setConservation(null);
    }

    @Transient
    public String getImagePath () {
        switch (type) {
            case GROUP:
                if (image != null) {
                    return "sadfa";
                }
                return "";
            default:
                return image;
        }
    }
}
