package com.chatapp.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "conservation_user")
@Builder
public class ConservationUser {

    @EmbeddedId
    private ConservationUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("conservationId")
    @JoinColumn(name = "conservation_id")
    private Conservation conservation;

    private LocalDateTime joinTime;

    private LocalDateTime leftTime;


    @Builder.Default
    private boolean isLeader = false;

    public ConservationUser(User user, Conservation conservation) {
        this.user = user;
        this.conservation = conservation ;
        this.id = new ConservationUserId(user.getId(), conservation.getId());
        this.joinTime = LocalDateTime.now();
    }
}
