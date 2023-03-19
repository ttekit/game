package com.example.clientsservice.repositories;

import com.example.clientsservice.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
