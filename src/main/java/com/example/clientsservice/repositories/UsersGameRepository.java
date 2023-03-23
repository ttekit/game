package com.example.clientsservice.repositories;

import com.example.clientsservice.models.User;
import com.example.clientsservice.models.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersGameRepository extends JpaRepository<UserGame, Integer> {
    @Query(value = "SELECT * FROM users_games, games\n" +
            "WHERE users_games.games_id = games.id\n" +
            "AND games.id = :id", nativeQuery = true)
    List<UserGame> findByGameId(@Param("id") Integer id);
    @Query(value = "SELECT * FROM users_games, users\n" +
            "WHERE users_games.games_id = users.id\n" +
            "AND users.id = :id\n", nativeQuery = true)
    List<UserGame> findByUserId(@Param("id") Integer id);
    @Query(value = "SELECT * FROM users_games, games, users\n" +
            "WHERE users_games.games_id = games.id\n" +
            "AND users_games.users_id = users.id\n" +
            "AND users.id = :userId\n" +
            "AND games.id = :gameId", nativeQuery = true)
    UserGame findByUserIdAndGameId(@Param("userId")Integer userId, @Param("gameId") Integer gameId);

    @Query(value = "select u from User u join u.games g where g.Id = :gameId")
    List<User> findUsersByGameId(@Param("gameId") Integer gameId);
}
