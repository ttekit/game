package com.example.clientsservice.ui.controllers;

import com.example.clientsservice.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
    @Autowired
    GameService gameService;
    @GetMapping("/game?id")
    public String game(Model model, Integer id){
        model.addAttribute("game", gameService.findById(id));
        return "/game/gamePattern";
    }
}
