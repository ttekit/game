package com.example.clientsservice.models;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Column(nullable = false, columnDefinition = "DECIMAL(10) default 60")
    private Float timeLimit;
    @Column(columnDefinition = "VARCHAR(250) default 'default.png'")
    private String icon;
    @ManyToMany()
    @ToString.Exclude
    @JoinTable(name = "users_games",
            joinColumns = @JoinColumn(table = "games"),
            inverseJoinColumns = @JoinColumn(table = "users")
    )
    private List<User> users;

    public Game(Integer id, String name, Integer popularity, Float timeLimit, MultipartFile icon){
        this.Id = id;
        this.name = name;
        this.popularity = popularity;
        this.timeLimit = timeLimit;
        this.icon = icon.getName();
    }
}
