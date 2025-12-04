package com.univer.mediamanager.repository.media.game;

import com.univer.mediamanager.model.media.Game;
import java.util.List;

public interface GameCustomRepository {

    List<Game> findGamesWithDevelopers();

    List<Game> findByDeveloperName(String name);

    List<Game> findLongestGames(int minTimeInGame);
}