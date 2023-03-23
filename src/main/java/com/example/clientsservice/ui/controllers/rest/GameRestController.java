package com.example.clientsservice.ui.controllers.rest;

import com.example.clientsservice.models.Game;
import com.example.clientsservice.models.User;
import com.example.clientsservice.models.UserGame;
import com.example.clientsservice.security.UserDetailImpl;
import com.example.clientsservice.services.GameService;
import com.example.clientsservice.services.UserGameService;
import com.example.clientsservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class GameRestController {
    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;

    @Autowired
    UserGameService ugService;

    @PostMapping("/game/rest/saveResult")
    private String saveResult(@RequestParam("gameId") String gameId, @RequestParam("result") String rating, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = -2;
        if (principal instanceof UserDetails) {
            userId = (((UserDetailImpl) principal).getId());
        }

        User user = userService.findById(userId);
        Game game = gameService.findById(Integer.parseInt(gameId));

        if (game.getUsers() == null) {
            game.setUsers(new ArrayList<>());
        }
        if(!game.getUsers().contains(user)){
            game.getUsers().add(user);
            game.setPopularity(game.getPopularity()+1);
            gameService.save(game);
        }

        UserGame userGame = ugService.findByUserIdAndGameId(userId, Integer.parseInt(gameId));

        if(userGame.getRating() < Integer.parseInt(rating)){
            userGame.setRating(Integer.parseInt(rating));
            ugService.save(userGame);
        }
        return "success";
    }
}
