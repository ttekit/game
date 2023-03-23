package com.example.clientsservice.models;

import com.example.clientsservice.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
//
@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public enum Role {
        USER,
        ADMIN;

        public static String[] getNames() {
            return Arrays.stream(Role.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
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

    @ManyToMany(mappedBy = "users")
    @ToString.Exclude
    private List<Game> games;

}
