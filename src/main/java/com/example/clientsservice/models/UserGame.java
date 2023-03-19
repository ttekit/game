
package com.example.clientsservice.models;

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
@Table(name = "users_games")
public class UserGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToMany(mappedBy = "users")
    @ToString.Exclude
    private List<Game> games;
    @ManyToMany(mappedBy = "games")
    @ToString.Exclude
    private List<User> users;
    @Column(nullable = false, columnDefinition = "INT(10) default 0")
    private Integer rating;

}

