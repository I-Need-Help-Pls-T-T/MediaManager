package com.univer.mediamanager.repository.media.game;

import com.univer.mediamanager.model.media.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameCustomRepositoryImpl implements GameCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Game> findGamesWithDevelopers() {
        return entityManager.createQuery(
                        "SELECT DISTINCT g FROM Game g LEFT JOIN FETCH g.developer", Game.class)
                .getResultList();
    }

    @Override
    public List<Game> findByDeveloperName(String name) {
        return entityManager.createQuery(
                        "SELECT DISTINCT g FROM Game g " +
                                "LEFT JOIN g.developer d " +
                                "WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))", Game.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Game> findLongestGames(int minTimeInGame) {
        return entityManager.createQuery(
                        "SELECT g FROM Game g WHERE g.timeInGame >= :minTimeInGame ORDER BY g.timeInGame DESC", Game.class)
                .setParameter("minTimeInGame", minTimeInGame)
                .getResultList();
    }
}