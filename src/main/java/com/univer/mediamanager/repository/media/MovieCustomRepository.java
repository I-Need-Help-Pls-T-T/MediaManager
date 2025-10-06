package com.univer.mediamanager.repository.media;

import com.univer.mediamanager.model.media.Movie;
import java.util.List;

public interface MovieCustomRepository {

    List<Movie> findMoviesWithCrew();

    List<Movie> findByCrewMemberName(String name);

    List<Movie> findHighBudgetMovies(double minBudget);
}