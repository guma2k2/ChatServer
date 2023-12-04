package com.chatapp.server.repository;

import com.chatapp.server.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(
        """
        SELECT m
        FROM Message m
        LEFT JOIN FETCH m.conservation c
        LEFT JOIN FETCH m.user u
        WHERE m.conservation.id = :conservationId
        AND m.createdAt = (
            SELECT MAX(m2.createdAt)
            FROM Message m2
            WHERE m2.conservation.id = :conservationId
        )
        """
    )
    Optional<Message> findByConservationIdAndLatestTime(@Param("conservationId") Long conservationId);

}
