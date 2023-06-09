package com.example.clientsservice.ui.controllers.rest;

import com.example.clientsservice.models.Game;
import com.example.clientsservice.models.User;
import com.example.clientsservice.models.enums.Status;
import com.example.clientsservice.services.GameService;
import com.example.clientsservice.services.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Objects;

import static com.example.clientsservice.devdep.Logger.printInFixColor;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AdminRestController {
    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;

    @PostMapping("/admin/rest/submitRegister")
    private String submitRegister(@RequestBody String str, BCryptPasswordEncoder encoder) {
        Gson gson = new Gson();
        User user = gson.fromJson(str, User.class);

        printInFixColor(userService.findByUsername(user.getUsername()));
        printInFixColor(userService.findByUsername(user.getUsername()) != null);

        if(userService.findByUsername(user.getUsername()) != null){
            return "This username is already in use";
        }
        else{
            user.setRole(User.Role.USER);
            user.setStatus(Status.ACTIVE);
            user.setPassword(encoder.encode(user.getPassword()));
            printInFixColor(user);
            try {
                user = userService.save(user);
            } catch (Exception e) {
                return "Some error happened, service is temporary unavailable";
            }

            if (user != null) {
                return "success";
            }
            return "Some error happened, service is temporary unavailable";
        }
    }

    @PostMapping("/admin/rest/updateUser")
    private String updateUser(@RequestBody String str, BCryptPasswordEncoder encoder) {
        Gson gson = new Gson();
        User userUpdate = gson.fromJson(str, User.class);
        User user = userService.findById(userUpdate.getId());
        if (Objects.equals(userUpdate.getPassword(), "")) {
            userUpdate.setPassword(user.getPassword());
        } else {
            userUpdate.setPassword(encoder.encode(userUpdate.getPassword()));
        }
        if (!user.equals(userUpdate)) {
            userService.save(userUpdate);
        }
        printInFixColor(userUpdate);
        if (userUpdate != null) {

            return "success";
        } else {
            return "error";
        }
    }


    @RequestMapping(path = "/admin/rest/addNewGame", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    private String addNewUser(@ModelAttribute("game") Game game, @RequestParam("file") MultipartFile file) {
        game.setPopularity(0);
        game.setIcon("default.png");
        game = gameService.save(game);
        game = this.write(file, game);
        printInFixColor(game);
        gameService.save(game);
        return "success";
    }

    public Game write(MultipartFile file, Game game) {
        String fileName = game.getId().toString() + ".png";
        Path path = Paths.get("src/main/resources/static/images/game/" + fileName);
        File toWriteFile = new File(path.toAbsolutePath().toString());
        game.setIcon(fileName);
        try (OutputStream os = new FileOutputStream(toWriteFile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return game;
    }
}
