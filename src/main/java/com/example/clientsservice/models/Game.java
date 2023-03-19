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
@Table(name = "games")
public class Game {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(length = 25, nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "DECIMAL(10) default 0")
    private Integer popularity;

    @ManyToMany(mappedBy = "games")
    private List<User> users;
}
