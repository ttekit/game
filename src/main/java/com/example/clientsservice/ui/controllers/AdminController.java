package com.example.clientsservice.ui.controllers;

import com.example.clientsservice.models.User;
import com.example.clientsservice.models.enums.Status;
import com.example.clientsservice.services.GameService;
import com.example.clientsservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String main(){
        return "/admin/main";
    }

    @GetMapping("/admin/editGames")
    public String editGames(Model model){
        model.addAttribute("games", gameService.findAll());
        return "/admin/editGames";
    }
    @GetMapping("/admin/editUsers")
    public String editUsers(Model model){
        model.addAttribute("users", userService.findAll());
        model.addAttribute("statuses", Status.getNames());
        model.addAttribute("roles", User.Role.getNames());
        return "/admin/editUsers";
    }
    @GetMapping("/admin/addNewGame")
    public String addNewGame(){
        return "/admin/addNewGame";
    }

}
