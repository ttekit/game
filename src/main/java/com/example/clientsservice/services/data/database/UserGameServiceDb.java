package com.example.clientsservice.services.data.database;

import com.example.clientsservice.models.UserGame;
import com.example.clientsservice.repositories.UsersGameRepository;
import com.example.clientsservice.services.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGameServiceDb implements UserGameService {
    @Autowired
    private UsersGameRepository usersGameRepository;

    @Override
    public UserGame save(UserGame userGame) {
        return usersGameRepository.save(userGame);
    }

    @Override
    public UserGame findById(Integer id) {
        return usersGameRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserGame> findByUserId(Integer id) {
        return usersGameRepository.findByUserId(id);
    }

    @Override
    public List<UserGame> findByGameId(Integer id) {
        return usersGameRepository.findByGameId(id);
    }

    @Override
    public UserGame findByUserIdAndGameId(Integer userId, Integer gameId) {
        return usersGameRepository.findByUserIdAndGameId(userId, gameId);
    }
}
