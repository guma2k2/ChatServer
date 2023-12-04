package com.chatapp.server.repository;

import com.chatapp.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


    @Query("""
            SELECT u
            FROM User u
            LEFT JOIN FETCH u.conservationUserList c
            WHERE u.email = :email
            """)
    Optional<User> findByEmailReturnConservations(String email);

    @Query("""
            SELECT u
            FROM User u
            WHERE u.email = :email OR u.userName = :userName
            """)
    Optional<User> findByEmailOrUserName(@Param("email") String email, @Param("userName") String userName);
}
