package com.example.clientsservice.services;

import com.example.clientsservice.models.User;
import com.example.clientsservice.models.UserGame;

import java.util.List;

public interface UserGameService {
    UserGame save(UserGame userGame);
    UserGame findById(Integer id);
    List<UserGame> findByUserId(Integer id);
    List<UserGame> findByGameId(Integer id);
    UserGame findByUserIdAndGameId(Integer userId, Integer gameId);
    List<User> findUsersByGameId(Integer gameId);
}
