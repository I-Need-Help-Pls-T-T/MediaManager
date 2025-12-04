package com.univer.mediamanager.repository.media.game;

import com.univer.mediamanager.model.media.Game;
import com.univer.mediamanager.model.enums.MultiplayerMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, GameCustomRepository {
    List<Game> findByTitleContainingIgnoreCase(String title);
    List<Game> findByRatingGreaterThanEqual(double rating);

    List<Game> findByPublisherContainingIgnoreCase(String publisher);
    List<Game> findByMultiplayerMode(MultiplayerMode mode);
    List<Game> findByCompletionTimeLessThanEqual(int time);
    List<Game> findByTimeInGameGreaterThanEqual(int time);

    @Query("SELECT g FROM Game g LEFT JOIN FETCH g.developer WHERE g.id = :id")
    Optional<Game> findByIdWithDevelopers(Long id);
}