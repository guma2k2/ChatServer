package com.chatapp.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 30)
    private String userName;

    @Column(nullable = false,unique = true,length = 18)
    private String email;

    private String password;

    @Builder.Default
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConservationUser> conservationUserList = new ArrayList<>();

    private String image;

    public String getImagePath () {
        if(image == null) {
            return "";
        }
        return "https://img.freepik.com/premium-vector/account-icon-user-icon-vector-graphics_292645-552.jpg";
    }
}
