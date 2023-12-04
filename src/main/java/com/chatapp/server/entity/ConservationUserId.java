package com.chatapp.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class ConservationUserId implements Serializable {

    @Column(name = "conservation_id")
    private Long conservationId;

    @Column(name = "user_id")
    private Long userId;

    public ConservationUserId(Long userId, Long conservationId) {
        this.userId = userId;
        this.conservationId = conservationId;
    }
}
