package com.chatapp.server.repository;

import com.chatapp.server.entity.Conservation;
import com.chatapp.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConservationRepository extends JpaRepository<Conservation,Long> {
}
