package com.chatapp.server.repository;

import com.chatapp.server.entity.ConservationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConservationUserRepository extends JpaRepository<ConservationUser, Long> {

    @Query("""
            SELECT x
            FROM ConservationUser x
            JOIN FETCH x.user u
            JOIN FETCH x.conservation c
            WHERE u.id = :userId AND c.id = :conservationId
            """)
    Optional<ConservationUser> findByUserConservation(@Param("userId") Long user,
                                                      @Param("conservationId") Long conservationId);
}
