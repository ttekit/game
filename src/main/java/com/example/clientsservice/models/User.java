package com.example.clientsservice.models;

import com.example.clientsservice.models.enums.Role;
import com.example.clientsservice.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

//
@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 25, nullable = false)
    private String email;
    @Column(length = 25, nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private Status status;
    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private Role role;

    @ManyToMany()
    @JoinTable(name = "users_games",
            joinColumns = @JoinColumn(table = "users"),
            inverseJoinColumns = @JoinColumn(table = "games")
    )
    private List<Game> games;
}
