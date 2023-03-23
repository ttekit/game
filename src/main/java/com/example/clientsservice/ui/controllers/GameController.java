package com.example.clientsservice.ui.controllers;


import com.example.clientsservice.models.User;
import com.example.clientsservice.models.UserGame;
import com.example.clientsservice.services.GameService;
import com.example.clientsservice.services.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.example.clientsservice.devdep.Logger.printInFixColor;


@Controller
public class GameController {
    @Autowired
    GameService gameService;

    @Autowired
    UserGameService ugService;
    @GetMapping("/games")
    public String main(Model model) {
        model.addAttribute("games", gameService.findAll());
        return "game/main";
    }
    @GetMapping("/games/game")
    public String game(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("game", gameService.findById(id));
        return "game/gamePattern";
    }
    @GetMapping("/games/gameRecords")
    public String gameRecords(Model model, @RequestParam("id") Integer id) {
        Map<User, Integer> userRecords = new HashMap<>();
        List<User> users = ugService.findUsersByGameId(id);
        List<UserGame> records = ugService.findByGameId(id);

        Iterator<User> userIterator = users.iterator();
        for (int i = 0; i < records.size() && userIterator.hasNext(); i++) {
            User user = userIterator.next();
            userRecords.put(user, records.get(i).getRating());
        }
        printInFixColor(userRecords);
        model.addAttribute("users", userRecords);
        return "game/topTable";
    }
}
