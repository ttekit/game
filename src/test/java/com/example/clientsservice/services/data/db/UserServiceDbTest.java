package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.User;
import com.example.clientsservice.models.enums.Status;
import com.example.clientsservice.services.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.clientsservice.devdep.Logger.printInFixColor;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceDbTest {
    @Autowired
    UserService userService;

    static User userExpected = new User(1, "test", "test", "test", Status.ACTIVE, User.Role.USER, null);
    static User userActual;




    @Test
    @Order(1)
    void save() {
        userActual = userService.save(userExpected);
        printInFixColor(userActual);
        assertNotNull(userActual);
        userExpected = userActual;
    }

    @Test
    @Order(2)
    void findById(){
        userActual = userService.findById(userExpected.getId());
        printInFixColor(userActual);
    }

    @Test
    @Order(3)
    void findAll(){
        List<User> users = userService.findAll();
        printInFixColor(users);
    }

    @Test
    @Order(4)
    void deleteById(){
        userService.deleteById(userActual.getId());
    }

}
