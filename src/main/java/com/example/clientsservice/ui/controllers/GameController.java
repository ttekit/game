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

import java.util.*;

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
        List<Integer> recordsInt = new ArrayList<>();

        for (int i = 0; i < records.size(); i++) {
            recordsInt.add(records.get(i).getRating());
        }

        recordsInt.sort(Comparator.reverseOrder());
        Iterator<User> userIterator = users.iterator();
        for (int i = 0; i < records.size() && userIterator.hasNext(); i++) {
            User user = userIterator.next();
            userRecords.put(user, recordsInt.get(i));
        }
        LinkedHashMap<User, Integer> sortedUsersRecordsMap = new LinkedHashMap<>();
        for (int num : recordsInt) {
            for (Map.Entry<User, Integer> entry : userRecords.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedUsersRecordsMap.put(entry.getKey(), num);
                }
            }
        }

        printInFixColor(sortedUsersRecordsMap);
        model.addAttribute("users", sortedUsersRecordsMap);
        return "game/topTable";
    }
}
