package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Game;
import com.example.clientsservice.models.User;
import com.example.clientsservice.models.UserGame;

import com.example.clientsservice.models.enums.Status;
import com.example.clientsservice.services.GameService;
import com.example.clientsservice.services.UserGameService;
import com.example.clientsservice.services.UserService;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.example.clientsservice.devdep.Logger.printInFixColor;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameUserServiceDbTest {
    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;
    @Autowired
    UserGameService userGameService;


    static User user = new User(1, "test", "test", "test", Status.ACTIVE, User.Role.USER, null);
    static Game game = new Game(1, "testing", 0, null);

    @Test
    @Order(1)
    void save() {
        user = userService.save(user);
        game = gameService.save(game);

        if (game.getUsers() == null) {
            game.setUsers(new ArrayList<>());
        }

        game.getUsers().add(user);
        game = gameService.save(game);

        printInFixColor(game.getUsers());
    }

    @Test
    @Order(2)
    void findByUserId() {
        List<UserGame> userGameList = userGameService.findByUserId(user.getId());
        printInFixColor(userGameList);
    }
    @Test
    @Order(3)
    void findByGameId() {
        List<UserGame> userGameList = userGameService.findByGameId(game.getId());
        printInFixColor(userGameList);
    }
    @Test
    @Order(4)
    void findByUserIdAndGameId() {
        UserGame userGame = userGameService.findByUserIdAndGameId(user.getId(), game.getId());
        printInFixColor(userGame);
    }

}
