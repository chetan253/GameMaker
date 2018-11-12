package com.gamemaker;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<ScoreDatabase, Integer> {
//    public List<ScoreDatabase> findAllOrOrderByScore();

    @Query("SELECT new ScoreDatabase(userId, userName, gameId, score) " +
            "FROM ScoreDatabase ORDER BY gameId, score DESC")
    List<ScoreDatabase> groupByGameIdAndScore();
}

