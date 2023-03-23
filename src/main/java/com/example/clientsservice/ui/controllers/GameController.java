package com.example.clientsservice.ui.controllers;

import com.example.clientsservice.models.User;
import com.example.clientsservice.security.UserDetailImpl;
import com.example.clientsservice.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.clientsservice.devdep.Logger.printInFixColor;

@Controller
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping("/games/game")
    public String game(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("game", gameService.findById(id));
        return "game/gamePattern";
    }

    @GetMapping("/games")
    public String main(Model model) {
        model.addAttribute("games", gameService.findAll());
        return "game/main";
    }
}
