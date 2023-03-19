package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Game;
import com.example.clientsservice.services.GameService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.clientsservice.devdep.Logger.printInFixColor;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameServiceDbTest {
    @Autowired
    GameService gameService;

    static Game gameExpected = new Game(1, "testing", 0, null);
    static Game gameActual;

    @Test
    @Order(1)
    void save(){
        gameActual = gameService.save(gameExpected);
        printInFixColor(gameActual);
        assertNotNull(gameActual);
        gameExpected = gameActual;
    }
    @Test
    @Order(2)
    void findById(){
        gameActual = gameService.findById(gameExpected.getId());
        printInFixColor(gameActual);
    }

    @Test
    @Order(3)
    void findAll(){
        List<Game> games = gameService.findAll();
        printInFixColor(games);
    }

    @Test
    @Order(4)
    void deleteById(){
        gameService.deleteById(gameActual.getId());
    }
}
