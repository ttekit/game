package com.example.clientsservice.services.data.database;

import com.example.clientsservice.models.Game;
import com.example.clientsservice.repositories.GameRepository;
import com.example.clientsservice.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceDb implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game findById(Integer id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        gameRepository.deleteById(id);
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }
}
