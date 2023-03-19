package com.example.clientsservice.services;

import com.example.clientsservice.models.Game;

import java.util.List;

public interface GameService {
    Game save(Game game);
    Game findById(Integer id);
    void deleteById(Integer id);

    List<Game> findAll();
}
